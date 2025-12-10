package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class HomeController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }
    }

    public void goTask() throws IOException {
        App.setRoot("taskMenu");
    }
}
