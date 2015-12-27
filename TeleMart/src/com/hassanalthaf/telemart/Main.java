/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart;

import com.hassanalthaf.telemart.users.User;
import com.hassanalthaf.telemart.users.UserRepository;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

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
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        //System.out.println(userRepository.fetchAll().get(0).getId());
        System.out.println(BCrypt.hashpw("hassan123", BCrypt.gensalt()));
        launch(args);
    }
    
}
