package main;

import java.io.IOException;
import java.util.ArrayList;

class RetrieveObjectThread extends Thread {

    private FinalDataList finalDataList;

    private final String url, country, type;
    private final int index;

    RetrieveObjectThread(final String url, final String country, final String type, final int index) {
        this.type = type;
        this.country = country;
        this.url = url;
        this.index = index;
    }

    void setResultSetter(FinalDataList finalDataList) {
        this.finalDataList = finalDataList;
    }

    public void run() {
        ArrayList<CountryData_> finalArray = new ArrayList<>();
        try {
            finalArray.addAll(FileProcessing_.readObject(url, country, type));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finalDataList.setResult(finalArray, index);
    }
}