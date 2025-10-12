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
      
}
