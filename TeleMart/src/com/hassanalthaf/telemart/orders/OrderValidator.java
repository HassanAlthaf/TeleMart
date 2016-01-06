/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.customers.CustomerService;
import com.hassanalthaf.telemart.customers.exceptions.CustomerNotFoundException;
import com.hassanalthaf.telemart.users.UserRepository;
import com.hassanalthaf.telemart.users.exceptions.UserNotFoundException;

/**
 *
 * @author hassan
 */
public class OrderValidator {
    public OrderValidator(Order order) throws Exception {
        this.validateCustomer(order.getCustomerId());
        this.validateUser(order.getUserId());
    }
    
    public void validateCustomer(int customerId) throws CustomerNotFoundException {
        CustomerService customerService = new CustomerService();
        
        if (!customerService.doesCustomerExist(customerId)) {
            throw new CustomerNotFoundException("The customer you selected does not exist!");
        }
    }
    
        public void validateUser(int userId) throws UserNotFoundException {
        UserRepository userRepository = new UserRepository();
        
        try {
            userRepository.fetch(userId);
        } catch(UserNotFoundException exception) {
            throw new UserNotFoundException("The operator details were not found!");
        }
    }
}
