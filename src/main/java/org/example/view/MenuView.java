package org.example.view;

import java.util.Scanner;

public class MenuView extends View {
    public void displayInstructions() {
        System.out.println("Welcome to the Mythology Combat Game!");
        System.out.println("Here are the ways you can create characters:");
        System.out.println("1. Manual Selection for both characters");
        System.out.println("   - You will select or create both characters manually.");
        System.out.println("2. One Manual and One Random Character");
        System.out.println("   - You will select or create one character manually, and the other will be chosen randomly.");
        System.out.println("3. Both Random Characters");
        System.out.println("   - Both characters will be chosen randomly from the available ones.");
        System.out.println("In each combat, you will fight your characters against each other in a turn-based battle.");
        System.out.println("Good luck and may the best warrior win!");
        System.out.println("-----------------------------------------------------------------------");

    }

    public void displayMenu() {
        System.out.println("\n--- Game Menu ---");
        System.out.println("1. Select both characters manually");
        System.out.println("2. One random character, one manually selected");
        System.out.println("3. Both characters random");
        System.out.println("4. Exit");
    }

    public void promptCharacterSelectionOrCreation() {
        System.out.println("Select or create a character:");
        System.out.println("1. Select an existing character");
        System.out.println("2. Create a new character");
    }

}
