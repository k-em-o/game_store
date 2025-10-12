package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;

import javafx.event.ActionEvent;

public class TaskMenuController {

    public void goHome(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    public void goBrowse(ActionEvent event) throws IOException {
        App.setRoot("browse");
    }

    public void goNews(ActionEvent event) throws IOException {
        App.setRoot("news");
    }

    public void goAbout(ActionEvent event) throws IOException {
        App.setRoot("about");
    }
    public void goSignUp(ActionEvent event) throws IOException {
        App.setRoot("signUp");
    }
    public void goShoppingCart(ActionEvent event) throws IOException {
        App.setRoot("shoppingCart");
    }
    public void goProfile(ActionEvent event) throws IOException {
        App.setRoot("profile");
    }
    public void goLogIn(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }
    public void goGameDetails(ActionEvent event) throws IOException {
        App.setRoot("gameDetails");
    }
    public void goConfirmOrder(ActionEvent event) throws IOException {
        App.setRoot("confirmOrder");
    }
}
