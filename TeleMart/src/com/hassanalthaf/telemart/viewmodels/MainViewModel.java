/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.viewmodels;

import com.hassanalthaf.telemart.users.UserController;
import com.hassanalthaf.telemart.users.UserLoginResponseModes;
import com.hassanalthaf.telemart.users.UserState;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private Label errorBox;
    
    @FXML
    private TextField usernameBox;
    
    @FXML
    private PasswordField passwordBox;
    
    private UserController userController;

    @FXML
    private void loginClick(MouseEvent event) {
        this.login();
    }

    @FXML
    private void loginEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.login();
        }
    }
    
    private UserState userState;
    
    private void login() {
        String username = this.usernameBox.getText();
        String password = this.passwordBox.getText();
        
        UserLoginResponseModes returnMode = this.userController.login(username, password);
        
        this.usernameBox.setText("");
        this.passwordBox.setText("");
        
        switch (returnMode) {
            case DISABLED:
                this.errorBox.setText("Your account is disabled.");
                break;
            case INVALID:
                this.errorBox.setText("Wrong details entered.");
                break;
            case SUCCESS:
                try {
                    this.openDashboard();
                } catch (IOException exception) {
                    this.errorBox.setText("Failed to launch dashboard.");
                }
                break;
            default:
                this.errorBox.setText("Failed to login.");
                break;
        }
        
        this.errorBox.setOpacity(1);
    }
    
    private void openDashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/views/Dashboard.fxml"));
        Parent dashboard = fxmlLoader.load();
        DashboardViewModel dashboardViewModel = fxmlLoader.getController();
        dashboardViewModel.show(this.mainWindow, this.userState);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userState = new UserState();
        this.userController = new UserController(this.userState);
    }
}
