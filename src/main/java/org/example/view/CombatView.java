package org.example.view;

import org.example.controller.WeaponController;
import org.example.model.Characters.Character;
import org.example.controller.CombatController;
import org.example.model.Potion;
import org.example.model.Weapon;
import org.example.view.*;

import java.util.List;


public class CombatView extends View {
    private final CombatController combatController = new CombatController();
    private final WeaponController weaponController = new WeaponController();

    public void initiateCombat(Character character1, Character character2) {
        displayMessage("Combat begins between " + character1.getName() + " and " + character2.getName() + "!");
        combatController.initiateCombat(character1, character2, getUserBoolean());
        displayMessage("Combat has ended.");
    }

    public int getActionChoice() {
        int choice = -1;

        while (choice < 1 || choice > 3) {
            displayMessage("Choose an action:");
            displayMessage("1. Attack");
            displayMessage("2. Use Potion");
            displayMessage("3. Equip Weapon");
            System.out.print("Enter your action (1-3): ");
            choice = getUserInt();
            clearBuffer(scanner);
            if (choice < 1 || choice > 3) {
                displayMessage("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        return choice;
    }

    public int getPotionIndexFromUser(Character character) {
        displayMessage("Choose a potion from the following list:");
        for (int i = 0; i < character.getPotions().size(); i++) {
            Potion potion = character.getPotions().get(i);
            displayMessage((i + 1) + ". " + potion.getName());
        }
        displayMessage("Enter the number of the potion you want to use:");
        return getUserInt() - 1; // Restar 1 para que el índice empiece desde 0
    }

    public int getWeaponIndexFromUser() {
        displayMessage("Choose a weapon from the following list:");
        // Mostrar las armas disponibles
        List<Weapon> weapons = weaponController.getAllWeapons();
        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            displayMessage((i + 1) + ". " + weapon.getName() + " - " + weapon.getDescription());
        }
        // Solicitar la entrada del índice del arma
        displayMessage("Enter the number of the weapon you want to equip:");
        return getUserInt() - 1; // Restar 1 para que el índice empiece desde 0
    }


    public void displayCombatState(Character character1, Character character2) {
        displayMessage("\nCurrent combat status:");
        displayMessage(character1.getName() + " - Health: " + character1.getHealth());
        displayMessage(character2.getName() + " - Health: " + character2.getHealth());
        displayMessage("--------------------------");
    }

    public void displayWinner(Character winner) {
        displayMessage("Congratulations! " + winner.getName() + " has won the battle!");
    }

}
