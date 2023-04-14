package com.godeltech.web.dto.response;

import com.godeltech.web.validator.Violation;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
public class ErrorResponseDto {
    private final HttpStatus httpStatus;
    private final List<Violation> violations;
}
