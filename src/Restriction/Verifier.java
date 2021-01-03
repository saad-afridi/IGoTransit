package Restriction;

import transitapp.Card;

public class Verifier {

	/**
	 * 
	 */
	public void verifyFares() {

	}

	/**
	 * 
	 */
	public boolean verifyRider(Card card) {
		if (card.getBalance() <= 0) {
			return false;
		}
		
		return true;
	}

}
