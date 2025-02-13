package org.example.view;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public int getUserInt() {
        return scanner.nextInt();
    }

    public String getUserString() {
        return scanner.nextLine();
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Battle for Olympus!");
    }

    public void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Create character");
        System.out.println("2. Battle");
        System.out.println("5. Exit");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayLists() {
        displayMessage("Lists of characters");
        displayMessage("1. Mythological Animals");
        displayMessage("2. Gods");
        displayMessage("3. Heroes");
        displayMessage("4. Titans");
        displayMessage("Enter your choice:");
    }
}
