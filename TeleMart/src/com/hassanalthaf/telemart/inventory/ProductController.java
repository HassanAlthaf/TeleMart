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
    
    public void updateProduct(int id, String brand, String model, String colour, double unitPrice, int availableQuantity, String specifications) throws Exception {
        
        Product product = new Product(brand, model, colour, unitPrice, availableQuantity, specifications);
        
        product.setId(id);
        
        this.productService.update(product);
        
    }
    
    public List<Product> fetchAllProducts() {
        return this.productService.fetchAllProducts();
    }
    
    public List<Product> fetchExcluding(List<Integer> selectedProducts) {
        return this.productService.fetchExcluding(selectedProducts);
    }
    
    public Product fetchProduct(int id) throws ProductNotFoundException {
        return this.productService.fetchProduct(id);
    }
    
    public void deleteProduct(int id) {
        this.productService.delete(id);
    }
}
