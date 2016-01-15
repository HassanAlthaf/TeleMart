/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

import com.hassanalthaf.telemart.DatabaseDriver;
import com.hassanalthaf.telemart.users.exceptions.UserNotFoundException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hassan
 */
public class UserRepository {
    
    private DatabaseDriver databaseDriver;
    
    public UserRepository() {
        this.databaseDriver = DatabaseDriver.getInstance();
    }
    
    public void insert(User user) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        
        session.close();
    }
    
    public User fetch(int id) throws UserNotFoundException {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        
        criteria.add(Restrictions.eq("id", id));
        
        List<User> user = criteria.list();
        
        if(user.size() < 1) {
            throw new UserNotFoundException();
        }
        
        return user.get(0);
    }
    
    public List<User> fetchAll() {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        
        return criteria.list();
    }
    
    public User fetchByUsername(String username) throws UserNotFoundException {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        
        criteria.add(Restrictions.eq("username", username));
        
        List<User> users = criteria.list();
        
        if (users.size() < 1) {
            throw new UserNotFoundException();
        }
        
        return users.get(0);
    }
    
    public boolean isNicNumberTaken(String nicNumber) {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        
        criteria.add(Restrictions.eq("nicNumber", nicNumber));
        
        List<User> users = criteria.list();
        
        if (users.size() < 1) {
            return false;
        }
        
        return true;
    }
    
    public boolean isUsernameTaken(String username) {
        Session session = this.databaseDriver.openSession();
        
        Criteria criteria = session.createCriteria(User.class);
        
        criteria.add(Restrictions.eq("username", username));
        
        List<User> users = criteria.list();
        
        if (users.size() < 1) {
            return false;
        }
        
        return true;
    }
    
    public void update(User user) {
        Session session = this.databaseDriver.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        
        session.close();
    }
}
