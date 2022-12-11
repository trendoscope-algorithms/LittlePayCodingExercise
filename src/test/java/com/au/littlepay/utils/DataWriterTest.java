package com.au.littlepay.utils;
import com.au.littlepay.data.output.Trip;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class DataWriterTest {
    DataWriter writer = DataWriter.getInstance();

    @Test
    void writeToCsv() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        writer = DataWriter.getInstance();
        List<Trip> trips = new ArrayList<>();
        writer.writeToCsv(trips, "output/trips_test.csv");
    }
}
