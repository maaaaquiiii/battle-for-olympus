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
    private static CharacterController characterController = new CharacterController();
    private static View view = new View();
    private static MenuView menuView = new MenuView();
    private static CharacterView characterView = new CharacterView();
    private static CombatView combatView = new CombatView();



    public static void main(String[] args) {
        menuView.displayInstructions();
        startGame();
    }

    private static void startGame() {
        int option = 0;
        while (option != 4) {
            menuView.displayMenu();
            option = view.getUserInt();
            switch (option) {
                case 1 -> manualSelection();
                case 2 -> oneRandomOneManual();
                case 3 -> bothRandom();
                case 4 -> exitGame();
                default -> view.displayMessage("Invalid option, try again");
            }
        }
    }

    private static Character selectOrCreateCharacter() {
        menuView.promptCharacterSelectionOrCreation();
        int choice = view.getUserInt();

        if (choice == 1) {
            return selectExistingCharacter();
        } else if (choice == 2) {
            return createNewCharacter();
        } else {
            view.displayMessage("Invalid choice. Please try again.");
            return selectOrCreateCharacter();
        }
    }

    private static Character selectExistingCharacter() {
        List<Character> characters = characterController.getAllCharacters();

        if (characters.isEmpty()) {
            view.displayMessage("No characters available. Proceeding to create a new character.");
            return selectOrCreateCharacter();
        }

        view.displayMessage("List of all characters");
        characterView.displayCharacterList(characters);
        view.clearBuffer();
        view.displayMessage("Enter the name of the character you want to select:");
        String characterName = view.getUserString();

        try {
            return characterController.findCharacterByName(characterName);
        } catch (NoSuchElementException e) {
            view.displayMessage("Character not found. Please try again.");
            return selectExistingCharacter();
        }
    }

    private static Character createNewCharacter() {
        view.clearBuffer();
        view.displayMessage("Enter character's name:");
        String name = view.getUserString();
        view.displayMessage("Enter character's type (Animal, God, Hero, Human, Titan):");
        String type = view.getUserString();

        int health = 0;
        while (health < 1000 || health > 9000) {
            try {
                view.displayMessage("Enter character's health (between 1000 and 9000):");
                health = view.getUserInt();
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }

        int attack = 0;
        while (attack < 100 || attack > 200) {
            try {
                view.displayMessage("Enter character's attack (between 100 and 200):");
                attack = view.getUserInt();
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }

        int defense = 0;
        while (defense < 5 || defense > 20) {
            try {
                view.displayMessage("Enter character's defense (between 5 and 20):");
                defense = view.getUserInt();
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }

        try {
            Character newCharacter = characterController.createCharacter(name, type, health, attack, defense);
            characterController.addCharacter(newCharacter);
            view.displayMessage("Character created successfully!");
            return newCharacter;
        } catch (IllegalArgumentException e) {
            view.displayMessage("Invalid character data: " + e.getMessage());
            return createNewCharacter();
        }
    }

    private static void manualSelection() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = selectOrCreateCharacter();
        combatView.startCombat(character1, character2);
    }


    private static void oneRandomOneManual() {
        Character character1 = selectOrCreateCharacter();
        Character character2;

        try {
            character2 = characterController.getRandomCharacter();
        } catch (IllegalStateException e) {
            view.displayMessage("No characters available for random selection. Creating a new character.");
            character2 = createNewCharacter();
        }

        combatView.startCombat(character1, character2);
    }

    private static void bothRandom() {
        Character character1, character2;

        try {
            character1 = characterController.getRandomCharacter();
            character2 = characterController.getRandomCharacter();
        } catch (IllegalStateException e) {
            view.displayMessage("Not enough characters for random selection. Creating two new characters.");
            character1 = createNewCharacter();
            character2 = createNewCharacter();
        }

        combatView.startCombat(character1, character2);
    }

    private static void exitGame() {
        System.out.println("Exiting...");
        exit(0);
    }
}