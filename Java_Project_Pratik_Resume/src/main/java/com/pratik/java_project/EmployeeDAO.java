package com.pratik.java_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Check if Employee Already Exists
    private boolean isEmployeeExists(String name) {
        String query = "SELECT COUNT(*) FROM employee WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Employee exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add Employee (Prevents Duplicates)
    public void addEmployee(String name, int age, String department, double salary) {
        if (isEmployeeExists(name)) {
            System.out.println("Employee already exists!");
            return;
        }
        String query = "INSERT INTO employee (name, age, department, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, department);
            stmt.setDouble(4, salary);
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("department"), rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;  // No extra message here
    }

    // Update Employee
    public void updateEmployee(int id, String name, int age, String department, double salary) {
        // Check if employee exists
        if (!isEmployeeIdExists(id)) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        String query = "UPDATE employee SET name=?, age=?, department=?, salary=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, department);
            stmt.setDouble(4, salary);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper Method: Check if Employee ID Exists
    private boolean isEmployeeIdExists(int id) {
        String query = "SELECT COUNT(*) FROM employee WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM employee WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"),
                                    rs.getInt("age"), rs.getString("department"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Delete Employee
    public void deleteEmployee(int id) {
        // Check if employee exists
        if (!isEmployeeIdExists(id)) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        String query = "DELETE FROM employee WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
