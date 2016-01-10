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
    private boolean productSelected = false;
    private List<Integer> selectedProducts;
    private boolean customerHasMembership = false;
    private final static double DISCOUNT_RATE = 0.95;
    private double totalValue = 0;
    private double discountedAmount = 0;
    private boolean isCustomerSelected = false;
    
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
        
        this.orderItem.setQuantity(quantity);
    }
    
    public void saveOrderItem() {
        
        List<OrderItem> orderItems = this.order.getOrderItems();
        
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        
        orderItems.add(this.orderItem);
        
        this.incrementBillValue(this.orderItem.getQuantity() * this.orderItem.getUnitPrice());
        this.selectedProducts.add(this.orderItem.getProductId());
        this.order.setOrderItems(orderItems);
        this.orderItem = new OrderItem();
        this.productSelected = false;
    }
    
    public OrderItem getOrderItem() {
        return this.orderItem;
    }
    
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
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
        
        this.decrementBillValue(orderItem.getQuantity() * orderItem.getUnitPrice());
        int index = this.selectedProducts.indexOf(orderItem.getProductId());
        this.selectedProducts.remove(index);
    }
    
    public void setCustomer(Customer customer, boolean isNull) {
        this.order.setCustomer(customer);
        
        if(!isNull) {
            this.order.setCustomerId(customer.getId());
            this.setCustomerHasMembership(customer.hasMembership());
            this.isCustomerSelected = true;
        } else {
            this.isCustomerSelected = false;
            this.setCustomerHasMembership(false);
        }
    }
    
    public Customer getSelectedCustomer() {
        return this.order.getCustomer();
    }
    
    public Order getOrder() {
        return this.order;
    }
    
    public void incrementBillValue(double value) {
        if (this.customerHasMembership) {
            double newAmount = value * OrderState.DISCOUNT_RATE;
            this.discountedAmount += (value - newAmount);
            this.totalValue += newAmount;
        } else {
            this.totalValue += value;
        }
    }
    
    public void decrementBillValue(double value) {
        if (this.customerHasMembership) {
            double newAmount = value * OrderState.DISCOUNT_RATE;
            this.discountedAmount -= (value - newAmount);
            this.totalValue -= newAmount;
        } else {
            this.totalValue -= value;
        }
    }
    
    public void setCustomerHasMembership(boolean hasMembership) {
        if(hasMembership && !this.customerHasMembership) {
            double newValue = this.totalValue * OrderState.DISCOUNT_RATE;
            this.discountedAmount = (this.totalValue - newValue);
            this.totalValue = newValue;
            this.order.setHasDiscount(1);
        } else if (!hasMembership && this.customerHasMembership) {
            this.totalValue += this.discountedAmount;
            this.discountedAmount = 0;
            
            this.order.setHasDiscount(0);
        }
        
        this.customerHasMembership = hasMembership;
    }
    
    public double getBillValue() {
        return this.totalValue;
    }
    
    public double getDiscountedAmount() {
        return this.discountedAmount;
    }
    
    public boolean isCustomerSelected() {
        return this.isCustomerSelected;
    }
}
