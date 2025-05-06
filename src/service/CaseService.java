package service;

import db.DatabaseConnection;
import models.Case;
import java.sql.*;
import java.util.*;

public class CaseService {

    private final Connection connection;

    public CaseService() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    // add new case
    public boolean addCase(Case newCase) {
        String query = "INSERT INTO cases (title, description, region, status, suspect, urgency, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newCase.getTitle());
            stmt.setString(2, newCase.getDescription());
            stmt.setString(3, newCase.getRegion());
            stmt.setString(4, newCase.getStatus());
            stmt.setString(5, newCase.getSuspect());
            stmt.setString(6, newCase.getUrgency());
            stmt.setString(7, newCase.getDate());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding case: " + e.getMessage());
            return false;
        }
    }

    //list all cases
    public List<Case> getAllCases() {
        List<Case> caseList = new ArrayList<>();
        String query = "SELECT * FROM cases";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Case c = new Case(
                        rs.getInt("CaseId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("region"),
                        rs.getString("status"),
                        rs.getString("suspect"),
                        rs.getString("urgency"),
                        rs.getString("date")
                );
                caseList.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving cases: " + e.getMessage());
        }

        return caseList;
    }


    //find cases by title, suspect name, or region
    public List<Case> searchCases(String keyword) {
        List<Case> results = new ArrayList<>();
        String query = "SELECT * FROM cases WHERE title LIKE ? OR suspect LIKE ? OR region LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String wildcardKeyword = "%" + keyword + "%";
            stmt.setString(1, wildcardKeyword);
            stmt.setString(2, wildcardKeyword);
            stmt.setString(3, wildcardKeyword);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Case c = new Case(
                        rs.getInt("CaseId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("region"),
                        rs.getString("status"),
                        rs.getString("suspect"),
                        rs.getString("urgency"),
                        rs.getString("date")
                );
                results.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error searching cases: " + e.getMessage());
        }

        return results;
    }


    //update a case
    public boolean updateCase(Case updatedCase) {
        String query = "UPDATE cases SET title=?, description=?, region=?, status=?, suspect=?, urgency=?, date=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedCase.getTitle());
            stmt.setString(2, updatedCase.getDescription());
            stmt.setString(3, updatedCase.getRegion());
            stmt.setString(4, updatedCase.getStatus());
            stmt.setString(5, updatedCase.getSuspect());
            stmt.setString(6, updatedCase.getUrgency());
            stmt.setString(7, updatedCase.getDate());
            stmt.setInt(8, updatedCase.getCaseId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating case: " + e.getMessage());
            return false;
        }
    }

    //delete case
    public boolean deleteCase(int caseId) {
        String query = "DELETE FROM cases WHERE caseId=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, caseId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting case: " + e.getMessage());
            return false;
        }
    }

    //filter by region
    public List<Case> filterByRegion(String region) {
        List<Case> allCases = getAllCases();
        List<Case> filtered = new ArrayList<>();

        for (Case c : allCases) {
            if (c.getRegion().equalsIgnoreCase(region)) {
                filtered.add(c);
            }
        }

        return filtered;
    }

    //filter by urgency
    public List<Case> filterByUrgency(String urgency) {
        List<Case> allCases = getAllCases();
        List<Case> filtered = new ArrayList<>();

        for (Case c : allCases) {
            if (c.getUrgency().equalsIgnoreCase(urgency)) {
                filtered.add(c);
            }
        }

        return filtered;
    }
}