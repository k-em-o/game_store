// WishlistController.java
package com.game_store.screens;

import java.io.IOException;
import java.util.List;

import com.game_store.App;
import com.game_store.models.Game;
import com.game_store.models.User;
import com.game_store.services.LoggedInUser;
import com.game_store.services.WishlistService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WishlistController {

    @FXML
    private StackPane contentArea;

    @FXML
    private VBox wishlistContainer;

    @FXML
    public void initialize() {
        NavbarController navbar = NavbarController.getInstance();
        if (navbar != null) {
            navbar.setContentArea(contentArea);
        }
        loadWishlist();
    }

    private void loadWishlist() {
        wishlistContainer.getChildren().clear();
        User user = LoggedInUser.getUser();
        List<Game> games = WishlistService.getWishlistGames(user.getId());

        for (Game game : games) {
            wishlistContainer.getChildren().add(createWishlistCard(game));
        }
    }

    private HBox createWishlistCard(Game game) {

        ImageView imageView = new ImageView(new Image(game.getCoverImage()));
        imageView.setFitWidth(120);
        imageView.setFitHeight(160);

        Label title = new Label(game.getTitle());
        title.getStyleClass().add("wishlist-title");

        Label price = new Label("$" + game.getPrice());
        price.getStyleClass().add("wishlist-price");

        Button addToCart = new Button("Add to Cart");
        addToCart.setOnAction(e -> WishlistService.addToCart(game));

        Button remove = new Button("Remove");
        remove.setOnAction(e -> {
            User user = LoggedInUser.getUser();
            WishlistService.removeItem(user.getId(), game.getId());
            loadWishlist();
        });

        VBox infoBox = new VBox(10, title, price, addToCart, remove);
        HBox card = new HBox(20, imageView, infoBox);
        card.getStyleClass().add("wishlist-card");

        return card;
    }

    // ===== Navigation =====
    public void goOrders(ActionEvent event) throws IOException {
        App.setRoot("myOrder");
    }

    public void goPayments(ActionEvent event) throws IOException {
        App.setRoot("payments");
    }

    public void goProfile(ActionEvent event) throws IOException {
        App.setRoot("profile");
    }
}
