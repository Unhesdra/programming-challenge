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

    	List<List<String>> weatherRecords = new ArrayList<>();
    	String weatherFileName = "de/exxcellent/challenge/weather.csv";
    	ReadFile readWeatherFile = new ReadFile();
    	File weatherFile;
		try {
			weatherFile = readWeatherFile.readFileFromResources(weatherFileName);
			weatherRecords = readWeatherFile.convertCSVToList(weatherFile);
		} catch (URISyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		readWeatherFile.getSpreadFromList(weatherRecords);
    	
        // Your preparation code …

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}