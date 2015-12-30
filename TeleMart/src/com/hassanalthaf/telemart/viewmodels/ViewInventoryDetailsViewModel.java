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
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
