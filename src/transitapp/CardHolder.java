package transitapp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A CardHolder class that consists of a name, email, cards and trips.
 * It can get its last three trips it took, add money to one of the card,
 * remove a card, add a card, change its name, get total distance
 * traveled, average time spent on the transit system and average trip
 * cost for a specific month.
 */
public class CardHolder {

	private String name, email;
	private ArrayList<Card> cards;
	private ArrayList<Trip> trips;

	/**
	 * Constructs a CardHolder object without Cards
	 *
	 * @param name 			String representing the name
	 * @param email 		String representing an email
	 */
	public CardHolder(String name, String email) {
		this(name, email, new ArrayList<Card>());
	}

	/**
	 * Constructs a CardHolder object with Cards
	 *
	 * @param name 			String representing the name
	 * @param email 		String representing an email
	 * @param cards 		a list of Cards
	 */
	public CardHolder(String name, String email, ArrayList<Card> cards) {
		this.name = name;
		this.email = email;
		this.cards = cards;
		this.trips = new ArrayList<Trip>();
	}

	/**
	 * Return the average trip time of this CardHolder
	 * 
	 * @return the average trip time
	 */
	public double avgTripTime() {
		int minutes = 0;
		for (Trip trip : this.trips) {
			minutes += trip.getTotalTripTime();
		}

		if (this.trips.size() == 0) {
			return 0;
		}

		return minutes / this.trips.size();
	}

	/**
	 * return the distance of the trip
	 * 
	 * @return return the distance of the trip
	 */
	public int totalTripLength() {
		int length = 0;
		for (Trip trip : this.trips) {
			length = length + trip.getTripLength();
		}
		return length;
	}

	/**
	 * Returns the monthly trip cost
	 * 
	 * @return the monthly trip cost
	 */
	public double monthTripCost() {
		if (this.trips == null) {
			return 0;
		}
		int total = 0;
		for (Trip trip : this.trips) {
			total += trip.getTripFee();
		}
		TransitTime start = this.trips.get(0).getTaps().get(0).getTime();
		ArrayList<Tap> endTaps = this.trips.get(this.trips.size() - 1).getTaps();
		TransitTime end = endTaps.get(endTaps.size() - 1).getTime();
		long monthDiff = Math.abs(start.getTime().until(end.getTime(), ChronoUnit.MONTHS));
		return total / monthDiff + 1;
	}

	/**
	 * Returns a card object given the card's name.
	 *
	 * @param cardName of the card we would like to find.
	 * @return a card object.
	 */
	public Card getCard(String cardName) {

		for (int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).getName().equals(cardName)) {
				return this.cards.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns a list of cards the CardHolder has
	 * 
	 * @return the list of cards of this CardHolder
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public ArrayList<Trip> getRecentTrip() {
		ArrayList<Trip> recentTrips = new ArrayList<Trip>();
		if (this.trips.size() < 3) {
			return this.trips;
		} else {
			recentTrips.add(this.trips.get(this.trips.size() - 3));
			recentTrips.add(this.trips.get(this.trips.size() - 2));
			recentTrips.add(this.trips.get(this.trips.size() - 1));
		}
		return recentTrips;
	}

	/**
	 * Returns an array list containing all the trips of this CardHolder.
	 *
	 * @return array list containing all the trips of this CardHolder.
	 */
	public ArrayList<Trip> getTrips() {
		return this.trips;
	}

	/**
	 * Adds funds based on the specified number to the given card.
	 *
	 * @param cardName of the card.
	 * @param add      an integer representing the amount we would like to add to
	 *                 the given card.
	 */

	public void addBalance(String cardName, int add) {
		Card card = this.getCard(cardName);
		if (card != null) {
			card.addBalance(add);
		}
	}

	/**
	 * Adds a trip to this CardHolder's trip list.
	 *
	 * @param trip that this CardHolder has added.
	 */
	public void addTrip(Trip trip) {
		this.trips.add(trip);
		Collections.sort(this.trips, new Trip.SortTrips());
	}

	/**
	 * A setter for this CardHolder's name
	 *
	 * @param newName to be used.
	 */
	public void changeName(String newName) {
		this.name = newName;
	}

	/**
	 * Adds a card to this CardHolder's account.
	 *
	 * @param card object to be added.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}

	/**
	 * Removes the card from this CardHolder's account
	 * 
	 * @param card			Card to remove from CardHolder's account
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}

	
	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * Return the average cost for a given month
	 * 
	 * @param month			Integer representing a month 1,...,12
	 * @return the average cost per month
	 */
	public double monthTripCost(int month) {
		if (this.trips == null) {
			return 0;
		}

		TransitTime start = this.trips.get(0).getTaps().get(0).getTime();
		ArrayList<Tap> endTaps = this.trips.get(this.trips.size() - 1).getTaps();
		TransitTime end = endTaps.get(endTaps.size() - 1).getTime();
		return Math.abs(start.getTime().until(end.getTime(), ChronoUnit.MONTHS));
	}

	@Override
	public String toString() {
		StringBuilder cardsToString = new StringBuilder();

		for (Card card : this.cards) {
			cardsToString.append(card.toString());
		}

		if (cards.size() > 0) {
			return "Name [" + this.name + "] | [" + this.email + "] | " + cardsToString + ".";
		}

		return "Name [" + this.name + "] | [" + this.email + "].";
	}
}