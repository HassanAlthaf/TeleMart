/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import java.util.List;

/**
 *
 * @author hassan
 */
public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    
    public OrderItemService() {
        this.orderItemRepository = new OrderItemRepository();
    }
    
    public void saveAll(List<OrderItem> orderItems) throws Exception {
        for (OrderItem item : orderItems) {
            new OrderItemValidator(item);
            
            this.orderItemRepository.insert(item);
        }
    }
}
