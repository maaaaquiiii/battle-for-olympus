package org.example.view;

//import org.example.controller.WeaponController;
import org.example.model.Characters.Character;
import org.example.controller.CombatController;


public class CombatView extends View {
    private final CombatController combatController = new CombatController();
    private final PotionView potionView = new PotionView();
    private final WeaponView weaponView = new WeaponView();
    private final CharacterView characterView = new CharacterView();

    public void startCombat(Character character1, Character character2) {
        displayCombatState(character1);
        displayCombatState(character2);
        displayMessage("For the second character, do you want it to be against the machine (random) or against another player (manual)? (true -> random, false -> manual)");
        boolean isRandom = getUserBoolean();

        displayMessage("Combat starts!");
        while (character1.isAlive() || character2.isAlive()) {
            displayCharacterTurn(character1, character2);
            if(isRandom == true) {
                displayRandomCharacterTurn(character2, character1);
            } else {
                displayCharacterTurn(character2, character1);
            }
        }
        if(character1.isAlive() && !character2.isAlive()) {
            displayWinner(character1, character2);
        } else {
            displayWinner(character2, character1);
        }
    }

    private void displayRandomCharacterTurn(Character attacker, Character defender) {
        combatController.randomCharacterTurn(attacker, defender);
        displayCombatState(attacker);
        displayCombatState(defender);
    }

    private void displayCharacterTurn(Character attacker, Character character2) {

        int action = actionTurn(attacker);
        if (action == 1) {
            combatController.attack(attacker, character2);
        } else if (action == 2) {
            int potionIndex = getPotionIndexFromUser(attacker);
            combatController.useManualPotion(attacker, potionIndex);
        } else if (action == 3) {
            int weaponIndex = getWeaponIndexFromUser();
            combatController.equipManualWeapon(attacker, weaponIndex);
        } else {
            displayMessage("Invalid action. Please try again.");
        }
        displayCombatState(attacker);
        displayCombatState(character2);
    }

    private int actionTurn(Character character) {
        displayMessage(character.getName() + "'s turn!");
        displayActions();
        return getUserInt();
    }

    private void displayActions() {
        displayMessage("Choose an action:");
        displayMessage("1. Attack");
        displayMessage("2. Use Potion");
        displayMessage("3. Equip Weapon");
        System.out.print("Enter your action (1-3): ");
    }

    private int getPotionIndexFromUser(Character character) {
        potionView.displayPotions(character.getPotions());
        displayMessage("Enter the number of the potion you want to use:");
        return potionView.getPotionIndexFromUser();
    }

    private int getWeaponIndexFromUser() {
        displayMessage("Choose a weapon from the following list:");
        weaponView.displayWeaponsDetails();
        displayMessage("Enter the number of the weapon you want to equip:");
        return weaponView.getWeaponIndexFromUser();
    }


    private void displayCombatState(Character character) {
        displayMessage("\nCurrent" + character.getName() + " status:");
        characterView.displayCharacterDetails(character);
        displayMessage("--------------------------");
    }

    public void displayWinner(Character winner, Character loser) {
        displayMessage("Congratulations! " + winner.getName() + " has won the battle!");
        displayMessage("I´m sorry! " + loser.getName() + ", it will be later");
    }
}
