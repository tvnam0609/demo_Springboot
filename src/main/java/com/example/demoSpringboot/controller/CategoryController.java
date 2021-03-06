package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.CategoryService;
import com.example.demoSpringboot.service.ProductService;
import com.example.demoSpringboot.service.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.createNewCategory(categoryDTO);
        return new ResponseEntity<>("Category valid", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findCategoryByName(@RequestParam(required = false) String categoryName) {
        List<Category> categoryList = categoryService.findCategoryByCategoryName(categoryName);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/findCategoryByProductName")
    public ResponseEntity<?> findCategoryByProductName(@RequestParam(required = false) String name) {
        List<Category> categoryList;
        if(name == null) {
            categoryList = categoryService.findAll();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
        categoryList = categoryService.findCategoryByProductName(name);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryList = categoryService.findById(id);
        if(!categoryList.isPresent()) {
            return new ResponseEntity<>("Category does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        Optional<Category> newCategory = categoryService.findById(category.getId());
        categoryService.save(category);
        return new ResponseEntity<>("Update completed", HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id, Pageable pageable) {
        Page<Product> productList = productService.findProductByCategoryId(id, pageable);
        if(!productList.isEmpty()) {
            return new ResponseEntity<>("Category ??ang ???????c s??? d???ng", HttpStatus.NO_CONTENT);
        }
        categoryService.remove(id);
        return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
    }
}
