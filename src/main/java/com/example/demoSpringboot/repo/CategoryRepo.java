package com.example.demoSpringboot.repo;

import com.example.demoSpringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findCategoryByCategoryName(String name);
}
