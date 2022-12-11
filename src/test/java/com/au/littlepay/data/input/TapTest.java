package com.au.littlepay.data.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TapTest {
    private Tap tap;
    @BeforeEach
    void init(){
        tap = new Tap();
        tap.setPan("33334444");
        tap.setId(1);
        tap.setTapType("ON");
        tap.setStopId("Stop1");
        tap.setCompanyId("Company1");
        tap.setBusId("Bus1");
        tap.setDateTimeUTC(LocalDateTime.parse("2022-12-11T10:50:55"));
    }
    @Test
    void getId() {
        assertEquals(tap.getId(), 1);
    }

    @Test
    void getTapType() {
        assertEquals(tap.getTapType(), "ON");
    }
}
