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

    private String name;

    private String description;

    private String type;

    private double price;

    @ManyToOne
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Category category;

}
