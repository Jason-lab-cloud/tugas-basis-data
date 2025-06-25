package com.example.tugasbasisdata.controller;

import com.example.tugasbasisdata.HelloApplication;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.example.tugasbasisdata.controller.siswaController;
import java.io.IOException;
import com.example.tugasbasisdata.dtos.siswa;
import java.sql.*;
import com.example.tugasbasisdata.source.datasource;
import com.example.tugasbasisdata.controller.guruController;

public class loginPageController {

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField UsernameField;

    @FXML
    private void handleLogin() {
        String selectedRole = roleComboBox.getValue();
        String password = passwordField1.getText();
        String Username = UsernameField.getText();
        try (Connection c = datasource.getConnection()){
            HelloApplication app = HelloApplication.getApplicationInstance();
            FXMLLoader loader;
            Scene scene;
            System.out.println("jesus");
            PreparedStatement stmt = c.prepareStatement("select * from users where username = ? and password = ? and role = ?;") ;
            stmt.setString(1, Username);
            stmt.setString(2,password);
            String role;
            if (selectedRole.equalsIgnoreCase("teacher")){
                role = "guru";
            } else if (selectedRole.equalsIgnoreCase("student")){
                role = "siswa";
            }else role = "admin";
            stmt.setString(3, role);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int userid = rs.getInt("user_id");
                System.out.println("hello");
                if (selectedRole.equals("Admin")){
                    loader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                    scene = new Scene(loader.load());
                    app.getPrimaryStage().setScene(scene);
                    app.getPrimaryStage().setTitle("admin View");
                }
                else if (selectedRole.equals("Student")){
                    loader = new FXMLLoader(HelloApplication.class.getResource("siswa.fxml"));
                    scene = new Scene(loader.load());
                    app.getPrimaryStage().setScene(scene);
                    app.getPrimaryStage().setTitle("siswa View");
                    siswaController s = loader.getController();
                    s.setUserId(userid);
                }
                else {
                    app.getPrimaryStage().setTitle("guru View");
                    loader = new FXMLLoader(HelloApplication.class.getResource("guru.fxml"));
                    scene = new Scene(loader.load());
                    app.getPrimaryStage().setScene(scene);
                    guruController g = loader.getController();
                    g.setUserId(userid);
                    boolean a = g.isWaliKelas(userid);
                    g.setWaliKelas(a);
                }
            } else {
                showAlert("Login Success", "login sebagai " + selectedRole);
            }
        } catch (SQLException | IOException e) {
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
