package menu;

import dsa.CaseQueue;
import dsa.CaseSorter;
import models.Case;
import service.CaseService;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CaseMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static CaseService caseService;
    private static final CaseQueue caseQueue = new CaseQueue();


    static {
        try {
            caseService = new CaseService();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
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
            System.out.println("7. Sort Cases by Urgency");
            System.out.println("8. View New Cases");   //cases added in this session
            System.out.println("0. Back to Main Menu");
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
                    sortByUrgency();
                    break;
                case "8":
                    viewCaseQueue();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void addCase() {
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
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Case newCase = new Case(title, description, region, status, suspect, urgency, date);
        if (caseService.addCase(newCase)) {
            caseQueue.enqueue(newCase);
            System.out.println("Case added and queued.");
        } else {
            System.out.println("Failed to add case.");
        }
    }

    private static void viewCases() {
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
    }

    private static void updateCase() {
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
        System.out.print("New Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Case updatedCase = new Case(id, title, description, region, status, suspect, urgency, date);
        if (caseService.updateCase(updatedCase)) {
            System.out.println("Case updated.");
        } else {
            System.out.println("Failed to update case.");
        }
    }

    private static void deleteCase() {
        System.out.print("Enter Case ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (caseService.deleteCase(id)) {
            System.out.println("Case deleted.");
        } else {
            System.out.println("Failed to delete case.");
        }
    }

    private static void filterByRegion() {
        System.out.print("Enter region to filter: ");
        String region = scanner.nextLine();
        List<Case> cases = caseService.filterByRegion(region);
        for (Case c : cases) {
            System.out.println("ID: " + c.getCaseId() + " | Title: " + c.getTitle());
        }
    }

    private static void filterByUrgency() {
        System.out.print("Enter urgency to filter (High/Medium/Low): ");
        String urgency = scanner.nextLine();
        List<Case> cases = caseService.filterByUrgency(urgency);
        for (Case c : cases) {
            System.out.println("ID: " + c.getCaseId() + " | Title: " + c.getTitle());
        }
    }

    private static void sortByUrgency() {
        List<Case> cases = caseService.getAllCases();
        CaseSorter.sortByUrgency(cases);
        System.out.println("Cases sorted by urgency:");
        for (Case c : cases) {
            System.out.println(c.getTitle() + " - " + c.getUrgency());
        }
    }

    private static void viewCaseQueue() {
        System.out.println("Cases in queue:");
        caseQueue.displayQueue();
    }
}