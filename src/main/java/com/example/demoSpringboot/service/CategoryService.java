package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void remove(Long id);

    Category createNewCategory(CategoryDTO categoryDTO);

    List<Category> findCategoryByCategoryName(String categoryName);
    List<Category> findCategoryByProductName(String name);
}
