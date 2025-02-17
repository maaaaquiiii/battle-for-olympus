package org.example.controller;

import org.example.model.Characters.Character;
import org.example.model.Potion;
import org.example.model.Weapon;
import org.example.view.MenuView;
import org.example.controller.PotionController;

import java.util.Random;

import static org.example.view.View.scanner;

public class CombatController {
    private PotionController potionController;
    private MenuView menuView;
    private Random random;

    public CombatController() {
        this.potionController = new PotionController();
        this.menuView = new MenuView();
        this.random = new Random();
    }

    public void initiateCombat(Character character1, Character character2) {
        menuView.displayMessage("\n--- THE FIGHT BEGINS ---\n");
        showCombatStatus(character1, character2);
        System.out.println(character1.getName() + " vs " + character2.getName());
        boolean isCombatOver = false;

        while (!isCombatOver) {
            character1Turn(character1, character2);
            if (!character2.isAlive()) {
                isCombatOver = true;
            }
            character2Turn(character2, character1);
            if (!character1.isAlive()) {
                isCombatOver = true;
            }
            showCombatStatus(character1, character2);
        }

        Character winner = (character1.isAlive()) ? character1 : character2;
        Character loser = (!character1.isAlive()) ? character1 : character2;
        System.out.printf("%s beat %s\n", winner.getName(), loser.getName());
    }

    private void showCombatStatus(Character character1, Character character2) {
        System.out.println(character1);
        System.out.println(character2);
    }

    public void character1Turn(Character character1, Character character2) {
        menuView.displayMessage("Choose an action (1: Attack, 2: Use Potion, 3: Equip Weapon): ");
        int action = -1;
        while (action < 1 || action > 3) {
            action = menuView.getUserInt();
            switch (action) {
                case 1 -> {
                    if (character1.isCriticalHit()) {
                        character1.attack(character2, 3);
                        menuView.displayMessage("Critical hit! Your attack is multiplied by 3.");
                        System.out.printf("%s attacks %s, ", character1.getName(), character2.getName());
                        System.out.printf("%s takes %d damage! Remaining health: %d\n", character2.getName(), (character1.getAttack() * 3), character2.getHealth());
                    } else {
                        character1.attack(character2);
                        System.out.printf("%s attacks %s, ", character1.getName(), character2.getName());
                        System.out.printf("%s takes %d damage! Remaining health: %d\n", character2.getName(), character1.getAttack(), character2.getHealth());
                    }
                }
                case 2 -> character1UsePotion(character1);
                case 3 -> equipWeapon(character1);
                default -> System.out.println("Invalid action! Please choose again.");
            }
        }
    }

    public void character2Turn(Character character2, Character character1) {
        int action = random.nextInt(10);

        System.out.println("random action: " + action);
        switch (action) {
            case 1, 5 -> character2UsePotion(character2);
            case 3 -> {
                equipWeaponRandomly(character2);
                System.out.printf("%s equips %s", character2.getName(), character2.getWeapon().getName());
            }
            default -> {
                if (character2.isCriticalHit()) {
                    menuView.displayMessage("Critical hit! Your attack is multiplied by 3.");
                    System.out.printf("%s attacks %s, ", character2.getName(), character1.getName());
                    character2.attack(character1, 3);
                    System.out.printf("%s takes %d damage! Remaining health: %d\n", character1.getName(), (character2.getAttack() * 3), character1.getHealth());
                } else {
                    character2.attack(character1);
                    System.out.printf("%s attacks %s, ", character2.getName(), character1.getName());
                    System.out.printf("%s takes %d damage! Remaining health: %d\n", character1.getName(), character2.getAttack(), character1.getHealth());
                }
            }
        }
        int randomNum = random.nextInt(10);
        System.out.println("Random number for assign potion to character 2 " + randomNum);
        if (randomNum < 3) {
            PotionController potionController = new PotionController();
            potionController.assignRandomPotionToCharacter(character2);
        }
    }

    public void character1UsePotion(Character character1) {
        CharacterController characterController = new CharacterController();
        characterController.assignPotionToCharacter(character1);
    }

    public void character2UsePotion(Character character2) {
        if (!character2.getPotions().isEmpty()) {
            Potion randomPotion = potionController.getRandomPotion();
            potionController.usePotion(character2, randomPotion);
            //System.out.println(character2.getName() + " uses " + randomPotion.getName() + "!");
        } else {
            System.out.println(character2.getName() + " has no potions to use!");
        }
    }
    public void equipWeapon(Character character1) {
        WeaponController weaponController = new WeaponController();
        weaponController.showAllWeapons();

        menuView.clearBuffer(menuView.getScanner());
        menuView.displayMessage("Choose a weapon to equip by number:");
        int weaponChoice = menuView.getUserInt();
        Weapon weapon = weaponController.getWeaponByIndex(weaponChoice - 1);
        character1.setWeapon(weapon);
        System.out.printf("%s equips %s!\n", character1.getName(), weapon.getName());
    }

    private void equipWeaponRandomly(Character character2) {
        WeaponController weaponController = new WeaponController();

        Weapon[] allWeapons = weaponController.getWeapons();
        if (allWeapons.length == 0) {
            menuView.displayMessage("No weapons available to equip.");
            return;
        }
        Weapon randomWeapon = weaponController.getRandomWeapon();
        character2.setWeapon(randomWeapon);
        System.out.printf("%s equips %s!\n", character2.getName(), randomWeapon.getName());
    }
}
