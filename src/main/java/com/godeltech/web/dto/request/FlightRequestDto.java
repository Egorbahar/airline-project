package com.godeltech.web.dto.request;

import com.godeltech.web.validator.annotation.DateValidation;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FlightRequestDto {
    @NotNull(message = "{flight.aircraft.notNull}")
    private Long aircraftId;
    @NotNull(message = "{flight.flight-crew.notNull}")
    private Long flightCrewId;
    @NotNull(message = "{flight.departureDate.notNull}")
    @DateValidation
    private String departureDate;
    @NotNull(message = "{flight.arrivalDate.notNull}")
    @DateValidation
    private String arrivalDate;
}
