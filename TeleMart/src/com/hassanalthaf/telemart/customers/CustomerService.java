/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import com.hassanalthaf.telemart.customers.exceptions.CustomerNotFoundException;
import java.util.List;

/**
 *
 * @author hassan
 */
public class CustomerService {
    
    private CustomerRepository customerRepository;
    
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }
    
    public int insertNewCustomer(Customer customer) throws Exception {
        
        new CustomerValidator(customer);
        
        return this.customerRepository.insert(customer);
        
    }
    
    public void save(Customer customer) {
        this.customerRepository.update(customer);
    }
    
    public List<Customer> fetchAllCustomers() {
        return this.customerRepository.fetchAll();
    }
}
