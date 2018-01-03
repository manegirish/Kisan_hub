package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by GirishM on 02-01-2018.
 */

class Analysis_ {

	static List<String> years() {
		List<String> yearsList = new ArrayList<>();
		for (int y = 1910; y <= 2017; y++) {
			yearsList.add(String.valueOf(y));
		}
		return yearsList;
	}

	static void yearlyAverage(ArrayList<CountryData_> countryDataArrayList, String country, String factor) {
		ListIterator<CountryData_> listIterator = countryDataArrayList.listIterator();

		ArrayList<CountryData_> averageDataList = new ArrayList<>();
		int index = 0;
		double summation = 0;

		while (listIterator.hasNext()) {
			CountryData_ countryData_ = listIterator.next();
			String record = countryData_.getReading();
			if (record.equalsIgnoreCase("N/A")) {
				record = "0";
			}
			if (index < (countryDataArrayList.size() - 1)) {
				if (countryData_.getCountry().equalsIgnoreCase(country)
						&& countryData_.getType().equalsIgnoreCase(factor)) {
					String currentYear = countryData_.getYear();
					String nextYear = countryDataArrayList.get(index + 1).getYear();
					summation = summation + Double.parseDouble(record);
					if (!currentYear.equalsIgnoreCase(nextYear)) {
						String record_summation = new DecimalFormat("##.#").format(summation);

						CountryData_ countrAvgData = new CountryData_(currentYear, record_summation);
						averageDataList.add(countrAvgData);
						summation = 0;
					}
				}
			}
			index++;
		}
		maxAverageValue(averageDataList, country);
	}

	static CountryData_ maxAverageValue(ArrayList<CountryData_> averageDataList, String country) {

		double max_value = 0;
		int index = 0;
		String year = "";

		for (CountryData_ countryData_ : averageDataList) {
			if (max_value <= Double.parseDouble(countryData_.getAverage())) {
				max_value = Double.parseDouble(countryData_.getAverage());
				year = countryData_.getYear();
			}
		}

		System.out.println("Maximum Yearly Average Rainfall Recorded in " + country +" : "+ year + " => " +new DecimalFormat("##.#").format(max_value/12)+"\n");
		return averageDataList.get(index);
	}

	static double maxValue(ArrayList<CountryData_> countryDataArrayList, String country, String factor) {
		double max_value = 0;
		for (CountryData_ countryData_ : countryDataArrayList) {
			double reading = 0;
			if (countryData_.getCountry().equalsIgnoreCase(country)
					&& countryData_.getType().equalsIgnoreCase(factor)) {
				if (!countryData_.getReading().equalsIgnoreCase("N/A")) {
					reading = Double.parseDouble(countryData_.getReading());
				}
				if (max_value <= reading) {
					max_value = reading;
				}
			}
		}
		return max_value;
	}

	static double minValue(ArrayList<CountryData_> countryDataArrayList, String country, String factor) {
		double min_value = 0;
		for (CountryData_ countryData_ : countryDataArrayList) {
			// System.out.println(record.getReading());
			double reading = 0;
			if (countryData_.getCountry().equalsIgnoreCase(country)
					&& countryData_.getType().equalsIgnoreCase(factor)) {
				if (!countryData_.getReading().equalsIgnoreCase("N/A")) {
					reading = Double.parseDouble(countryData_.getReading());
				}
				if (min_value >= reading) {
					min_value = reading;
				}
			}
		}
		return min_value;
	}

	static double maxValues(ArrayList<Double> recordsList) {
		double max_value = 0;
		for (Double record : recordsList) {
			if (max_value < record) {
				max_value = record;
			}
		}
		return max_value;
	}

}
