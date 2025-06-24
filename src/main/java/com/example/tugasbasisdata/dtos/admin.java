package com.example.tugasbasisdata.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class admin {
    private int admin_id;
    private String name;
    private int user_id;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public admin(int admin_id, String name, int user_id, String email) {
        this.admin_id = admin_id;
        this.name = name;
        this.user_id = user_id;
        this.email = email;
    }
    public admin(ResultSet rs) throws SQLException {
        this.admin_id = rs.getInt("admin_id");
        this.name = rs.getString("name");
        this.user_id =rs.getInt("user_id");
        this.email = rs.getString("email");
    }
}
