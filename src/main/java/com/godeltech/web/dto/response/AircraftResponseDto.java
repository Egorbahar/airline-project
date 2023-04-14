package com.godeltech.web.dto.response;

import lombok.Data;

@Data
public class AircraftResponseDto {
    private final Long id;
    private final String name;
    private final Long categoryId;
    private final String categoryName;
}
