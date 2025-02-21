package org.example;

import org.example.model.Characters.Character;
import org.example.controller.CharacterController;
import org.example.view.CombatView;
import org.example.view.View;
import org.example.view.MenuView;
import org.example.view.CharacterView;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.System.exit;


public class Main {
    private static final CharacterController CHARACTER_CONTROLLER = new CharacterController();
    private static final View VIEW = new View();
    private static final MenuView MENU_VIEW = new MenuView();
    private static final CharacterView CHARACTER_VIEW = new CharacterView();
    private static final CombatView COMBAT_VIEW = new CombatView();



    public static void main(String[] args) {
        MENU_VIEW.displayInstructions();
        startGame();
    }

    private static void startGame() {
        int option = 0;
        while (option != 4) {
            MENU_VIEW.displayMenu();
            option = VIEW.getUserInt();
            switch (option) {
                case 1 -> manualSelection();
                case 2 -> oneRandomOneManual();
                case 3 -> bothRandom();
                case 4 -> exitGame();
                default -> VIEW.displayMessage("Invalid option, try again");
            }
        }
    }

    private static Character selectOrCreateCharacter() {
        MENU_VIEW.promptCharacterSelectionOrCreation();
        int choice = VIEW.getUserInt();

        if (choice == 1) {
            return selectExistingCharacter();
        } else if (choice == 2) {
            return createNewCharacter();
        } else {
            VIEW.displayMessage("Invalid choice. Please try again.");
            return selectOrCreateCharacter();
        }
    }

    private static Character selectExistingCharacter() {
        List<Character> characters = CHARACTER_CONTROLLER.getAllCharacters();

        if (characters.isEmpty()) {
            VIEW.displayMessage("No characters available. Proceeding to create a new character.");
            return selectOrCreateCharacter();
        }

        VIEW.displayMessage("List of all characters");
        CHARACTER_VIEW.displayCharacterList(characters);
        View.clearBuffer();
        VIEW.displayMessage("Enter the name of the character you want to select:");
        String characterName = VIEW.getUserString();

        try {
            return CHARACTER_CONTROLLER.findCharacterByName(characterName);
        } catch (NoSuchElementException e) {
            VIEW.displayMessage("Character not found. Please try again.");
            return selectExistingCharacter();
        }
    }

    private static Character createNewCharacter() {
        View.clearBuffer();
        VIEW.displayMessage("Enter character's name:");
        String name = VIEW.getUserString();
        VIEW.displayMessage("Enter character's type (Animal, God, Hero, Human, Titan):");
        String type = VIEW.getUserString();

        int health = 0;
        while (health < 1000 || health > 9000) {
            try {
                VIEW.displayMessage("Enter character's health (between 1000 and 9000):");
                health = VIEW.getUserInt();
            } catch (NumberFormatException e) {
                VIEW.displayMessage("Invalid input. Please enter a number.");
            }
        }

        int attack = 0;
        while (attack < 100 || attack > 200) {
            try {
                VIEW.displayMessage("Enter character's attack (between 100 and 200):");
                attack = VIEW.getUserInt();
            } catch (NumberFormatException e) {
                VIEW.displayMessage("Invalid input. Please enter a number.");
            }
        }

        int defense = 0;
        while (defense < 5 || defense > 20) {
            try {
                VIEW.displayMessage("Enter character's defense (between 5 and 20):");
                defense = VIEW.getUserInt();
            } catch (NumberFormatException e) {
                VIEW.displayMessage("Invalid input. Please enter a number.");
            }
        }

        try {
            Character newCharacter = CHARACTER_CONTROLLER.createCharacter(name, type, health, attack, defense);
            CHARACTER_CONTROLLER.addCharacter(newCharacter);
            VIEW.displayMessage("Character created successfully!");
            return newCharacter;
        } catch (IllegalArgumentException e) {
            VIEW.displayMessage("Invalid character data: " + e.getMessage());
            return createNewCharacter();
        }
    }

    private static void manualSelection() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = selectOrCreateCharacter();
        COMBAT_VIEW.startCombat(character1, character2);
    }


    private static void oneRandomOneManual() {
        Character character1 = selectOrCreateCharacter();
        Character character2;

        try {
            character2 = CHARACTER_CONTROLLER.getRandomCharacter();
        } catch (IllegalStateException e) {
            VIEW.displayMessage("No characters available for random selection. Creating a new character.");
            character2 = createNewCharacter();
        }

        COMBAT_VIEW.startCombat(character1, character2);
    }

    private static void bothRandom() {
        Character character1, character2;

        try {
            character1 = CHARACTER_CONTROLLER.getRandomCharacter();
            character2 = CHARACTER_CONTROLLER.getRandomCharacter();
        } catch (IllegalStateException e) {
            VIEW.displayMessage("Not enough characters for random selection. Creating two new characters.");
            character1 = createNewCharacter();
            character2 = createNewCharacter();
        }

        COMBAT_VIEW.startCombat(character1, character2);
    }

    private static void exitGame() {
        System.out.println("Exiting...");
        exit(0);
    }
}