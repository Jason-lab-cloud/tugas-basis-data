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
    public class AdminController {

        @FXML
        private TextField tfNamaSiswa, tfNIS, tfTanggalLahir;
        @FXML
        private TextField tfHari, tfJamMulai, tfJamSelesai, tfNamaGuru, tfPelajaran;
        @FXML
        private TextField tfNISPembagian, tfKelasPembagian;

        // Input Data Siswa
        @FXML
        public void simpanSiswa() {
            String sql = "INSERT INTO siswa (nis, nama, tanggal_lahir) VALUES (?, ?, ?)";

            try (Connection conn = datasource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, tfNIS.getText());
                stmt.setString(2, tfNamaSiswa.getText());
                stmt.setString(3, tfTanggalLahir.getText());
                stmt.executeUpdate();

                showAlert("Berhasil", "Data siswa berhasil disimpan.");
            } catch (SQLException e) {
                showAlert("Error", e.getMessage());
            }
        }

        // Input Jadwal Kelas
        @FXML
        public void simpanJadwal() {
            String sql = "INSERT INTO jadwal (hari, jam_mulai, jam_selesai, nama_guru, mata_pelajaran) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = datasource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, tfHari.getText());
                stmt.setString(2, tfJamMulai.getText());
                stmt.setString(3, tfJamSelesai.getText());
                stmt.setString(4, tfNamaGuru.getText());
                stmt.setString(5, tfPelajaran.getText());
                stmt.executeUpdate();

                showAlert("Berhasil", "Data jadwal berhasil disimpan.");
            } catch (SQLException e) {
                showAlert("Error", e.getMessage());
            }
        }

        // Bagi Kelas ke Siswa
        @FXML
        public void bagiKelas() {
            String sql = "INSERT INTO kelas_siswa (nis, kelas) VALUES (?, ?)";

            try (Connection conn = datasource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, tfNISPembagian.getText());
                stmt.setString(2, tfKelasPembagian.getText());
                stmt.executeUpdate();

                showAlert("Berhasil", "Siswa berhasil dimasukkan ke kelas.");
            } catch (SQLException e) {
                showAlert("Error", e.getMessage());
            }
        }

        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
}