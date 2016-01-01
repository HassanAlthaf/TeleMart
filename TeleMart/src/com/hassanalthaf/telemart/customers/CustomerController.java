/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hassan
 */
public class CustomerController {
    
    private CustomerService customerService;
    
    public CustomerController() {
        this.customerService = new CustomerService();
    }
    
    public void addNewCustomer(String nicNumber, boolean membership, String name, int contactNumber, String address, String emailAddress) throws Exception {
        
        int membershipNumber = 0;
        
        Customer customer = new Customer(nicNumber, membershipNumber, name, contactNumber, address, emailAddress);
       
        int id = this.customerService.insertNewCustomer(customer);
        
        if (membership) {
            customer.setId(id);
            customer.setMembershipNumber(this.generateCustomerMembershipNumber(id));
            
            this.customerService.save(customer);
        }
    }
    
    public int generateCustomerMembershipNumber(int id) {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        return Integer.parseInt(dateFormat.format(new Date()) + id);
    }
    
    public List<Customer> fetchAllCustomers() {
        return this.customerService.fetchAllCustomers();
    }
}
