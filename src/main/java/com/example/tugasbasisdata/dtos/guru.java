package com.example.tugasbasisdata.dtos;

public class guru {
    private int teacher_id;
    private String name;
    private int subject_id;
    private String phone_num;
    private String email;
    private int user_id;

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public guru(int teacher_id, String name, int subject_id, String phone_num, String email, int user_id) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.subject_id = subject_id;
        this.phone_num = phone_num;
        this.email = email;
        this.user_id = user_id;
    }
}
