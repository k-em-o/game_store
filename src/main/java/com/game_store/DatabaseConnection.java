package com.game_store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "https://uxdfzosnrodoqvbwjupt.supabase.co";
    private static final String USER = "k-em-o";
    private static final String PASSWORD = "GameStore@pro";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connection successful!");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    // 🔹 دالة main الصحيحة اللي Maven محتاجها
    public static void main(String[] args) {
        try (Connection conn = connect()) {
            if (conn != null) {
                // استعلام بسيط لاختبار الاتصال
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT NOW();");
                if (rs.next()) {
                    System.out.println("🕒 Database time: " + rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
