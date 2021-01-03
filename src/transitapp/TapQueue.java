package transitapp;

import Admin.Admin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Special Queue class based on Tap objects which is used for filtering invalid trips. Tap queue contains a list
 * of items, pair of start / end times, and a last tap attribute
 */
public class TapQueue {

    private ArrayList<Tap> items;
    private Tap lastTap;
    private TransitTime[] interval = {new TransitTime(), new TransitTime()}; //[0] = start, [1] = end

    
    /**
     * Constructs an empty TapQueue with no last tap
     */
    public TapQueue() {
        this.items = new ArrayList<Tap>();
        this.lastTap = null;
        
    }
    
    /**
     * Constructs a TapQueue that contains the given items queue and the last tap being the last item
     * @param items			List of taps to be in the initial queue
     */
    public TapQueue(ArrayList<Tap> items) {
        this.items = items;
        this.lastTap = this.items.get(this.items.size() - 1);
    }
    
    /**
     * Return the time of the first and last tap in TapQueue
     * 
     * @return			User readable string concatenation 
     */
    public String getInterval() {
    	return interval[0] + ", " + interval[1];
    }
    
    /**
     * Queue a new tap into the TapQueue, if a queued tap makes the trip invalid, dequeue and return all items and 
     * then queue the new tap in
     * 
     * @param tap
     * @return ArrayList<Tap>		All taps that are dequeued if an invalid tap is queued
     */
    public ArrayList<Tap> queue(Tap tap) {
    	interval[1] = tap.getTime();
        ArrayList<Tap> trip = new ArrayList<>();
        boolean twoHoursPassed = TransitTime.hasHourPassed(interval[0], 
        							interval[1], 2);
        boolean dayPassed = TransitTime.hasHourPassed(interval[0], 
				interval[1], 24);

        // if no Taps to compare
        if (this.lastTap == null) {
            this.items.add(tap);
            this.lastTap = tap;
            this.updateTime();
        	return trip;
        }
        // If the last action was the same as the current ie. IN -> IN or OUT -> OUT,
        // Return all the items excluding the one added
        if (this.lastTap.getAction().equals(tap.getAction())) {
            trip.addAll(this.dequeueAll());
        }
        
        // else if we have OUT -> IN
        else if(tap.getAction().equals("IN")) {
        	
        	// IF OUT -> IN at different locations (disjoint)
        	if (!tap.getLocation().equals(this.lastTap.getLocation())) {
        		trip.addAll(this.dequeueAll());
        	}
        	
        	// IF OUT -> IN after 2 hours passed (disjoint)
        	else if(twoHoursPassed) {
            	trip.addAll(this.dequeueAll());
            }
     
        }
        // IN -> OUT, if we somehow go IN Bus and OUT Subway or vice versa
        else if(!tap.getTransportation().equals(this.lastTap.getTransportation())) {
    		trip.addAll(this.dequeueAll());
    	}
        
        // IN -> OUT, if we somehow go IN Bus A and OUT Bus B or vice versa
        else if(!tap.getRoutename().equals(this.lastTap.getRoutename())) {
    		trip.addAll(this.dequeueAll());
    	}
        
        // IN -> OUT, if a trip is longer than a day, INVALID (probably
        // result from a power shutout)
        else if(dayPassed) {
        	trip.addAll(this.dequeueAll());
        }
        
        //Add to queue, change last Tap and update time
        this.items.add(tap);
        this.lastTap = tap;
        this.updateTime();
        return trip;
    }
    
    /**
     * Update the start and end time in the interval attribute
     */
    public void updateTime() {
    	if (this.items != null) {
    		interval[0] = this.items.get(0).getTime();
    		interval[1] = this.items.get(this.items.size() - 1).getTime();
    	}
    }

    /**
     * Dequeue and return the tap and the end of the queue
     * 
     * @return tap at the back of the queue
     */
    public Tap dequeue() {
        return this.items.remove(0);
    }

    /**
     * Dequeue and return all element in the queue
     * 
     * @return ArrayList<Tap> all items in the queue
     */
    public ArrayList<Tap> dequeueAll() {
        ArrayList<Tap> taps = new ArrayList<Tap>();
        while (!this.items.isEmpty()) {
            taps.add(dequeue());
        }
        return taps;
    }
    
    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	
    	for (Tap item: this.items) {
    		s.append(item);
    		s.append("\n");
    	}
    	
    	return s.toString();
    }
}