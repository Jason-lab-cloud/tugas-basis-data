package com.example.tugasbasisdata.dtos;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    private int user_id;
    private String username;
    private String password;
    private String role;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public user(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public user(ResultSet rs) throws SQLException {
        this.user_id = rs.getInt("user_id");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.role = rs.getString("role");
    }
}
