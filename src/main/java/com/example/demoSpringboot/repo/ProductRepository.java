package com.example.demoSpringboot.repo;

import com.example.demoSpringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select name from product", nativeQuery = true)
    List<String> findAllNameProduct();

//    @Query(value = "select * from product where name like CONCAT('%', :name, '%')", nativeQuery = true)
//    List<Product> findProductByName(@Param("name") String name);

    List<Product> findProductByName(String name);

    @Query(value = "select * from product where name like concat('%', :search, '%') or type like concat('%', :search, '%')", nativeQuery = true)
    List<Product> findProductByNameOrType(@Param("search") String search);

}
