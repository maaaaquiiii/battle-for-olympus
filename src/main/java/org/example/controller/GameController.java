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

    private Character selectOrCreateCharacter() {

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

    private void bothRandom() {
        Character character1 = characterController.getRandomCharacter();
        Character character2 = characterController.getRandomCharacter();
        combatController.initiateCombat(character1, character2);
    }
}
