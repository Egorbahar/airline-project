package com.godeltech.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FlightCrewRequestDto {
    @NotBlank(message = "{flight-crew.name.notBlank}")
    private String name;
}
