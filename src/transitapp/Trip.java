package transitapp;

import LoadingData.Reader;
import Admin.Admin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A trip consists of a card, list of taps, length of trip in time and minutes, and the cost of the trip. A trip will
 * be able to change some of it's many characteristics and be able to compare itself with other trips
 */
public class Trip {

    private Card card;
    private int tripTime;
    private int tripLength = 0;
    private double tripFee = 0.0;
    private ArrayList<Tap> taps = new ArrayList<Tap>();
    private static double FEE_CAP = 6.00;
    
    public static Admin admin;
    public static TransitSystem tSystem; 

    /**
     * Contructs a trip with the given card, list of taps, and the transit system which it was taken on
     * 
     * @param card			Card used for the trip
     * @param taps			List of taps placed throughout the tirp
     * @param transitSystem			Transit system that the trip was taken on
     */
    public Trip(Card card, ArrayList<Tap> taps, TransitSystem transitSystem) {
        this.card = card;
        this.taps = taps;
        this.tSystem = transitSystem;
        this.tripFee = setTripFee();
        this.tripLength = setTripLength();
        this.tripTime = TransitTime.getDifference(taps.get(0).getTime(), taps.get(taps.size() - 1).getTime());
    }
    

	/**
     * Return the total time this trip takes in minutes
     *
     * @return Total time this trip takes
     */
    public int getTotalTripTime() {
        return this.tripTime;
    }
    
    /** 
     * Return the taps that make up this trip
     * 
     * @return ArrayList<Tap>
     */
    public ArrayList<Tap> getTaps() {
    	return this.taps;
    }

    /**
     * Return the TransitTime of when the trip ends (i.e the last tap)
     * 
     * @return TransitTime 
     */
    public TransitTime getEndDate() {
    	return taps.get(taps.size() - 1).getTime();
    }
    
    /**
     * Returns the card number that has this trip.
     *
     * @return card number
     */
    public Card getTripCard() {
        return this.card;
    }
    
    /**
     * Return the length of trip in kilometers
     * 
     * @return trip length
     */
    public int getTripLength() {
    	return this.tripLength;
    }
    
    /**
     * Return the cost of this trip in dollars
     * 
     * @return cost of trip
     */
    public double getTripFee() {
    	if (this.tripFee >= FEE_CAP) {
    		return FEE_CAP;
    	}
    	return this.tripFee;
    }
    
    /**
     * Set the length of the trip based on the taps that make up the trip
     * 
     * @return trip length
     */
    private int setTripLength() {
    	int count = 0;
        for (int i = 0; i < this.taps.size() - 1; i += 2) {
            Route route = tSystem.findRoute(this.taps.get(i).getRoutename());
            if (route != null) {
                count += route.getDistance(taps.get(i).getLocation(),
                        taps.get(i + 1).getLocation());
            }
        }
        return count;
    }

    /**
     * Set the cost of the trip based on the taps that make up the trip
     * 
     * @return cost of trip
     */
    private double setTripFee() {
        double count = 0;
        for (int i = 0; i < this.taps.size() - 1; i += 2) {
            Route route = tSystem.findRoute(this.taps.get(i).getRoutename());
            if (route!= null) {
                count += route.getFee(taps.get(i).getLocation(),
                        taps.get(i + 1).getLocation());
            }
        }
        return count;
    }
    
    /**
     * Implementation of the compare interface so that sort methods can work on the Trip data type
     */
    public static class SortTrips implements Comparator<Trip> {

    	@Override
    	public int compare(Trip x, Trip y) {
    		return x.getEndDate().getTime().compareTo(y.getEndDate().getTime());
    	}
    	
    }
    
    @Override
    public String toString() {
    	StringBuilder taps = new StringBuilder();
    	
    	for (Tap tap: this.taps) {
    		taps.append(tap);
    		taps.append("\n");
    	}
    
    	return "\n===== Trip =====\n" +
    			"Card: " + getTripCard().getName() + '\n' + taps + '\n' + 
    			"Fee: " + this.getTripFee() + "$" + '\n' + 
    			"================\n";
    }
        
}