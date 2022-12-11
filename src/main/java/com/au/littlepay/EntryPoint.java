package com.au.littlepay;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.output.Trip;
import com.au.littlepay.service.Processor;
import com.au.littlepay.utils.DataReader;
import com.au.littlepay.utils.DataWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class EntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Tap> taps = DataReader.getInstance().readTaps(args[0]);
        Processor processor = new Processor();
        List<Trip> trips = processor.process(taps);
        DataWriter.getInstance().writeToCsv(trips, args[1]);
        trips.forEach(trip -> System.out.println(trip));
    }
}
