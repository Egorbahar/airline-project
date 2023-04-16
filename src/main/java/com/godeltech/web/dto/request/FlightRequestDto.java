package com.godeltech.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FlightRequestDto {
    @NotNull(message = "{flight.aircraft.notNull}")
    private Long aircraftId;
    @NotNull(message = "{flight.flight-crew.notNull}")
    private Long flightCrewId;
    private String departureDate;
    private String arrivalDate;
}