/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.inventory.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author hassan
 */
public class OrderItem {
    private int id;
    private int productId;
    private int orderId;
    private Product product;
    private IntegerProperty quantity = new SimpleIntegerProperty();
    
    public OrderItem() { }
    
    public OrderItem(int productId, int orderId, int quantity) {
        this.setProductId(productId);
        this.setOrderId(orderId);
        this.setQuantity(quantity);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getQuantity() {
        return this.quantity.get();
    }
    
    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
    
    public String getName() {
        return this.product.getBrand() + " " + this.product.getModel();
    }
    
    public double getUnitPrice() {
        return this.product.getUnitPrice();
    }
    
    public String getTotalValue() {
        return String.valueOf(this.getQuantity() * this.getUnitPrice());
    }
}
