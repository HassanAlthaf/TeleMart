/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart;

import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.customers.CustomerService;
import com.hassanalthaf.telemart.users.UserRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
