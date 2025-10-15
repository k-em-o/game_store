package com.game_store.screens;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.game_store.DatabaseConnection;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HomeController {

    // @FXML
    // private ListView<String> categoryList; // لازم تكون موجودة في home.fxml

    // @FXML
    // public void initialize() {
    //     loadCategories();
    // }

    // private void loadCategories() {
    //     try (Connection conn = DatabaseConnection.connect()) {
    //         if (conn != null) {
    //             String query = "SELECT name FROM categories"; // عدل حسب اسم الجدول
    //             Statement stmt = conn.createStatement();
    //             ResultSet rs = stmt.executeQuery(query);

    //             while (rs.next()) {
    //                 String categoryName = rs.getString("name");
    //                 categoryList.getItems().add(categoryName);
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
