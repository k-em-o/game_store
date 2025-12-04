package com.game_store.screens;

import java.io.IOException;

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

    @FXML
    private VBox imageContainer;
    @FXML
    private TextField fullName;
    @FXML
    private TextField username;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox showPasswordCheckBox;
    @FXML
    private CheckBox policyCheckBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;

    private BackgroundImage backgroundImage;

    @FXML
    public void initialize() {
        // إعداد الخلفية
        Image image = new Image(getClass().getResourceAsStream("/com/game_store/assets/Login.png"));
        backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false));
        imageContainer.setBackground(new Background(backgroundImage));

        imageContainer.widthProperty().addListener(this::updateBackgroundSize);
        imageContainer.heightProperty().addListener(this::updateBackgroundSize);

        // زرار Submit
        loginButton.setOnAction(e -> {
            try {
                handleSignUp();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // show / hide password
        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());

        passwordField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        passwordField.textProperty().addListener((o, old, val) -> passwordTextField.setText(val));
        passwordTextField.textProperty().addListener((o, old, val) -> passwordField.setText(val));
    }

    private void updateBackgroundSize(ObservableValue<?> obs, Number oldVal, Number newVal) {
        BackgroundSize bgSize = new BackgroundSize(
                imageContainer.getWidth(), imageContainer.getHeight(),
                false, false, false, false);

        BackgroundImage dynamicBg = new BackgroundImage(
                backgroundImage.getImage(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bgSize);

        imageContainer.setBackground(new Background(dynamicBg));
    }

    // ================================
    // SIGN UP FUNCTION
    // ================================
    private void handleSignUp() throws IOException {
        String name = fullName.getText();
        String user = username.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validation
        if (name.isEmpty() || user.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields are required");
            // اختفاء الرسالة بعد 3 ثواني
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(3));
            pause.setOnFinished(e -> errorLabel.setText(""));
            pause.play();
            return;
        }

        if (!policyCheckBox.isSelected()) {
            errorLabel.setText("You must accept the policy");
            // اختفاء الرسالة بعد 3 ثواني
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(3));
            pause.setOnFinished(e -> errorLabel.setText(""));
            pause.play();
            return;
        }

        // Hash password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // User object
        User newUser = new User(
                email,
                user, // username
                name, // full_name
                "Unknown", // country
                hashedPassword // password_hash
        );

        // Send API request
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(newUser);

        boolean success = ApiClient.createUser(body);

        if (!success) {
            errorLabel.setText("Account creation failed");
            // اختفاء الرسالة بعد 3 ثواني
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(3));
            pause.setOnFinished(e -> errorLabel.setText(""));
            pause.play();
            return;
        }

        // Clear error and navigate home
        errorLabel.setText("");
        App.setRoot("home");
    }

    // ================================
    // NAVIGATION
    // ================================
    public void goLogIn() throws IOException {
        App.setRoot("logIn");
    }

    public void goHome() throws IOException {
        App.setRoot("home");
    }
}
