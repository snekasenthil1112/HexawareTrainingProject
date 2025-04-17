package util;

import java.sql.Connection;
import java.sql.SQLException;
import util.DBConnection; // Import your connection class

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Database connected successfully!");
            } else {
                System.out.println("❌ Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error while connecting to database:");
            e.printStackTrace();
        }
    }
}

