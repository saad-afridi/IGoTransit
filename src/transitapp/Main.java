// This is a placeholder package and placeholder class
// Feel free to rename or remove these when you add in your own code (just make sure to add/commit/push any changes made,
//		and let your teammates know to pull the changes. Follow the workflow in the a2 instructions)

package transitapp;

import LoadingData.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import Admin.Admin;

public class Main {
    public static void main(String[] args) {

		Admin admin = new Admin();
		LoadTap(admin);
		
		CardHolder user = admin.getCardHolder("1204");
		Card card = user.getCard("71044126");
		ArrayList<Tap> taps = card.getTaps();
		System.out.println("Taps for user 1204, card 71044126");
		for (Tap tap: taps) {
			System.out.println(tap);
		}
		
		
		CardHolder user1 = admin.getCardHolder("4950");
		Card card1 = user1.getCard("12345678");
		ArrayList<Tap> taps1 = card1.getTaps();
		System.out.println("Taps for user 4950, card 12345678, user 4950 "
				+ "doesnt have a card 12345678, so a new card is created");
		
		for (Tap tap1: taps1) {
			System.out.println(tap1);
		}

	}
    /**
     * Loads all the Taps into all corresponding cards, and returns a HashMap that contains the Card as the key, 
     * and an ArrayList that has the taps as the value
     * @param admin the Admin class that has all the info about the CardHolders
     * @return HashMap that contains the Card as the key, and an ArrayList that has the taps as the value
     */
    public static HashMap<Card, ArrayList<Tap>> LoadTap(Admin admin) {

    	ArrayList<String[]> tapinfo = Reader.doAll("\\src\\testing-files\\Trips2.txt");
    	HashMap<Card, ArrayList<Tap>> hash = new HashMap<Card, ArrayList<Tap>>();
		for (String[] rawTap : tapinfo) {
			
        	String usernum = rawTap[0];
        	String cardnum = rawTap[1];
        	String action = rawTap[2];
        	String transportation = rawTap[3];
        	String location = rawTap[4];
        	String timeStr = rawTap[5];
        	String routename = rawTap[6];

			TransitTime time = new TransitTime(timeStr);
        	CardHolder user = admin.getCardHolder(usernum);
			Card card = user.getCard(cardnum);

			Tap tap = new Tap(action, location, transportation, time, routename);

			hash.putIfAbsent(card, new ArrayList<Tap>());
        	hash.get(card).add(tap);
        }

		return hash;
    }
}