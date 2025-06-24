package com.example.tugasbasisdata.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class siswa {
    private int siswa_id;
    private String name;
    private Date birthdate;
    private String address;
    private String gender;
    private int user_id;
    private int class_id;

    public int getSiswa_id() {
        return siswa_id;
    }

    public void setSiswa_id(int siswa_id) {
        this.siswa_id = siswa_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public siswa(int siswa_id, String name, Date birthdate, String address, String gender, int user_id, int class_id) {
        this.siswa_id = siswa_id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.gender = gender;
        this.user_id = user_id;
        this.class_id = class_id;
    }
    public siswa(ResultSet rs) throws SQLException {
        this.siswa_id = rs.getInt("siswa_id");
        this.name = rs.getString("name");
        this.birthdate = rs.getDate("birth_date");
        this.address = rs.getString("address");
        this.gender = rs.getString("gender");
        this.class_id = rs.getInt("class_id");
        this.user_id = rs.getInt("user_id");
    }
}
