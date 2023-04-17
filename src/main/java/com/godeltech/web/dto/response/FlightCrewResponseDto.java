package com.godeltech.web.dto.response;

import com.godeltech.persistence.model.Employee;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Set;

@Data
public class FlightCrewResponseDto {
    private final Long id;
    private final String name;
    private final Set<Employee> employees;
}
