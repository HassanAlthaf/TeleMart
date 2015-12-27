/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author hassan
 */
public class MainViewModel implements Initializable {
    
    @FXML
    private Parent mainWindow;

    @FXML
    private void loginClick(MouseEvent event) {
        try {
            this.openDashboard();
        } catch (IOException exception) {
            
        }
    }

    @FXML
    private void loginEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                this.openDashboard();
            } catch (IOException exception) {

            }
        }
    }
    
    private void openDashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/Dashboard.fxml"));
        Parent dashboard = fxmlLoader.load();
        DashboardViewModel dashboardViewModel = fxmlLoader.getController();
        dashboardViewModel.show(this.mainWindow);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
