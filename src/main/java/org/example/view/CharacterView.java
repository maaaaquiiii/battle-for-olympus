package org.example.view;

import org.example.model.Character;

public class CharacterView {
    public void displayCharacterStats(Character character) {
        System.out.println("Character Stats:");
        System.out.println(character);
    }

    public void displayWinner(Character winner) {
        System.out.println(winner.getName() + " wins the fight!");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayCharacterSkills(Character character) {
        if (character.hasSkill()) {
            System.out.println(character.getName() + " has the following skills:");
            character.getSkills().forEach(skill -> System.out.println("- " + skill.getName()));
        } else {
            System.out.println(character.getName() + " has no skills.");
        }
    }

    public void displayPotions(Character character) {
        if (character.hasPotion()) {
            System.out.println(character.getName() + " has the following potions:");
            character.getPotions().forEach(potion -> System.out.println("- " + potion.getName()));
        } else {
            System.out.println(character.getName() + " has no potions.");
        }
    }
}
