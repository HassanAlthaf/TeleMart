/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.inventory.ProductService;
import com.hassanalthaf.telemart.orders.exceptions.NotFoundException;

/**
 *
 * @author hassan
 */
public class OrderItemValidator {
    public OrderItemValidator(OrderItem orderItem) throws Exception {
        this.validateProductId(orderItem.getProductId());
        this.validateOrderId(orderItem.getOrderId());
    }
    
    private void validateProductId(int productId) throws Exception {
        ProductService productService = new ProductService();
        
        if (!productService.doesProductExist(productId)) {
            throw new NotFoundException("The product with code " + productId + " was not found!");
        }
    }
    
    private void validateOrderId(int orderId) throws Exception {
        OrderService orderService = new OrderService();
        
        if (!orderService.doesOrderExist(orderId)) {
            throw new NotFoundException("The product with code " + orderId + " was not found!");
        }
    }
}
