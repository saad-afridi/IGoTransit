package transitapp;

import java.util.ArrayList;

/**
 * A Card represents a transit system card that consists of a name, balance, holdername, suspension status and list of taps.
 * The Card is able to preform actions that are useful to the transit system, for example adding / subtracting funds to it's balance
 *
 */
public class Card {

	private static double STARTING_BALANCE = 19;
	private String NOT_VALID = "Only $10, $20 or $50 can be added at a time";

	private String name;
	private double balance;
	private ArrayList<Tap> taps;
	private String holderName;
	private boolean isSuspended = false;

	/**
     * Constructs a new card with a name and a card holder name
     * 
     * @param name 			Name for the card
     */
	public Card(String name, String holderName) {
		this.name = name;
		this.balance = STARTING_BALANCE;
		this.taps = new ArrayList<Tap>();
		this.holderName = holderName;
	}

	/**
	 * Return the name of the cardholder of this card
	 * 
	 * @return the CardHolder's name of this card as a String
	 */
	public String getHolderName() {
		return this.holderName;
	}

	/**
     * Return the state of the card (i.e is it suspended or not)
     * 
     * @return boolean true of false
     */
	public boolean isSuspended() {
		return this.isSuspended;
	}

	/**
     * Suspends the card
     */
	public void suspend() {
		this.isSuspended = true;
	}

	/**
     * Unsuspends this card
     */
	public void unsuspend() {
		this.isSuspended = false;
	}

	/**
     * Add a given value to this card's balance.
     * 
     * @param add 			An integer representing the amount to be added to this card.
     */
	public void addBalance(double add) {
		this.balance = this.balance + add;
	}

	/**
     * Deducts the given value from this card's balance.
     * 
     * @param value 		An integer representing the amount to be charged from this card.
     */
	public void charge(double value) {
		this.balance = this.balance - value;
	}

	/**
	 * Sets the starting balance of this card.
	 * 
	 * @param balance an integer representing the initial balance of a card.
	 */
	public static void setStartingBalance(int balance) {
		STARTING_BALANCE = balance;
	}

	/**
     * Returns the balance of this card.
     * 
     * @return balance of this card.
     */
	public double getBalance() {
		return this.balance;
	}

	/**
     * Returns the name of this card.
     * 
     * @return name of this card.
     */
	public String getName() {
		return this.name;
	}

	public void addTap(Tap tap) {
		this.taps.add(tap);
	}

  /**
     * Return all the taps this card has done
     * @return ArrayList<Tap>
     */
	public ArrayList<Tap> getTaps() {
		return this.taps;
	}

	@Override
	public String toString() {
		return "Card: [" + this.name + "] | Balance: [$" + Double.toString(this.balance) + "] ";
	}
}
