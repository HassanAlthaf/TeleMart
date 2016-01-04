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
        
        new CustomerValidator(customer, false, null);
        
        return this.customerRepository.insert(customer);
        
    }
    
    public void save(Customer customer) throws Exception {
        
        Customer originalCustomerData = this.customerRepository.fetchCustomer(customer.getId());
        
        new CustomerValidator(customer, true, originalCustomerData.getNicNumber());
        
        this.customerRepository.update(customer);
    }
    
    public Customer fetch(int id) throws Exception {
        return this.customerRepository.fetchCustomer(id);
    }
    
    public List<Customer> fetchAllCustomers() {
        return this.customerRepository.fetchAll();
    }
    
    public void deleteCustomer(int id) {
        this.customerRepository.delete(id);
    }
    
    public boolean doesCustomerExist(int id) {
        try {
            this.fetch(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
