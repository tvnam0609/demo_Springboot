package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
List<Product> findAll();
Optional<Product> findById(Long id);
void save(Product product);
void remove(Long id);
List<String> findAllNameProduct();
List<Product> findProductByName(String name);
List<Product> findProductByNameOrType(String search);

List<Product> findProductByNameAndType(String name, String type);

List<Product> findAllProductByCategoryName(String categoryName);

List<Product> findProductByCategoryId(Long categoryId);

Double sumPriceOfProductByCategoryId(Long categoryId);

Double avgPriceOfProductByCategoryId(Long categoryId);
}
