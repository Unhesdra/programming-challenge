package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

	public File readFileFromResources(String fileName) throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		
		if(resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return new File(resource.toURI());
		}
	}

	public List<List<String>> convertCSVToList(File file) throws FileNotFoundException {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				records.add(getRecordsFromLine(scanner.nextLine()));
			}
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

	public void getSpreadFromList(List<List<String>> records) {
		int maxTemperature = Integer.parseInt(records.get(1).get(1));
		int minTemperature = Integer.parseInt(records.get(1).get(2));
		int minSpread = Math.abs(maxTemperature - minTemperature);
		int dayMinSpread = 1;
		
		for (int i = 1; i < records.size(); i++) {			
			maxTemperature = Integer.parseInt(records.get(i).get(1));
			minTemperature = Integer.parseInt(records.get(i).get(2));
			if (Math.abs(maxTemperature - minTemperature) < minSpread) {
				minSpread = Math.abs(maxTemperature - minTemperature);
				dayMinSpread = i + 1;
			}
		}
		System.out.printf("The day with the smallest temperature spread was day %d with a spread of %d°C%n", dayMinSpread, minSpread);
	}
}
