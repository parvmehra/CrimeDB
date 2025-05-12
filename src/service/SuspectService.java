package service;

import db.DatabaseConnection;
import models.Suspect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuspectService {
    private final Connection connection;


    public SuspectService() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean addSuspect(Suspect suspect) {
        String query = "INSERT INTO suspects (name, alias, age, description, crimeType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, suspect.getName());
            stmt.setString(2, suspect.getAlias());
            stmt.setString(3, suspect.getAge());
            stmt.setString(4, suspect.getDescription());
            stmt.setString(5, suspect.getCrimeType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding suspect: " + e.getMessage());
        }
        return false;
    }

    public List<Suspect> getAllSuspects() {
        List<Suspect> suspects = new ArrayList<>();
        String query = "SELECT * FROM suspects";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suspects.add(new Suspect(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getString("age"),
                        rs.getString("description"),
                        rs.getString("crimeType")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving suspects: " + e.getMessage());
        }
        return suspects;
    }

    public List<Suspect> searchSuspects(String keyword) {
        List<Suspect> results = new ArrayList<>();
        String query = "SELECT * FROM suspects WHERE name LIKE ? OR alias LIKE ? OR crimeType LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String searchKeyword = "%" + keyword + "%";
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Suspect(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("alias"),
                            rs.getString("age"),
                            rs.getString("description"),
                            rs.getString("crimeType")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching suspects: " + e.getMessage());
        }
        return results;
    }

    public boolean updateSuspect(Suspect suspect) {
        String query = "UPDATE suspects SET name=?, alias=?, age=?, description=?, crimeType=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, suspect.getName());
            stmt.setString(2, suspect.getAlias());
            stmt.setString(3, suspect.getAge());
            stmt.setString(4, suspect.getDescription());
            stmt.setString(5, suspect.getCrimeType());
            stmt.setInt(6, suspect.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating suspect: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteSuspect(int id) {
        String query = "DELETE FROM suspects WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting suspect: " + e.getMessage());
        }
        return false;
    }
}