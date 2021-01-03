package transitapp;
/**
 * A CardAction class. It's consists of a user, card, the action and
 * amount which is optional.
 */
public class CardAction {
	
	private CardHolder user;
	private Card card;
	private String action;
	private double amount;
	
	/**
	 * Constructs a CardAction object without an amount
	 * 
	 * @param user			CardHolder of the action
	 * @param card			Card that does the action
	 * @param action		String representing an action
	 */
	public CardAction(CardHolder user, Card card, String action) {
		this.user = user;
		this.card = card;
		this.action = action;
	}
	
	/**
	 * Constructs a CardAction object with an amount
	 * 
	 * @param user			CardHolder of the action
	 * @param card			Card that does the action
	 * @param action		String representing an action
	 * @param amount		Double representing an amount to do action with	
	 */
	public CardAction(CardHolder user, Card card, String action, double amount) {
		this.user = user;
		this.card = card;
		this.action = action;
		this.amount = amount;
	}
	
	/**
	 * Simulates the Action of the CardAction. Current actions include:
	 * suspending a card, unsuspending a card, adding balance to a Card,
	 * adding or removing a Card from the user
	 */
	public void act() {
		
		if (this.action.equals("RemoveCard")) {
			user.removeCard(card);
		}
		
		else if (this.action.equals("AddCard")) {
			user.addCard(card);
		}
		
		else if (this.action.equals("AddBalance")) {
			card.addBalance(amount);
		}
		
		else if (this.action.equals("Suspend")) {
			card.suspend();
		}
		
		else if (this.action.equals("Unsuspend")) {
			card.unsuspend();
		}
	}
}
