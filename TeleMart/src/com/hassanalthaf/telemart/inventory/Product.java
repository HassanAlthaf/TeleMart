/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    private int id;
    private StringProperty brand = new SimpleStringProperty();
    private StringProperty model = new SimpleStringProperty();
    private StringProperty colour = new SimpleStringProperty();
    private DoubleProperty unitPrice = new SimpleDoubleProperty();
    private IntegerProperty availableQuantity = new SimpleIntegerProperty();
    private String specifications;
    
    public Product() {}
    
    public Product(String brand, String model, String colour, Double unitPrice, int availableQuantity, String specifications) {
        this.brand.set(brand);
        this.model.set(model);
        this.colour.set(colour);
        this.unitPrice.set(unitPrice);
        this.availableQuantity.set(availableQuantity);
        this.specifications = specifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return this.model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getColour() {
        return this.colour.get();
    }

    public void setColour(String colour) {
        this.colour.set(colour);
    }

    public Double getUnitPrice() {
        return this.unitPrice.get();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public int getAvailableQuantity() {
        return this.availableQuantity.get();
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity.set(availableQuantity);
    }

    public String getSpecifications() {
        return this.specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
