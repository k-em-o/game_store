package com.game_store.screens;

import com.game_store.services.ApiClient;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class DeveloperDashboardController {

    @FXML
    private StackPane contentArea;

    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField platformField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField coverUrlField;
    @FXML
    private TextField storeUrlField;
    @FXML
    private CheckBox publishedCheck;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }
    }

    @FXML
    private void handleAddGame() {

        try {
            String json = "{ " +
                    "\"title\": \"" + titleField.getText() + "\"," +
                    "\"description\": \"" + descriptionField.getText() + "\"," +
                    "\"price\": " + priceField.getText() + "," +
                    "\"discount_percentage\": " +
                    (discountField.getText().isEmpty() ? "0" : discountField.getText()) + "," +
                    "\"platform\": \"" + platformField.getText() + "\"," +
                    "\"category_id\": \"" + categoryField.getText() + "\"," +
                    "\"cover_image_url\": \"" + coverUrlField.getText() + "\"," +
                    "\"store_url\": \"" + storeUrlField.getText() + "\"," +
                    "\"is_published\": " + publishedCheck.isSelected() +
                    " }";

            boolean success = ApiClient.createGame(json);

            if (success) {
                statusLabel.setText("✅ Game added successfully");
                javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                        javafx.util.Duration.seconds(3));
                pause.setOnFinished(e -> statusLabel.setText(""));
                pause.play();
                clearForm();
            } else {
                statusLabel.setText("❌ Failed to add game");

                javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                        javafx.util.Duration.seconds(3));
                pause.setOnFinished(e -> statusLabel.setText(""));
                pause.play();
            }

        } catch (Exception e) {
            statusLabel.setText("⚠ Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        titleField.clear();
        descriptionField.clear();
        priceField.clear();
        discountField.clear();
        platformField.clear();
        categoryField.clear();
        coverUrlField.clear();
        storeUrlField.clear();
        publishedCheck.setSelected(false);
    }
}
