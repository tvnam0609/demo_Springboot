package com.example.demoSpringboot.repo;

import com.example.demoSpringboot.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "select * from category where :categoryName is null or category.category_name like CONCAT('%', :categoryName, '%')"
            , countQuery = "select count(*) from category where :categoryName is null or category.category_name like CONCAT('%', :categoryName '%') ", nativeQuery = true)
    List<Category> findCategoryByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select * from category c join product p on c.id = p.category_id and p.name like concat('%', :name, '%') limit 1", nativeQuery = true)
    List<Category> findCategoryByProductName(@Param("name") String name);
}
