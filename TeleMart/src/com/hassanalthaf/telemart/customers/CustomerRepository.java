/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import com.hassanalthaf.telemart.DatabaseDriver;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hassan
 */
public class CustomerRepository {
    private DatabaseDriver databaseDriver;
    
    public CustomerRepository() {
        this.databaseDriver = new DatabaseDriver();
    }
    
    public int insert(Customer customer) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        
        return customer.getId();
    }
    
    public void update(Customer customer) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
    }
    
    public boolean isNicNumberTaken(String nicNumber) {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(Customer.class);
        
        criteria.add(Restrictions.eq("nicNumber", nicNumber));
        
        List<Customer> customers = criteria.list();
        
        if (customers.size() < 1) {
            return false;
        }
        
        return true;
    }
}
