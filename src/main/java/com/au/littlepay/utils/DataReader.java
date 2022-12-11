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
    private static Map<String, List<Destination>> distanceMatrix;
    private static final String PRICE_FILE = "prices.json";

    @SuppressWarnings("unchecked")
    public static Map<String, List<Destination>> getDistanceMatrix() throws IOException, URISyntaxException {
        if(distanceMatrix == null){
            Gson gson = new Gson();
            URL resource = DataReader.class.getClassLoader().getResource(PRICE_FILE);
            Reader reader = new java.io.FileReader(new File(resource.toURI()));
            Type distanceMapType = new TypeToken<Map<String, List<Destination>>>() {}.getType();
            distanceMatrix= gson.fromJson(reader, distanceMapType);
        }
        return distanceMatrix;
    }

    public static List<Tap> readTaps(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        CSVReader csvReader = new CSVReader(reader);
        return new CsvToBeanBuilder<Tap>(csvReader)
                .withType(Tap.class)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();
    }
}
