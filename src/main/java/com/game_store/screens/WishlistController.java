package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;
import com.game_store.models.WishlistItem;
import com.game_store.services.WishlistService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WishlistController {

        @FXML
        private VBox wishlistContainer;
        @FXML
        private StackPane contentArea;

        private final WishlistService service = new WishlistService();

        @FXML
        public void initialize() {
                NavbarController navbar = NavbarController.getInstance();
                if (navbar != null) {
                        navbar.setContentArea(contentArea);
                }
                loadWishlist();
        }

        public void goProfile(ActionEvent event) throws IOException {
                App.setRoot("profile");
        }
        public void goOrders(ActionEvent event) throws IOException {
                App.setRoot("myOrder");
        }


        public void goPayments(ActionEvent event) throws IOException {
                App.setRoot("payments");
        }


        private void loadWishlist() {
                wishlistContainer.getChildren().clear();

                for (WishlistItem item : service.getWishlist()) {

                        /* ========= CARD ========= */
                        VBox card = new VBox(15);
                        card.setPrefWidth(720);
                        card.setStyle(
                                        "-fx-background-color: #151B34;" +
                                                        "-fx-padding: 18;" +
                                                        "-fx-background-radius: 18;");

                        /* ========= TOP ROW (IMAGE + NAME + PRICE) ========= */
                        HBox topRow = new HBox(15);
                        topRow.setAlignment(Pos.TOP_LEFT);

                        // IMAGE (LOCAL)
                        ImageView image = new ImageView(
                                        new Image(getClass().getResourceAsStream(item.getImagePath())));
                        image.setFitWidth(120);
                        image.setFitHeight(120);
                        image.setPreserveRatio(true);

                        // NAME + PRICE COLUMN
                        VBox infoBox = new VBox(6);
                        infoBox.setAlignment(Pos.TOP_LEFT);

                        Label title = new Label(item.getTitle());
                        title.setStyle(
                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 20;" +
                                                        "-fx-font-weight: bold;");

                        Label price = new Label("$" + item.getPrice());
                        price.setStyle(
                                        "-fx-text-fill: #5A6CFF;" +
                                                        "-fx-font-size: 18;");

                        infoBox.getChildren().addAll(title, price);

                        Region spacer = new Region();
                        HBox.setHgrow(spacer, Priority.ALWAYS);

                        topRow.getChildren().addAll(image, infoBox, spacer);

                        /* ========= BUTTONS ========= */
                        HBox buttons = new HBox(12);
                        buttons.setAlignment(Pos.CENTER_RIGHT);

                        Button addToCart = new Button("Add to Cart");
                        addToCart.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #151B34;" +
                                                        "-fx-background-radius: 18;" +
                                                        "-fx-padding: 6 22;");

                        Button remove = new Button("Remove");
                        remove.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-border-color: #5A6CFF;" +
                                                        "-fx-border-radius: 18;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-padding: 6 22;");

                        buttons.getChildren().addAll(addToCart, remove);

                        card.getChildren().addAll(topRow, buttons);
                        wishlistContainer.getChildren().add(card);
                }
        }
}
