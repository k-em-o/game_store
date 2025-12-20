package com.game_store.components;

import java.io.IOException;

import com.game_store.App;
import com.game_store.models.Game;
import com.game_store.screens.GameDetailsController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    public void setData(Game game) {
        this.game = game;

        gameName.setText(game.getTitle());

        if (game.getPrice() == 0) {
            gamePrice.setText("FREE");
        } else {
            double finalPrice = game.getDiscount() > 0
                    ? game.getPrice() - (game.getPrice() * game.getDiscount() / 100.0)
                    : game.getPrice();

            gamePrice.setText("$" + String.format("%.2f", finalPrice));
        }

        try {
            gameImage.setImage(new Image(game.getCoverImage(), true));
        } catch (Exception e) {
            System.out.println("⚠️ Image load failed");
        }

        rootCard.setOnMouseClicked(this::openGameDetails);
    }

    private void openGameDetails(javafx.scene.input.MouseEvent e) {
    try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/game_store/screens/gameDetails.fxml"));

        Parent root = loader.load();

        GameDetailsController controller = loader.getController();
        controller.setGame(game);

        App.setRoot("gameDetails"); // ✅ نفس Scene بنفس CSS

    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

}
