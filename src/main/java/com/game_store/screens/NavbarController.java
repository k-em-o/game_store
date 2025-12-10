package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;
import com.game_store.services.LoggedInUser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class NavbarController {

    private static NavbarController instance;

    public NavbarController() {
        instance = this;
    }

    public static NavbarController getInstance() {
        return instance;
    }

    @FXML
    private Button loginBtn;
    @FXML
    private Button signupBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TextField searchField;

    private StackPane contentArea;

    public void setContentArea(StackPane contentArea) {
        this.contentArea = contentArea;
        updateAuthButtons();
    }

    private void updateAuthButtons() {
        boolean loggedIn = LoggedInUser.getUser() != null;

        loginBtn.setVisible(!loggedIn);
        signupBtn.setVisible(!loggedIn);

        profileBtn.setVisible(loggedIn);
        logoutBtn.setVisible(loggedIn);
    }

    @FXML
    void goHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void goBrowse() throws IOException {
        App.setRoot("browse");
    }

    @FXML
    void goNews() throws IOException {
        App.setRoot("news");
    }

    @FXML
    void goAbout() throws IOException {
        App.setRoot("about");
    }

    @FXML
    void goProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    void login() throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    void signup() throws IOException {
        App.setRoot("signUp");
    }

    @FXML
    void logout() throws IOException {
        LoggedInUser.setUser(null);
        updateAuthButtons();
        App.setRoot("logIn");
    }
}
