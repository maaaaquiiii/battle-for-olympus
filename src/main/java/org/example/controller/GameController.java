package org.example.controller;

import org.example.model.Potion;
import org.example.model.Weapon;
import org.example.model.Characters.Character;
import org.example.view.MenuView;

import java.util.List;

import static java.lang.System.exit;

public class GameController {
    private CharacterController characterController;
    private CombatController combatController;
    private MenuView menuView;

    public GameController() {
        this.characterController = new CharacterController();
        this.combatController = new CombatController();
        this.menuView = new MenuView();
    }

    public void startGame() {
        int option = 0;
        menuView.displayInstructions();
        while(option != 4) {
            menuView.displayMenu();
            option = menuView.getUserInt();
            switch (option) {
                case 1 -> manualSelection();
                case 2 -> oneRandomOneManual();
                case 3 -> bothRandom();
                case 4 -> {
                    menuView.displayMessage("Exiting...");
                    exit(0);
                }
                default -> throw new IllegalArgumentException("Invalid option, try again");
            }
        }
    }

    private void manualSelection() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = selectOrCreateCharacter();
        combatController.initiateCombat(character1, character2);
    }


    private void oneRandomOneManual() {
        Character character1 = selectOrCreateCharacter();
        Character character2 = characterController.getRandomCharacter();
        combatController.initiateCombat(character1, character2);
    }

    private Character selectOrCreateCharacter() {
        menuView.promptCharacterSelectionOrCreation();
        menuView.clearBuffer(menuView.getScanner());
        int choice = menuView.getUserInt();

        if (choice == 1) {
            List<Character> characters = characterController.getAllCharacters();
            characters.forEach(c -> System.out.println(c.getName()));
            menuView.clearBuffer(menuView.getScanner());
            System.out.print("Enter the character name: ");
            String name = menuView.getUserString();
            return characterController.findCharacterByName(name);
        } else {
            menuView.clearBuffer(menuView.getScanner());
            menuView.displayMessage("Enter new character name: ");
            String name = menuView.getUserString();
            menuView.clearBuffer(menuView.getScanner());
            menuView.displayMessage("Enter type (animal, hero, human, god, titan): ");
            String type = menuView.getUserString();
            menuView.clearBuffer(menuView.getScanner());
            menuView.displayMessage("Enter the health (values between 100-1000): ");
            int health = menuView.getUserInt();
            menuView.clearBuffer(menuView.getScanner());
            menuView.displayMessage("Enter the attack (values between 5-30): ");
            int attack = menuView.getUserInt();
            menuView.clearBuffer(menuView.getScanner());
            menuView.displayMessage("Enter the defense (values between 5-30): ");
            int defense = menuView.getUserInt();

            return characterController.createCharacter(name, type, health, attack, defense);
        }
    }

    private void bothRandom() {
        Character character1 = characterController.getRandomCharacter();
        assignRandomItems(character1);

        Character character2 = characterController.getRandomCharacter();
        assignRandomItems(character2);

        System.out.println("\nRandom Matchup: " + character1.getName() + " vs " + character2.getName());
        combatController.initiateCombat(character1, character2);
    }

    private void assignRandomItems(Character character) {
        WeaponController weaponController = new WeaponController();
        PotionController potionController = new PotionController();

        if (character == null) {
            throw new IllegalArgumentException("Error: Character not found. Please check the entered name.");
        }
        if (character.getWeapon() == null) {
            Weapon weapon = weaponController.getRandomWeapon();
            characterController.equipWeapon(character, weapon);
        }
        Potion potion = potionController.getRandomPotion();
        character.setPotion(potion);
    }
}
