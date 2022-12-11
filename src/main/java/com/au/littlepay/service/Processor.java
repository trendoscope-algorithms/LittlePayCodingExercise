package com.au.littlepay.service;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.output.Trip;
import com.au.littlepay.data.pricing.Destination;
import com.au.littlepay.utils.DataReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Processor {
    private Map<String, List<Destination>> distances;

    public Processor() throws IOException, URISyntaxException {
        distances = DataReader.getDistanceMatrix();
    }
    public List<Trip> process(List<Tap> taps){
        List<Trip> trips = new ArrayList<>();
        Map<String, Tap> openTaps = new HashMap<>();
        taps.stream().forEach(tap -> {
            if(openTaps.keySet().contains(tap.getPan())){
                Tap source = openTaps.remove(tap.getPan());
                List<Destination> destinations = distances.get(source.getStopId().trim());
                if(tap.getBusId().equalsIgnoreCase(source.getBusId()) && tap.getCompanyId().equalsIgnoreCase(source.getCompanyId())){
                    trips.add(new Trip(source, tap, destinations));
                }
                else{
                    trips.add(new Trip(source, destinations));
                    openTaps.put(tap.getPan(), tap);
                }
            }else{
                if(tap.getTapType().trim().equalsIgnoreCase("ON")){
                    openTaps.put(tap.getPan(), tap);
                }else{
                    List<Destination> destinations = distances.get(tap.getStopId().trim());
                    trips.add(new Trip(tap, destinations));
                }
            }
        });
        openTaps.values().forEach(tap -> {
            List<Destination> destinations = distances.get(tap.getStopId().trim());
            trips.add(new Trip(tap, destinations));
        });
        return trips;
    }
}
