/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import java.time.Instant;
import java.util.List;

/**
 *
 * @author hassan
 */
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    
    public OrderController() {
        this.orderService = new OrderService();
        this.orderItemService = new OrderItemService();
    }
    
    public void save(Order order, int userId) throws Exception {
        order.setUserId(userId);
        order.setDate(Instant.now().getEpochSecond());
        this.orderService.validate(order);
        
        int id = this.orderService.save(order);
        
        for (OrderItem orderItem : order.getOrderItems()) {
           orderItem.setOrderId(id);
           this.orderItemService.validate(orderItem);
        }
        
        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItemService.save(orderItem);
        }
    }
    
    public Order fetch(int id) throws Exception {
        return this.orderService.fetch(id);
    }
    
    public void delete(Order order) throws Exception {
        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItemService.refundItem(orderItem);
        }
        
        this.orderService.remove(order);
    }     
    
    public List<Order> fetchAll() throws Exception {
        return this.orderService.fetchAll();
    }
}
