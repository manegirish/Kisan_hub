package main;

import java.io.FileWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by GirishM on 28-12-2017.
 */

class WriteCsv_ {

    static void makeFile(final List<CountryData_> countryDataArrayList) {
       /* String directory = android.os.Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/kisanTest/";
        File myDir = new File(directory);
        if (!myDir.exists()) {
            boolean created = myDir.mkdir();
        }*/

        final String filename = "";// directory + "output.csv";
        new Thread() {
            public void run() {
                try {
                    FileWriter fw = new FileWriter(filename);
                    fw.append("region_code");
                    fw.append(",");
                    fw.append("weather_param");
                    fw.append(",");
                    fw.append("year");
                    fw.append(",");
                    fw.append("key");
                    fw.append(",");
                    fw.append("value");
                    fw.append(",");
                    fw.append("\n");

                    ListIterator<CountryData_> listIterator = countryDataArrayList.listIterator();
                    int index = 0;
                    while (listIterator.hasNext()) {
                        CountryData_ countryData_ = listIterator.next();
                        String rowString = countryData_.getCountry() + "," +
                                countryData_.getType() + "," +
                                countryData_.getYear() + "," +
                                countryData_.getMonth() + "," +
                                countryData_.getReading();

                        fw.append(rowString);
                        fw.append("\n");

                        System.out.println(index + ":" + rowString);
                        index++;
                    }
                    fw.close();
                } catch (Exception ignored) {
                }
            }
        }.start();
    }

}
