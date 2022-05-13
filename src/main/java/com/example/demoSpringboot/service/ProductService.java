package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createNewProduct(ProductDTO productDTO);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void remove(Long id);

    List<String> findAllNameProduct();

    Page<Product> findProductByName(String name, Pageable pageable);

    Page<Product> findProductByNameOrType(String search, Pageable pageable);

    Page<Product> findProductByNameAndType(String name, String type, Pageable pageable);

    Page<Product> findAllProductByCategoryName(String categoryName, Pageable pageable);

    Page<Product> findProductByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findAllProduct(Pageable pageable);

    Double sumPriceOfProductByCategoryId(Long categoryId);

    Double avgPriceOfProductByCategoryId(Long categoryId);
}
