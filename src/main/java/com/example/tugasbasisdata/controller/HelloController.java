package com.example.tugasbasisdata.controller;

import com.example.tugasbasisdata.HelloApplication;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.*;
import com.example.tugasbasisdata.source.datasource;
public class HelloController {

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField UsernameField;

    @FXML
    private void handleLogin() {
        HelloApplication app = HelloApplication.getApplicationInstance();
        FXMLLoader loader;
        Scene scene;
        String selectedRole = roleComboBox.getValue();
        String password = passwordField.getText();
        String Username = UsernameField.getText();
        try (Connection c = datasource.getConnection()){
            PreparedStatement stmt = c.prepareStatement("select * from users where username = ? and password = ? and role = ?;") ;
            stmt.setString(1, Username);
            stmt.setString(2,password);
            stmt.setString(3, selectedRole.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                if (selectedRole.equals("admin")){

                }
                else if (selectedRole.equals("siswa")){

                }
                else {

                }
            } else {
                showAlert("Login Success", "login sebagai " + selectedRole);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
