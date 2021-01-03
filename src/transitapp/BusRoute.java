package transitapp;

import java.util.ArrayList;

/**
 * A BusRoute class that extends the Abstract class Route. It consists 
 * of a name, all its stops and its fare. It can get a Fee for a trip from one
 * stop to another.
 */
public class BusRoute extends Route {
	
	/**
	 * Constructs a BusRoute object
	 * 
	 * @param name		the name of this BusRoute
	 * @param stops		the list of stops in this BusRoute
	 */
	public BusRoute(String name, ArrayList<String> stops) {
		super(name, stops);
		this.setFare(2.0);
	}

	@Override
	public double getFee(String start, String end) {
		return this.getFare();
	}
	
	@Override
	public String getTransportation() {return "bus";}
	
	@Override
	public String toString() {
		return "Bus " + this.getName() + ", Stops: " + this.getStops(); 
	}

}
