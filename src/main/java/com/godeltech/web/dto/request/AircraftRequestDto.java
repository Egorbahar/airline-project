package com.godeltech.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AircraftRequestDto {
    @NotBlank(message = "{aircraft.name.notBlank}")
    private final String name;
    @NotNull(message = "{aircraft.categoryId.notNull}")
    private final Long categoryId;
}
