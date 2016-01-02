/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
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
public class ViewCustomerViewModel implements Initializable {

    @FXML
    private Parent viewCustomer;
    
    @FXML
    private Label nicNumber;
    
    @FXML
    private Label membershipNumber;
    
    @FXML
    private Label name;
    
    @FXML
    private Label contactNumber;
    
    @FXML
    private Label emailAddress;
    
    @FXML
    private Text address; 
    
    private Customer customer;

    public void show(Customer customer) {
        this.customer = customer;
        
        Scene scene = new Scene(this.viewCustomer);
        
        Stage stage = new Stage();
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        
        this.populateData();
        
        stage.show();
    }
    
    private void populateData() {
        this.nicNumber.setText(this.customer.getNicNumber());
        this.membershipNumber.setText(String.valueOf(this.customer.getMembershipNumber()));
        this.name.setText(this.customer.getName());
        this.contactNumber.setText(String.valueOf(this.customer.getContactNumber()));
        this.emailAddress.setText(this.customer.getEmailAddress());
        this.address.setText(this.customer.getAddress());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
