package com.example.demoSpringboot.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(message = "Max 255 characters", max = 255)
    @NotBlank(message = "Category name is mandatory")
    private String categoryName;
}
