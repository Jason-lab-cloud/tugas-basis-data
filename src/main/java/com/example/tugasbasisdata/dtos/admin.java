package com.example.tugasbasisdata.dtos;

public class admin {
    private int admin_id;
    private String name;
    private int user_id;

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

    public admin(int admin_id, String name, int user_id) {
        this.admin_id = admin_id;
        this.name = name;
        this.user_id = user_id;
    }
}
