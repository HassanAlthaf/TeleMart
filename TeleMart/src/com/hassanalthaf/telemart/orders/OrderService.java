/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.customers.CustomerRepository;
import com.hassanalthaf.telemart.users.UserRepository;
import java.util.List;

/**
 *
 * @author hassan
 */
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderItemService orderItemService;
    private UserRepository userRepository;
    
    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.customerRepository = new CustomerRepository();
        this.orderItemService = new OrderItemService();
        this.userRepository = new UserRepository();
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
    
    public List<Order> fetchAll() throws Exception {
        List<Order> orders = this.orderRepository.fetchAll();

        for (int iterator = 0; iterator < orders.size(); iterator++) {
            orders.get(iterator).setCustomer(this.customerRepository.fetchCustomer(orders.get(iterator).getCustomerId()));
            orders.get(iterator).setOrderItems(this.orderItemService.fetchByOrderId(orders.get(iterator).getId()));
            orders.get(iterator).setUser(this.userRepository.fetch(orders.get(iterator).getUserId()));
        }
        
        return orders;
    }
}
