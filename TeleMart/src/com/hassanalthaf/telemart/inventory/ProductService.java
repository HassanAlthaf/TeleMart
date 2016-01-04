/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

import com.hassanalthaf.telemart.inventory.exceptions.ProductNotFoundException;
import java.util.List;

/**
 *
 * @author hassan
 */
public class ProductService {
    
    private ProductRepository productRepository;
    
    public ProductService() {
        this.productRepository = new ProductRepository();
    }
    
    public void save(Product product) throws Exception {
        new ProductValidator(product);
        
        this.productRepository.insertProduct(product);
    }
    
    public void update(Product product) throws Exception {
        new ProductValidator(product);
        
        this.productRepository.update(product);
    }
    
    public List<Product> fetchAllProducts() {
        return this.productRepository.fetchAllProducts();
    }
    
    public Product fetchProduct(int id) throws ProductNotFoundException {
        return this.productRepository.fetchProduct(id);
    }
    
    public void delete(int id) {
        this.productRepository.delete(id);
    }
    
    public boolean doesProductExist(int id) {
        try {
            this.productRepository.fetchProduct(id);       
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
