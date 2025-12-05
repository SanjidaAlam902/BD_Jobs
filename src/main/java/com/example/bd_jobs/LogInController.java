package com.example.bd_jobs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LogInController {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label loginValidateLabel;

    private static final String USERS_FILE = "UserAccounts.txt";


    private static class User {
        String fullName;
        String email;
        String role;

        public User(String fullName, String email, String role) {
            this.fullName = fullName;
            this.email = email;
            this.role = role;
        }
    }

    @FXML
    public void initialize() {
        loginValidateLabel.setText("Please enter your email and password to log in.");
    }

    @FXML
    public void loginOnClick(ActionEvent actionEvent) {
        String email = usernameTextField.getText().trim();
        String password = passwordTextField.getText();

        if (email.isBlank() || password.isBlank()) {
            loginValidateLabel.setText("❌ Error: Both email and password are required.");
            return;
        }

        User authenticatedUser = authenticate(email, password);

        if (authenticatedUser != null) {
            loginValidateLabel.setText("✅ Success! Logged in as " + authenticatedUser.fullName + ".");
            showAlert("Login Successful", "Welcome, " + authenticatedUser.fullName + ". You are signed in as a " + authenticatedUser.role + ".");

            clearFields();
        }
        else {
            loginValidateLabel.setText("❌ Authentication Failed: Invalid email or password.");
        }
    }

    private User authenticate(String email, String password) {
        try {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                loginValidateLabel.setText("Error: User accounts file not found. Create an account first.");
                return null;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",", -1);

                if (tokens.length >= 5) {
                    String storedEmail = tokens[1].trim();
                    String storedPassword = tokens[4].trim();

                    if (storedEmail.equalsIgnoreCase(email) && storedPassword.equals(password)) {
                        scanner.close();
                        return new User(
                                tokens[0].trim(),
                                tokens[1].trim(),
                                tokens[2].trim()
                        );
                    }
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearFields() {
        usernameTextField.clear();
        passwordTextField.clear();
    }

    private void showAlert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }

    @FXML
    public void OnClickNavigateCreateAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CreateAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}