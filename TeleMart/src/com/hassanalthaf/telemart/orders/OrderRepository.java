/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders;

import com.hassanalthaf.telemart.DatabaseDriver;
import com.hassanalthaf.telemart.orders.exceptions.NotFoundException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hassan
 */
public class OrderRepository {
    private DatabaseDriver databaseDriver;
    
    public Order fetch(int id) throws NotFoundException {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq("id", id));
        
        List<Order> orders = criteria.list();
        
        if (orders.size() < 1) {
            throw new NotFoundException();
        }
        
        return orders.get(0);
    }
    
    public List<Order> fetchAll() {
        Session session = this.databaseDriver.openSession();
        
        return session.createCriteria(Order.class).list();
    }
    
    public int insert(Order order) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        
        return order.getId();
    }
    
    public void update(Order order) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
    }
}
