package com.godeltech.web.validator;

import lombok.Data;

@Data
public class Violation {
    private final String fieldName;
    private final String message;
}
