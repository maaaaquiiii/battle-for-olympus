package org.example.view;

import java.util.Scanner;

public class View {
    public static Scanner scanner = new Scanner(System.in);

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

    public boolean getUserBoolean() {
        return scanner.nextBoolean();
    }

    public static void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine(); //clear the buffer
        }
    }
}
