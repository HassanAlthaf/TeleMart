/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.users.User;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private User operator;
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
        
    public String getDate() {
        Date date = new Date(this.date * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM YYYY");
        return simpleDateFormat.format(date);
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
    
    public void setUser(User user) {
        this.operator = user;
    }
    
    public User getUser() {
        return this.operator;
    }
    
    public String getOperatorName() {
        return this.operator.getUsername();
    }
    
    public String getCustomerNIC() {
        return this.customer.getNicNumber();
    }
    
    public String getBillValue() {
        double discountRate = 1;
        double total = 0;
        
        if (this.hasDiscount == 1) {
            discountRate = 0.95;
        }
        
        for (int iterator = 0; iterator < this.orderItems.size(); iterator++) {
            total += (this.orderItems.get(iterator).getQuantity() * this.orderItems.get(iterator).getUnitPrice()) * discountRate;
        }

        
        return String.format("%.2f", total);
    }
}
