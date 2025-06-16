package com.example.tugasbasisdata.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class test {
    public static void main(String[] args) {
        try {
            Connection conn = datasource.getConnection();
            if (conn != null) {
                System.out.println("Connection established");

                // Do things once connection is established
                // In this case, print the PostgreSQL version
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
                while (rs.next()) {
                    System.out.print(rs.getInt(1));
                    System.out.print(" - ");
                    System.out.print(rs.getString(2));
                    System.out.print(" - ");
                    System.out.println(rs.getString(3));
                }
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
