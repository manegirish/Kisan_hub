package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class CsvFileWriter {

	private static final String DATA_SEPARATOR = ",";
	private static final String LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "region_code,weather_param,year,key,value";

	public static void writeCsvFile(String fileName, ArrayList<CountryData_> countryDataArrayList) {
		
/*		File files = new File(fileName);
		if (files.exists()) {
			files.delete();
		}
		
		if (files.mkdirs()) {
			System.out.println("File created!");
		} else {
			System.out.println("Failed to create file!");
			return;
		}*/

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(LINE_SEPARATOR);

			ListIterator<CountryData_> listIterator = countryDataArrayList.listIterator();
			while (listIterator.hasNext()) {
				
				CountryData_ countryData_ = listIterator.next();
				
				fileWriter.append(countryData_.getCountry());
				fileWriter.append(DATA_SEPARATOR);
				fileWriter.append(countryData_.getType());
				fileWriter.append(DATA_SEPARATOR);
				fileWriter.append(countryData_.getYear());
				fileWriter.append(DATA_SEPARATOR);
				fileWriter.append(countryData_.getMonth());
				fileWriter.append(DATA_SEPARATOR);
				fileWriter.append(countryData_.getReading());
				fileWriter.append(LINE_SEPARATOR);
				
			}
			System.out.println("CSV file was created successfully !!!\n");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!\n");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!\n");
				e.printStackTrace();
			}

		}
	}
}
