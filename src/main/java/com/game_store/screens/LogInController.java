// LogInController.java
package com.game_store.screens;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.game_store.App;
import com.game_store.models.User;
import com.game_store.services.ApiClient;
import com.game_store.services.LoggedInUser;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class LogInController {

    @FXML private VBox imageContainer;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private BackgroundImage backgroundImage;

    @FXML
    public void initialize() {
        try {
            var imageStream = getClass().getResourceAsStream("/com/game_store/assets/Login.png");
            if(imageStream != null){
                Image image = new Image(imageStream);
                backgroundImage = new BackgroundImage(
                        image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false)
                );
                imageContainer.setBackground(new Background(backgroundImage));

                imageContainer.widthProperty().addListener(this::updateBackgroundSize);
                imageContainer.heightProperty().addListener(this::updateBackgroundSize);
            }
        } catch(Exception e){ System.out.println("Failed to load background: " + e.getMessage()); }

        loginButton.setOnAction(e -> {
            try { handleLogin(); } 
            catch (IOException ex) { ex.printStackTrace(); }
        });

        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        passwordField.textProperty().addListener((obs, oldText, newText) -> passwordTextField.setText(newText));
        passwordTextField.textProperty().addListener((obs, oldText, newText) -> passwordField.setText(newText));
    }

    private void updateBackgroundSize(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
        BackgroundSize bgSize = new BackgroundSize(imageContainer.getWidth(), imageContainer.getHeight(), false,false,false,false);
        BackgroundImage dynamicBg = new BackgroundImage(backgroundImage.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
        imageContainer.setBackground(new Background(dynamicBg));
    }

    public void goSignUp(ActionEvent event) throws IOException { App.setRoot("signUp"); }
    public void goHome(ActionEvent event) throws IOException { App.setRoot("home"); }

    private void handleLogin() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if(email.isEmpty() || password.isEmpty()){
            showTemporaryError("Please enter email and password");
            return;
        }

        User user = ApiClient.getUserByEmail(email);
        if(user == null || !BCrypt.checkpw(password, user.getPassword_hash())){
            showTemporaryError("Invalid email or password");
            return;
        }

        LoggedInUser.setUser(user); // حفظ بيانات المستخدم

        System.out.println("Login Success: " + user.getFull_name() + " Role: " + user.getUser_role());
        errorLabel.setText("");

        switch(user.getUser_role() != null ? user.getUser_role() : "user") {
            case "admin":
                App.setRoot("adminDashboard");
                break;

            case "developer":
                App.setRoot("developerDashboard");
                break;

            default:
                App.setRoot("home");
                break;
        }
    }

    private void showTemporaryError(String msg){
        errorLabel.setText(msg);
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(3));
        pause.setOnFinished(e -> errorLabel.setText(""));
        pause.play();
    }
}
