package com.au.littlepay.data.input;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Tap {
    private int id;
    @CsvDate(value =" dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTimeUTC;
    private String tapType;
    private String stopId;
    private String companyId;
    private String busId;
    private String pan;
}
