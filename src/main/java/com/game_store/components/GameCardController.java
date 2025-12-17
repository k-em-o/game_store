package com.game_store.components;

import java.io.IOException;

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

    private String gameId;

    public void setData(String id, String title, double price, int discount, String imageUrl) {
        this.gameId = id;

        // اسم اللعبة
        gameName.setText(title);

        // السعر
        if (price == 0) {
            gamePrice.setText("FREE");
        } else {
            double finalPrice = discount > 0
                    ? price - (price * discount / 100.0)
                    : price;

            gamePrice.setText("$" + String.format("%.2f", finalPrice));
        }

        // الصورة
        try {
            Image image = new Image(imageUrl, true);
            gameImage.setImage(image);
        } catch (Exception e) {
            System.out.println("⚠️ Image load failed: " + imageUrl);
        }

        // فتح التفاصيل
        rootCard.setOnMouseClicked(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/game_store/screens/gameDetails.fxml")
                );
                Parent root = loader.load();

                GameDetailsController controller = loader.getController();
                controller.setGameId(gameId);

                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
