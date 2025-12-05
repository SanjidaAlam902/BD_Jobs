package com.example.bd_jobs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountController {

    @FXML private TextField fullNameUserTextField;
    @FXML private TextField emailUserTextField;
    @FXML private ComboBox<String> userRoleComboBox;
    @FXML private DatePicker dobUserDatePicker;
    @FXML private TextField validateUserTextField; // Used for simple alerts
    @FXML private PasswordField createPasswordCreateAccountTextField;
    @FXML private TextArea userInfoValidateLabel; // Used for detailed output/success message

    // Constants
    private static final String USERS_FILE = "UserAccounts.txt";
    private static final List<String> USER_ROLES = Arrays.asList(
            "Candidate",
            "Recruiter",
            "Career Coach",
            "Sponsor",
            "IT Support"
    );

    @FXML
    public void initialize() {
        userRoleComboBox.setItems(FXCollections.observableArrayList(USER_ROLES));

        validateUserTextField.setText("");
        userInfoValidateLabel.setText("Please fill out the form to create your account.");
    }

    @FXML
    public void createAccountUserOnClick(ActionEvent actionEvent) {
        String fullName = fullNameUserTextField.getText().trim();
        String email = emailUserTextField.getText().trim();
        String role = userRoleComboBox.getValue();
        LocalDate dob = dobUserDatePicker.getValue();
        String password = createPasswordCreateAccountTextField.getText();

        if (!validateInput(fullName, email, role, dob, password)) {
            return;
        }

        String dobString = dob.format(DateTimeFormatter.ISO_DATE);

        String accountData = String.join(",",
                sanitizeCsvField(fullName),
                sanitizeCsvField(email),
                sanitizeCsvField(role),
                sanitizeCsvField(dobString),
                sanitizeCsvField(password)
        );

        if (saveAccountToFile(accountData)) {
            validateUserTextField.setText("✅ Account Created Successfully!");
            userInfoValidateLabel.setText(
                    "Welcome, " + fullName + "!\n" +
                            "Role: " + role + "\n" +
                            "Your account has been saved. You can now log in to your " + role + " dashboard."
            );
            clearFields();
        }
        else {
            validateUserTextField.setText("❌ Error: Failed to save account data.");
            userInfoValidateLabel.setText("A critical error occurred while writing to the storage file. Please check file permissions.");
        }
    }


    private boolean validateInput(String fullName, String email, String role, LocalDate dob, String password) {
        validateUserTextField.setText("");
        userInfoValidateLabel.setText("");

        if (fullName.isBlank() || email.isBlank() || role == null || dob == null || password.isBlank()) {
            validateUserTextField.setText("❌ Error: All fields are required.");
            userInfoValidateLabel.setText("Please ensure you have filled in your full name, email, selected a role, date of birth, and created a password.");
            return false;
        }

        if (!isValidEmail(email)) {
            validateUserTextField.setText("❌ Error: Invalid Email Format.");
            userInfoValidateLabel.setText("The provided email address is not in a valid format (e.g., user@example.com).");
            return false;
        }

        if (password.length() < 8) {
            validateUserTextField.setText("❌ Error: Password too short.");
            userInfoValidateLabel.setText("Password must be at least 8 characters long for security.");
            return false;
        }

        if (dob.isAfter(LocalDate.now())) {
            validateUserTextField.setText("❌ Error: Invalid Date of Birth.");
            userInfoValidateLabel.setText("Date of Birth cannot be in the future.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean saveAccountToFile(String accountData) {
        try (FileWriter fw = new FileWriter(USERS_FILE, true)) {
            fw.write(accountData + System.lineSeparator());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearFields() {
        fullNameUserTextField.clear();
        emailUserTextField.clear();
        createPasswordCreateAccountTextField.clear();
        userRoleComboBox.getSelectionModel().clearSelection();
        dobUserDatePicker.setValue(null);
    }

    private String sanitizeCsvField(String s) {
        if (s == null) return "";
        return s.replace(",", " ").replace("\n", " ").replace("\r", " ").trim();
    }

    @FXML
    public void OnClickNavigateLogIn(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}