package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class of the solution. This class outputs the minimal spread of two different
 * csv files, namely weather.csv and football.csv.
 * By referencing {@link ReadFile}, this class calls 3 methods to: read the file; convert the file
 * into a List; and get the minimal spread.
 * In the end this information will be output in the console.
 *
 * @author Rafael Teixeira <teixeirarc@hotmail.com>
 * @version 0.1
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
    	weatherFile = readWeatherFile.readFileFromResources(weatherFileName);
		weatherRecords = readWeatherFile.convertCSVToList(weatherFile);
		
		String[] weatherSpreadData = readWeatherFile.getSpreadFromList(weatherRecords, "MxT", "MnT");
		System.out.printf("The day with the smallest temperature spread"
				+ " was day %s with a spread of %s°C%n", weatherSpreadData[0], weatherSpreadData[1]);
		
		List<List<String>> footballRecords = new ArrayList<>();
    	String footballFileName = "de/exxcellent/challenge/football.csv";
    	ReadFile readFootballFile = new ReadFile();
    	File footballFile;
		footballFile = readFootballFile.readFileFromResources(footballFileName);
		footballRecords = readFootballFile.convertCSVToList(footballFile);
				
		String[] footballSpreadData = readWeatherFile.getSpreadFromList(footballRecords, "Goals", "Goals Allowed");
		System.out.printf("The team with the smallest difference between"
				+ " Goals and Goals Allowed was %s with a spread of %s Goals%n", footballSpreadData[0], footballSpreadData[1]);
    	
        // Your preparation code …

//        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
//        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
//
//        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
//        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}