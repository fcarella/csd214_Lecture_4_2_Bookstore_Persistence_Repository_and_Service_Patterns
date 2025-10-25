package csd214.bookstore.f25;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for their choice
        System.out.println("Please select which application version to run:");
        System.out.println("1: JPA Database Version (App)");
        System.out.println("2: In-Memory Repository Version (App2)");
        System.out.print("Enter your choice (1 or 2): ");

        // Read the user's input
        String choice = scanner.nextLine();

        // Use a switch statement to determine which app to run
        switch (choice) {
            case "1":
                System.out.println("\n--- Running JPA Database Version (App) ---");
                System.out.println("This will connect to the database and persist data.");
                new App().run();
                break;
            case "2":
                System.out.println("\n--- Running In-Memory Repository Version (App2) ---");
                System.out.println("This will use a temporary, in-memory map. Data will be lost on exit.");
                new App2().run();
                break;
            default:
                // Handle invalid input
                System.out.println("\nInvalid choice. Please run the program again and enter 1 or 2.");
                break;
        }

        // It's good practice to close the scanner when it's no longer needed
        scanner.close();
    }
}