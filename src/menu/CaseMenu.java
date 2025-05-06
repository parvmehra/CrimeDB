package menu;

import models.Case;
import service.CaseService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CaseMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CaseService caseService;

    static {
        try {
            caseService = new CaseService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showCaseMenu() {
        while (true) {
            System.out.println("\n--- Case Menu ---");
            System.out.println("1. Add Case");
            System.out.println("2. View All Cases");
            System.out.println("3. Update Case");
            System.out.println("4. Delete Case");
            System.out.println("5. Filter Cases by Region");
            System.out.println("6. Filter Cases by Urgency");
            System.out.println("7. Back to Main Menu");  // New option
            System.out.print("Select: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCase();
                    break;
                case "2":
                    viewCases();
                    break;
                case "3":
                    updateCase();
                    break;
                case "4":
                    deleteCase();
                    break;
                case "5":
                    filterByRegion();
                    break;
                case "6":
                    filterByUrgency();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void addCase() {
        try {
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Region: ");
            String region = scanner.nextLine();
            System.out.print("Status: ");
            String status = scanner.nextLine();
            System.out.print("Suspect Name: ");
            String suspect = scanner.nextLine();
            System.out.print("Urgency: ");
            String urgency = scanner.nextLine();
            System.out.print("Date: ");
            String date = scanner.nextLine();

            Case newCase = new Case(title, description, region, status, suspect, urgency, date);
            if (caseService.addCase(newCase)) {
                System.out.println("Case added successfully.");
            } else {
                System.out.println("Failed to add case.");
            }
        } catch (Exception e) {
            System.out.println("Error adding case: " + e.getMessage());
        }
    }

    private static void viewCases() {
        try {
            List<Case> cases = caseService.getAllCases();
            for (Case c : cases) {
                System.out.println("\nCase ID: " + c.getCaseId());
                System.out.println("Title: " + c.getTitle());
                System.out.println("Description: " + c.getDescription());
                System.out.println("Region: " + c.getRegion());
                System.out.println("Status: " + c.getStatus());
                System.out.println("Suspect: " + c.getSuspect());
                System.out.println("Urgency: " + c.getUrgency());
                System.out.println("Date: " + c.getDate());
            }
        } catch (Exception e) {
            System.out.println("Error viewing cases: " + e.getMessage());
        }
    }

    private static void updateCase() {
        try {
            System.out.print("Enter Case ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("New Title: ");
            String title = scanner.nextLine();
            System.out.print("New Description: ");
            String description = scanner.nextLine();
            System.out.print("New Region: ");
            String region = scanner.nextLine();
            System.out.print("New Status: ");
            String status = scanner.nextLine();
            System.out.print("New Suspect Name: ");
            String suspect = scanner.nextLine();
            System.out.print("New Urgency: ");
            String urgency = scanner.nextLine();
            System.out.print("New Date: ");
            String date = scanner.nextLine();

            Case updatedCase = new Case(id, title, description, region, status, suspect, urgency, date);
            if (caseService.updateCase(updatedCase)) {
                System.out.println("Case updated.");
            } else {
                System.out.println("Failed to update case.");
            }
        } catch (Exception e) {
            System.out.println("Error updating case: " + e.getMessage());
        }
    }

    private static void deleteCase() {
        try {
            System.out.print("Enter Case ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (caseService.deleteCase(id)) {
                System.out.println("Case deleted.");
            } else {
                System.out.println("Failed to delete case.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting case: " + e.getMessage());
        }
    }

    private static void filterByRegion() {
        try {
            System.out.print("Enter region to filter by: ");
            String region = scanner.nextLine();
            List<Case> regionCases = caseService.filterByRegion(region);
            for (Case c : regionCases) {
                System.out.println("ID: " + c.getCaseId() + ", Title: " + c.getTitle() + ", Region: " + c.getRegion());
            }
        } catch (Exception e) {
            System.out.println("Error filtering cases by region: " + e.getMessage());
        }
    }

    private static void filterByUrgency() {
        try {
            System.out.print("Enter urgency to filter by (High/Medium/Low): ");
            String urgency = scanner.nextLine();
            List<Case> urgencyCases = caseService.filterByUrgency(urgency);
            for (Case c : urgencyCases) {
                System.out.println("ID: " + c.getCaseId() + ", Title: " + c.getTitle() + ", Urgency: " + c.getUrgency());
            }
        } catch (Exception e) {
            System.out.println("Error filtering cases by urgency: " + e.getMessage());
        }
    }
}