package com.godeltech.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.godeltech.web.validator.DateValidation;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
public class FlightRequestDto {
    @NotNull(message = "{flight.aircraft.notNull}")
    private Long aircraftId;
    @NotNull(message = "{flight.flight-crew.notNull}")
    private Long flightCrewId;
    @NotNull(message = "{flight.departureDate.notNull}")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateValidation
    private String departureDate;
    @NotNull(message = "{flight.arrivalDate.notNull}")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateValidation
    private String arrivalDate;
}
