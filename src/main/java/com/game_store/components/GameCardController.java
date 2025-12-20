package com.game_store.components;

import java.io.IOException;

import com.game_store.models.Game;
import com.game_store.screens.GameDetailsController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameCardController {

    @FXML
    private AnchorPane rootCard;

    @FXML
    private ImageView gameImage;

    @FXML
    private Label gameName;

    @FXML
    private Label gamePrice;

    private Game game;

    // ================== SET DATA ==================
    public void setData(Game game) {
        this.game = game;

        // Title
        gameName.setText(game.getTitle());

        // Price
        if (game.getPrice() == 0) {
            gamePrice.setText("FREE");
        } else {
            double finalPrice = game.getDiscount() > 0
                    ? game.getPrice() - (game.getPrice() * game.getDiscount() / 100.0)
                    : game.getPrice();
            gamePrice.setText("$" + String.format("%.2f", finalPrice));
        }

        // Image
        try {
            gameImage.setImage(new Image(game.getCoverImage(), true));
        } catch (Exception e) {
            System.out.println("⚠️ Image load failed: " + game.getCoverImage());
        }

        // Click event to open details
        rootCard.setOnMouseClicked(this::openGameDetails);
    }

    // ================== OPEN GAME DETAILS ==================
    private void openGameDetails(javafx.scene.input.MouseEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/game_store/screens/gameDetails.fxml")
            );

            // Load FXML
            Parent root = loader.load();

            // Get controller and pass the Game object
            GameDetailsController controller = loader.getController();
            controller.setGame(game);

            // Set the new root in the existing Scene (keeps stylesheets)
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
