package transitapp;

import java.util.ArrayList;

/**
 * A SubwayRoute class that extends the Abstract class Route. It consists 
 * of a name, all its stops and its fare. It can get a Fee for a trip from one
 * stop to another
 */
public class SubwayRoute extends Route {
	
	/**
	 * Constructs a SubwayRoute object
	 * 
	 * @param name		the name of this SubwayRoute
	 * @param stops		the list of stops in this SubwayRoute
	 */
	public SubwayRoute(String name, ArrayList<String> stops) {
		super(name, stops);
		this.setFare(0.5);
	}
	
	@Override
	public double getFee(String start, String end) {
		int distance = this.getDistance(start, end);
		
		if (distance == -1) {return 0;}
		
		return distance * this.getFare();
	}

	@Override
	public String toString() {
		return "Subway " + this.getName() + ", Stops: " + this.getStops(); 
	}
	
	@Override
	public String getTransportation() {return "subway";}
	
}
