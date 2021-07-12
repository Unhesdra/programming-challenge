package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test case.
 * 
 * @author Rafael Teixeira <teixeirarc@hotmail.com>
 */
class AppTest {

    @Test
    void valuesAreExtractedCorrectlyFromWeatherCSVFile() throws URISyntaxException, FileNotFoundException {
    	String[] spreadData = new String[2];
    	String fileName = "de/exxcellent/challenge/weather.csv";
    	ReadFile readFile = new ReadFile(fileName);
    	readFile.readFileFromResources();
    	readFile.convertCSVToList();
    	spreadData = readFile.getSpreadFromList("MxT", "MnT");
    	
    	assertEquals(spreadData[0], "14");
    	assertEquals(spreadData[1], "2");
    }
    
    @Test
    void weatherCSVFileFromResoursesCouldNotBeFound() {
    	String fileName = "de/exxcellent/chalenge/weather.csv";
    	ReadFile readFile = new ReadFile(fileName);
    	
    	assertThrows(NullPointerException.class, () -> readFile.readFileFromResources());
    }
    
    @Test
    void valuesAreExtractedCorrectlyFromFootballCSVFile() throws URISyntaxException, FileNotFoundException {
    	String[] spreadData = new String[2];
    	String fileName = "de/exxcellent/challenge/football.csv";
    	ReadFile readFile = new ReadFile(fileName);
    	readFile.readFileFromResources();
    	readFile.convertCSVToList();
    	
    	spreadData = readFile.getSpreadFromList("Goals", "Goals Allowed");
    	
    	assertEquals(spreadData[0], "Aston_Villa");
    	assertEquals(spreadData[1], "1");
    }
    
    @Test
    void footballCSVFileFromResoursesCouldNotBeFound() {
    	String fileName = "de/exxcellent/chalenge/football.csv";
    	ReadFile readFile = new ReadFile(fileName);
    	
    	assertThrows(NullPointerException.class, () -> readFile.readFileFromResources());
    }
}