package com.example.tugasbasisdata.dtos;

public class mapel {
    private int mapel_id;
    private String mapel_name;

    public int getMapel_id() {
        return mapel_id;
    }

    public void setMapel_id(int mapel_id) {
        this.mapel_id = mapel_id;
    }

    public String getMapel_name() {
        return mapel_name;
    }

    public void setMapel_name(String mapel_name) {
        this.mapel_name = mapel_name;
    }

    public mapel(int mapel_id, String mapel_name) {
        this.mapel_id = mapel_id;
        this.mapel_name = mapel_name;
    }
}
