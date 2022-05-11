package com.example.demoSpringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
