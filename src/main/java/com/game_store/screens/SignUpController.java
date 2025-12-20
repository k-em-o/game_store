package com.game_store.screens;

import java.io.IOException;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_store.App;
import com.game_store.models.User;
import com.game_store.services.ApiClient;

import javafx.beans.value.ObservableValue;
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

public class SignUpController {

    @FXML private VBox imageContainer;
    @FXML private TextField fullName;
    @FXML private TextField username;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private CheckBox policyCheckBox;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private BackgroundImage backgroundImage;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/game_store/assets/Login.png"));
        backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100,100,true,true,true,false));
        imageContainer.setBackground(new Background(backgroundImage));

        imageContainer.widthProperty().addListener(this::updateBackgroundSize);
        imageContainer.heightProperty().addListener(this::updateBackgroundSize);

        loginButton.setOnAction(e -> { 
            try { handleSignUp(); } catch(IOException ex){ ex.printStackTrace(); } 
        });

        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        passwordField.textProperty().addListener((o, old, val) -> passwordTextField.setText(val));
        passwordTextField.textProperty().addListener((o, old, val) -> passwordField.setText(val));
    }

    private void updateBackgroundSize(ObservableValue<?> obs, Number oldVal, Number newVal){
        BackgroundSize bgSize = new BackgroundSize(imageContainer.getWidth(), imageContainer.getHeight(), false,false,false,false);
        BackgroundImage dynamicBg = new BackgroundImage(backgroundImage.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
        imageContainer.setBackground(new Background(dynamicBg));
    }

    private void handleSignUp() throws IOException {
        String name = fullName.getText().trim();
        String user = username.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if(name.isEmpty() || user.isEmpty() || email.isEmpty() || password.isEmpty()){
            showTemporaryError("All fields are required");
            return;
        }

        if(!policyCheckBox.isSelected()){
            showTemporaryError("You must accept the policy");
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // ✅ توليد UUID للمستخدم الجديد
        String userId = UUID.randomUUID().toString();

        User newUser = new User(
            userId,      // ID غير فارغ
            email, 
            user, 
            name, 
            "",          // mobile
            "Unknown",   // country
            hashedPassword, 
            "user"       // role
        );

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(newUser);

        boolean success = ApiClient.createUser(body);
        if(!success){
            showTemporaryError("Account creation failed");
            return;
        }

        errorLabel.setText("");
        App.setRoot("home");
    }

    private void showTemporaryError(String msg){
        errorLabel.setText(msg);
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(3));
        pause.setOnFinished(e -> errorLabel.setText(""));
        pause.play();
    }

    public void goLogIn() throws IOException { App.setRoot("logIn"); }
    public void goHome() throws IOException { App.setRoot("home"); }
}
