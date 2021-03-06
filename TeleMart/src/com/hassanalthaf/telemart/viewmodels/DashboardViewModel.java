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
import com.hassanalthaf.telemart.orders.Order;
import com.hassanalthaf.telemart.orders.OrderController;
import com.hassanalthaf.telemart.orders.OrderItem;
import com.hassanalthaf.telemart.orders.OrderState;
import com.hassanalthaf.telemart.users.User;
import com.hassanalthaf.telemart.users.UserController;
import com.hassanalthaf.telemart.users.UserRanks;
import com.hassanalthaf.telemart.users.UserState;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private AnchorPane manageOrders;
    
    @FXML
    private AnchorPane addUser;
    
    @FXML
    private AnchorPane manageUsers;
    
    @FXML
    private AnchorPane accessForbidden;
    
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
    
    @FXML
    private Button addOrdersSelectedCustomer;
    
    @FXML
    private Label totalBillValue;
    
    @FXML
    private Label discount;
    
    @FXML
    private TableView manageOrdersTableView;
    
    @FXML
    private Label addUserSuccess;
    
    @FXML
    private Label addUserErrors;
    
    @FXML
    private TextField addUserNICNumber;
    
    @FXML
    private TextField addUserFullName;
    
    @FXML
    private TextField addUserEmail;
    
    @FXML
    private TextField addUserUsername;
    
    @FXML
    private TextField addUserPassword;
    
    @FXML
    private TextField addUserAddress;
    
    @FXML
    private TextField addUserContactNumber;
    
    @FXML
    private TextField addUserSalary;
    
    @FXML
    private ChoiceBox addUserRank;
    
    @FXML
    private Label manageUsersSuccess;
    
    @FXML
    private Label manageUsersErrors;
    
    @FXML
    private TableView manageUsersTableView;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Button viewInventoryUpdate;
    
    @FXML
    private Button viewInventoryDelete;
    
    private CustomerController customerController;
    private ProductController productController;
    private OrderController orderController;
    private AnchorPane currentPage;
    private UserState userState;
    private OrderState orderState;
    private Stage stage;
    private UserController userController;

    
    private void changePage(AnchorPane page, int[] allowedRanks) {
        boolean allowed = false;
        
        for (int rank : allowedRanks) {
            if (rank == this.userState.getUser().getRank()) {
                allowed = true;
                break;
            }
        }
        
        if (!allowed) {
            page = this.accessForbidden;
        }
        
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
        
        if (this.userState.getUser() != null) {
            
            switch (id) {
                case "homeMenuItem":
                    this.changePage(this.home, new int[]{UserRanks.CASHIER.getValue(), UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    break;
                case "viewInventoryMenuItem":
                    this.changePage(this.viewInventory, new int[]{UserRanks.CASHIER.getValue(), UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    this.populateProductsTable();
                    break;
                case "addCustomerMenuItem":
                    this.changePage(this.addCustomer, new int[]{UserRanks.CASHIER.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    break;
                case "addInventoryMenuItem":
                    this.changePage(this.addInventory, new int[]{UserRanks.CASHIER.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    break;
                case "manageCustomersMenuItem":
                    this.changePage(this.manageCustomers, new int[]{UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    this.populateCustomersTable();
                    break;
                case "addOrderMenuItem":
                    this.changePage(this.addOrder, new int[]{UserRanks.CASHIER.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    break;
                case "manageOrdersMenuItem":
                    this.changePage(this.manageOrders, new int[]{UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    this.populateManageOrdersTable();
                    break;
                case "logoutMenuItem":
                    this.logout();
                    break;
                case "addUserMenuItem":
                    this.changePage(this.addUser, new int[]{UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    this.initializeAddOrder();
                    break;
                case "manageUsersMenuItem":
                    this.changePage(this.manageUsers, new int[]{UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    this.populateManageUsersTable();
                    break;
                default:
                    this.changePage(this.home, new int[]{UserRanks.CASHIER.getValue(), UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()});
                    break;
            }
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
    
    private void updateTotalBillValue(double value) {
        
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
                OrderItem orderItem = this.orderState.getOrderItem();
                orderItem.setUnitPrice(orderItem.getProduct().getUnitPrice());
                this.orderState.setOrderItem(orderItem);
                this.orderState.saveOrderItem();
                this.refreshBillValues();
                this.refreshAddOrderItemsTable();
                this.addOrdersSuccess("Successfully added product!");
                this.addOrdersQuantity.setText("");
            }
        } else {
            this.addOrdersError("Please select a product!");
        }
    }
    
    @FXML
    private void removeOrderItem(MouseEvent event) {
        if (this.addOrdersTableView.getSelectionModel().getSelectedItem() != null) {
            this.orderState.removeOrderItem(this.getSelectedOrderItem());
            this.refreshBillValues();
            this.refreshAddOrderItemsTable();
            this.addOrdersSuccess("Successfully removed product!");
        } else {
            this.addOrdersError("Select an item to remove from table.");
        }
    }
    
    public void setOrderCustomer(Customer customer) {
        this.orderState.setCustomer(customer, false);
        this.addOrdersSelectedCustomer.setText(customer.getNicNumber());
        this.addOrdersSelectedCustomer.setDisable(false);
        this.refreshBillValues();
        this.addOrdersSuccess("Successfully selected customer!");
    }
    
    @FXML
    private void addOrdersSelectCustomer(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/SelectCustomer.fxml"));
        Parent selectCustomer = fxmlLoader.load();
        SelectCustomerViewModel selectCustomerViewModel = fxmlLoader.getController();
        
        selectCustomerViewModel.show(this);
    }
    
    @FXML
    private void addOrdersViewSelectedCustomer(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewCustomer.fxml"));
        Parent viewCustomer = fxmlLoader.load();
        ViewCustomerViewModel viewCustomerViewModel = fxmlLoader.getController();
        
        viewCustomerViewModel.show(this.orderState.getSelectedCustomer());
    }
    
    private void resetSelectedButton() {
        this.addOrdersSelectedCustomer.setDisable(true);
        this.addOrdersSelectedCustomer.setText("None");
    }
    
    @FXML
    private void addOrdersUnselectCustomer(MouseEvent event) {
        this.orderState.setCustomer((Customer)null, true);
        this.resetSelectedButton();
        this.refreshBillValues();
        this.addOrdersSuccess("Successfully un-selected customer!");
    }
    
    @FXML
    private void addOrdersReset(MouseEvent event) {
        this.orderState = new OrderState();
        this.resetSelectedButton();
        this.addOrdersQuantity.setText("");
        this.addOrdersTableView.getItems().clear();
        this.addOrdersSuccess("Order has been successfully reset!");
    }
    
    @FXML
    private void submitOrder(MouseEvent event) {
        if (!this.orderState.isCustomerSelected()) {
            this.addOrdersError("You need to select a customer!");
            return;
        }
        
        try {
            this.orderController.save(this.orderState.getOrder(), this.userState.getUser().getId());
            this.addOrdersSuccess("Successfully created order!");
        } catch (Exception exception) {
            exception.printStackTrace();
            this.addOrdersError(exception.getMessage());
        }
    }
    
    public void refreshBillValues() {
        this.totalBillValue.setText(String.format("%.2f", this.orderState.getBillValue()));
        this.discount.setText(String.format("%.2f", this.orderState.getDiscountedAmount()));
    }
    
    private void populateManageOrdersTable() {
        try {
            ObservableList<Order> orders = this.manageOrdersTableView.getItems();
            orders.clear();
            orders.addAll(this.orderController.fetchAll());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    @FXML
    private void manageOrderView(MouseEvent event) throws Exception {
        if (this.manageOrdersTableView.getSelectionModel().getSelectedItem() != null) {
            Order order = (Order)this.manageOrdersTableView.getSelectionModel().getSelectedItem();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewOrder.fxml"));
            Parent viewOrder = fxmlLoader.load();
            ViewOrderViewModel viewOrderViewModel = fxmlLoader.getController();
            
            viewOrderViewModel.show(order);
        }
    }
    
    @FXML
    private void undoOrder(MouseEvent event) throws Exception {
        if (this.manageOrdersTableView.getSelectionModel().getSelectedItem() != null) {
            Order order = (Order)this.manageOrdersTableView.getSelectionModel().getSelectedItem();
            
            order = this.orderController.fetch(order.getId());
            
            this.orderController.delete(order);
            
            this.populateManageOrdersTable();
        }
    }
    
    private void logout() {
        try {
            this.userState.logout();
            this.stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/MainView.fxml"));
            Parent mainWindow = fxmlLoader.load();
            MainViewModel mainViewModel = fxmlLoader.getController();

            mainViewModel.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    @FXML
    private void addUser(MouseEvent event) {
        User user = new User();
        
        user.setNicNumber(this.addUserNICNumber.getText());
        user.setUsername(this.addUserUsername.getText());
        user.setPassword(this.addUserPassword.getText());
        user.setFullName(this.addUserFullName.getText());
        
        int contactNumber;
        
        try {
            contactNumber = Integer.parseInt(this.addUserContactNumber.getText());
        } catch (Exception exception) {
            this.addUserErrorMessage("Contact number must only contain numbers!");
            return;
        }
        
        user.setContactNumber(contactNumber);
        user.setEmail(this.addUserEmail.getText());
        user.setAddress(this.addUserAddress.getText());
        
        double salary;
        
        try {
            salary = Double.parseDouble(this.addUserSalary.getText());
        } catch (Exception xception) {
            this.addUserErrorMessage("The salary field may only contain decimals and numbers!");
            return;
        }
        
        user.setSalary(salary);
        
        user.setRank(this.addUserRank.getSelectionModel().getSelectedIndex());
        
        try {
            this.userController.createUser(user);
        } catch (Exception exception) {
            this.addUserErrorMessage(exception.getMessage());
            return;
        }
        
        this.addUserSuccessMessage("Successfully created user!");
    }
    
    private void addUserSuccessMessage(String message) {
        this.addUserErrors.setOpacity(0);
        this.addUserSuccess.setText(message);
        this.addUserSuccess.setOpacity(1);
    }
    
    private void addUserErrorMessage(String message) {
        this.addUserSuccess.setOpacity(0);
        this.addUserErrors.setText(message);
        this.addUserErrors.setOpacity(1);
    }
    
    private void initializeAddOrder() {
        this.addUserRank.getItems().clear();
        this.addUserRank.getItems().addAll("Disabled", "Cashier", "Sales Executive");
        
        if (this.userState.getUser().getRank() == UserRanks.ADMINISTRATOR.getValue()) {
            this.addUserRank.getItems().addAll("Manager", "Administrator");
        }
    }
    
    public void populateManageUsersTable() {
        ObservableList<User> users = this.manageUsersTableView.getItems();
        users.clear();
        users.addAll(this.userController.fetchAll());
    }
    
    @FXML
    private void viewUser(MouseEvent event) throws IOException {
        if (this.manageUsersTableView.getSelectionModel().getSelectedItem() != null) {
            User user = this.fetchSelectedUser();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewUser.fxml"));
            fxmlLoader.load();
            ViewUserViewModel viewUserViewModel = fxmlLoader.getController();
            viewUserViewModel.show(user);
        }
    }
    
    @FXML
    private void updateUser(MouseEvent event) throws IOException {
        if (this.manageUsersTableView.getSelectionModel().getSelectedItem() != null) {
            User user = this.fetchSelectedUser();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/UpdateUser.fxml"));
            fxmlLoader.load();
            UpdateUserViewModel updateUserViewModel = fxmlLoader.getController();
            
            updateUserViewModel.show(user, this.userState.getUser().getRank(), this.userController, this);
        }
    }
   
    private User fetchSelectedUser() {
        return (User)this.manageUsersTableView.getSelectionModel().getSelectedItem();
    }
    
    public void show(Parent main, UserState userState) {
        Scene scene = new Scene(this.dashboard);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        
        this.userMenu.setText(userState.getUser().getUsername());
        
        Stage mainStage = (Stage)main.getScene().getWindow();
        mainStage.close();
        
        this.userState = userState;
        this.orderState = new OrderState();
        
        this.customerController = new CustomerController();
        this.productController = new ProductController();
        this.orderController = new OrderController();
        this.userController = new UserController(this.userState);
        
        int userRank = this.userState.getUser().getRank();
        
        ObservableList<Menu> menus = this.menuBar.getMenus();
        
        if (userRank == UserRanks.CASHIER.getValue()) {
            menus.remove(4);
            
        } else if (userRank == UserRanks.SALES_EXECUTIVE.getValue()) {
            menus.remove(2);
            menus.remove(3);
            this.viewInventoryUpdate.setDisable(true);
            this.viewInventoryDelete.setDisable(true);
        }
        
        stage.show();
        
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.currentPage = this.home;
    }
}