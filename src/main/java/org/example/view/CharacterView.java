package org.example.view;

import org.example.model.Characters.Character;
import org.example.model.Potion;

import java.util.Scanner;

public class CharacterView extends View {
    public void displayCharacterDetails(Character character) {
        System.out.println("Character name: " + character.getName());
        System.out.println("Health: " + character.getHealth());
        System.out.println("Attack: " + character.getAttack());
        System.out.println("Defense: " + character.getDefense());
    }

    public void displayPotionDetails(Potion potion) {
        System.out.println("Potion name: " + potion.getName());
        System.out.println("Health boost: " + potion.getHealthBoost());
        System.out.println("Defense boost: " + potion.getDefenseBoost());
    }

    public int getUserInt() {
        System.out.print("Select an option: ");
        return scanner.nextInt();
    }

    public String getUserString() {
        return scanner.nextLine();
    }

}
