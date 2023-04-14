package com.godeltech.web.dto.response;

import lombok.Data;

@Data
public class FlightResponseDto {
    private final Long id;
    private final String departureDate;
    private final String arrivalDate;
    private final Long flightCrewId;
    private final Long aircraftId;
}
