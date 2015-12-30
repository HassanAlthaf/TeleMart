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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hassan
 */
public class ViewInventoryDetailsViewModel implements Initializable {

    private ProductController productController;
    private Product product;
    
    @FXML
    private Parent viewInventoryDetails;
    
    @FXML
    private Label brand;
    
    @FXML
    private Label model;
    
    @FXML
    private Label colour;
    
    @FXML
    private Label unitPrice;
    
    @FXML
    private Label availableQuantity;
    
    @FXML
    private Text specifications;
    
    public ViewInventoryDetailsViewModel() {
        this.productController = new ProductController();
    }
    
    public void show(Product product) {
        this.product = product;
        
        Scene scene = new Scene(this.viewInventoryDetails);
        
        Stage stage = new Stage();
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        this.loadData();
        stage.show();
    }
    
    public void loadData() {
        this.brand.setText(this.product.getBrand());
        this.model.setText(this.product.getModel());
        this.colour.setText(this.product.getColour());
        this.unitPrice.setText(String.valueOf(this.product.getUnitPrice()));
        this.availableQuantity.setText(String.valueOf(this.product.getAvailableQuantity()));
        this.specifications.setText(this.product.getSpecifications());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
