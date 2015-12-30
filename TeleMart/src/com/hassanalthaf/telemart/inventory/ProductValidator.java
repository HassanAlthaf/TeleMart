/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

import com.hassanalthaf.telemart.inventory.exceptions.InvalidLengthException;
import com.hassanalthaf.telemart.inventory.exceptions.InvalidArgumentException;

/**
 *
 * @author hassan
 */
class ProductValidator {
    
    public ProductValidator(Product product) throws Exception {
        this.validateBrand(product.getBrand());
        this.validateModel(product.getModel());
        this.validateColour(product.getColour());
        this.validateUnitPrice(product.getUnitPrice());
        this.validateAvailableQuantity(product.getAvailableQuantity());
        this.validateSpecifications(product.getSpecifications());
    }
    
    public void validateBrand(String brand) throws InvalidLengthException {
        if (brand.length() < 3 || brand.length() > 50) {
            throw new InvalidLengthException("Brand name can be 3 to 50 characters only.");
        }
    }
    
    public void validateModel(String model) throws InvalidLengthException {
        if (model.length() < 3 || model.length() > 50) {
            throw new InvalidLengthException("Model name can be 3 to 50 characters only.");
        }
    }
    
    public void validateColour(String colour) throws InvalidLengthException {
        if (colour.length() < 3 || colour.length() > 50) {
            throw new InvalidLengthException("Colour can be 3 to 50 characters only.");
        }
    }
    
    public void validateUnitPrice(double unitPrice) throws InvalidArgumentException {
        if (unitPrice < 0 || unitPrice > 999999999 || unitPrice == 0) {
            throw new InvalidArgumentException("Invalid unit price entered.");
        }
    }
    
    public void validateAvailableQuantity(int availableQuantity) throws InvalidArgumentException {
        if (availableQuantity < 0 || availableQuantity > 999999999) {
            throw new InvalidArgumentException("Invalid quantity entered.");
        }
    }
    
    public void validateSpecifications(String specifications) throws InvalidArgumentException {
        if (specifications.length() < 10 || specifications.length() > 300) {
            throw new InvalidArgumentException("The specification can be 10 to 300 characters only.");
        }
    }
}
