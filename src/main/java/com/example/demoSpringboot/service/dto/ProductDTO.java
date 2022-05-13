package com.example.demoSpringboot.service.dto;

import com.example.demoSpringboot.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductDTO {
    private long id;
    @Size(message = "Max 255 characters", max = 255)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(message = "Max 255 characters", max = 255)
    private String description;

    @Size(message = "Max 255 characters", max = 255)
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Min(value = 50000, message = "Price can not be less than 50000")
    @Max(value = 500000, message = "Price can not be greater than 500000")
    private double price;

    @ManyToOne
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Category category;
}
