/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.preloader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author hassan
 */
public class PreloaderViewModel {
    @FXML
    private Label progressText;
    
    public void updateProgressText(String text) {
        this.progressText.setText(text);
    }
}
