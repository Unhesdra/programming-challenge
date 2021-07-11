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
