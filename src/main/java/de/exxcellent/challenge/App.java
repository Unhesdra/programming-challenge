package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

    	List<List<String>> records = new ArrayList<>();
    	String fileName = "de/exxcellent/challenge/weather.csv";
    	ReadFile readFile = new ReadFile();
    	File file;
		try {
			file = readFile.readFileFromResources(fileName);
			records = readFile.convertCSVToList(file);
		} catch (URISyntaxException | FileNotFoundException e) {
			System.out.println("String could not be parsed as a URL reference!");
			e.printStackTrace();
		}
		
		readFile.getSpreadFromList(records);
    	
        // Your preparation code …

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}