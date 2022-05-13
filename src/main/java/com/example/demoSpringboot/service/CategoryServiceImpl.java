package com.example.demoSpringboot.service;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.repo.CategoryRepo;
import com.example.demoSpringboot.service.dto.CategoryDTO;
import com.example.demoSpringboot.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepo categoryRepo;

    CategoryMapper categoryMapper = new CategoryMapper();


    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Category createNewCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findCategoryByCategoryName(String categoryName) {
        return categoryRepo.findCategoryByCategoryName(categoryName);
    }

    @Override
    public List<Category> findCategoryByProductName(String name) {
        return categoryRepo.findCategoryByProductName(name);
    }
}
