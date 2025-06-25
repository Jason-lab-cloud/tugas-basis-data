package com.example.tugasbasisdata.controller;
import javafx.fxml.FXML;
import com.example.tugasbasisdata.source.datasource;

import java.sql.*;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class adminController {
    @FXML private TextField tfID, tfkelas;
    @FXML private TextField  tfNIS, tfNama, tfEmail, tfTanggalLahir;
    @FXML private TextField tfGender, tfAlamat, tfTahunMasuk, tfUsername, tfPassword;
    @FXML private TextField tfNamaKelas, tfWaliKelasId;

    // Input Jadwal
    @FXML private TextField tfHari, tfJamMulai, tfJamSelesai;
    @FXML private TextField tfClassID, tfGuruID, tfMapelID;

    // UI Feedback
    @FXML private Label lblStatus;

    private void getsiswa(){

    }
    @FXML
    public void simpanSiswa() {
        String insertUser = "INSERT INTO users (user_id, username, password, role) VALUES (?, ?, ?, ?)";
        String insertSiswa = "INSERT INTO siswa (siswa_id, name, birth_date, address, gender, joining_year, email, user_id, nis, class_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        String getNextUserId = "SELECT COALESCE(MAX(user_id), 0) + 1 FROM users";

        try (Connection conn = datasource.getConnection()) {
            conn.setAutoCommit(false);

            // 1. Ambil user_id terbaru
            int userId = 0;
            try (PreparedStatement nextStmt = conn.prepareStatement(getNextUserId)) {
                ResultSet rs = nextStmt.executeQuery();
                if (rs.next()) userId = rs.getInt(1);
            }

            // 2. Insert ke tabel users
            PreparedStatement userStmt = conn.prepareStatement(insertUser);
            userStmt.setInt(1, userId);
            userStmt.setString(2, tfUsername.getText());
            userStmt.setString(3, tfPassword.getText());
            userStmt.setString(4, "siswa");
            userStmt.executeUpdate();

            // 3. Insert ke tabel siswa
            PreparedStatement siswaStmt = conn.prepareStatement(insertSiswa);
            siswaStmt.setInt(1, Integer.parseInt(tfID.getText()));
            siswaStmt.setString(2, tfNama.getText());
            siswaStmt.setDate(3, Date.valueOf(tfTanggalLahir.getText()));
            siswaStmt.setString(4, tfAlamat.getText());
            siswaStmt.setString(5, tfGender.getText());
            siswaStmt.setInt(6, Integer.parseInt(tfTahunMasuk.getText()));
            siswaStmt.setString(7, tfEmail.getText());
            siswaStmt.setInt(8, userId);
            siswaStmt.setString(9,tfNIS.getText());
            siswaStmt.setInt(10,Integer.parseInt(tfkelas.getText()));
            siswaStmt.executeUpdate();

            conn.commit();
            lblStatus.setText(" Siswa berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText(" Gagal simpan siswa: " + e.getMessage());
        }
    }

    @FXML
    public void simpanJadwal() {
        String getNextId = "SELECT COALESCE(MAX(scedule_id), 0) + 1 FROM jadwal";
        String insertSQL = "INSERT INTO jadwal (scedule_id, start_time, end_time, class_id, teacher_id, subject_id, day) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = datasource.getConnection()) {
            // 1. Ambil next ID
            int nextId = 0;
            try (PreparedStatement stmt = conn.prepareStatement(getNextId)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) nextId = rs.getInt(1);
            }

            // 2. Insert jadwal baru
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setInt(1, nextId);
                stmt.setTime(2, Time.valueOf(tfJamMulai.getText()));
                stmt.setTime(3, Time.valueOf(tfJamSelesai.getText()));
                stmt.setInt(4, Integer.parseInt(tfClassID.getText()));
                stmt.setInt(5, Integer.parseInt(tfGuruID.getText()));
                stmt.setInt(6, Integer.parseInt(tfMapelID.getText()));
                stmt.setInt(7, Integer.parseInt(tfHari.getText()));

                stmt.executeUpdate();
                lblStatus.setText(" Jadwal berhasil ditambahkan dengan ID: " + nextId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText(" Gagal simpan jadwal: " + e.getMessage());
        }
    }

    @FXML
    public void bagiKelasKeSiswa(int siswaId, int kelasId) {
        String sql = "UPDATE siswa SET class_id = ? WHERE siswa_id = ?";

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kelasId);
            stmt.setInt(2, siswaId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                lblStatus.setText("Kelas berhasil dibagikan ke siswa.");
            } else {
                lblStatus.setText("Siswa tidak ditemukan.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText("Gagal membagi kelas: " + e.getMessage());
        }
    }
    @FXML
    public void tambahKelas() {
        String getNextId = "SELECT COALESCE(MAX(kelas_id), 0) + 1 FROM kelas";
        String insertSQL = "INSERT INTO kelas (kelas_id, nama_kelas, homeroom_teacher_id) VALUES (?, ?, ?)";

        try (Connection conn = datasource.getConnection()) {
            int nextId = 0;
            try (PreparedStatement stmt = conn.prepareStatement(getNextId)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) nextId = rs.getInt(1);
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setInt(1, nextId);
                stmt.setString(2, tfNamaKelas.getText());
                stmt.setInt(3, Integer.parseInt(tfWaliKelasId.getText()));

                stmt.executeUpdate();
                lblStatus.setText(" Kelas berhasil ditambahkan dengan ID: " + nextId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lblStatus.setText(" Gagal simpan kelas: " + e.getMessage());
        }
    }
}
