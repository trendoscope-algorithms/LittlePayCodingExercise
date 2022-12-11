package com.au.littlepay.utils;

import com.au.littlepay.data.output.Trip;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    private static DataWriter writer;

    private DataWriter(){

    }

    public static DataWriter getInstance(){
        if(writer == null){
            writer = new DataWriter();
        }
        return writer;
    }

    @SuppressWarnings("unchecked")
    public void writeToCsv(List<Trip> trips, String filename) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename));

//        HeaderColumnNameMappingStrategy<Trip> strategy = new HeaderColumnNameMappingStrategy<>();
//        strategy.setType(Trip.class);

        StatefulBeanToCsv<Trip> beanToCsv = new StatefulBeanToCsvBuilder<Trip>(writer)
            .build();
        beanToCsv.write(trips);
        writer.close();
    }
}
