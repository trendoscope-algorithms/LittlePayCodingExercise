package com.au.littlepay.data.output;

import com.au.littlepay.data.input.Tap;
import com.au.littlepay.data.pricing.Destination;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
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
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Started")
    private LocalDateTime started;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Finished")
    private LocalDateTime finished;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "DurationSecs")
    private Long durationSeconds;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "FromStopId")
    private String fromStopId;
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "ToStopId")
    private String toStopId;
    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "ChargeAmount")
    private Double chargeAmount;
    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "CompanyId")
    private String companyId;
    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "BusID")
    private String busId;
    @CsvBindByPosition(position = 8)
    @CsvBindByName(column = "PAN")
    private String pan;
    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "Status")
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
                .mapToDouble(Destination::getPrice)
                .max().getAsDouble();
    }

    private Double getPrice(List<Destination> destinationPrices, Tap destination){
        Map<String, Destination> destinationMap = destinationPrices.stream()
                .collect(Collectors.toMap(Destination::getDestinationStation, Function.identity()));
        return destinationMap.get(destination.getStopId().trim()).getPrice();
    }
}
