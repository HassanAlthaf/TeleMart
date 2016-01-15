/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.users.User;
import com.hassanalthaf.telemart.users.UserController;
import com.hassanalthaf.telemart.users.UserRanks;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateUserViewModel implements Initializable {
    
    @FXML
    private Parent updateUser;
    
    @FXML
    private TextField nicNumber;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField fullName;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField address;
    
    @FXML
    private TextField salary;
    
    @FXML
    private TextField contactNumber;
    
    @FXML
    private ChoiceBox rank;
    
    @FXML
    private Label success;
    
    @FXML
    private Label errors;
    
    private User user;
    private int loggedInRank;
    private UserController userController;
    private DashboardViewModel dashboardViewModel;
    
    public void show(User user, int loggedInRank, UserController userController, DashboardViewModel dashboardViewModel) {
        this.user = user;
        this.loggedInRank = loggedInRank;
        this.userController = userController;
        this.dashboardViewModel = dashboardViewModel;
        
        Scene scene = new Scene(this.updateUser);
        
        Stage stage = new Stage();
        
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        
        this.populateFields();
        
        stage.show();
    }
    
    public void populateFields() {
        this.nicNumber.setText(this.user.getNicNumber());
        this.username.setText(this.user.getUsername());
        this.fullName.setText(this.user.getFullName());
        this.email.setText(this.user.getEmail());
        this.address.setText(this.user.getAddress());
        this.salary.setText(String.valueOf(this.user.getSalary()));
        this.contactNumber.setText(String.valueOf(this.user.getContactNumber()));
        
        this.rank.getItems().addAll("Disabled", "Cashier", "Sales Executive");
        
        if(this.loggedInRank == UserRanks.ADMINISTRATOR.getValue()) {
            this.rank.getItems().addAll("Manager", "Administrator");
        }
        
        this.rank.getSelectionModel().select(this.user.getRank());
    }
    
    private void showSuccess(String message) {
        this.errors.setOpacity(0);
        this.success.setText(message);
        this.success.setOpacity(1);
    }
    
    private void showError(String message) {
        this.success.setOpacity(0);
        this.errors.setText(message);
        this.errors.setOpacity(1);
    }
    
    @FXML
    private void addUser(MouseEvent event) {
        
        boolean passwordChanged = false;
    
        this.user.setNicNumber(this.nicNumber.getText());
        this.user.setUsername(this.username.getText());
        
        if (!this.password.getText().equals("")) {
            this.user.setPassword(this.password.getText());
            passwordChanged = true;
        }
        
        this.user.setFullName(this.fullName.getText());
        
        int contactNumber;
        
        try {
            contactNumber = Integer.parseInt(this.contactNumber.getText());
        } catch (Exception exception) {
            this.showError("Contact number must only contain numbers!");
            return;
        }
        
        this.user.setContactNumber(contactNumber);
        this.user.setEmail(this.email.getText());
        this.user.setAddress(this.address.getText());
        
        double salary;
        
        try {
            salary = Double.parseDouble(this.salary.getText());
        } catch (Exception exception) {
            this.showError("The salary field may only contain decimals and numbers!");
            return;
        }
        
        this.user.setSalary(salary);
        
        this.user.setRank(this.rank.getSelectionModel().getSelectedIndex());
        
        try {
            this.userController.update(this.user, this.loggedInRank, passwordChanged);
        } catch (Exception exception) {
            this.showError(exception.getMessage());
            return;
        }
        
        this.showSuccess("Successfully updated user!");
        this.dashboardViewModel.populateManageUsersTable();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
