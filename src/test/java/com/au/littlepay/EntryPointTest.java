package com.au.littlepay;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.service.Processor;
import com.au.littlepay.utils.DataReader;
import com.au.littlepay.utils.DataWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EntryPointTest {

    @Mock
    DataWriter mockWriter = mock(DataWriter.class);

    @Mock
    DataReader mockReader = mock(DataReader.class);

    @Mock
    Processor mockProcessor = mock(Processor.class);

    @InjectMocks
    EntryPoint entryPoint;

    @Test
    void testEntryPointMain() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, URISyntaxException {
        entryPoint = new EntryPoint();
        assertNotNull(entryPoint);
        doNothing().when(mockWriter).writeToCsv(anyList(),anyString());
        when(mockReader.readTaps(anyString())).thenReturn(new ArrayList<>());

        when(mockProcessor.process(anyList())).thenReturn(new ArrayList<>());
        entryPoint.main(new String[]{"input/taps.csv", "output/trips_test.csv"});
    }
}
