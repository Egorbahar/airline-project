package com.godeltech.web.dto.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Set;

@Data
public class FlightCrewResponseDto {

    private final String name;
    private final Map<Long,String> employees;

}
