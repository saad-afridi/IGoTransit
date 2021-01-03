package transitapp;

import java.util.ArrayList;
import LoadingData.Reader;

/**
 *  A TransitSystem class that keeps track of all the BusRoute and SubwayRoute
 *  objects in the system. It can find the Route object given a String representing
 *  the Route's name, and add more Routes to keep track of.
 */
public class TransitSystem {

	private ArrayList<BusRoute> busRoutes = new ArrayList<BusRoute>();
	private ArrayList<SubwayRoute> subwayRoutes = new ArrayList<SubwayRoute>();

	/**
     * Constructs a TransitSystem object from the data in Routes.txt
     */
	public TransitSystem() {

		ArrayList<String[]> data = Reader.doAll("\\src\\testing-files\\Routes.txt");

		for (String[] line : data) {
			String type = line[0];
			String name = line[1];
			ArrayList<String> stops = new ArrayList<String>();
			for (int i = 2; i < line.length; i++) {
				stops.add(line[i]);
			}

			if (type.equals("bus")) {
				this.addBusRoute(new BusRoute(name, stops));
			}
			if (type.equals("subway")) {
				this.addSubwayRoute(new SubwayRoute(name, stops));
			}
		}
	}

	/**
	 * Returns an array list containing all the bus routes.
	 * 
	 * @return an array list containing all the bus routes.
	 */
	public ArrayList<BusRoute> getBusRoutes() {
		return this.busRoutes;
	}

	/**
	 * Returns an array list containing all the subway routes. 
	 * 
	 * @return an array list containing all the subway routes.
	 */
	public ArrayList<SubwayRoute> getSubwaysRoutes() {
		return this.subwayRoutes;
	}

	/**
     * Adds a busRoute to the TransitSystem
     * 
     * @param busRoute 		BusRoute object to be added
     */
	public void addBusRoute(BusRoute busRoute) {
		this.busRoutes.add(busRoute);
	}

	/**
	 * Adds a subwayRoute to the TransitSystem
	 * 
	 * @param SubwayRoute 	SubwayRoute object to be added
	 */
	public void addSubwayRoute(SubwayRoute SubwayRoute) {
		this.subwayRoutes.add(SubwayRoute);
	}
	
	/**
     * Returns a Route object if the name matches otherwise
     * returns null
     * 
     * @param name			String representing the name of a Route
     * @return a Route object iff the name matches one of the Route
     * 			in the TransitSystem otherwise null
     */
	public Route findRoute(String name) {
		for (BusRoute bus : this.busRoutes) {
			if (bus.getName().equals(name)) {
				return bus;
			}
		}
		for (SubwayRoute sub : this.subwayRoutes) {
			if (sub.getName().equals(name)) {
				return sub;
			}
		}
		return null;
	}

	@Override
	public String toString() {

		StringBuilder busRouteStr = new StringBuilder();
		StringBuilder subRouteStr = new StringBuilder();

		for (Route busRoute : busRoutes) {
			busRouteStr.append(busRoute.toString());
			busRouteStr.append("\n");
		}

		for (Route subRoute : subwayRoutes) {
			subRouteStr.append(subRoute.toString());
			subRouteStr.append("\n");
		}

		return "===== TransitSystem =====\n" + busRouteStr + subRouteStr + "=========================";
	}
}