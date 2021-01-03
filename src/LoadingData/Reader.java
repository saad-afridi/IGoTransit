package LoadingData;

import java.io.*;
import java.util.*;

public class Reader {

    private Scanner x;

    public void openFile(String path) {
        try {
            File file = new File(path);
            x = new Scanner(file);
        }
        catch (Exception e) {
            System.out.println("Invalid Directory!");
        }
    }

    public ArrayList<String[]> readFile() {
        ArrayList<String[]> outer = new ArrayList<String[]>();
        while(x.hasNextLine()) {
            String line = x.nextLine();
            
            // Ignore empty lines and lines starting with #
            if (line.length() <= 0) {continue;}
            if (line.charAt(0) == '#') {continue;}
            
            outer.add(line.split(","));
        }
        return outer;
    }

    public void closeFile() {
        x.close();
    }
    
    public static ArrayList<String[]> doAll(String add_path){
    	Reader r = new Reader();
    	File currFile = new File("");
    	String path = currFile.getAbsolutePath() + add_path;
    	r.openFile(path);
    	ArrayList<String[]> data = r.readFile();
    	r.closeFile();
    	return data;
    }
}