package com.game_store.screens;

import java.net.URL;
import java.util.ResourceBundle;

import com.game_store.models.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameDetailsController implements Initializable {

    // ================= UI =================
    @FXML
    private Label gameTitle;

    @FXML
    private Label gamePrice;

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

        @FXML
    private StackPane contentArea;
    // ================= DATA =================
    private Game game;

    // ================= INIT =================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }
        if (backButton != null) {
            backButton.setOnAction(this::goBack);
        }
    }

    // ================= SET GAME =================
    public void setGame(Game game) {
        this.game = game;
        loadGameDetails();
    }

    // ================= LOAD DATA =================
    private void loadGameDetails() {
        if (game == null) return;

        // Title
        gameTitle.setText(
                game.getTitle() != null ? game.getTitle() : ""
        );

        // Price
        double price = game.getPrice();
        int discount = game.getDiscount();

        if (price == 0) {
            gamePrice.setText("FREE");
        } else {
            double finalPrice =
                    discount > 0 ? price - (price * discount / 100.0) : price;
            gamePrice.setText("$" + String.format("%.2f", finalPrice));
        }

        // Description
        descriptionArea.setText(
                game.getDescription() != null ? game.getDescription() : ""
        );

        // Image
        try {
            String imgUrl = game.getCoverImage();
            if (imgUrl != null && !imgUrl.isEmpty()) {
                gameImage.setImage(new Image(imgUrl, true));
            }
        } catch (Exception e) {
            System.out.println("⚠ Image load failed");
        }
    }

    // ================= HOVER EFFECTS =================
    @FXML
    private void hoverButton(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setStyle(
                "-fx-opacity: 0.85;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10;"
        );
    }

    @FXML
    private void exitButton(javafx.scene.input.MouseEvent event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        if ("buyButton".equals(id)) {
            btn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        } else if ("addToCartButton".equals(id)) {
            btn.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        } else if ("addToWishlistButton".equals(id)) {
            btn.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black;");
        }
    }

    // ================= BACK =================
    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/game_store/screens/browse.fxml")
            );

            Parent root = loader.load();

            Stage stage =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
