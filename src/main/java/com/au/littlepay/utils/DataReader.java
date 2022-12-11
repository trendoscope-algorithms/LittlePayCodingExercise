package com.au.littlepay.utils;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.pricing.Destination;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DataReader {
    private static DataReader instance;
    private static Map<String, List<Destination>> distanceMatrix;
    private static final String PRICE_FILE = "prices.json";

    private DataReader(){

    }

    public static DataReader getInstance(){
        if(instance == null){
            instance = new DataReader();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<Destination>> getDistanceMatrix() throws IOException, URISyntaxException {
        if(distanceMatrix == null){
            Gson gson = new Gson();
            InputStream resource = DataReader.class.getClassLoader().getResourceAsStream(PRICE_FILE);
            Reader reader = new InputStreamReader(resource);
            Type distanceMapType = new TypeToken<Map<String, List<Destination>>>() {}.getType();
            distanceMatrix= gson.fromJson(reader, distanceMapType);
        }
        return distanceMatrix;
    }

    public List<Tap> readTaps(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        CSVReader csvReader = new CSVReader(reader);
        return new CsvToBeanBuilder<Tap>(csvReader)
                .withType(Tap.class)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();
    }
}
