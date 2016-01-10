/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.preloader;

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author hassan
 */
public class Main extends Preloader {
    private Stage stage;
    private int counter = 0;
    private final String[] MESSAGES = new String[]{
        "Loading View...",
        "Loading Assets...",
        "Loading Data...",
        "Launching application..."
    };
    
    private PreloaderViewModel preloaderViewModel;
 
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hassanalthaf/telemart/preloader/view/Preloader.fxml"));
        Parent preloader = fxmlLoader.load();
        this.preloaderViewModel = fxmlLoader.getController();
        
        Scene scene = new Scene(preloader);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        this.stage = stage;
    }
 
    @Override
    public void handleProgressNotification(ProgressNotification progressNotification) {}
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {}
 
    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof ProgressNotification) {
            this.preloaderViewModel.updateProgressText(this.MESSAGES[this.counter]);
            counter++;
        } else if (preloaderNotification instanceof StateChangeNotification) {
            stage.hide();
        }
    }  
}
