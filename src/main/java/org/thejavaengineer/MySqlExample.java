package org.thejavaengineer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlExample {
    public static void main(String[] args) {
        // Update URL for MySQL
        String url = "jdbc:mysql://localhost:3306/social_network"; // Replace 'yourdb' with your database name
        String user = "root"; // Replace with your MySQL username
        String password = "rootroot"; // Replace with your MySQL password

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert data
            long startInsert = System.nanoTime();
            try (PreparedStatement insertStmt = connection.prepareStatement(
                    "INSERT INTO cachetable (`key`, `value`) VALUES (?, ?)")) {
                insertStmt.setString(1, "mykey");
                insertStmt.setString(2, "Hello, Database!");
                insertStmt.executeUpdate();
            }
            long endInsert = System.nanoTime();

            // Retrieve data
            long startSelect = System.nanoTime();
            try (PreparedStatement selectStmt = connection.prepareStatement(
                    "SELECT `value` FROM cachetable WHERE `key` = ?")) {
                selectStmt.setString(1, "mykey");
                ResultSet resultSet = selectStmt.executeQuery();
                if (resultSet.next()) {
                    String value = resultSet.getString("value");
                    System.out.println("Retrieved value: " + value);
                }
            }
            long endSelect = System.nanoTime();

            // Print results
            System.out.println("INSERT operation time: " + (endInsert - startInsert) / 1_000_000.0 + " ms");
            System.out.println("SELECT operation time: " + (endSelect - startSelect) / 1_000_000.0 + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
