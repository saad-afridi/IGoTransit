package LoadingData;

import transitapp.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Writing {

	public static void writeTaps(Card card, ArrayList<Tap> taps, String fileName) throws IOException {
		
		File currFile = new File("");
    	String path = currFile.getAbsolutePath() + fileName;
    	File file = new File(path);
    	
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		for (Tap tap : taps) {
			
			String usernum = card.getHolderName();
			String cardnum = card.getName();
			String action = tap.getAction();
			String transportation = tap.getTransportation();
			String location = tap.getLocation();
			String timestr = tap.getTime().revert();
			String routename = tap.getRoutename();
			
			String entry = usernum + "," + cardnum + "," + action + "," + transportation + "," + location + "," + timestr + "," + routename;
			printWriter.println(entry);
		}
		
		printWriter.close();
	}
	
	public static void writeCardActions(Card card, String action, double number, String fileName) throws IOException {
		
		File currFile = new File("");
    	String path = currFile.getAbsolutePath() + fileName;
    	File file = new File(path);
    	
    	FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		if (action == "AddBalance") {
			printWriter.println(card.getHolderName() + "," + card.getName() + "," + action + "," + number);
		}
    	
		else if (action == "Suspend" || action == "Unsuspend" || action == "RemoveCard" || action == "AddCard") {
			printWriter.println(card.getHolderName() + "," + card.getName() + "," + action);
		}
		
		printWriter.close();
	}
	
	public static void main(String[] args) throws IOException {
		Card card = new Card("61038196", "NEW INPUT");
		TransitTime t1 = new TransitTime("2001:12:04:09:23");
		TransitTime t2 = new TransitTime("2001:12:04:12:42");
		Tap tap1 = new Tap("IN", "A", "bus", t1, "35W");
		Tap tap2 = new Tap("OUT", "B", "bus", t2, "35W");
		ArrayList<Tap> taps = new ArrayList<>(); taps.add(tap1); taps.add(tap2);
	}
}