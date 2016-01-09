/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.inventory.ProductService;
import java.util.List;

/**
 *
 * @author hassan
 */
public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    private ProductService productService;
    
    public OrderItemService() {
        this.orderItemRepository = new OrderItemRepository();
        this.productService = new ProductService();
    }
    
    public void validate(OrderItem orderItem) throws Exception {
        Product product = this.productService.fetchProduct(orderItem.getProductId());
        product.setAvailableQuantity(product.getAvailableQuantity() - orderItem.getQuantity());
        this.productService.update(product);
        new OrderItemValidator(orderItem);
    }
    
    public void save(OrderItem orderItem)  {
        this.orderItemRepository.insert(orderItem);
    }
    
    public List<OrderItem> fetchByOrderId(int orderId) {
        List<OrderItem> orderItems = this.orderItemRepository.fetchByOrderId(orderId);
        
        for (int iterator = 0; iterator < orderItems.size(); iterator++) {
            OrderItem orderItem = orderItems.get(iterator);
            
            try {
                orderItem.setProduct(this.productService.fetchProduct(orderItem.getProductId()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        
        return orderItems;
    }
}
