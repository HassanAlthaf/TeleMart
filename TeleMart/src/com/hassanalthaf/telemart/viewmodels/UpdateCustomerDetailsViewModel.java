/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.customers.CustomerController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class UpdateCustomerDetailsViewModel implements Initializable {

    @FXML
    private Parent updateCustomerDetails;
    
    @FXML
    private TextField nicNumber;
    
    @FXML
    private TextField membershipNumber;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField contactNumber;
    
    @FXML
    private TextField emailAddress;
    
    @FXML
    private TextArea address; 
    
    @FXML
    private Label errors;
    
    @FXML
    private Label success;
    
    @FXML
    private ToggleButton membershipStatus;
    
    private Customer customer;
    private CustomerController customerController;
    private DashboardViewModel dashboardViewModel;
    
    private final String RED_COLOUR = "#C0392B";
    private final String GREEN_COLOUR = "#27AE60";
    
    public void show(Customer customer, CustomerController customerController, DashboardViewModel dashboardViewModel) {
        this.customer = customer;
        this.customerController = customerController;
        this.dashboardViewModel = dashboardViewModel;
        
        Scene scene = new Scene(this.updateCustomerDetails);
        
        Stage stage = new Stage();
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        this.populateFields();
        stage.show();
    }
    
    private void populateFields() {
        this.nicNumber.setText(this.customer.getNicNumber());
        this.membershipNumber.setText(String.valueOf(this.customer.getMembershipNumber()));
        this.name.setText(this.customer.getName());
        this.contactNumber.setText(String.valueOf(this.customer.getContactNumber()));
        this.emailAddress.setText(this.customer.getEmailAddress());
        this.address.setText(this.customer.getAddress());
        this.membershipNumber.setEditable(false);
        
        if (this.customer.getMembershipNumber() == 0) {
            this.setMembershipStatus(false);
        } else {
            this.setMembershipStatus(true);
        }
    }
    
    private void setMembershipStatus(boolean mode) {
        if (mode) {
            this.membershipStatus.setSelected(true);
            this.membershipStatus.setStyle("-fx-base: " + this.GREEN_COLOUR + ";");
            this.membershipStatus.setText("Enabled");
        } else {
            this.membershipStatus.setSelected(false);
            this.membershipStatus.setStyle("-fx-base: " + this.RED_COLOUR + ";");
            this.membershipStatus.setText("Disabled");
        }
    }
    
    @FXML
    private void toggleButton(MouseEvent event) {
        this.setMembershipStatus(this.membershipStatus.selectedProperty().get());
    }
    
    @FXML
    private void updateCustomer(MouseEvent event) {
        this.errors.setOpacity(0);
        this.success.setOpacity(0);
        
        this.customer.setNicNumber(this.nicNumber.getText());
        this.customer.setName(this.name.getText());
        
        try {
            this.customer.setContactNumber(Integer.parseInt(this.contactNumber.getText()));
        } catch (Exception exception) {
            this.customer.setContactNumber(0);
        }
        
        this.customer.setAddress(this.address.getText());
        this.customer.setEmailAddress(this.emailAddress.getText());
        boolean membershipStatus = this.membershipStatus.selectedProperty().get();
        
        try {
            this.customerController.updateCustomer(this.customer, membershipStatus);
            this.success.setText("Successfully stored customer details!");
            this.success.setOpacity(1);
        } catch (Exception exception) {
            this.errors.setText(exception.getMessage());
            this.errors.setOpacity(1);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
