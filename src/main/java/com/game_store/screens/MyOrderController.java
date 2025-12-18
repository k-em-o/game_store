package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class MyOrderController {
       @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {

        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }

    }

    // ===== Navigation =====
    public void goPayments(ActionEvent event) throws IOException {
        App.setRoot("payments");
    }
    public void goWishlist(ActionEvent event) throws IOException {
        App.setRoot("wishlist-view");
    }

    public void goProfile(ActionEvent event) throws IOException {
        App.setRoot("profile");
    }

}
