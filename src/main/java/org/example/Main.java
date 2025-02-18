package org.example;

import org.example.model.Characters.Character;
import org.example.controller.CharacterController;
import org.example.controller.CombatController;
import org.example.view.View;
import org.example.view.MenuView;
import org.example.view.CharacterView;

import java.util.List;

import static java.lang.System.exit;


public class Main {
    private static CharacterController characterController = new CharacterController();
    private static CombatController combatController = new CombatController();
    private static View view = new View();
    private static MenuView menuView = new MenuView();
    private static CharacterView characterView = new CharacterView();


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
        view.displayMessage("Enter the name of the character you want to select");
        String characterName = view.getUserString();
        Character character = characterController.findCharacterByName(characterName);

        if (character == null) {
            view.displayMessage("Character not found. Please try again.");
            return selectExistingCharacter();  // Si no se encuentra, volver a intentar
        } else {
            view.displayMessage("You selected the character: ");
            characterView.displayCharacterDetails(character);
            return character;
        }
    }

    private static Character createNewCharacter() {
        view.displayMessage("Enter character's name:");
        String name = view.getUserString();
        view.displayMessage("Enter character's type (Animal, God, Hero, Human, Titan):");
        String type = view.getUserString();

        int health = 0;
        while (health < 1000 || health > 9000) {
            view.displayMessage("Enter character's health (between 1000 and 9000):");
            health = view.getUserInt();
        }

        int attack = 0;
        while (attack < 10 || attack > 50) {
            view.displayMessage("Enter character's attack (between 10 and 50):");
            attack = view.getUserInt();
        }

        int defense = 0;
        while (defense < 5 || defense > 20) {
            view.displayMessage("Enter character's defense (between 5 and 20):");
            defense = view.getUserInt();
        }

        Character newCharacter = characterController.createCharacter(name, type, health, attack, defense);
        characterController.addCharacter(newCharacter);
        view.displayMessage("Character created successfully!");
        return newCharacter;
    }

    private static void manualSelection() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = selectOrCreateCharacter();
        view.displayMessage("For the second character, do you want it to be against the machine (random) or against another player (manual)? (true -> random, false -> manual)");
        boolean isRandom = view.getUserBoolean();
        combatController.initiateCombat(character1, character2, isRandom);
    }


    private static void oneRandomOneManual() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = characterController.getRandomCharacter();
        view.displayMessage("For the second character, do you want it to be against the machine (random) or against another player (manual)? (true -> random, false -> manual)");
        boolean isRandom = view.getUserBoolean();
        initiateCombat(character1, character2, isRandom);
    }

    private static void bothRandom() {
        Character character1 = characterController.getRandomCharacter();
        Character character2 = characterController.getRandomCharacter();
        view.displayMessage("For the second character, do you want it to be against the machine (random) or against another player (manual)? (true -> random, false -> manual)");
        boolean isRandom = view.getUserBoolean();
        initiateCombat(character1, character2, isRandom);
    }

    private static void exitGame() {
        System.out.println("Exiting...");
        exit(0);
    }

    private static void initiateCombat(Character character1, Character character2, boolean isPlayer2Random) {
        boolean isCombatOver = false;
        while (!isCombatOver) {
            combatController.characterTurn(character1, character2, false);
            if(!character2.isAlive()) {
                isCombatOver = true;
            }
            combatController.characterTurn(character2, character1, isPlayer2Random);
        }
    }
}