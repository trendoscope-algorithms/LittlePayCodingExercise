package com.au.littlepay.data.output;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.pricing.Destination;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void createCompletedTrip() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("Stop2", 10.0));
        destinations.add(new Destination("Stop3", 12.0));
        Tap tapStart = new Tap();
        tapStart.setPan("33334444");
        tapStart.setId(1);
        tapStart.setTapType("ON");
        tapStart.setStopId("Stop1");
        tapStart.setCompanyId("Company1");
        tapStart.setBusId("Bus1");
        tapStart.setDateTimeUTC(LocalDateTime.parse("2022-12-11T10:50:55"));
        Tap tapEnd = new Tap();
        tapEnd.setPan("33334444");
        tapEnd.setId(2);
        tapEnd.setStopId("Stop2");
        tapEnd.setTapType("OFF");
        tapEnd.setCompanyId("Company1");
        tapEnd.setBusId("Bus1");
        tapEnd.setDateTimeUTC(LocalDateTime.parse("2022-12-11T11:50:55"));
        Trip trip = new Trip(tapStart, tapEnd, destinations);
        assertNotEquals(trip.getChargeAmount(), 0.0);
        assertNotNull(trip.toString());
    }

    @Test
    void createCancelledTrip() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("Stop2", 10.0));
        destinations.add(new Destination("Stop3", 12.0));
        Tap tapStart = new Tap();
        tapStart.setPan("33334444");
        tapStart.setId(1);
        tapStart.setTapType("ON");
        tapStart.setStopId("Stop1");
        tapStart.setCompanyId("Company1");
        tapStart.setBusId("Bus1");
        tapStart.setDateTimeUTC(LocalDateTime.parse("2022-12-11T10:50:55"));
        Tap tapEnd = new Tap();
        tapEnd.setPan("33334444");
        tapEnd.setId(2);
        tapEnd.setStopId("Stop1");
        tapEnd.setTapType("OFF");
        tapEnd.setCompanyId("Company1");
        tapEnd.setBusId("Bus1");
        tapEnd.setDateTimeUTC(LocalDateTime.parse("2022-12-11T11:50:55"));
        Trip trip = new Trip(tapStart, tapEnd, destinations);
        assertEquals(trip.getChargeAmount().doubleValue(), 0.0);
        assertNotNull(trip.toString());
    }

    @Test
    void createIncompleteTrip() {
        List<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination("Stop2", 10.0));
        destinations.add(new Destination("Stop3", 12.0));
        Tap tapStart = new Tap();
        tapStart.setPan("33334444");
        tapStart.setId(1);
        tapStart.setTapType("ON");
        tapStart.setStopId("Stop1");
        tapStart.setCompanyId("Company1");
        tapStart.setBusId("Bus1");
        tapStart.setDateTimeUTC(LocalDateTime.parse("2022-12-11T10:50:55"));
        Trip trip = new Trip(tapStart, destinations);
        assertEquals(trip.getChargeAmount().doubleValue(), 12.0);
        assertNotNull(trip.toString());
    }

}
