package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void remove(Long id);
    List<Category> findCategoryByCategoryName(String name);
}
