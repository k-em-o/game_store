package com.game_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://db.uxdfzosnrodoqvbwjupt.supabase.co:5432/postgres?user=postgres&password=GameStore@pro";
    private static final String USER = "postgres";
    private static final String PASSWORD = "GameStore@pro";

        public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Supabase successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
}
