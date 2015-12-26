/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
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
    private AnchorPane anotherPage;
    
    @FXML
    private AnchorPane differentPage;
    
    private AnchorPane currentPage;
    
    private void changePage(AnchorPane page) {
        this.currentPage.setOpacity(0);
        this.currentPage = page;
        this.currentPage.toFront();
        this.currentPage.setOpacity(1);
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
            case "anotherPageMenuItem":
                this.changePage(this.anotherPage);
                break;
            case "differentPageMenuItem":
                this.changePage(this.differentPage);
                break;
            default:
                this.changePage(this.home);
                break;
        }
    }
    
    public void show(Parent main) {
        Scene scene = new Scene(this.dashboard);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Main.APPLICATION_TITLE);
        stage.setResizable(false);
        stage.show();
        
        Stage mainStage = (Stage)main.getScene().getWindow();
        mainStage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.currentPage = this.home;
    }    
    
}
