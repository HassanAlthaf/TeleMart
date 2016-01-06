/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.customers.Customer;
import java.util.List;

/**
 *
 * @author hassan
 */
public class Order {
    private int id;
    private int customerId;
    private int userId;
    private long date;
    private int hasDiscount = 0;
    
    private Customer customer;
    private List<OrderItem> orderItems;
    
    public Order() { }
    
    public Order(int customerId) {
        this.setCustomerId(customerId);
    }
            
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public int getCustomerId() {
        return this.customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return this.userId;
    }

    public void setDate(long date) {
        this.date = date;
    }
        
    public long getDate() {
        return this.date;
    }
    
    public void setHasDiscount(int value) {
        this.hasDiscount = value;
    }
    
    public int getHasDiscount() {
        return this.hasDiscount;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }
}
