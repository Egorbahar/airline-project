package com.godeltech.web.dto.response;

import lombok.Data;

@Data
public class EmployeeResponseDto {
    private final Long id;
    private final String name;
    private final String surname;
    private final String title;
    private final Long flightCrewId;
}
