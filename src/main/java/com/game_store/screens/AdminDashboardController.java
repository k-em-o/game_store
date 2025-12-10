package com.game_store.screens;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.models.User;
import com.game_store.services.ApiClient;
import com.game_store.services.LoggedInUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class AdminDashboardController {

    @FXML private Label adminNameLabel;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> roleFilter;
    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, String> colFullName;
    @FXML private TableColumn<User, String> colUsername;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, Void> colDelete;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // ===== عرض اسم الادمن =====
        User currentAdmin = LoggedInUser.getUser();
        if(currentAdmin != null)
            adminNameLabel.setText("Admin: " + currentAdmin.getFull_name());

        // ===== إعداد أعمدة الجدول =====
        colFullName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFull_name()));
        colUsername.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUsername()));
        colEmail.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));

        colRole.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUser_role()));
        colRole.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<String> combo = new ComboBox<>();
            {
                combo.getItems().addAll("admin","user","developer");
                combo.setOnAction(e -> {
                    User u = getTableView().getItems().get(getIndex());
                    if(u != null){
                        u.setUser_role(combo.getValue());
                        u.setIs_developer("developer".equals(combo.getValue()));
                        String json = "{\"user_role\":\"" + u.getUser_role() + "\",\"is_developer\":" + u.isIs_developer() + "}";
                        boolean success = ApiClient.updateUser(u.getId(), json);
                        if(!success) System.out.println("Failed to update role for: " + u.getUsername());
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item, empty);
                if(empty) setGraphic(null);
                else { combo.setValue(item); setGraphic(combo); }
            }
        });

        colDelete.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Delete");
            {
                btn.setOnAction(e -> {
                    User u = getTableView().getItems().get(getIndex());
                    if(u != null){
                        boolean success = ApiClient.deleteUser(u.getId());
                        if(success) getTableView().getItems().remove(u);
                        else System.out.println("Failed to delete: " + u.getUsername());
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // ===== Role Filter =====
        roleFilter.getItems().addAll("All","admin","user","developer");
        roleFilter.setValue("All");

        // ===== الأحداث =====
        searchField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                applyFilters();
            }
        });

        roleFilter.setOnAction(e -> applyFilters());

        // ===== تحميل المستخدمين =====
        loadUsers();

        // ===== توزيع الأعمدة على كامل الجدول =====
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void loadUsers(){
        try{
            String json = ApiClient.getUsers();
            User[] arr = new ObjectMapper().readValue(json, User[].class);
            userList.setAll(arr);
            usersTable.setItems(userList);
        } catch(Exception e){
            System.out.println("Load Users Failed: " + e.getMessage());
        }
    }

    private void applyFilters(){
        String search = searchField.getText().trim();
        String role = roleFilter.getValue();
        ObservableList<User> filtered = FXCollections.observableArrayList();

        for(User u : userList){
            boolean matchesSearch = search.isEmpty() || u.getUsername().toLowerCase().contains(search.toLowerCase());
            boolean matchesRole = role.equals("All") || u.getUser_role().equalsIgnoreCase(role);
            if(matchesSearch && matchesRole) filtered.add(u);
        }

        usersTable.setItems(filtered);
    }
}
