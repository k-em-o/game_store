package com.game_store.components;

import java.io.IOException;

import com.game_store.models.Game;
import com.game_store.screens.GameDetailsController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    // ✅ Game كامل
    private Game game;

    // ================== Set Data ==================
    public void setData(Game game) {
        this.game = game;

        // اسم اللعبة
        gameName.setText(game.getTitle());

        // السعر
        if (game.getPrice() == 0) {
            gamePrice.setText("FREE");
        } else {
            double finalPrice = game.getDiscount() > 0
                    ? game.getPrice() - (game.getPrice() * game.getDiscount() / 100.0)
                    : game.getPrice();

            gamePrice.setText("$" + String.format("%.2f", finalPrice));
        }

        // الصورة
        try {
            Image image = new Image(game.getCoverImage(), true);
            gameImage.setImage(image);
        } catch (Exception e) {
            System.out.println("⚠️ Image load failed: " + game.getCoverImage());
        }

        // فتح صفحة التفاصيل عند الضغط
        rootCard.setOnMouseClicked(e -> openGameDetails(e));
    }

    // ================== Open Game Details ==================
    private void openGameDetails(javafx.scene.input.MouseEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/game_store/screens/GameDetails.fxml"));
            Parent root = loader.load();

            // تمرير اللعبة كاملة
            GameDetailsController controller = loader.getController();
            controller.setGame(game);

            // نفس Stage
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
