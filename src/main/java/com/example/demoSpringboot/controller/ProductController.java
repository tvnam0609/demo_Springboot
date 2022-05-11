package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        Optional<Product> productList = productService.findById(id);
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
