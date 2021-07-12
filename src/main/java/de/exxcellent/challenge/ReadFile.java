package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *This class cointais the three methods needed to read a csv file, converting it into a List
 *and finally collect the minimal spread between two given columns.
 * 
 * @author Rafael Teixeira <teixeirarc@hotmail.com>
 *
 */

public class ReadFile {
	
	/**
	 * This class returns a file in the resources folder depending on the parameter passed to it.
	 *  
	 * @param fileName
	 * @return file
	 * @exception NullPointerException
	 */
	
	private String fileName;
	
	public ReadFile(String fileName) {
		this.fileName = fileName;
	}

	public File readFileFromResources() {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(this.fileName);
		File file = null;
		
		if(resource == null)
			throw new NullPointerException("File path is wrong or Resource folder is empty");
		
		try {
			file = new File(resource.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * This class returns a List of String List for a given csv file. 
	 * 
	 * @param file
	 * @return records
	 */

	public List<List<String>> convertCSVToList(File file) {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				records.add(getRecordsFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return records;
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
	 * This class returns the minimum spread between the values of two given columns. It also returns
	 * the value of the first column for which the spread was minimum.
	 * 
	 * @param records
	 * @param columnName1
	 * @param columnName2
	 * @return
	 */

	public String[] getSpreadFromList(List<List<String>> records, String columnName1, String columnName2) {
		
		String[] minSpreadData = new String[2];
				
		int columnIndex1 = records.get(0).indexOf(columnName1);
		int columnIndex2 = records.get(0).indexOf(columnName2);
		int maxTemperature = Integer.parseInt(records.get(1).get(columnIndex1));
		int minTemperature = Integer.parseInt(records.get(1).get(columnIndex2));
		int minSpread = Math.abs(maxTemperature - minTemperature);
		
		for (int i = 1; i < records.size(); i++) {			
			maxTemperature = Integer.parseInt(records.get(i).get(columnIndex1));
			minTemperature = Integer.parseInt(records.get(i).get(columnIndex2));
			if (Math.abs(maxTemperature - minTemperature) < minSpread) {
				minSpread = Math.abs(maxTemperature - minTemperature);
				minSpreadData[0] = records.get(i).get(0);
				minSpreadData[1] = Integer.toString(minSpread) ;
			}
		}
		return minSpreadData;
	}
}
