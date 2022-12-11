package com.au.littlepay.data.output;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.pricing.Destination;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@ToString
public class Trip {
    public enum Status {
        COMPLETED,
        INCOMPLETE,
        CANCELLED
    }
    private LocalDateTime started;
    private LocalDateTime finished;
    private Long durationSeconds;
    private String fromStopId;
    private String toStopId;
    private Double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private Status status;

    public Trip(Tap source, Tap destination, List<Destination> destinationPrices){
        started = source.getDateTimeUTC();
        finished = destination.getDateTimeUTC();
        durationSeconds = finished.toEpochSecond(ZoneOffset.UTC)-started.toEpochSecond(ZoneOffset.UTC);
        fromStopId = source.getStopId();
        toStopId = destination.getStopId();
        companyId = source.getCompanyId();
        busId = source.getBusId();
        pan = source.getPan();
        chargeAmount = fromStopId.trim().equals(toStopId.trim())? 0.0 : getPrice(destinationPrices, destination);
        status = fromStopId.trim().equals(toStopId.trim())? Status.CANCELLED : Status.COMPLETED;
    }

    public Trip(Tap source, List<Destination> destinationPrices){
        started = source.getDateTimeUTC();
        fromStopId = source.getStopId();
        companyId = source.getCompanyId();
        busId = source.getBusId();
        pan = source.getPan();
        status = Status.INCOMPLETE;
        chargeAmount = getMaxPrice(destinationPrices);
    }

    private Double getMaxPrice(List<Destination> destinationPrices){
        return destinationPrices.stream()
                .mapToDouble(destination -> destination.getPrice())
                .max().getAsDouble();
    }

    private Double getPrice(List<Destination> destinationPrices, Tap destination){
        Map<String, Destination> destinationMap = destinationPrices.stream()
                .collect(Collectors.toMap(Destination::getDestinationStation, Function.identity()));
        return destinationMap.get(destination.getStopId().trim()).getPrice();
    }
}
