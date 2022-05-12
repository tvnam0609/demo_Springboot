package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<String> findAllNameProduct() {
        return productRepository.findAllNameProduct();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> findProductByNameOrType(String search) {
        return productRepository.findProductByNameOrType(search);
    }

    @Override
    public List<Product> findAllProductByCategoryName(String categoryName) {
        return productRepository.findAllProductByCategoryName(categoryName);
    }

    @Override
    public List<Product> findProductByCategoryId(Long categoryId) {
        return productRepository.findProductByCategoryId(categoryId);
    }

    @Override
    public Double sumPriceOfProductByCategoryId(Long categoryId) {
        return productRepository.sumPriceOfProductByCategoryId(categoryId);
    }

    @Override
    public Double avgPriceOfProductByCategoryId(Long categoryId) {
        return productRepository.avgPriceOfProductByCategoryId(categoryId);
    }


}
