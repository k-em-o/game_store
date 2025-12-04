package com.game_store.screens;

import java.io.IOException;

import com.game_store.App;

import javafx.event.ActionEvent;

public class HomeController {

    public void goTask(ActionEvent event) throws IOException {
        App.setRoot("taskMenu");
    }

}