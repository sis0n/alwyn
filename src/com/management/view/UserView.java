package com.management.view;

import com.management.controller.UserController;
import com.management.model.User;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private UserController controller;
    private Scanner scanner = new Scanner(System.in);

    public UserView(UserController controller) {
        this.controller = controller;
    }

    private void printHeader(String title) {
        System.out.println("\n========================================");
        System.out.println("   " + title.toUpperCase());
        System.out.println("========================================");
    }

    private void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private void printUserTable(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("| No users found in the system.        |");
            return;
        }

        String hr = "+------------+----------------------+---------------------------+";
        System.out.println(hr);
        System.out.printf("| %-10s | %-20s | %-25s |%n", "ID", "Name", "Email");
        System.out.println(hr);

        for (User user : users) {
            System.out.printf("| %-10s | %-20s | %-25s |%n", 
                user.getId(), 
                user.getName(), 
                user.getEmail());
        }
        System.out.println(hr);
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public void showMenu() {
        while (true) {
            clearScreen();
            System.out.println("****************************************");
            System.out.println("*      USER MANAGEMENT SYSTEM V1.0     *");
            System.out.println("****************************************");
            System.out.println("  [1] Add New User");
            System.out.println("  [2] View All Users");
            System.out.println("  [3] Update Existing User");
            System.out.println("  [4] Delete User");
            System.out.println("  [5] Exit Application");
            System.out.println("****************************************");
            System.out.print("Select an option: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println(">> Error: Please enter a valid numeric choice.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    printHeader("Add New User");
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    boolean added = controller.addUser(id, name, email);
                    if (added) {
                        System.out.println("\nUser successfully added to the system!");
                    } else {
                        System.out.println("\nError: User ID '" + id + "' already exists!");
                    }
                    pause();
                    break;

                case 2:
                    printHeader("Registered Users List");
                    printUserTable(controller.getAllUsers());
                    pause();
                    break;

                case 3:
                    printHeader("Update User Details");
                    System.out.print("Enter ID of user to update: ");
                    String upId = scanner.nextLine();
                    
                    User existingUser = controller.getUserById(upId);
                    if (existingUser == null) {
                        System.out.println("Error: User ID '" + upId + "' not found!");
                        pause();
                        break;
                    }

                    System.out.println("\nTarget User: " + existingUser.getName());
                    System.out.println("----------------------------------------");
                    System.out.println("What would you like to update?");
                    System.out.println(" [1] ID    [2] Name    [3] Email    [4] All");
                    System.out.print("Choice: ");
                    
                    if (!scanner.hasNextInt()) {
                        System.out.println(">> Invalid sub-choice.");
                        scanner.next();
                        break;
                    }
                    
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();

                    String upIdNew = existingUser.getId();
                    String upName = existingUser.getName();
                    String upEmail = existingUser.getEmail();

                    if (updateChoice == 1 || updateChoice == 4) {
                        System.out.print("Enter New ID: ");
                        upIdNew = scanner.nextLine();
                    }
                    if (updateChoice == 2 || updateChoice == 4) {
                        System.out.print("Enter New Name: ");
                        upName = scanner.nextLine();
                    }
                    if (updateChoice == 3 || updateChoice == 4) {
                        System.out.print("Enter New Email: ");
                        upEmail = scanner.nextLine();
                    }

                    if (updateChoice >= 1 && updateChoice <= 4) {
                        controller.updateUser(upId, upIdNew, upName, upEmail);
                        System.out.println("\nUser details updated successfully!");
                    } else {
                        System.out.println("Invalid choice! No changes were saved.");
                    }
                    pause();
                    break;

                case 4:
                    printHeader("Delete User Record");
                    System.out.print("Enter ID to delete: ");
                    String delId = scanner.nextLine();
                    
                    User userToDelete = controller.getUserById(delId);
                    if (userToDelete == null) {
                        System.out.println("Error: User ID '" + delId + "' not found!");
                        pause();
                        break;
                    }

                    System.out.println("\nTarget User: " + userToDelete.getName());
                    System.out.print("Are you sure you want to delete this user? (Y/N): ");
                    String confirm = scanner.nextLine();
                    
                    if (confirm.equalsIgnoreCase("Y")) {
                        controller.deleteUser(delId);
                        System.out.println("User record deleted successfully!");
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                    pause();
                    break;

                case 5:
                    System.out.println("\nThank you for using the system. Goodbye!");
                    return;

                default:
                    System.out.println(">> Error: '" + choice + "' is not a valid menu option.");
                    pause();
            }
        }
    }
}
