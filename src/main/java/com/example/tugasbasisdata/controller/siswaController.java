package com.example.tugasbasisdata.controller;

import javafx.fxml.FXML;
import com.example.tugasbasisdata.source.datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class siswaController {

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML private TextArea areaOutput;
    @FXML private VBox root;

    // === 1. Tampilkan Biodata ===
    @FXML
    public void lihatBiodata() {
        String sql = "SELECT * FROM siswa WHERE user_id = ?";

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String biodata = "Nama: " + rs.getString("name") +
                        "\nNIS: " + rs.getString("nis") +
                        "\nEmail: " + rs.getString("email") +
                        "\nTanggal Lahir: " + rs.getDate("birth_date") +
                        "\nAlamat: " + rs.getString("address") +
                        "\nGender: " + rs.getString("gender") +
                        "\nTahun Masuk: " + rs.getInt("joining_year");
                areaOutput.setText(biodata);
            } else {
                areaOutput.setText("Data siswa tidak ditemukan.");
            }

        } catch (SQLException e) {
            areaOutput.setText(" Gagal ambil biodata: " + e.getMessage());
        }
    }

    // === 2. Tampilkan Jadwal Kelas ===
    @FXML
    public void lihatJadwal() {
        String sql = """
            SELECT m.subjects_name, j.start_time, j.end_time, j.day
            FROM siswa s
            JOIN jadwal j ON s.class_id = j.class_id
            JOIN mapel m ON j.subject_id = m.subjects_id
            WHERE s.user_id = ?
            ORDER BY j.day, j.start_time
        """;

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder jadwal = new StringBuilder("Jadwal Kelas:\n");
            while (rs.next()) {
                jadwal.append("• ")
                        .append(rs.getString("subjects_name"))
                        .append(" | ")
                        .append(rs.getTime("start_time"))
                        .append(" - ")
                        .append(rs.getTime("end_time"))
                        .append(" | Hari ke-")
                        .append(rs.getInt("day"))
                        .append("\n");
            }

            areaOutput.setText(jadwal.toString());

        } catch (SQLException e) {
            areaOutput.setText(" Gagal ambil jadwal: " + e.getMessage());
        }
    }

    // === 3. Tampilkan Nilai Ujian ===
    @FXML
    public void lihatNilai() {
        String sql = """
            SELECT m.subjects_name, t.test_name, t.nilai, t.date
            FROM siswa s
            JOIN test t ON s.siswa_id = t.siswa_id
            JOIN mapel m ON t.subject_id = m.subjects_id
            WHERE s.user_id = ?
            ORDER BY t.date DESC
        """;

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder nilai = new StringBuilder("Nilai Ujian:\n");
            while (rs.next()) {
                nilai.append("• ")
                        .append(rs.getString("subjects_name"))
                        .append(" - ")
                        .append(rs.getString("test_name"))
                        .append(": ")
                        .append(rs.getInt("nilai"))
                        .append(" (")
                        .append(rs.getDate("date"))
                        .append(")\n");
            }

            areaOutput.setText(nilai.toString());

        } catch (SQLException e) {
            areaOutput.setText(" Gagal ambil nilai: " + e.getMessage());
        }
    }
}
