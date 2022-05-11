package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>("Category valid", HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/findCategoryByName")
    public ResponseEntity<?> findCategoryByName(@RequestParam String name) {
        List<Category> categoryList = categoryService.findCategoryByCategoryName(name);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryList = categoryService.findById(id);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        Optional<Category> newCategory = categoryService.findById(category.getId());
        categoryService.save(category);
        return new ResponseEntity<>("Update completed", HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.remove(id);
        return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
    }
}
