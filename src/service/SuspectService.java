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

    //add a suspect
    public boolean addSuspect(Suspect suspect) {
        String query = "INSERT INTO suspects (name,  alias, age, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, suspect.getName());
            stmt.setString(2, suspect.getAlias());
            stmt.setString(3, suspect.getAge());
            stmt.setString(4, suspect.getDescription());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding suspect: " + e.getMessage());
            return false;
        }
    }

    //list all suspects
    public List<Suspect> getAllSuspects() {
        List<Suspect> suspects = new ArrayList<>();
        String query = "SELECT * FROM suspects";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Suspect s = new Suspect(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getString("age"),
                        rs.getString("description")

                );
                suspects.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching suspects: " + e.getMessage());
        }
        return suspects;
    }

    //find suspect by name or alias
    public List<Suspect> searchSuspects(String keyword) {
        List<Suspect> results = new ArrayList<>();
        String query = "SELECT * FROM suspects WHERE name LIKE ? OR alias LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String wildcardKeyword = "%" + keyword + "%";
            stmt.setString(1, wildcardKeyword);
            stmt.setString(2, wildcardKeyword);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Suspect s = new Suspect(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getString("age"),
                        rs.getString("description")

                );
                results.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error searching suspects: " + e.getMessage());
        }

        return results;
    }

    //update suspect
    public boolean updateSuspect(Suspect suspect) {
        String query = "UPDATE suspects SET name = ?, alias = ?, age = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, suspect.getName());
            stmt.setString(2, suspect.getAlias());
            stmt.setString(3, suspect.getAge());
            stmt.setString(4, suspect.getDescription());
            stmt.setInt(5, suspect.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating suspect: " + e.getMessage());
            return false;
        }
    }

    //delete suspect
    public boolean deleteSuspect(int suspectId) {
        String query = "DELETE FROM suspects WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, suspectId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting suspect: " + e.getMessage());
            return false;
        }
    }
}