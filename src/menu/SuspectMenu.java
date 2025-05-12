package menu;

import models.Suspect;
import service.SuspectService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SuspectMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static SuspectService suspectService;


    static {
        try {
            suspectService = new SuspectService();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void showSuspectMenu() {
        while (true) {
            System.out.println("\n--- Suspect Menu ---");
            System.out.println("1. Add Suspect");
            System.out.println("2. View All Suspects");
            System.out.println("3. Search Suspect by Name, Alias, or Crime Type");
            System.out.println("4. Update Suspect");
            System.out.println("5. Delete Suspect");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addSuspect();
                    break;
                case "2":
                    viewSuspects();
                    break;
                case "3":
                    searchSuspect();
                    break;
                case "4":
                    updateSuspect();
                    break;
                case "5":
                    deleteSuspect();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void addSuspect() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Alias: ");
            String alias = scanner.nextLine();
            System.out.print("Age: ");
            String age = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Crime Type: ");
            String crimeType = scanner.nextLine();

            Suspect suspect = new Suspect(name, alias, age, description, crimeType);
            boolean success = suspectService.addSuspect(suspect);
            System.out.println(success ? "Suspect added successfully." : "Failed to add suspect.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewSuspects() {
        try {
            List<Suspect> suspects = suspectService.getAllSuspects();
            if (suspects.isEmpty()) {
                System.out.println("No suspects found.");
            } else {
                for (Suspect s : suspects) {
                    System.out.println("\nID: " + s.getId());
                    System.out.println("Name: " + s.getName());
                    System.out.println("Alias: " + s.getAlias());
                    System.out.println("Age: " + s.getAge());
                    System.out.println("Description: " + s.getDescription());
                    System.out.println("Crime Type: " + s.getCrimeType());
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving suspects: " + e.getMessage());
        }
    }

    private static void searchSuspect() {
        try {
            System.out.print("Enter keyword (name, alias, or crime type): ");
            String keyword = scanner.nextLine();
            List<Suspect> results = suspectService.searchSuspects(keyword);
            if (results.isEmpty()) {
                System.out.println("No suspects found matching the keyword.");
            } else {
                for (Suspect s : results) {
                    System.out.println("\nID: " + s.getId());
                    System.out.println("Name: " + s.getName());
                    System.out.println("Alias: " + s.getAlias());
                    System.out.println("Age: " + s.getAge());
                    System.out.println("Description: " + s.getDescription());
                    System.out.println("Crime Type: " + s.getCrimeType());
                }
            }
        } catch (Exception e) {
            System.out.println("Error during search: " + e.getMessage());
        }
    }

    private static void updateSuspect() {
        try {
            System.out.print("Enter ID of suspect to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("New Name: ");
            String name = scanner.nextLine();
            System.out.print("New Alias: ");
            String alias = scanner.nextLine();
            System.out.print("New Age: ");
            String age = scanner.nextLine();
            System.out.print("New Description: ");
            String description = scanner.nextLine();
            System.out.print("New Crime Type: ");
            String crimeType = scanner.nextLine();

            Suspect updated = new Suspect(id, name, alias, age, description, crimeType);
            boolean success = suspectService.updateSuspect(updated);
            System.out.println(success ? "Suspect updated successfully." : "Failed to update suspect.");
        } catch (Exception e) {
            System.out.println("Error updating suspect: " + e.getMessage());
        }
    }

    private static void deleteSuspect() {
        try {
            System.out.print("Enter ID of suspect to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            boolean success = suspectService.deleteSuspect(id);
            System.out.println(success ? "Suspect deleted successfully." : "Failed to delete suspect.");
        } catch (Exception e) {
            System.out.println("Error deleting suspect: " + e.getMessage());
        }
    }
}