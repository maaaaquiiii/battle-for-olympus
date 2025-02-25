package org.example.view;

import org.example.controller.CharacterController;
import org.example.model.Characters.Character;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.System.exit;

public class MenuView extends View {
    private static final CharacterController CHARACTER_CONTROLLER = new CharacterController();
    private static final CharacterView CHARACTER_VIEW = new CharacterView();
    private static final CombatView COMBAT_VIEW = new CombatView();

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
        System.out.print("Select an option: ");
    }

    public void promptCharacterSelectionOrCreation() {
        System.out.println("Select or create a character:");
        System.out.println("1. Select an existing character");
        System.out.println("2. Create a new character");
        System.out.print("Select an option: ");
    }

    public void startGame() {
        int option = 0;
        while (option != 4) {
            displayMenu();
            option = getUserInt();
            switch (option) {
                case 1 -> manualSelection();
                case 2 -> oneRandomOneManual();
                case 3 -> bothRandom();
                case 4 -> exitGame();
                default -> displayMessage("Invalid option, try again");
            }
        }
    }

    private Character selectOrCreateCharacter() {
        promptCharacterSelectionOrCreation();
        int choice = getUserInt();

        if (choice == 1) {
            return selectExistingCharacter();
        } else if (choice == 2) {
            return createNewCharacter();
        } else {
            displayMessage("Invalid choice. Please try again.");
            return selectOrCreateCharacter();
        }
    }

    private Character selectExistingCharacter() {
        List<Character> characters = CHARACTER_CONTROLLER.getAllCharacters();

        if (characters.isEmpty()) {
            displayMessage("No characters available. Proceeding to create a new character.");
            return selectOrCreateCharacter();
        }

        displayMessage("List of all characters");
        CHARACTER_VIEW.displayCharacterList(characters);
        clearBuffer();
        displayMessage("Enter the name of the character you want to select:");
        String characterName = getUserString();

        try {
            return CHARACTER_CONTROLLER.findCharacterByName(characterName);
        } catch (NoSuchElementException e) {
            displayMessage("Character not found. Please try again.");
            return selectExistingCharacter();
        }
    }

    private Character createNewCharacter() {
        clearBuffer();
        displayMessage("Enter character's name:");
        String name = getUserString();
        displayMessage("Enter character's type (Animal, God, Hero, Human, Titan):");
        String type = getUserString();

        try {
            Character newCharacter = CHARACTER_CONTROLLER.createCharacter(name, type);
            CHARACTER_CONTROLLER.addCharacter(newCharacter);
            displayMessage("Character created successfully!");
            return newCharacter;
        } catch (IllegalArgumentException e) {
            displayMessage("Invalid character data: " + e.getMessage());
            return createNewCharacter();
        }
    }

    private void manualSelection() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = selectOrCreateCharacter();
        COMBAT_VIEW.startCombat(character1, character2);
    }

    private void oneRandomOneManual() {
        Character character1 = selectOrCreateCharacter();
        Character character2;

        try {
            character2 = CHARACTER_CONTROLLER.getRandomCharacter();
        } catch (IllegalStateException e) {
            displayMessage("No characters available for random selection. Creating a new character.");
            character2 = createNewCharacter();
        }

        COMBAT_VIEW.startCombat(character1, character2);
    }

    private void bothRandom() {
        Character character1, character2;

        try {
            character1 = CHARACTER_CONTROLLER.getRandomCharacter();
            character2 = CHARACTER_CONTROLLER.getRandomCharacter();
        } catch (IllegalStateException e) {
            displayMessage("Not enough characters for random selection. Creating two new characters.");
            character1 = createNewCharacter();
            character2 = createNewCharacter();
        }

        COMBAT_VIEW.startCombat(character1, character2);
    }

    private void exitGame() {
        System.out.println("Exiting...");
        exit(0);
    }

}
