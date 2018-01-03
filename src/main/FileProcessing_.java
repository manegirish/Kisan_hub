package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by GirishM on 28-12-2017.
 */

class FileProcessing_ {

    static final String TAG = FileProcessing_.class.getSimpleName();

    private static ArrayList<String> rowArray(String readString) {
        /*
        * England,Max temp,1910,JAN,5.4
        * */
        ArrayList<String> rowArray = new ArrayList<>();
        int index = 0;
        while (index < readString.length() - 3) {
            index = index + 3;
            String reading = readString.substring(index - 1, index + 4);
            index = index + 4;
            rowArray.add(reading);
        }
        return rowArray;
    }

    private static String reading(String reading) {
        if (reading.trim().length() <= 0) {
            return "N/A";
        } else {
            return reading;
        }
    }

    private static ArrayList<CountryData_> rowItem(String country, String type, String year,
                                                   ArrayList<String> monthArray,
                                                   ArrayList<String> readingList) {

        ArrayList<CountryData_> countryDataArrayList = new ArrayList<>();
        int index = 0;
        for (String month : monthArray) {
            String record = readingList.get(index);
            CountryData_ countryData_ = new CountryData_(country, type, year, month, reading(record));
            countryDataArrayList.add(countryData_);
            index++;
        }
        return countryDataArrayList;
    }

    static List<CountryData_> readObject(String file_url, String country, String type) throws IOException {
    	System.out.println(type+" > "+file_url);
        List<CountryData_> finalArray = new ArrayList<>();
        URL url = new URL(file_url);
        Scanner scanner = new Scanner(url.openStream());
        boolean tableStarted = false;
        ArrayList<String> monthList = new ArrayList<>();
        while (scanner.hasNext()) {
            String lineString = scanner.nextLine();
            if (lineString.length() <= 0) {
                tableStarted = true;
            }
            if (tableStarted) {
                if (lineString.length() > 4) {
                    String year = lineString.substring(0, 4).trim();
                    String readings = lineString.substring(5, 91);
                    if (lineString.contains("Year")) {
                        monthList = rowArray(readings);
                    } else {
                        ArrayList<String> readingList = rowArray(readings);
                        
                        if (monthList.size() > 0 && readingList.size() > 0) {
                            finalArray.addAll(rowItem(country, type, year, monthList, readingList));
                        }
                    }
                }
            }
        }
       // System.out.println(type+" > "+finalArray.size());
        scanner.close();
        return finalArray;
    }
}
