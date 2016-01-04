/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.inventory.exceptions.InvalidArgumentException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hassan
 */
public class OrderState {
    private Order order;
    private OrderItem orderItem;
    private int quantity;
    private boolean productSelected = false;
    private List<Integer> selectedProducts;
    
    public OrderState() {
        this.order = new Order();
        this.orderItem = new OrderItem();
        this.selectedProducts = new ArrayList<>();
    }
    
    public void select(Product product) {
        this.orderItem.setProduct(product);
        this.orderItem.setProductId(product.getId());
        this.productSelected = true;
    }
    
    public void setQuantity(int quantity) throws Exception {
        Product product = this.orderItem.getProduct();
        
        if (quantity > product.getAvailableQuantity()) {
            throw new InvalidArgumentException("You cannot order more units than available.");
        }
        
        this.quantity = quantity;
        this.orderItem.setQuantity(quantity);
    }
    
    public List<OrderItem> saveOrderItem() {
        
        List<OrderItem> orderItems = this.order.getOrderItems();
        
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        
        orderItems.add(this.orderItem);
        
        this.selectedProducts.add(this.orderItem.getProductId());
        this.order.setOrderItems(orderItems);
        this.orderItem = new OrderItem();
        this.productSelected = false;
        this.quantity = 0;
        
        
        return orderItems;
    }
    
    public List<Integer> getSelectedProducts() {
        return this.selectedProducts;
    }
    
    public boolean isProductSelected() {
        return this.productSelected;
    }
    
    public List<OrderItem> getOrderItems() {
        return this.order.getOrderItems();
    }
    
    public void removeOrderItem(OrderItem orderItem) {
        List<OrderItem> orderItems = this.order.getOrderItems();
        orderItems.remove(orderItem);
        
        this.order.setOrderItems(orderItems);
        
        int index = this.selectedProducts.indexOf(orderItem.getProductId());
        this.selectedProducts.remove(index);
    }
    
    public void setCustomer(Customer customer) {
        this.order.setCustomer(customer);
        
        if(customer != null) {
            this.order.setCustomerId(customer.getId());
        }
    }
    
    public Customer getSelectedCustomer() {
        return this.order.getCustomer();
    }
}
