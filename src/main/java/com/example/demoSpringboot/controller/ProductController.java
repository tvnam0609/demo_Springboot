package com.example.demoSpringboot.controller;

import com.example.demoSpringboot.model.Category;
import com.example.demoSpringboot.model.Product;
import com.example.demoSpringboot.service.CategoryService;
import com.example.demoSpringboot.service.ProductService;
import com.example.demoSpringboot.service.dto.ProductDTO;
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

//    @PostMapping
//    public ResponseEntity<?> createNewProduct(@Valid @RequestBody Product product) {
//        Optional<Category> category = categoryService.findById(product.getCategory().getId());
//        if(!category.isPresent()) {
//            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
//        }
//        productService.save(product);
//        Optional<Product> productList = productService.findById(product.getId());
//        return new ResponseEntity<>(productList, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody ProductDTO productDTO) {
        Optional<Category> category = categoryService.findById(productDTO.getCategory().getId());
        if(!category.isPresent()) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        productService.createNewProduct(productDTO);
//        List<Product> productList = productService.findAllProduct();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllProduct(Pageable pageable) {
        Page<Product> productList = productService.findAllProduct(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findProductByName(@RequestParam(required = false) String name, Pageable pageable) {
        Page<Product> productList = productService.findProductByName(name, pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByNameAndType")
    public ResponseEntity<?> findAllProductByNameAndType(@RequestParam(required = false) String name, @RequestParam(required = false) String type, Pageable pageable) {
        Page<Product> productList;
        if (name == null || type == null) {
            return new ResponseEntity<>("Product name and type required", HttpStatus.OK);
        }
        productList= productService.findProductByNameAndType(name,type, pageable);
        if(productList.isEmpty()) {
            return new ResponseEntity<>("No products", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductByCategoryName")
    public ResponseEntity<?> findProductByCategoryName(@RequestParam(required = false) String categoryName, Pageable pageable) {
        Page<Product> productList = productService.findAllProductByCategoryName(categoryName, pageable);
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
