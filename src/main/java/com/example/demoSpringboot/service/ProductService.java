package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
List<Product> findAll();
Optional<Product> findById(Long id);
void save(Product product);
void remove(Long id);
List<String> findAllNameProduct();
Page<Product> findProductByName(String name, Pageable pageable);
Page<Product> findProductByNameOrType(String search, Pageable pageable);

List<Product> findProductByNameAndType(String name, String type);

List<Product> findAllProductByCategoryName(String categoryName);

List<Product> findProductByCategoryId(Long categoryId);

Page<Product> findAllProduct(Pageable pageable);

Double sumPriceOfProductByCategoryId(Long categoryId);

Double avgPriceOfProductByCategoryId(Long categoryId);
}
