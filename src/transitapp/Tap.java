package transitapp;

public class Tap {
	private String action;
	private String location;
	private String transportation;
	private TransitTime time;
	private String routename;

	/**
	 * Initializing a tap given an action, location of the tap, transportation being used and
	 * time.
	 *
	 * Precondition: action can either be "in" or "out".
	 *
	 * @param action of the tap.
	 * @param location of the tap.
	 * @param transportation used.
	 * @param time of this tap.
	 */
	public Tap(String action, String location, String transportation, TransitTime time, String routename) {
		this.action = action;
		this.location = location;
		this.transportation = transportation;
		this.time = time;
		this.routename = routename;
	}
	
	/**
	 * Returns the routename where the tap happens
	 * @return the routename as String
	 */
	public String getRoutename() {
		return this.routename;
	}

	/**
	 * Returns the action of this tap (either tap in, or tap out).
	 *
	 * @return the kind of action of this tap.
	 */
	public String getAction() { return this.action; }

	/**
	 *	Returns the location of this tap.
	 *
	 * @return location of this tap.
	 */
	public String getLocation() { return this.location; }

	/**
	 * Returns the transportation type used for this tap.
	 *
	 * @return transportation type used for this tap.
	 */
	public String getTransportation() { return this.transportation; }

	/**
	 * Returns the time stamp of this tap.
	 *
	 * @return time when this tap took place.
	 */
	public TransitTime getTime() { return this.time; }

	@Override
	public String toString() {
		return this.action + " at " + this.location
				+ " on a " + this.transportation + ": " + this.routename + " at " + this.time.toString();
	}

}
