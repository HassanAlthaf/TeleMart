/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

import com.hassanalthaf.telemart.DatabaseDriver;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
}
