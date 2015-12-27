/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart;

import com.hassanalthaf.telemart.users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author hassan
 */
public class Main extends Application {
    
    public static final String APPLICATION_TITLE = "TeleMart - ERP System";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
        
        Scene scene = new Scene(root);
        
        Image image = new Image("com/hassanalthaf/telemart/views/images/icon.png");
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.setTitle(Main.APPLICATION_TITLE);
        Main.tryH();
        stage.show();
    }
    
    public static void tryH() {
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory;
        
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            
            User user = new User("A", "A", "A", "A", 1, "A", "A", 1);
        
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception exception) {
            StandardServiceRegistryBuilder.destroy(registry);
            exception.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
