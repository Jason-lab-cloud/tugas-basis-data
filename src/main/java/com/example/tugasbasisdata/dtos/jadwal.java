package com.example.tugasbasisdata.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class jadwal {
    private int jadwal_id;
    private int day;
    private Time awal;
    private Time akhir;
    private int kelas_id;
    private int guru_id;
    private int subject_id;

    public int getJadwal_id() {
        return jadwal_id;
    }

    public void setJadwal_id(int jadwal_id) {
        this.jadwal_id = jadwal_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getAwal() {
        return awal;
    }

    public void setAwal(Time awal) {
        this.awal = awal;
    }

    public Time getAkhir() {
        return akhir;
    }

    public void setAkhir(Time akhir) {
        this.akhir = akhir;
    }

    public int getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(int kelas_id) {
        this.kelas_id = kelas_id;
    }

    public int getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(int guru_id) {
        this.guru_id = guru_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public jadwal(int jadwal_id, int day, Time awal, Time akhir, int kelas_id, int guru_id, int subject_id) {
        this.jadwal_id = jadwal_id;
        this.day = day;
        this.awal = awal;
        this.akhir = akhir;
        this.kelas_id = kelas_id;
        this.guru_id = guru_id;
        this.subject_id = subject_id;
    }
    public jadwal(ResultSet rs) throws SQLException {
        this.jadwal_id = rs.getInt("scedule_id");
        this.day = rs.getInt("day");
        this.awal = rs.getTime("start_time");
        this.akhir = rs.getTime("end_time");
        this.kelas_id = rs.getInt("class_id");
        this.guru_id = rs.getInt("teacher_id");
        this.subject_id = rs.getInt("subject_id");
    }
}
