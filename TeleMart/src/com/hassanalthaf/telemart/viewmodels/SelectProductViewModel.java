/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import com.hassanalthaf.telemart.inventory.Product;
import com.hassanalthaf.telemart.inventory.ProductController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
public class SelectProductViewModel implements Initializable {
    @FXML
    private Parent selectProduct;
    
    @FXML
    private TableView productTableView;
    
    private DashboardViewModel dashboardViewModel;  
    private ProductController productController;
    private Stage stage;
    private List<Integer> selectedProducts;
    
    public void show(DashboardViewModel dashboardViewModel, List<Integer> selectedProducts) {
        this.selectedProducts = selectedProducts;
        this.dashboardViewModel = dashboardViewModel;
        this.productController = new ProductController();
        
        Scene scene = new Scene(this.selectProduct);
        
        this.stage = new Stage();
        this.stage.setTitle(Main.APPLICATION_TITLE);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        
        this.populateTableView();
        
        this.stage.show();
    }
    
    private void populateTableView() {
        this.productTableView.getItems().clear();
        this.productTableView.getItems().addAll(this.productController.fetchExcluding(this.selectedProducts));
    }
    
    private Product getSelectedRowObject() {
        return (Product)this.productTableView.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void select(MouseEvent event) {
        if (this.productTableView.getSelectionModel().getSelectedItem() != null) {
            this.dashboardViewModel.selectProduct(this.getSelectedRowObject());
            this.stage.close();
        }
    }
    
    @FXML
    private void view(MouseEvent event) throws IOException {
        if (this.productTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/ViewInventoryDetailsView.fxml"));
            Parent viewInventoryDetails = fxmlLoader.load();
            ViewInventoryDetailsViewModel viewInvetoryDetailsViewModel = fxmlLoader.getController();
            viewInvetoryDetailsViewModel.show(this.getSelectedRowObject());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
