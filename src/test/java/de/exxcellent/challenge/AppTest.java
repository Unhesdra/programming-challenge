package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }
    
    @Test
    void valuesAreExtractedCorrectlyFromWeatherCSVFile() throws URISyntaxException, FileNotFoundException {
    	String fileName = "de/exxcellent/challenge/weather.csv";
    	ReadFile readFile = new ReadFile();
    	File file = readFile.readFileFromResources(fileName);
    	
    	List<List<String>> records = new ArrayList<>();
    	records = readFile.convertCSVToList(file);
    	
    	assertEquals(records.get(30).size(), 14);
    }
    
    @Test
    void weatherCSVFileFromResoursesCouldNotBeFound() {
    	String fileName = "de/exxcellent/chalenge/weather.csv";
    	ReadFile readFile = new ReadFile();
    	
    	assertThrows(IllegalArgumentException.class, () -> readFile.readFileFromResources(fileName));
    }
    
    @Test
    void valuesAreExtractedCorrectlyFromFootballCSVFile() throws URISyntaxException, FileNotFoundException {
    	String fileName = "de/exxcellent/challenge/football.csv";
    	ReadFile readFile = new ReadFile();
    	File file = readFile.readFileFromResources(fileName);
    	
    	List<List<String>> records = new ArrayList<>();
    	records = readFile.convertCSVToList(file);
    	
    	assertEquals(records.get(20).size(), 8);
    }
    
    @Test
    void footballCSVFileFromResoursesCouldNotBeFound() {
    	String fileName = "de/exxcellent/chalenge/football.csv";
    	ReadFile readFile = new ReadFile();
    	
    	assertThrows(IllegalArgumentException.class, () -> readFile.readFileFromResources(fileName));
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

}