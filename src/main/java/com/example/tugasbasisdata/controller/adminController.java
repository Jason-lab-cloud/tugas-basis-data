package com.example.tugasbasisdata.controller;
import com.example.tugasbasisdata.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import com.example.tugasbasisdata.source.datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.tugasbasisdata.dtos.admin;
import com.example.tugasbasisdata.dtos.siswa;
import com.example.tugasbasisdata.dtos.user;
import com.example.tugasbasisdata.dtos.kelas;
import com.example.tugasbasisdata.dtos.jadwal;
import javafx.scene.control.TextField;

public class adminController {
    @FXML
    private TextField kelas_id;

    @FXML
    private TextField nama_kelas;

    @FXML
    private TextField ht_id;

    @FXML
    public void addclass(){
        HelloApplication app = HelloApplication.getApplicationInstance();
        FXMLLoader loader;
        Scene scene;
        int id = Integer.parseInt(kelas_id.getText());
        String name = nama_kelas.getText();
        int home = Integer.parseInt(ht_id.getText());
        try (Connection c = datasource.getConnection()){
            PreparedStatement stmt = c.prepareStatement("insert into kelas (kelas_id, nama_kelas, homeroom_teacher_id) values (?, ?, ?)") ;
            stmt.setInt(1, id);
            stmt.setString(2,name);
            stmt.setInt(3, home);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
