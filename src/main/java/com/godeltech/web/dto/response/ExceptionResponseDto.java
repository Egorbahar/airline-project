package com.godeltech.web.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponseDto {
    private final HttpStatus httpStatus;
    private final String message;
}