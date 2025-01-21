package com.example.taskmanager;

import java.util.Scanner;

public class TaskManagerApplication {
    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskController.addTask(description);
                    break;
                case 2:
                    taskController.listTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to complete: ");
                    int completeId = scanner.nextInt();
                    taskController.completeTask(completeId);
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    taskController.deleteTask(deleteId);
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
