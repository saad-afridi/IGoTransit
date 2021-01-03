package Admin;
import transitapp.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * An Admin represents an admin user on the transit app. They will be able to view a plethora of statistcs about
 * the current transity system, such as the total trips in one day. They also will have an attribute which contains
 * every single card holder registered to their system.
 */
public class Admin extends Monitor {
	
	public Admin() {
        HashMap<Card, ArrayList<Tap>> taps = Main.LoadTap(this);
        HashMap<Card, ArrayList<Trip>> trips = loadTrips(taps, this.transitSystem, this);	
	}

	/**
	 * Helper function for averageTime() and averageDistance(), has the general format of adding values to an
	 * accumulator and dividing by the number of elements added (i.e finding the average)
	 * 
	 * @param  format    precondition will be that format == "time" or format == "distance", which will decide the operation
	 * @return average
	 */
    public int helpAverages(String format) {
        float acc = 0; int count = 0;

        for (CardHolder cardholder : this.getCardHolders()) {
            for (Trip trip : cardholder.getTrips()) {
                if (format.equals("time")) {
                    acc += trip.getTotalTripTime();
                }
                else {
                    acc += trip.getTripLength();
                }
                count += 1;
            }
        }

        try {
            return (int) (acc / count);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Return the average time of all the trips ever taken on the transit system
     * 
     * @return average in minutes
     */
    public int averageTime() {
        return helpAverages("time");
    }

    /**
     * Return the average distance of all the trips ever taken on the transit system
     * 
     * @return average in kilometers
     */
    public float averageDistance() {
        return helpAverages("distance");
    }

    /**
     * Return the number of trips taken on the most recent day
     * 
     * @return number of trips
     */
    public int numTripsLastDay() {
        return todaysTrips(lastDate()).size();
    }

    /**
     * Return an ArrayList<Trip> containing all trips ever taken on the transit system
     * 
     * @return ArrayList<Trip> of trips
     */
    public ArrayList<Trip> allTrips() {
        ArrayList<Trip> trips = new ArrayList<>();

        for (CardHolder cardholder : this.getCardHolders()) {
            trips.addAll(cardholder.getTrips());
        }
        
        Collections.sort(trips, new Trip.SortTrips());
        return trips;
    }

    /**
     * Return the date of the most recent trip taken in the transit system
     * 
     * @return TransitTime date
     */
    public TransitTime lastDate() {
        TransitTime currentDate = new TransitTime("0001:01:01:00:00");
        
        if (this.allTrips() != null) {
        	return this.allTrips().get(this.allTrips().size() - 1).getEndDate();
        }
        
        return currentDate;
    }
  
    /**
     * Return the number of rides taken on a specific transportation medium
     * 
     * @param transportation			value will be "bus" or "subway" on the default system
     * @return number of rides on said transportation medium
     */
	public int getTotalRides(String transportation) {
		int busRides = 0;
		
		for (Trip trip : allTrips()) {
			for (Tap tap : trip.getTaps()) {
				if (tap.getTransportation().equals(transportation) && tap.getAction().equals("OUT")) {
					busRides++;
				}
			}
		}
		
		return busRides;
		}

    /**
     * Return true iff t1 == t2 in all aspects except hour and minute
     * 
     * @param t1			LocalDateTime
     * @param t2 			LocalDateTime
     * @return true or false
     */
    public boolean compareDate(LocalDateTime t1, LocalDateTime t2) {
        return t1.getYear() == (t2.getYear()) && 
        			t1.getMonthValue() == (t2.getMonthValue()) && 
        					t1.getDayOfMonth() == (t2.getDayOfMonth());
    }

    /**
     * Return the profit made on the most recent day of operations
     * 
     * @return profit
     */
    public float lastDayProfits() {
        float profit = (float) 0;
        TransitTime date = lastDate();

        for (Trip trip : allTrips()) {
            if (compareDate(date.getTime(), trip.getEndDate().getTime())) {
                profit += trip.getTripFee();
            }
        }
        
        return profit; 
    }

    /**
     * Return an ArrayList<Trip> of all trips taken on the most recent day of operation
     * 
     * @param date
     * @return ArrayList<Trip>
     */
    public ArrayList<Trip> todaysTrips(TransitTime date) {
        ArrayList<Trip> trips = new ArrayList<>();

        for (Trip trip : allTrips()) {
            if (compareDate(trip.getEndDate().getTime(), date.getTime())) {
                trips.add(trip);
            }
        }
        
        return trips;
    }

    /**
     * Return an ArrayList<Trip> of all valid trips using information from the input text files"
     * 
     * @param dict			A HashMap with cards associated with their respective taps, these taps may be invalid
     * @param t				The transit system that will be operated on
     * @param a				An admin user, the trips will be stored in the admin users' card holders
     *
     * @return A HashMap with cards associated with their respective valid trips
     */
    public static HashMap<Card, ArrayList<Trip>> loadTrips(HashMap<Card, ArrayList<Tap>> dict, 
    		TransitSystem t, Admin a) {

        HashMap<Card, ArrayList<Trip>> tripLog = new HashMap<>();
        for (Card card : dict.keySet()) {
        	if (card!= null) {
            TapQueue queue = new TapQueue();
            ArrayList<Trip> trips = new ArrayList<Trip>();
            for (Tap tap : dict.get(card)) {
                ArrayList<Tap> taps = queue.queue(tap);
                if (!taps.isEmpty()) {
                	Trip trip = new Trip(card, taps, t);
                    trips.add(trip);
                    card.charge(trip.getTripFee());
                    a.getCardHolder(card.getHolderName()).addTrip(trip);
                }
            }
            ArrayList<Tap> taps = queue.dequeueAll();
            if (!taps.isEmpty()) {
                Trip trip = new Trip(card, taps, t);
                trips.add(trip);
                card.charge(trip.getTripFee());
                a.getCardHolder(card.getHolderName()).addTrip(trip);
            	}
            tripLog.put(card, trips);	
        	}
        
        }
        return tripLog;
    }
    
    /**
     * Return an array of loaded trips but without charging the card or adding to card hold
     * @param card
     * @param taps
     * @param admin
     * @param tranSys
     * @return
     */
    
    public static ArrayList<Trip> getTrips(Card card, ArrayList<Tap> taps, Admin admin, TransitSystem tranSys) {
    	TapQueue queue = new TapQueue();
    	ArrayList<Trip> trips = new ArrayList<>();
    	
    	for (Tap tap : taps) {
            ArrayList<Tap> temp = queue.queue(tap);
            if (!temp.isEmpty()) {
            	Trip trip = new Trip(card, temp, tranSys);
                trips.add(trip);
            }
    	}
    	
    	ArrayList<Tap> temp = queue.dequeueAll();
    	if (!temp.isEmpty()) {
    		Trip trip = new Trip(card, temp, tranSys);
            trips.add(trip);
    	}
    	
    	return trips;
    }
}
     
