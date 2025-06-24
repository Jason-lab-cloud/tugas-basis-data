package com.example.tugasbasisdata.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class kelas {
    private int kelas_id;
    private int kelas_tingkat;
    private String nama_kelas;
    private int homeroom_teacher_id;

    public int getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(int kelas_id) {
        this.kelas_id = kelas_id;
    }

    public int getKelas_tingkat() {
        return kelas_tingkat;
    }

    public void setKelas_tingkat(int kelas_tingkat) {
        this.kelas_tingkat = kelas_tingkat;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public int getHomeroom_teacher_id() {
        return homeroom_teacher_id;
    }

    public void setHomeroom_teacher_id(int homeroom_teacher_id) {
        this.homeroom_teacher_id = homeroom_teacher_id;
    }

    public kelas(int kelas_id, int kelas_tingkat, String nama_kelas, int homeroom_teacher_id) {
        this.kelas_id = kelas_id;
        this.kelas_tingkat = kelas_tingkat;
        this.nama_kelas = nama_kelas;
        this.homeroom_teacher_id = homeroom_teacher_id;
    }
    public kelas(ResultSet rs) throws SQLException {
        this.kelas_id = rs.getInt("kelas_id");
        this.nama_kelas = rs.getString("nama_kelas");
        this.homeroom_teacher_id = rs.getInt("homeroom_teacher_id");
    }
}
