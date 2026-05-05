package com.management.view;

import com.management.controller.UserController;
import com.management.model.User;
import java.util.Scanner;

public class UserView {
    private UserController controller;
    private Scanner scanner = new Scanner(System.in);

    public UserView(UserController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- User Management System ---");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    controller.addUser(id, name, email);
                    System.out.println("User added successfully!");
                    break;
                case 2:
                    System.out.println("\nUser List:");
                    controller.getAllUsers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter ID of user to update: ");
                    String upId = scanner.nextLine();
                    
                    User existingUser = controller.getUserById(upId);
                    if (existingUser == null) {
                        System.out.println("Error: User ID not found!");
                        break;
                    }

                    System.out.println("User found: " + existingUser.getName());
                    System.out.println("What would you like to update?");
                    System.out.println("1. ID");
                    System.out.println("2. Name");
                    System.out.println("3. Email");
                    System.out.println("4. All");
                    System.out.print("Choice: ");
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
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println("Invalid choice! No changes made.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    String delId = scanner.nextLine();
                    boolean deleted = controller.deleteUser(delId);
                    if (deleted) {
                        System.out.println("User deleted successfully!");
                    } else {
                        System.out.println("Error: User ID not found!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
