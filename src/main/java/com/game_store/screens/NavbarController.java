package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;
import com.game_store.services.LoggedInUser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class NavbarController {

    private static NavbarController instance;

    public NavbarController() {
        instance = this;
    }

    public static NavbarController getInstance() {
        return instance;
    }

    // ===== AUTH BOX (Login / SignUp) =====
    @FXML
    private HBox authBox;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    // ===== USER BOX (Profile / Cart) =====
    @FXML
    private HBox userBox;

    @FXML
    private Button profileBtn;

    @FXML
    private Button cartBtn;

    // ===== SEARCH =====
    @FXML
    private TextField searchField;

    private StackPane contentArea;

    public void setContentArea(StackPane contentArea) {
        this.contentArea = contentArea;
        updateAuthButtons();
    }

    // =========================
    // AUTH VISIBILITY HANDLING
    // =========================
    private void updateAuthButtons() {
        boolean loggedIn = LoggedInUser.getUser() != null;

        authBox.setVisible(!loggedIn);
        authBox.setManaged(!loggedIn);

        userBox.setVisible(loggedIn);
        userBox.setManaged(loggedIn);
    }

    // =========================
    // NAVIGATION
    // =========================
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
    void goCart() throws IOException {
        App.setRoot("shoppingCart");
    }

    // =========================
    // LOGOUT (لو احتاجته لاحقًا)
    // =========================
    @FXML
    void logout() throws IOException {
        LoggedInUser.setUser(null);
        updateAuthButtons();
        App.setRoot("logIn");
    }
}
