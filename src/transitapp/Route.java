package transitapp;
import java.util.ArrayList;

/**
 * An abstract Route class. Routes should consist of a name (unique), all their stops and
 * fare. All Route objects should be able to calculate between two stops and return
 * a fee for a ride.
 */
public abstract class Route {
	
	private String name;
	private ArrayList<String> stops;
	private double fare;
	
	/**
	 * Initializer for it's children to inherit
	 * 
	 * @param name		this route's name
	 * @param stops		all the stops in this route
	 */
	public Route(String name, ArrayList<String> stops) {
		this.name = name;
		this.stops = stops;
	}

	/**
	 * Returns the name of the Route
	 * 
	 * @return the name of the Route
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the stops consisted in this Route
	 * 
	 * @return all the stops in the Route
	 */
	public ArrayList<String> getStops() {
		return this.stops;
	}
	
	/**
	 * Returns the fare of this 
	 * 
	 * @return the fare of the Route
	 */
	public double getFare() {
		return this.fare;
	}
	
	/**
	 * Sets the fare of the Route to the given fare
	 * 
	 * @param fare		double representing the Fare of this Route 
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	/**
	 * Returns the total Fee of the trip from between start to end
	 * 
	 * @param start 	the name of the starting stop
	 * @param end 		the name of the stop where the trip ends
	 * @return the amount of money that should be charged for a trip
	 * 			from start to end
	 */
	public abstract double getFee(String start, String end);
	
	/**
	 * Returns the distance between start and end
	 * @param start		the name of the starting stop
	 * @param end		the name of the stop where the trip ends
	 * @return -1 if start or end is not in stops otherwise returns
	 * 			the distance (number of stops) between them
	 */
	public int getDistance(String start, String end) {
		
		if (this.getStops().indexOf(start) == -1 ||
				this.getStops().indexOf(end) == -1) {
			return -1;
		}
		return Math.abs(this.getStops().indexOf(start) - this.getStops().indexOf(end));
	}

	public abstract String getTransportation();


}
