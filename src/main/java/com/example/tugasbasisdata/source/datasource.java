package com.example.tugasbasisdata.source;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class datasource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static int currentUserId; // Untuk menyimpan ID user yang sedang login

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/projects");
        config.setUsername("postgres");
        config.setPassword("9SaltySpies");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
