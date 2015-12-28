/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import com.hassanalthaf.telemart.DatabaseDriver;

/**
 *
 * @author hassan
 */
public class CustomerRepository {
    private DatabaseDriver databaseDriver;
    
    public CustomerRepository() {
        this.databaseDriver = new DatabaseDriver();
    }
    
    public void insert(Customer customer) {
        //this.databaseDriver.openSession().save(customer);
        System.out.println("Inserted!");
    }
}
