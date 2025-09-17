package com.nie.csd.services;

import java.util.List;
// import java.util.logging.Logger; // Removed to avoid collision with org.slf4j.Logger

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nie.csd.exceptions.IdNotPresentException;
import com.nie.csd.models.Products;
import com.nie.csd.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
    
    Logger logger = LoggerFactory.getLogger(ProductService.class); //this is important to define first

    @Autowired

    private ProductRepository repository;

    public List<Products> getAllProducts() {
        logger.info("retrieving all products from the database of collection products");
        return repository.findAll();
    }
    public Products getAllProductsId(String id)throws IdNotPresentException {
        logger.debug("Retrueving product with id:{} from the dattabase"+"of collection products",id);
        return repository.findById(id)
        .orElseThrow(()->{
            logger.error("Product not found with id: {}",id);
            return new IdNotPresentException("Product not found with id: "+id);
        });
    }
    public Products addProduct(Products product) {
        logger.info("Adding new product to the database of collection products");
        return repository.save(product);
    }
    public Products getByProductId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByProductId'");
    }
    //update product by id if not adding the new product
    public Products updateProduct(String id, Products products) {
       Products existingproduct=repository.findById(id).get();
      if(existingproduct!=null){
        existingproduct.setName(products.getName());
        existingproduct.setDescription(products.getDescription());
        existingproduct.setCategory(products.getCategory());
        existingproduct.setTags(products.getTags());
        existingproduct.setPrice(products.getPrice());
        existingproduct.setStock(products.getStock());
        return repository.save(products);

       }
        return repository.save(products);
    }
    public void deleteProduct(String id) {
        Products existingproduct=repository.findById(id).get();
        if (existingproduct!=null){
            repository.deleteById(id);
            System.out.println("Product deleted successfully");
        }
        else{
            System.out.println("Product not found");
        }
        
    }
}