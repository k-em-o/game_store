package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;
import com.game_store.models.User;
import com.game_store.services.ApiClient;
import com.game_store.services.LoggedInUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

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
    private Button saveBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        User user = LoggedInUser.getUser();

        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }

        if (user != null) {
            helloLabel.setText("Hello, " + user.getUsername());
            nameField.setText(user.getUsername());
            emailField.setText(user.getEmail());
            mobileField.setText(user.getMobile());
            logoutBtn.setDisable(false);
        } else {
            helloLabel.setText("Hello, Gest");
            saveBtn.setDisable(true);
            logoutBtn.setDisable(true);
        }

    }

    // ===== Navigation =====
    public void goOrders(ActionEvent event) throws IOException {
        App.setRoot("myOrder");
    }

    public void goWishlist(ActionEvent event) throws IOException {
        App.setRoot("wishlist-view");
    }

    public void goPayments(ActionEvent event) throws IOException {
        App.setRoot("payments");
    }

    public void logout(ActionEvent event) throws IOException {
        LoggedInUser.logout();
        App.setRoot("home");
    }

    // ===== Save Profile =====
    public void saveProfile(ActionEvent event) {
        User user = LoggedInUser.getUser();
        if (user == null)
            return;

        user.setUsername(nameField.getText());
        user.setEmail(emailField.getText());
        user.setMobile(mobileField.getText());

        // بدل Text Blocks لو فيه مشاكل
        String json = "{ \"username\": \"" + user.getUsername() +
                "\", \"email\": \"" + user.getEmail() + "\" , \"mobile\": \"" + user.getMobile() + "\" }";

        boolean updated = ApiClient.updateUser(user.getId(), json);

        if (updated)
            System.out.println("✅ Profile Updated");
        else
            System.out.println("❌ Update Failed");
    }

}
