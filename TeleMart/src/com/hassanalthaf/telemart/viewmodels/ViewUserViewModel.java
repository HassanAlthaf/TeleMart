/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.users.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class ViewUserViewModel implements Initializable {
    
    @FXML
    private Parent viewUser;
    
    @FXML
    private Text address;
    
    @FXML
    private Label nicNumber;
    
    @FXML
    private Label username;

    @FXML
    private Label fullName;
    
    @FXML
    private Label contactNumber;
    
    @FXML
    private Label email;
    
    @FXML
    private Label salary;
    
    @FXML
    private Label rank;
    
    private User user;
    
    public void show(User user) {
        this.user = user;
        
        Scene scene = new Scene(this.viewUser);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        
        this.populateData();
        
        stage.show();
    }
    
    private void populateData() {
        String[] ranks = new String[]{"Disabled", "Cashier", "Sales Executive", "Manager", "Administrator"};
        
        this.address.setText(this.user.getAddress());
        
        this.nicNumber.setText(this.user.getNicNumber());
        this.username.setText(this.user.getUsername());
        this.fullName.setText(this.user.getFullName());
        this.contactNumber.setText(String.valueOf(this.user.getContactNumber()));
        this.email.setText(this.user.getEmail());
        this.salary.setText(String.valueOf(this.user.getSalary()));
        this.rank.setText(ranks[this.user.getRank()]);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
