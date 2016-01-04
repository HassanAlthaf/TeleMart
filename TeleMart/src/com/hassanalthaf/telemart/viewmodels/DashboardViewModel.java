/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.customers.Customer;
import com.hassanalthaf.telemart.customers.CustomerController;
import com.hassanalthaf.telemart.inventory.ProductController;
import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.orders.OrderItem;
import com.hassanalthaf.telemart.orders.OrderState;
import com.hassanalthaf.telemart.users.UserState;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    
    @FXML
    private AnchorPane addInventory;
    
    @FXML
    private AnchorPane manageCustomers;
            
    @FXML
    private AnchorPane addOrder;
    
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
    
    @FXML
    private Label addInventorySuccess;
    
    @FXML
    private Label addInventoryErrors;
    
    @FXML
    private TextField addInventoryBrand;
    
    @FXML
    private TextField addInventoryModel;
    
    @FXML
    private TextField addInventoryColour;
    
    @FXML
    private TextField addInventoryUnitPrice;
    
    @FXML
    private TextField addInventoryAvailableQuantity;
    
    @FXML
    private TextArea addInventorySpecifications;
    
    @FXML
    private TableView customersTableView;
    
    @FXML
    private Label addOrdersSuccessBox;
    
    @FXML
    private Label addOrdersErrorsBox;
    
    @FXML
    private TextField addOrdersQuantity;
    
    @FXML
    private TableView addOrdersTableView;
    
    private CustomerController customerController;
    private ProductController productController;
    private AnchorPane currentPage;
    private UserState userState;
    private OrderState orderState;

    
    private void changePage(AnchorPane page) {
        this.currentPage.setOpacity(0);
        this.currentPage = page;
        this.currentPage.toFront();
        this.currentPage.setOpacity(1);
    }
    
    public void populateProductsTable() {
        ObservableList<Product> products = this.productTableView.getItems();
        products.clear();
        products.addAll(this.productController.fetchAllProducts());
    }
    
    public void populateCustomersTable() {
        ObservableList<Customer> customers = this.customersTableView.getItems();
        customers.clear();
        customers.addAll(this.customerController.fetchAllCustomers());
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
            case "addInventoryMenuItem":
                this.changePage(this.addInventory);
                break;
            case "manageCustomersMenuItem":
                this.changePage(this.manageCustomers);
                this.populateCustomersTable();
                break;
            case "addOrderMenuItem":
                this.changePage(this.addOrder);
                break;
            default:
                this.changePage(this.home);
                break;
        }
    }
    
    @FXML
    private void addCustomer(MouseEvent event) {
        this.addCustomerErrors.setOpacity(0);
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
    
    @FXML
    private void addInventory(MouseEvent event) {
        this.addInventorySuccess.setOpacity(0);
        this.addInventoryErrors.setOpacity(0);
        
        String brand = this.addInventoryBrand.getText();
        String model = this.addInventoryModel.getText();
        String colour = this.addInventoryColour.getText();
        
        double unitPrice;
        
        try {
            unitPrice = Double.parseDouble(this.addInventoryUnitPrice.getText());
        } catch (Exception exception) {
            unitPrice = 0;
        }
        
        int availableQuantity;
        
        try {
            availableQuantity = Integer.parseInt(this.addInventoryAvailableQuantity.getText());
        } catch (Exception exception) {
            availableQuantity = 0;
        }
        
        String specifications = this.addInventorySpecifications.getText();
        
        try {
            this.productController.addNewProduct(brand, model, colour, unitPrice, availableQuantity, specifications);
            this.addInventorySuccess.setText("Successfully stored new inventory details!");
            this.addInventorySuccess.setOpacity(1);
        } catch (Exception exception) {
            this.addInventoryErrors.setText(exception.getMessage());
            this.addInventoryErrors.setOpacity(1);
        }
    }
    
    @FXML
    private void inventoryTableRefresh(MouseEvent event) {
        this.populateProductsTable();
    }
    
    private Product getSelectedProduct() {
        Product product = (Product)this.productTableView.getSelectionModel().getSelectedItem();
        return product;
    }
    
    @FXML
    private void inventoryTableViewDetails(MouseEvent event) throws Exception {
        if (this.productTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewInventoryDetailsView.fxml"));
            Parent viewInventoryDetails = fxmlLoader.load();
            ViewInventoryDetailsViewModel viewInvetoryDetailsViewModel = fxmlLoader.getController();

            viewInvetoryDetailsViewModel.show(this.getSelectedProduct());
        }
    }
    
    @FXML
    private void inventoryTableUpdate(MouseEvent event) throws Exception {
        if (this.productTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/UpdateInventoryDetailsView.fxml"));
            Parent updateInventoryDetails = fxmlLoader.load();
            UpdateInventoryDetailsViewModel updateInventoryDetailsViewModel = fxmlLoader.getController();
            
            updateInventoryDetailsViewModel.show(this.getSelectedProduct(), this);
        }
    }
    
    @FXML
    private void inventoryTableDelete(MouseEvent event) {
        if (this.productTableView.getSelectionModel().getSelectedItem() != null) {
            int id = this.getSelectedProduct().getId();

            this.productController.deleteProduct(id);
            this.populateProductsTable();
        }
    }
    
    private Customer getSelectedCustomer() {
        Customer customer = (Customer)this.customersTableView.getSelectionModel().getSelectedItem();
        return customer;
    }
    
    @FXML
    private void customerTableRefresh(MouseEvent event) {
        this.populateCustomersTable();
    }
    
    @FXML
    private void customerTableUpdate(MouseEvent event) throws Exception {
        if (this.customersTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/UpdateCustomerDetails.fxml"));
            Parent updateCustomerDetails = fxmlLoader.load();
            UpdateCustomerDetailsViewModel updateCustomerDetailsViewModel = fxmlLoader.getController();
            updateCustomerDetailsViewModel.show(this.getSelectedCustomer(), this.customerController, this);
        }
    }
    
    @FXML
    private void customerTableView(MouseEvent event) throws Exception {
        if (this.customersTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewCustomer.fxml"));
            Parent viewCustomer = fxmlLoader.load();
            ViewCustomerViewModel viewCustomerViewModel = fxmlLoader.getController();
            viewCustomerViewModel.show(this.getSelectedCustomer());
        }
    }
    
    @FXML
    private void customerTableDelete(MouseEvent event) {
        if(this.customersTableView.getSelectionModel().getSelectedItem() != null) {
            int id = this.getSelectedCustomer().getId();
            
            this.customerController.deleteCustomer(id);
            this.populateCustomersTable();
        }
    }
    
    @FXML
    private void addOrdersSelectProduct(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/SelectProduct.fxml"));
        Parent selectProduct = fxmlLoader.load();
        SelectProductViewModel selectProductViewModel = fxmlLoader.getController();
        selectProductViewModel.show(this, this.orderState.getSelectedProducts());
    }
    
    public void selectProduct(Product product) {
        this.orderState.select(product);
        this.addOrdersSuccess("Product successfully selected!");
    }
    
    private void addOrdersSuccess(String message) {
        this.addOrdersErrorsBox.setOpacity(0);
        this.addOrdersSuccessBox.setText(message);
        this.addOrdersSuccessBox.setOpacity(1);
    }
    
    private void addOrdersError(String message) {
        this.addOrdersSuccessBox.setOpacity(0);
        this.addOrdersErrorsBox.setText(message);
        this.addOrdersErrorsBox.setOpacity(1);
    }
    
    private OrderItem getSelectedOrderItem() {
        return (OrderItem)this.addOrdersTableView.getSelectionModel().getSelectedItem();
    }
    
    private void refreshAddOrderItemsTable() {
        ObservableList<OrderItem> orderItems = this.addOrdersTableView.getItems();
        orderItems.clear();
        orderItems.addAll(this.orderState.getOrderItems());
    }
    
    @FXML
    private void addOrderItem(MouseEvent event) {
        boolean valid = true;
        
        if (this.orderState.isProductSelected()) {
            int quantity;

            try {
                quantity = Integer.parseInt(this.addOrdersQuantity.getText());
            } catch (Exception exception) {
                quantity = 0;
                valid = false;
            }

            try {
                this.orderState.setQuantity(quantity);
            } catch (Exception exception) {
                this.addOrdersError(exception.getMessage());
                valid = false;
            }
            
            if (valid) {
                this.orderState.saveOrderItem();
                this.refreshAddOrderItemsTable();
                this.addOrdersSuccess("Successfully added product!");
            }
        } else {
            this.addOrdersError("Please select a product!");
        }
    }
    
    @FXML
    private void removeOrderItem(MouseEvent event) {
        if (this.addOrdersTableView.getSelectionModel().getSelectedItem() != null) {
            this.orderState.removeOrderItem(this.getSelectedOrderItem());
            this.refreshAddOrderItemsTable();
            this.addOrdersSuccess("Successfully removed product!");
        } else {
            this.addOrdersError("Select an item to remove from table.");
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
        this.orderState = new OrderState();
        
        this.customerController = new CustomerController();
        this.productController = new ProductController();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.currentPage = this.home;
    }
}
