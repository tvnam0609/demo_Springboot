package com.example.demoSpringboot.service.mapper;

import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.dto.ProductDTO;

public class ProductMapper {
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setType(productDTO.getType());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        return product;
    }
}
