package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.CategoryService;
import com.example.demoSpringboot.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>("Product valid", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllProduct() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findAllNameProduct")
    public ResponseEntity<?> findAllNameProduct() {
        List<String> listName = productService.findAllNameProduct();
        return new ResponseEntity<>(listName, HttpStatus.OK);
    }

    @GetMapping("/findProductByName")
    public ResponseEntity<?> findProductByName(@RequestParam String name) {
        List<Product> productList = productService.findProductByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByNameOrType")
    public ResponseEntity<?> findAllProductByNameOrType(@RequestParam String search) {
        List<Product> productList = productService.findProductByNameOrType(search);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategoryName")
    public ResponseEntity<?> findProductByCategoryName(@RequestParam String categoryName) {
        List<Product> productList = productService.findAllProductByCategoryName(categoryName);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategoryId/{categoryId}")
    public ResponseEntity<?> findProductByCategoryId(@PathVariable Long categoryId) {
        List<Product> productList = productService.findProductByCategoryId(categoryId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/sumPriceOfProductByCategoryId/{categoryId}")
    public ResponseEntity<?> sumPriceOfProductByCategoryId(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);
        double sum = 0;
        if (!category.isPresent()) {
            return new ResponseEntity<>("Category does not exist", HttpStatus.NOT_FOUND);
        }
        sum = productService.sumPriceOfProductByCategoryId(categoryId);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

    @GetMapping("/avgPriceOfProductByCategoryId/{categoryId}")
    public ResponseEntity<?> avgPriceOfProductByCategoryId(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);
        double avg = 0;
        if (!category.isPresent()) {
            return new ResponseEntity<>("Category does not exist", HttpStatus.NOT_FOUND);
        }
        avg = productService.avgPriceOfProductByCategoryId(categoryId);
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        Optional<Product> productList = productService.findById(id);
        if (!productList.isPresent()) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(product.getId());
        productService.save(product);
        return new ResponseEntity<>("Update completed", HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
    }
}
