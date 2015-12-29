/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

/**
 *
 * @author hassan
 */
class ProductService {
    
    private ProductRepository productRepository;
    
    public ProductService() {
        this.productRepository = new ProductRepository();
    }
    
    public void save(Product product) throws Exception {
        new ProductValidator(product);
        
        this.productRepository.insertProduct(product);
    }
    
}
