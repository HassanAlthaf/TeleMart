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
public class OrderItemRepository {
    private DatabaseDriver databaseDriver;
    
    public OrderItemRepository() {
        this.databaseDriver = new DatabaseDriver();
    }
    
    public OrderItem fetch(int id) throws NotFoundException {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(OrderItem.class);
        criteria.add(Restrictions.eq("id", id));
        
        List<OrderItem> orderItems = criteria.list();
        
        if (orderItems.size() < 1) {
            throw new NotFoundException();
        }
        
        return orderItems.get(0);
    }
    
    public List<OrderItem> fetchAll() {
        Session session = this.databaseDriver.openSession();
        
        return session.createCriteria(OrderItem.class).list();
    }
    
    public int insert(OrderItem orderItem) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(orderItem);
        transaction.commit();
        
        return orderItem.getId();
    }
    
    public void update(OrderItem orderItem) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.update(orderItem);
        transaction.commit();
    }
}
