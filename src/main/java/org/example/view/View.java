package org.example.view;

import java.util.Scanner;

public class View {
    public static Scanner scanner = new Scanner(System.in);


    public static Scanner getScanner() {
        return scanner;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getUserInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt();
    }

    public String getUserString() {
        return scanner.nextLine();
    }

    public static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); //clear the buffer
        }
    }


}
