package com.example.demoSpringboot.repo;

import com.example.demoSpringboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select name from product", nativeQuery = true)
    List<String> findAllNameProduct();


//    @Query(value = "select * from product where :name is null or name like CONCAT('%', :name, '%')", nativeQuery = true)
//    List<Product> findProductByName(@Param("name") String name);

    @Query(value = "select * from product where :name is null or product.name like CONCAT('%', :name, '%')"
            , countQuery = "select count(*) from product where :name is null or product.name like CONCAT('%', :name, '%') ", nativeQuery = true)
    Page<Product> findProductByName(@Param("name") String name, Pageable pageable);


//    List<Product> findProductByName(String name);

    @Query(value = "select * from product where :search is null or name like concat('%', :search, '%') or type like concat('%', :search, '%')",
            countQuery = "select count(*) from product where :search is null or name like concat('%', :search, '%') or type like concat('%', :search, '%')",nativeQuery = true)
    Page<Product> findProductByNameOrType(@Param("search") String search, Pageable pageable);

    @Query(value = "select * from product where name like concat('%', :name, '%') and type like concat('%', :type, '%')",
            countQuery = "select count(*) from product where name like concat('%', :name, '%') and type like concat('%', :type, '%')", nativeQuery = true)
    Page<Product> findProductByNameAndType(@Param("name") String name, @Param("type") String type, Pageable pageable);

    @Query(value = "select * from product p join category c on p.category_id = c.id and :categoryName is null or c.category_name like CONCAT('%', :categoryName, '%')",
            countQuery = "select count(*) from product p join category c on p.category_id = c.id and :categoryName is null or c.category_name like CONCAT('%', :categoryName, '%')",nativeQuery = true)
    Page<Product> findAllProductByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query(value = "select * from product p join category c on p.category_id = c.id and c.id = :categoryId",
            countQuery = "select count(*) from product p join category c on p.category_id = c.id and c.id = :categoryId",nativeQuery = true)
    Page<Product> findProductByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query(value = "select sum(p.price) from category c join product p on c.id = p.category_id and c.id = :categoryId", nativeQuery = true)
    Double sumPriceOfProductByCategoryId (@Param("categoryId") Long categoryId);

    @Query(value = "select round(avg(p.price), 2) from category c join product p on c.id = p.category_id and c.id = :categoryId", nativeQuery = true)
    Double avgPriceOfProductByCategoryId (@Param("categoryId") Long categoryId);

}
