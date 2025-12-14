package com.game_store.screens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GameDetailsController implements Initializable {

    @FXML
    private Label gameTitle;

    private String gameId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // فاضي مؤقتًا
    }

    // ✅ الميثود اللي كانت ناقصة
    public void setGameId(String gameId) {
        this.gameId = gameId;
        loadGameDetails();
    }

    private void loadGameDetails() {
        // مؤقتًا للتجربة
        if (gameTitle != null) {
            gameTitle.setText("Game ID: " + gameId);
        }
    }
}
