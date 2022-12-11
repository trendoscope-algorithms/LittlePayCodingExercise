package com.au.littlepay;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.output.Trip;
import com.au.littlepay.service.Processor;
import com.au.littlepay.utils.DataReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class EntryPoint {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Tap> taps = DataReader.readTaps("input/taps.csv");
        Processor processor = new Processor();
        List<Trip> trips = processor.process(taps);
        trips.forEach(trip -> System.out.println(trip));
    }
}
