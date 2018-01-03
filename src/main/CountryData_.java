package main;


/**
 * Created by GirishM on 02-01-2018.
 */

public class CountryData_ {

    private String country, type, year, month, reading,average;

    CountryData_(String country, String type, String year, String month, String reading) {
        this.country = country.trim();
        this.type = type.trim();
        this.year = year.trim();
        this.month = month.trim();
        this.reading = reading.trim();
    }

    CountryData_(String year,String average){
        this.year = year.trim();
        this.average = average.trim();
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public String getMonth() {
        return month;
    }

    public String getReading() {
        return reading;
    }

    public String getYear() {
        return year;
    }

    public String getAverage() {
        return average;
    }
}
