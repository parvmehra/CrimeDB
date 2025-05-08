package menu;

import java.util.Scanner;

public class MainMenu {
    public static void showMainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Case Menu");
            System.out.println("2. Suspect Menu");
            System.out.println("3. Logout");
            System.out.print("Select: ");



            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    CaseMenu.showCaseMenu();
                    break;
                case "2":
                    SuspectMenu.showSuspectMenu();
                    break;
                case "3":
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
