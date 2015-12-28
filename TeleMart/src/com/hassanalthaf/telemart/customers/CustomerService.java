/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

/**
 *
 * @author hassan
 */
public class CustomerService {
    
    private CustomerRepository customerRepository;
    
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }
    
    public void insertNewCustomer(Customer customer) throws Exception {
        
        new CustomerValidator(customer);
        
        this.customerRepository.insert(customer);
        
    }
}
