package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class of the solution. This class outputs the minimal spread of two different
 * csv files, namely weather.csv and football.csv.
 * By referencing {@link ReadFile}, this class calls 3 methods to: read the file; convert the file
 * into a List; and get the minimal spread.
 * In the end this information will be printed in the console.
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

    	String weatherFileName = "de/exxcellent/challenge/weather.csv";
    	String footballFileName = "de/exxcellent/challenge/football.csv";
    	
    	String[] weatherSpreadData = App.GetSpreadFromFile(weatherFileName, "MxT", "MnT");
    	String[] footballSpreadData = App.GetSpreadFromFile(footballFileName, "Goals", "Goals Allowed");
		
		System.out.printf("The day with the smallest temperature spread"
				+ " was day %s with a spread of %s°C%n", weatherSpreadData[0], weatherSpreadData[1]);
		
		System.out.printf("The team with the smallest difference between"
				+ " Goals and Goals Allowed was %s with a spread of %s Goal(s)%n", footballSpreadData[0], footballSpreadData[1]);
    }
    
    private static String[] GetSpreadFromFile(String fileName, String columnName1, String columnName2) {    	
    	ReadFile readFile = new ReadFile(fileName);
    	readFile.readFileFromResources();
    	readFile.convertCSVToList();
    	
    	return readFile.getSpreadFromList(columnName1, columnName2);
    }
}