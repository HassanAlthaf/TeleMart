/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.orders.Order;
import com.hassanalthaf.telemart.orders.OrderItem;
import com.hassanalthaf.telemart.users.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class ViewOrderViewModel implements Initializable {

    @FXML
    private Parent viewOrder;
    
    @FXML
    private Label date;
    
    @FXML
    private TableView orderItems;
    
    
    
    private Order order;
    
    public void show(Order order) {
        this.order = order;
        
        Scene scene = new Scene(this.viewOrder);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        
        this.date.setText(this.order.getFormattedDate());
        this.populateOrderItemsTable();
        
        stage.show();
    }
    
    private void populateOrderItemsTable() {
        ObservableList<OrderItem> list = this.orderItems.getItems();
        list.clear();
        list.addAll(this.order.getOrderItems());
    }
    
    @FXML
    private void viewCustomer(MouseEvent event) throws Exception {
        Customer customer = this.order.getCustomer();
       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewCustomer.fxml"));
        Parent viewCustomer = fxmlLoader.load();
        ViewCustomerViewModel viewCustomerViewModel = fxmlLoader.getController();
        
        viewCustomerViewModel.show(customer);
    }
    
    @FXML
    private void viewOperator(MouseEvent event) throws Exception {
        User user = this.order.getUser();
        
        // @TODO: Complete User Module.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
