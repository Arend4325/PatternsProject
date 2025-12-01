package com.wargame;

import com.wargame.config.ConfigManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ConfigManager.getUrl(),
                    ConfigManager.getUser(),
                    ConfigManager.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
