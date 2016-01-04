/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.customers.CustomerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class SelectCustomerViewModel implements Initializable {

    @FXML
    private Parent selectCustomer;
    
    @FXML
    private TableView<Customer> customerTableView;
    
    private Stage stage;
    private DashboardViewModel dashboardViewModel;
    
    public void show(DashboardViewModel dashboardViewModel) {
        this.dashboardViewModel = dashboardViewModel;
        
        Scene scene = new Scene(this.selectCustomer);
        
        this.stage = new Stage();
        this.stage.setTitle(Main.APPLICATION_TITLE);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.populateCustomersTable();
        
        this.stage.show();
    }
    
    private void populateCustomersTable() {
        CustomerService customerService = new CustomerService();
        
        ObservableList<Customer> customers = this.customerTableView.getItems();
        customers.clear();
        customers.addAll(customerService.fetchAllCustomers());
    }
    
    private Customer getSelectedCustomer() {
        return (Customer)this.customerTableView.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void view(MouseEvent event) throws IOException {
        if (this.customerTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewCustomer.fxml"));
            Parent viewCustomer = fxmlLoader.load();
            ViewCustomerViewModel viewCustomerViewModel = fxmlLoader.getController();

            viewCustomerViewModel.show(this.getSelectedCustomer());
        }
    }
    
    @FXML
    private void select(MouseEvent event) {
        if (this.customerTableView.getSelectionModel().getSelectedItem() != null) {
            this.dashboardViewModel.setOrderCustomer(this.getSelectedCustomer());
            this.stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
