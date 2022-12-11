package com.au.littlepay.service;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.output.Trip;
import com.au.littlepay.utils.DataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {
    private Processor processor;
    @BeforeEach
    void init() throws IOException, URISyntaxException {
        processor = new Processor();
    }
    @Test
    void process() throws FileNotFoundException {
        URL resource = ProcessorTest.class.getClassLoader().getResource("input/test1.csv");
        List<Tap> taps = DataReader.readTaps(resource.getPath());
        List<Trip> trips = processor.process(taps);
        assertEquals(trips.size(), 4);
    }

    @Test
    void processInconsistentTaps() throws FileNotFoundException {
        URL resource = ProcessorTest.class.getClassLoader().getResource("input/test2.csv");
        List<Tap> taps = DataReader.readTaps(resource.getPath());
        List<Trip> trips = processor.process(taps);
        assertEquals(trips.size(), 7);
    }

}
