/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.orders.Order;
import com.hassanalthaf.telemart.orders.OrderController;
import com.hassanalthaf.telemart.orders.OrderItem;
import com.hassanalthaf.telemart.orders.OrderItemService;
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
    
    @FXML
    private Label successBox;
    
    @FXML
    private Label errorsBox;
    
    @FXML
    private Label totalValue;
    
    @FXML
    private Label discountValue;
    
    private Order order;
    private OrderItemService orderItemService;
    private OrderController orderController;
    
    public void show(Order order) {
        this.order = order;
        
        Scene scene = new Scene(this.viewOrder);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        
        this.date.setText(this.order.getFormattedDate());
        this.orderItemService = new OrderItemService();
        this.orderController = new OrderController();
        
        this.populateOrderItemsTable();
                
        stage.show();
    }
    
    private void populateOrderItemsTable() {
        try {
            this.order = this.orderController.fetch(this.order.getId());
            ObservableList<OrderItem> list = this.orderItems.getItems();
            list.clear();
            list.addAll(this.order.getOrderItems());
            this.updateTotalValue();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
    
    @FXML
    private void refundOrderItem(MouseEvent event) {
        if (this.orderItems.getSelectionModel().getSelectedItem() != null) {
            OrderItem orderItem = (OrderItem)this.orderItems.getSelectionModel().getSelectedItem();
            
            try {
                this.orderItemService.refundItem(orderItem);
                this.success("Refunded item successfully!");
            } catch (Exception exception) {
                this.error(exception.getMessage());
            }
            
            this.populateOrderItemsTable();
        }
    }
    
    private void updateTotalValue() {
        double amountRate = 1;
        
        if (this.order.getHasDiscount() == 1) {
            amountRate = 0.95;
        }
        
        double totalAmount = 0;
        
        for (OrderItem orderItem : this.order.getOrderItems()) {
            totalAmount += (orderItem.getQuantity() * orderItem.getUnitPrice());
        }
        
        double newTotal = totalAmount * amountRate;
        double discount = totalAmount - newTotal;
        totalAmount = newTotal;
        
        this.totalValue.setText(String.format("%.2f", totalAmount));
        this.discountValue.setText(String.format("%.2f", discount));
    }
    
    private void success(String message) {
        this.errorsBox.setOpacity(0);
        this.successBox.setText(message);
        this.successBox.setOpacity(1);
    }
    
    private void error(String message) {
        this.successBox.setOpacity(0);
        this.errorsBox.setText(message);
        this.errorsBox.setOpacity(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
