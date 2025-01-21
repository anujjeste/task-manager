package com.example.taskmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskController {

    public void addTask(String description) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "INSERT INTO tasks (description, completed) VALUES (?, false)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, description);
            stmt.executeUpdate();
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listTasks() {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM tasks";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("ID: %d | Description: %s | Completed: %s%n",
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getBoolean("completed") ? "Yes" : "No");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void completeTask(int id) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "UPDATE tasks SET completed = true WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task deleted.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

