package com.example.demoSpringboot.service.mapper;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.service.dto.CategoryDTO;

public class CategoryMapper {
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }
}
