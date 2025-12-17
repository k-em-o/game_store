package com.game_store.screens;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class ShoppingCartController {
    @FXML
    private StackPane contentArea;
    
    @FXML
    public void initialize() {
        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }
    }
}
