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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hassan
 */
public class UserRepository {
    
    private DatabaseDriver databaseDriver;
    
    public UserRepository() {
        this.databaseDriver = new DatabaseDriver();
    }
    
    public User fetch(int id) {
        return this.databaseDriver.openSession().get(User.class, id);
    }
    
    public List<User> fetchAll() {
        Session session = this.databaseDriver.openSession();
        return session.createCriteria(User.class).list();
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
}
