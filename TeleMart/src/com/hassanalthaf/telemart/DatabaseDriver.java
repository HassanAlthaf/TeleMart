/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseDriver {
    private final static StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory sessionFactory;
    private static DatabaseDriver databaseDriver;
    
    private DatabaseDriver() {
        DatabaseDriver.sessionFactory = new MetadataSources(DatabaseDriver.standardServiceRegistry).buildMetadata().buildSessionFactory();
    }
    
    public static DatabaseDriver getInstance() {
        if (DatabaseDriver.databaseDriver == null) {
            DatabaseDriver.databaseDriver = new DatabaseDriver();
        }
        
        return DatabaseDriver.databaseDriver;
    }
    
    public static Session openSession() {
        return DatabaseDriver.sessionFactory.openSession();
    }
}
