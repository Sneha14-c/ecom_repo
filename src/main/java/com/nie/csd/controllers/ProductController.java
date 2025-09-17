package com.nie.csd.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nie.csd.exceptions.IdNotPresentException;
import com.nie.csd.models.Products;
import com.nie.csd.services.ProductService;

@RestController
@RequestMapping("/products")  // base path
public class ProductController {

    Logger logger = Logger.getLogger(ProductController.class.getName());
    @Autowired
    private ProductService service;

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Hello endpoint called");
        return "HELLO";
    }

    // Retrieve all products
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        logger.info("Retreiving all products from the database of collection products");
        List<Products> productsList = service.getAllProducts();
        return ResponseEntity.ok(productsList);
    }

    // Retrieve product by id
    @GetMapping("/{id}")
    public ResponseEntity<Products> getByProductId(@PathVariable("id") String id)
            throws IdNotPresentException {
        Products product = service.getAllProductsId(id);
        return ResponseEntity.ok(product);
    }

    // Add new product
    @PostMapping
    public ResponseEntity<Products> addProduct(@RequestBody Products products) {
        Products saved = service.addProduct(products);
        return ResponseEntity.ok(saved);
    }

    // Update product by id
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") String id,
                                                  @RequestBody Products products) {
        Products updated = service.updateProduct(id, products);
        return ResponseEntity.ok(updated);
    }

    // Delete product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
