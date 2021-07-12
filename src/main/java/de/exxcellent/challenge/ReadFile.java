package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *This class contains the three methods needed to read a csv file, converting it into a List
 *and finally collect the minimal spread between two given columns.
 * 
 * @author Rafael Teixeira <teixeirarc@hotmail.com>
 *
 */

public class ReadFile {
	
	private String fileName;
	private File file;
	private List<List<String>> recordsFromFile = new ArrayList<>();
	
	/**
	 * Constructor initializes the path of the file from which the spread values have to be read.
	 * 
	 * @param fileName
	 */
	
	public ReadFile(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * This method reads a file in the resources folder depending on the class attribute this.fileName.
	 *  
	 * @exception NullPointerException
	 */

	public void readFileFromResources() {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(this.fileName);
		
		if(resource == null)
			throw new NullPointerException("File path is wrong or Resource folder is empty");
		
		try {
			this.file = new File(resource.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method sets the class attribute recordsFromFile for a given csv file. 
	 * 
	 * @return records  
	 */

	public void convertCSVToList() {
		try (Scanner scanner = new Scanner(this.file)) {
			while(scanner.hasNextLine()) {
				this.recordsFromFile.add(getRecordsFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	

	private static List<String> getRecordsFromLine(String line) {
		List<String> values = new ArrayList<>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	        	values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
	
	/**
	 * This method returns a String Array with the minimum spread between the values of two given columns and also
	 * the value of the first column for which the spread was minimum.
	 * 
	 * @param columnName1
	 * @param columnName2
	 * @return minSpreadData
	 */

	public String[] getSpreadFromList(String columnName1, String columnName2) {
		
		List<Integer> spreadValues = new ArrayList<>();
		String[] minSpreadData = new String[2];
				
		int columnIndex1 = this.recordsFromFile.get(0).indexOf(columnName1);
		int columnIndex2 = this.recordsFromFile.get(0).indexOf(columnName2);
		
		this.recordsFromFile
			.subList(1, this.recordsFromFile.size())
			.forEach(record -> spreadValues
					.add(spreadOfARecord(record, columnIndex1, columnIndex2)));
		
		int minSpread = Collections.min(spreadValues);
		int indexOfMinSpread = spreadValues.indexOf(minSpread) + 1;
		
		minSpreadData[0] = this.recordsFromFile.get(indexOfMinSpread).get(0);
		minSpreadData[1] = Integer.toString(minSpread);
		
		return minSpreadData;
	}
	
	private int spreadOfARecord(List<String> record, int index1, int index2) {
		int valueOne = Integer.parseInt(record.get(index1));
		int valueTwo = Integer.parseInt(record.get(index2));
		return Math.abs(valueOne - valueTwo);
	}
}
