package com.game_store.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;

import com.game_store.models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameDetailsController implements Initializable {

    @FXML
    private Label gameTitle;
    @FXML
    private Label gamePrice; // ✅ Label جديد للسعر
    @FXML
    private ImageView gameImage;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextArea reviewArea;
    @FXML
    private Button buyButton;
    @FXML
    private Button addToCartButton;
    @FXML
    private Button addToWishlistButton;
    @FXML
    private Button backButton;

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Back button event
        if (backButton != null) {
            backButton.setOnAction(this::goBack);
        }
    }

    // تمرير اللعبة كاملة
    public void setGame(Game game) {
        this.game = game;
        loadGameDetails();
    }

    private void loadGameDetails() {
        if (game == null)
            return;

        // Game Title
        if (gameTitle != null) {
            gameTitle.setText(game.getTitle() != null ? game.getTitle() : "");
        }

        // Game Price
        if (gamePrice != null) {
            double price = game.getPrice();
            int discount = game.getDiscount();
            String priceText;

            if (price == 0) {
                priceText = "FREE";
            } else {
                double finalPrice = discount > 0 ? price - (price * discount / 100.0) : price;
                priceText = "$" + String.format("%.2f", finalPrice);
            }
            gamePrice.setText(priceText);
        }

        // Description
        if (descriptionArea != null) {
            String desc = game.getDescription();
            descriptionArea.setText(desc != null ? desc : "");
        }

        // Review
        // if (reviewArea != null) {
        // String review = game.getReview();
        // reviewArea.setText(review != null ? review : "");
        // }

        // Image
        if (gameImage != null) {
            try {
                String imgUrl = game.getCoverImage();
                if (imgUrl != null && !imgUrl.isEmpty()) {
                    Image image = new Image(imgUrl, true);
                    gameImage.setImage(image);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Image load failed: " + game.getCoverImage());
            }
        }
    }

    // ================= Hover Effects for Buttons =================
    @FXML
    private void hoverButton(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn != null) {
            btn.setStyle("-fx-opacity: 0.85; -fx-font-size: 14px; -fx-background-radius: 10;");
        }
    }

    @FXML
    private void exitButton(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn != null) {
            String id = btn.getId();
            if ("buyButton".equals(id)) {
                btn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
            } else if ("addToCartButton".equals(id)) {
                btn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
            } else if ("addToWishlistButton".equals(id)) {
                btn.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight: bold;");
            }
        }
    }

    // ================= Back Button =================
    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/game_store/screens/browse.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
