package com.example.tugasbasisdata;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HelloController {

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String selectedRole = roleComboBox.getValue();
        String password = passwordField.getText();

        if (selectedRole == null || password.isEmpty()) {
            showAlert("Login Failed", "role dan password harus diisi.");
        } else {
            showAlert("Login Success", "login sebagai " + selectedRole);
        }
    }

    @FXML
    private void handleReport() {
        showAlert("Report", "fungsi report dipanggil.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
