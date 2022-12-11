package com.au.littlepay.utils;

import com.au.littlepay.data.pricing.Destination;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class DataReaderTest {

    DataReader reader = DataReader.getInstance();
    @Test
    void getDistanceMatrix() throws IOException, URISyntaxException {
        Map<String, List<Destination>> distanceMatrix1 = reader.getDistanceMatrix();
        Map<String, List<Destination>> distanceMatrix2 = reader.getDistanceMatrix();
        assertNotNull(distanceMatrix1);
        assertEquals(distanceMatrix1, distanceMatrix2);
        assertEquals(distanceMatrix1.hashCode(), distanceMatrix2.hashCode());
        assertEquals(distanceMatrix1.size(), 3);
    }

    @Test
    void readTapsFileNotFound() throws FileNotFoundException {
        assertThrows(FileNotFoundException.class, ()->{
            reader.readTaps("input/taps2.csv");
        }, "Expected result input file not found");
    }

    @Test
    void readTapsValidFile() throws FileNotFoundException {
        assertDoesNotThrow(()->{
            reader.readTaps("input/taps.csv");
        }, "Expected result File Present and no error thrown");
    }
}
