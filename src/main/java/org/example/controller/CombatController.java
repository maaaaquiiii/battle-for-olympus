package org.example.controller;

import org.example.model.Characters.Character;
import org.example.model.Potion;
import org.example.model.Weapon;

import java.util.Random;

public class CombatController {
    private final PotionController potionController;
    private final WeaponController weaponController;
    private final Random random;

    public CombatController() {
        this.potionController = new PotionController();
        this.weaponController = new WeaponController();
        this.random = new Random();
    }

    public void randomCharacterTurn(Character attacker, Character defender) {
        int action = getRandomAction();
        System.out.println(attacker.getName() + " has chosen the option: " + action);
        switch (action) {
            case 2 -> character2UsePotion(attacker);
            case 3 -> equipWeaponRandomly(attacker);
            default -> attack(attacker, defender);
        }
    }

    private int getRandomAction() {
        return random.nextInt(10);
    }

    public void attack(Character attacker, Character defender) {
        int damageMultiplier = 1;
        if (attacker.isCriticalHit()) {
            System.out.println("Critical Hit! Damage x3");
            damageMultiplier = 3;

        }
        attacker.attack(defender, damageMultiplier);
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " and deals " + attacker.getAttack() + " damage.");
        System.out.println(defender.getName() + " now has " + defender.getHealth() + " health.");
    }

    private void character2UsePotion(Character character2) {
        potionController.assignRandomPotionToCharacter(character2);
        if (!character2.getPotions().isEmpty()) {
            int usePotion = random.nextInt(0, 1);
            System.out.println("usePotion number: " + usePotion);
            if(usePotion == 0) {
                potionController.usePotion(character2, character2.getPotions().get(random.nextInt(character2.getPotions().size())));
            }
            System.out.println(character2.getName() + " has decided save the potion");
        } else {
            System.err.println("Character has no potions");
        }
    }
    public void equipManualWeapon(Character character, int index) {
        weaponController.equipWeapon(character, weaponController.getWeaponByIndex(index));
    }

    private void equipWeaponRandomly(Character character2) {
        Weapon[] allWeapons = weaponController.getWeapons();
        if (allWeapons.length == 0) {
            System.err.println("No weapons");
        }
        Weapon randomWeapon = weaponController.getRandomWeapon();
        character2.setWeapon(randomWeapon);
    }
}
