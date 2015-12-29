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
public class ProductController {
    
    private ProductService productService;
    
    public ProductController() {
        this.productService = new ProductService();
    }
    
    public void addNewProduct(String brand, String model, String colour, double unitPrice, int availableQuantity, String specifications) throws Exception {
        
        this.productService.save(
            new Product(
                brand, model, colour, unitPrice, availableQuantity, specifications
            )
        );
        
    }
    
}
