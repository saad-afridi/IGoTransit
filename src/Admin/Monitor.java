package Admin;

import LoadingData.Reader;
import transitapp.Card;
import transitapp.CardAction;
import transitapp.CardHolder;
import transitapp.Main;
import transitapp.Tap;
import transitapp.TransitSystem;
import transitapp.TransitTime;
import transitapp.Trip;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Monitor represents a base version of an admin user. Monitor containds all of the basic requirements needed
 * to succuessfully create an Admin user which can they create it's own statistics as needed 
 */
public abstract class Monitor {

	private ArrayList<CardHolder> cardHolders = new ArrayList<CardHolder>();
	protected TransitSystem transitSystem;

	/**
	 * Constructs a Montior object by loading all card holders into it's cardHolder attribute and by preforming all 
	 * card actions. Both Card holders and card actions are stored in text files
	 */
	public Monitor() {
		this.transitSystem = new TransitSystem();

		// All the CardHolder and their Cards
		ArrayList<String[]> cardInfo = Reader.doAll("\\src\\testing-files\\CardHolders.txt");

		for (String[] profile : cardInfo) {
			String name = profile[0];
			String email = profile[1];
			ArrayList<Card> cards = new ArrayList<>();
			for (int i = 2; i < profile.length; i++) {
				cards.add(new Card(profile[i], name));
			}

			this.addCardHolder(new CardHolder(name, email, cards));
		}

		// All the Card Actions
		ArrayList<String[]> data = Reader.doAll("\\src\\testing-files\\CardActions.txt");

		for (String[] line : data) {
			String usernum = line[0];
			String cardnum = line[1];
			String action = line[2];

			CardHolder user = this.getCardHolder(usernum);

			Card card;
			if (user.getCard(cardnum) == null) {
				card = new Card(cardnum, usernum);
			} else {
				card = user.getCard(cardnum);
			}

			if (line.length == 3) {
				new CardAction(user, card, action).act();
			} else {
				new CardAction(user, card, action, Double.valueOf(line[3])).act();
			}
		}
	}


	/**
	 * Return a list of all card holders registered to the current transit system
	 * 
	 * @return ArrayList<CardHolder> 
	 */
	public ArrayList<CardHolder> getCardHolders() {
		return this.cardHolders;
	}
	
	/**
	 * Add a card holder to the list of card holders registered to the current transit system
	 * 
	 * @param c			Card Holder object
	 */
	public void addCardHolder(CardHolder c) {
		this.cardHolders.add(c);
	}

	/**
	 * Return a card holder object if the user number string is matches the user number of a registered card holder
	 * 
	 * @param usernum			A string representing the user number of a possible Card holder
	 * @return Card holder if there is a match, null otherwise
	 */
	public CardHolder getCardHolder(String usernum) {
		for (CardHolder user : this.cardHolders) {
			if (user.getName().equals(usernum)) {
				return user;
			}
		}
		
		return null;
	}


	public void setStartingBalance(int balance) {
		Card.setStartingBalance(balance);
	}

	@Override
	public String toString() {

		StringBuilder users = new StringBuilder();

		for (CardHolder user : this.cardHolders) {
			users.append(user.toString());
			users.append("\n");
		}
		return "===== CardHolders =====\n" + users.toString() + "=======================";
	}

	/**
	 * Returns the initialized transit system.
	 * 
	 * @return the initialized transit system.
	 */
	public TransitSystem getTransitSystem() {
		return this.transitSystem;
	}
}