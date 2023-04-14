package com.godeltech.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeeRequestDto {
    @NotBlank(message = "{employee.name.notBlank}")
    private String name;
    @NotBlank(message = "{employee.surname.notBlank}")
    private String surname;
    @NotBlank(message = "{employee.title.notBlank}")
    private String title;
}
