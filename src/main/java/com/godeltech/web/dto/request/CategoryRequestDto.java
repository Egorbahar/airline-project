package com.godeltech.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "{category.name.notBlank}")
    private String name;
}
