package com.example.tugasbasisdata.controller;

import com.example.tugasbasisdata.source.datasource;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class guruController {
    private int userId; // dari login

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML private TextArea areaOutput;
    @FXML private TextField tfSiswaId, tfMapelId, tfTestName, tfNilai;
    @FXML private DatePicker dpTanggal;

    // === 1. Lihat Jadwal Guru ===
    @FXML
    public void lihatJadwal() {
        String sql = """
            SELECT j.day, j.start_time, j.end_time, m.subjects_name, k.nama_kelas
            FROM jadwal j
            JOIN mapel m ON j.subject_id = m.subjects_id
            JOIN kelas k ON j.class_id = k.kelas_id
            JOIN guru g ON j.teacher_id = g.teacher_id
            WHERE g.user_id = ?
            ORDER BY j.day, j.start_time
        """;

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder("Jadwal Mengajar:\n");
            while (rs.next()) {
                sb.append("• ")
                        .append(rs.getString("subjects_name")).append(" - ")
                        .append(rs.getString("nama_kelas")).append(" | ")
                        .append(rs.getTime("start_time")).append(" - ")
                        .append(rs.getTime("end_time")).append(" | Hari ke-")
                        .append(rs.getInt("day")).append("\n");
            }
            areaOutput.setText(sb.toString());

        } catch (SQLException e) {
            areaOutput.setText(" Gagal ambil jadwal: " + e.getMessage());
        }
    }

    // === 2. Input Nilai ===
    @FXML
    public void inputNilai() {
        String getNextId = "SELECT COALESCE(MAX(test_id), 0) + 1 FROM test";
        String insertSQL = "INSERT INTO test (test_id, siswa_id, subject_id, test_name, nilai, date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = datasource.getConnection()) {
            int testId = 0;
            try (PreparedStatement nextStmt = conn.prepareStatement(getNextId)) {
                ResultSet rs = nextStmt.executeQuery();
                if (rs.next()) testId = rs.getInt(1);
            }

            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, testId);
            stmt.setInt(2, Integer.parseInt(tfSiswaId.getText()));
            stmt.setInt(3, Integer.parseInt(tfMapelId.getText()));
            stmt.setString(4, tfTestName.getText());
            stmt.setInt(5, Integer.parseInt(tfNilai.getText()));
            stmt.setDate(6, Date.valueOf(dpTanggal.getValue()));

            stmt.executeUpdate();
            areaOutput.setText(" Nilai berhasil disimpan.");

        } catch (SQLException e) {
            e.printStackTrace();
            areaOutput.setText(" Gagal simpan nilai: " + e.getMessage());
        }
    }
    private int teacherId;
    private boolean isWaliKelas = false;
    public void setWaliKelas(boolean b ){
        if (b){
            isWaliKelas = true;
            System.out.println("hello");
        }
    }
    public boolean isWaliKelas(int userId) {
        String sql = """
        SELECT 1
        FROM kelas
        WHERE homeroom_teacher_id = (
            SELECT teacher_id FROM guru WHERE user_id = ?
        )
    """;

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // jika ada hasil, berarti wali kelas

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void loadGuruData() {
        try (Connection conn = datasource.getConnection()) {
            // Dapatkan teacher_id
            String getTeacher = "SELECT teacher_id FROM guru WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(getTeacher);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                teacherId = rs.getInt("teacher_id");
            }

            // Cek apakah guru ini adalah wali kelas
            String checkWali = "SELECT 1 FROM kelas WHERE homeroom_teacher_id = ?";
            PreparedStatement waliStmt = conn.prepareStatement(checkWali);
            waliStmt.setInt(1, teacherId);
            ResultSet waliRs = waliStmt.executeQuery();
            isWaliKelas = waliRs.next();

            // Kalau wali kelas, aktifkan tombol cetak rapor
            btnCetakRapor.setDisable(!isWaliKelas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML private Button btnLihatJadwal, btnInputNilai, btnCetakRapor;

    // Tombol cetak rapor (khusus wali kelas)
    @FXML
    public void cetakRapor() {
        if (!isWaliKelas) {
            areaOutput.setText(" Anda bukan wali kelas.");
            return;
        }

        String sql = """
        SELECT s.name, m.subjects_name, t.test_name, t.nilai
        FROM kelas k
        JOIN siswa s ON s.class_id = k.kelas_id
        LEFT JOIN test t ON t.siswa_id = s.siswa_id
        LEFT JOIN mapel m ON t.subject_id = m.subjects_id
        WHERE k.homeroom_teacher_id = ?
        ORDER BY s.name, m.subjects_name
    """;

        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder(" Rapor Siswa:\n");
            while (rs.next()) {
                sb.append("• ")
                        .append(rs.getString("name"))
                        .append(" | ")
                        .append(rs.getString("subjects_name"))
                        .append(" - ")
                        .append(rs.getString("test_name"))
                        .append(": ")
                        .append(rs.getInt("nilai"))
                        .append("\n");
            }

            areaOutput.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            areaOutput.setText(" Gagal ambil rapor.");
        }
    }

}