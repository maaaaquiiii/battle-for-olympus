package org.example.controller;

import org.example.model.Characters.Character;
import org.example.model.Potion;
import org.example.model.Weapon;

import java.util.Random;

public class CombatController {
    private final CharacterController characterController;
    private final PotionController potionController;
    private final WeaponController weaponController;
    private final Random random;
    private final boolean isPlayer1Manual;
    private final boolean isPlayer2Manual;

    public CombatController() {
        this.characterController = new CharacterController();
        this.potionController = new PotionController();
        this.weaponController = new WeaponController();
        this.random = new Random();
        this.isPlayer1Manual = true;
        this.isPlayer2Manual = true;
    }

    public void initiateCombat(Character character1, Character character2, boolean isPlayer1Manual, boolean isPlayer2Manual) {
        boolean isCombatOver = false;
        while (!isCombatOver) {
            characterTurn(character1, character2, isPlayer1Manual);
            if(!character2.isAlive()) {
                isCombatOver = true;
            }
            characterTurn(character2, character1, isPlayer2Manual);
        }
    }


    private void characterTurn(Character attacker, Character defender, boolean manualMode) {
        if (manualMode) {
            int action = getManualAction(attacker);
            switch (action) {
                case 1 -> attack(attacker, defender);
                case 2 -> useManualPotion(attacker);
                case 3 -> equipManualWeapon(attacker);
            }
        } else {
            int action = getRandomAction();
            switch (action) {
                case 2 -> character2UsePotion(attacker);
                case 3 -> equipWeaponRandomly(attacker);
                default -> attack(attacker, defender);
            }
        }
    }

    private int getManualAction(Character character) {
        throw new UnsupportedOperationException("Manual input managed by MenuView");
    }

    private int getRandomAction() {
        return random.nextInt(10);
    }

    private void attack(Character attacker, Character defender) {
        int damageMultiplier = 1;
        if (attacker.isCriticalHit()) {
            damageMultiplier = 3;
        }
        attacker.attack(defender, damageMultiplier);
    }

    private void useManualPotion(Character character, int index) {
        if(!character.getPotions().isEmpty()) {
            Potion potion = potionController.getPotionByIndex(index);
            potionController.assignPotionToCharacter(character, potion);
            potionController.usePotion(character, potion);
        }
    }
    public void character2UsePotion(Character character2) {
        if (!character2.getPotions().isEmpty()) {
            potionController.assignRandomPotionToCharacter(character2);
            potionController.usePotion(character2, character2.getPotions().get(random.nextInt(character2.getPotions().size())));
        } else {
            throw new IllegalStateException("Character has no potions");
        }
    }
    public void equipManualWeapon(Character character, int index) {
        weaponController.equipWeapon(character, weaponController.getWeaponByIndex(index));
    }

    private void equipWeaponRandomly(Character character2) {
        Weapon[] allWeapons = weaponController.getWeapons();
        if (allWeapons.length == 0) {
            throw new IllegalStateException("No weapons");
        }
        Weapon randomWeapon = weaponController.getRandomWeapon();
        character2.setWeapon(randomWeapon);
        System.out.printf("%s equips %s!\n", character2.getName(), randomWeapon.getName());
    }
}
