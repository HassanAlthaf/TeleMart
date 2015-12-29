/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.CustomerController;
import com.hassanalthaf.telemart.customers.CustomerService;
import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.users.UserState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class DashboardViewModel implements Initializable {
    
    @FXML
    private Parent dashboard;
    
    @FXML
    private AnchorPane home;
   
    @FXML
    private AnchorPane viewInventory;
    
    @FXML
    private AnchorPane addCustomer;
    
    private AnchorPane currentPage;
    
    private UserState userState;
    
    @FXML
    private TableView productTableView;
    
    @FXML
    private Menu userMenu;
    
    @FXML
    private TextField addCustomerNicNumber;
    
    @FXML
    private TextField addCustomerName;
    
    @FXML
    private TextField addCustomerContactNumber;
    
    @FXML
    private TextField addCustomerAddress;
    
    @FXML
    private TextField addCustomerEmail;
    
    @FXML
    private CheckBox addCustomerMembership;
    
    @FXML
    private Label addCustomerSuccess;
    
    @FXML
    private Label addCustomerErrors;
    
    private CustomerController customerController;
    
    private void changePage(AnchorPane page) {
        this.currentPage.setOpacity(0);
        this.currentPage = page;
        this.currentPage.toFront();
        this.currentPage.setOpacity(1);
    }
    
    private void populateProductsTable() {
        ObservableList<Product> products = this.productTableView.getItems();
        
        products.add(new Product("Samsung", "Galaxy A8", "Black", 75000.00, 10, "Specs"));
    }
    
    public void menuItemClick(ActionEvent event) {
        Object source = (MenuItem)event.getSource();
        MenuItem clickedItem;
        
        if(source instanceof MenuItem) {
            clickedItem = (MenuItem)source;
        } else {
            return;
        }
        
        String id = clickedItem.getId();
        
        switch (id) {
            case "homeMenuItem":
                this.changePage(this.home);
                break;
            case "viewInventoryMenuItem":
                this.changePage(this.viewInventory);
                this.populateProductsTable();
                break;
            case "addCustomerMenuItem":
                this.changePage(this.addCustomer);
                break;
            default:
                this.changePage(this.home);
                break;
        }
    }
    
    @FXML
    private void addCustomer(MouseEvent event) {
        this.addCustomerSuccess.setOpacity(0);
        this.addCustomerSuccess.setOpacity(0);
        
        String nicNumber = this.addCustomerNicNumber.getText();
        String name = this.addCustomerName.getText();
        
        int contactNumber;
        
        try {
            contactNumber = Integer.parseInt(this.addCustomerContactNumber.getText());
        } catch (Exception exception) {
            contactNumber = 0;
        }
        
        String address = this.addCustomerAddress.getText();
        String email = this.addCustomerEmail.getText();
        boolean membership = this.addCustomerMembership.isSelected();
        
        try {
            this.customerController.addNewCustomer(nicNumber, membership, name, contactNumber, address, email);
            this.addCustomerSuccess.setText("Successfully stored customer details!");
            this.addCustomerSuccess.setOpacity(1);
        } catch (Exception exception) {
            this.addCustomerErrors.setText(exception.getMessage());
            this.addCustomerErrors.setOpacity(1);
        }
    }
    
    public void show(Parent main, UserState userState) {
        Scene scene = new Scene(this.dashboard);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> Platform.exit());
        
        this.userMenu.setText(userState.getUser().getUsername());
        
        stage.show();
        
        Stage mainStage = (Stage)main.getScene().getWindow();
        mainStage.close();
        
        this.userState = userState;
        
        this.customerController = new CustomerController();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.currentPage = this.home;
    }    
    
}
