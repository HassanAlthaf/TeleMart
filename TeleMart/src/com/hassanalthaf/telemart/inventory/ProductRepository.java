/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.inventory;

import com.hassanalthaf.telemart.DatabaseDriver;
import com.hassanalthaf.telemart.inventory.exceptions.ProductNotFoundException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hassan
 */
public class ProductRepository {

    private DatabaseDriver databaseDriver;
    
    public ProductRepository() {
        this.databaseDriver = DatabaseDriver.getInstance();
    }
    
    public void insertProduct(Product product) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        
        session.close();
    }
    
    public void update(Product product) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        
        session.close();
    }
    
    public Product fetchProduct(int id) throws ProductNotFoundException {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id", id));
        
        List<Product> product = criteria.list();
        
        if (product.size() < 1) {
            throw new ProductNotFoundException();
        }
        
        return product.get(0);
    }
    
    public List<Product> fetchAllProducts() {
        Session session = this.databaseDriver.openSession();
        
        return session.createCriteria(Product.class).list();
    }
    
    public void delete(int id) {
        Session session = this.databaseDriver.openSession();
        
        try {
            Product product = this.fetchProduct(id);
            
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        } catch (ProductNotFoundException exception) {
            session.close();
        }
        
        session.close();
    }
    
    public List<Product> fetchExcluding(List<Integer> selectedProducts) {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(Product.class);
        
        for (int id : selectedProducts) {
            criteria.add(Restrictions.not(Restrictions.eq("id", id)));
        }
        
        return criteria.list();
    }
}
