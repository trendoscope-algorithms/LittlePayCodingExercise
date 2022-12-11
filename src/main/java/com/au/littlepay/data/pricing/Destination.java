package com.au.littlepay.data.pricing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Destination {
    private String destinationStation;
    private Double price;
}
