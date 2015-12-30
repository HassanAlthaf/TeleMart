/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.inventory.ProductController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class UpdateInventoryDetailsViewModel implements Initializable {
    
    @FXML
    private Parent updateInventoryDetails;
    
    @FXML
    private Label success;
    
    @FXML
    private Label errors;
    
    @FXML
    private TextField brand;
    
    @FXML
    private TextField model;
    
    @FXML
    private TextField colour;
    
    @FXML
    private TextField unitPrice;
    
    @FXML
    private TextField availableQuantity;
    
    @FXML
    private TextArea specifications;
    
    private ProductController productController;
    
    private Product product;
    
    private DashboardViewModel dashboardViewModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void show(Product product, DashboardViewModel dashboardViewModel) {
        this.productController = new ProductController();
        this.product = product;
        this.dashboardViewModel = dashboardViewModel;
        
        Scene scene = new Scene(this.updateInventoryDetails);
        
        this.populateFieldsWithValues();
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        stage.show();
    }
    
    private void populateFieldsWithValues() {
        this.brand.setText(this.product.getBrand());
        this.model.setText(this.product.getModel());
        this.colour.setText(this.product.getColour());
        this.unitPrice.setText(String.valueOf(this.product.getUnitPrice()));
        this.availableQuantity.setText(String.valueOf(this.product.getAvailableQuantity()));
        this.specifications.setText(this.product.getSpecifications());
    }
    
    @FXML
    private void updateInventory(MouseEvent event) {
        this.success.setOpacity(0);
        this.errors.setOpacity(0);
        
        String brandValue = this.brand.getText();
        String modelValue = this.model.getText();
        String colourValue = this.colour.getText();
        
        double unitPriceValue;
        
        try {
            unitPriceValue = Double.parseDouble(this.unitPrice.getText());
        } catch (Exception exception) {
            unitPriceValue = 0;
        }
        
        int availableQuantityValue;
        
        try {
            availableQuantityValue = Integer.parseInt(this.availableQuantity.getText());
        } catch (Exception exception) {
            availableQuantityValue = 0;
        }
        
        String specificationsValue = this.specifications.getText();
        
        try {
            this.productController.updateProduct(this.product.getId(), brandValue, modelValue, colourValue, unitPriceValue, availableQuantityValue, specificationsValue);
            this.success.setText("Successfully updated inventory details!");
            this.success.setOpacity(1);
            this.dashboardViewModel.populateProductsTable();
        } catch (Exception exception) {
            this.errors.setText(exception.getMessage());
            this.errors.setOpacity(1);
        }
    }
    
}
