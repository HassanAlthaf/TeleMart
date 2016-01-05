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
public class OrderService {
    private OrderRepository orderRepository;
    
    public OrderService() {
        this.orderRepository = new OrderRepository();
    }
    
    public void validate(Order order) throws Exception {
        new OrderValidator(order);
    }
    
    public int save(Order order) {
        return this.orderRepository.insert(order);
    }
    
    public boolean doesOrderExist(int id) {
        try {
            this.orderRepository.fetch(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
