/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

import com.hassanalthaf.telemart.DatabaseDriver;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hassan
 */
public class ProductRepository {

    private DatabaseDriver databaseDriver;
    
    public ProductRepository() {
        this.databaseDriver = new DatabaseDriver();
    }
    
    public void insertProduct(Product product) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
    }
    
}
