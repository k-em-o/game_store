package com.game_store.screens;

import com.game_store.models.User;
import com.game_store.services.LoggedInUser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private Label helloLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mobileField;

    @FXML
    public void initialize() {

        User user = LoggedInUser.getUser();

        if (user != null) {
            helloLabel.setText("Hello " + user.getUsername());
            nameField.setText(user.getUsername());
            emailField.setText(user.getEmail());
            mobileField.setText("+20XXXXXXXXX"); // مؤقت
        } else {
            helloLabel.setText("Hello Guest");
        }
    }
}
