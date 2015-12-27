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
    final private StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory;
    
    public DatabaseDriver() {
        this.sessionFactory = new MetadataSources(this.standardServiceRegistry).buildMetadata().buildSessionFactory();
    }
    
    public Session openSession() {
        return this.sessionFactory.openSession();
    }
}
