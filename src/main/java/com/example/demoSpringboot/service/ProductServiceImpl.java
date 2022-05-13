package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> findProductByName(String name, Pageable pageable) {
        return productRepository.findProductByName(name, pageable);
    }

    @Override
    public Page<Product> findProductByNameOrType(String search, Pageable pageable) {
        return productRepository.findProductByNameOrType(search,pageable);
    }

    @Override
    public List<Product> findProductByNameAndType(String name, String type) {
        return productRepository.findProductByNameAndType(name, type);
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
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
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
