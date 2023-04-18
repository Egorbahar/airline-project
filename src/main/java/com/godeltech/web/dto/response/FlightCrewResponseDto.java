package com.godeltech.web.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class FlightCrewResponseDto {
    private final Long id;
    private final String name;
    private final Map<Long,String> employees;
}
