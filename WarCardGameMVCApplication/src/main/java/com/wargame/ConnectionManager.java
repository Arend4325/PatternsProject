package com.wargame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/war_game_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to MySQL successfully.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
            e.printStackTrace();
            return null;
        }
    }
}
