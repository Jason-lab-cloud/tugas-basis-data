package com.example.tugasbasisdata.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import com.example.tugasbasisdata.source.datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class siswa_controller {
    @FXML
    private Button Biodata;

    private int id;

    @FXML
    void RevealBiodata(){
        try (Connection c = datasource.getConnection()) {
            String query = "Select * from siswa where user_id = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load grade", e.toString());
        }
    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    }
