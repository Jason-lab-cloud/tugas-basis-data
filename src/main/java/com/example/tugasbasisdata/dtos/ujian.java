package com.example.tugasbasisdata.dtos;

import java.util.Date;

public class ujian {
    private int ujian_id;
    private int siswa_id;
    private int subject_id;
    private String test_name;
    private int score;
    private String notes;
    private Date date;

    public int getUjian_id() {
        return ujian_id;
    }

    public void setUjian_id(int ujian_id) {
        this.ujian_id = ujian_id;
    }

    public int getSiswa_id() {
        return siswa_id;
    }

    public void setSiswa_id(int siswa_id) {
        this.siswa_id = siswa_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ujian(int ujian_id, int siswa_id, int subject_id, String test_name, int score, String notes, Date date) {
        this.ujian_id = ujian_id;
        this.siswa_id = siswa_id;
        this.subject_id = subject_id;
        this.test_name = test_name;
        this.score = score;
        this.notes = notes;
        this.date = date;
    }
}
