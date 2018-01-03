package main;

import java.util.ArrayList;

/**
 * Created by GirishM on 28-12-2017.
 */

final class MakeCall_ {

	private final static String BASE_URL = "https://www.metoffice.gov.uk/pub/data/weather/uk/climate/datasets/";

	private static ArrayList<CountryData_> countryDataArrayList = new ArrayList<>();

	private static int urlIndex = 0;

	private final static String[] types = { "Tmax", "Tmin", "Tmean", "Sunshine", "Rainfall" };// {"Tmax", "Tmin"};//
	private final static String[] typesList = { "Max Temp", "Min Temp", "Mean Temp", "Sunshine", "Rainfall" };
	private final static String[] countries = { "UK", "England", "Wales", "Scotland" };// { "UK" };//

	private static final String[] urlList = new String[types.length * countries.length];
	private static final String[] typeList = new String[types.length * countries.length];
	private static final String[] countryList = new String[types.length * countries.length];

	static void allUrls() {
		int count = 0;
		for (String country : countries) {
			int index = 0;
			for (String type : types) {
				String final_url = BASE_URL + type + "/date/" + country + ".txt";
				urlList[count] = final_url;
				typeList[count] = typesList[index];
				countryList[count] = country;
				index++;
				count++;
			}
		}
		finalCallUrls(urlList[urlIndex], countryList[urlIndex], typeList[urlIndex], urlIndex);
	}

	private static void finalCallUrls(final String url, final String country, final String type,
			final int countryIndex) {
		final FinalDataList finalDataList = new FinalDataList() {
			@Override
			public void setResult(ArrayList<CountryData_> rowArray, int index) {
				countryDataArrayList.addAll(rowArray);
				urlIndex++;
				if (index < urlList.length - 1) {
					finalCallUrls(urlList[urlIndex], countryList[urlIndex], typeList[urlIndex], urlIndex);
				}
				if ((urlList.length - 1) == index) {
					System.out.println("\nFile Processing Ended......\n");

					String fileName = System.getProperty("user.home") + "\\weather_log.csv";
					System.out.println("Output CSV File : " + fileName);
					CsvFileWriter.writeCsvFile(fileName, countryDataArrayList);

					specialReadings();
				}
			}
		};

		RetrieveObjectThread retrieveDataThread = new RetrieveObjectThread(url, country, type, countryIndex);
		retrieveDataThread.setResultSetter(finalDataList);
		retrieveDataThread.start();
	}

	private static void specialReadings() {
		for (String country_ : countries) {
			for (String type : typesList) {
				switch (type) {
				case "Max Temp":
					System.out.println("Maximum Temprature Recorded in " + country_ + " : "
							+ Analysis_.maxValue(countryDataArrayList, country_, type));
					break;
				case "Min Temp":
					System.out.println("Minimum Temprature recorded in " + country_ + " : "
							+ Analysis_.minValue(countryDataArrayList, country_, type));
					break;
				case "Rainfall":
					System.out.println("Maximum Rainfall Recorded in" + country_ + " : "
							+ Analysis_.maxValue(countryDataArrayList, country_, type));
					Analysis_.yearlyAverage(countryDataArrayList, country_, type);
					break;
				default:
					break;
				}
			}
		}
	}

}
