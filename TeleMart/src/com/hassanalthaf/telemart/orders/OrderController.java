/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

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
    
    public void save(Order order) throws Exception {
        this.orderService.save(order);
        
        this.orderItemService.saveAll(order.getOrderItems());
    }
}
