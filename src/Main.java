import models.User;
import service.AuthService;
import menu.CaseMenu;
import menu.SuspectMenu;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Crime Database System");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    User user = login();
                    if (user != null) {
                        afterLoginMenu();
                    }
                    break;
                case "3":
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(name, email, password);
        if (authService.registerUser(newUser)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static User login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getName());
        } else {
            System.out.println("Login failed.");
        }
        return user;
    }

    private static void afterLoginMenu() {
        while (true) {
            System.out.println("\n1. Case Menu");
            System.out.println("2. Suspect Menu");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    CaseMenu.showCaseMenu();
                    break;
                case "2":
                    SuspectMenu.showSuspectMenu();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}