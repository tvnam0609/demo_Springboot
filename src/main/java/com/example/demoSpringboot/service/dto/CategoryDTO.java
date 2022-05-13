package com.example.demoSpringboot.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private Long id;

    @Size(message = "Max 255 characters", max = 255)
    @NotBlank(message = "Category name is mandatory")
    private String categoryName;
}
