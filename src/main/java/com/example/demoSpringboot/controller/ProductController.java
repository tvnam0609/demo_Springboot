package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.CategoryService;
import com.example.demoSpringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
        Optional<Category> category = categoryService.findById(product.getCategory().getId());
        if(!category.isPresent()) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        productService.save(product);
        Optional<Product> productList = productService.findById(product.getId());
        return new ResponseEntity<>(productList, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findAllProduct")
    public ResponseEntity<?> findAllProduct(Pageable pageable) {
        Page<Product> productList = productService.findAllProduct(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findAllNameProduct")
    public ResponseEntity<?> findAllNameProduct() {
        List<String> listName = productService.findAllNameProduct();
        return new ResponseEntity<>(listName, HttpStatus.OK);
    }

    @GetMapping("/findProductByName")
    public ResponseEntity<?> findProductByName(@RequestParam(required = false) String name, Pageable pageable) {
        Page<Product> productList = productService.findProductByName(name, pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByNameOrType")
    public ResponseEntity<?> findAllProductByNameOrType(@RequestParam(required = false) String search, Pageable pageable) {
        Page<Product> productList;
        if (search == null) {
            productList = productService.findAllProduct(pageable);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
        productList= productService.findProductByNameOrType(search, pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByNameAndType")
    public ResponseEntity<?> findAllProductByNameAndType(@RequestParam(required = false) String name, @RequestParam(required = false) String type) {
        List<Product> productList;
        if (name == null || type == null) {
            return new ResponseEntity<>("Product name and type required", HttpStatus.OK);
        }
        productList= productService.findProductByNameAndType(name,type);
        if(productList.isEmpty()) {
            return new ResponseEntity<>("No products", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategoryName")
    public ResponseEntity<?> findProductByCategoryName(@RequestParam(required = false) String categoryName) {
        List<Product> productList;
        if (categoryName == null) {
            productList = productService.findAll();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
        productList = productService.findAllProductByCategoryName(categoryName);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategoryId/{categoryId}")
    public ResponseEntity<?> findProductByCategoryId(@PathVariable(required = false) Long categoryId) {
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
