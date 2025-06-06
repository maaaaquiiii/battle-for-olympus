package org.example.view;

import org.example.model.Characters.Character;

import java.util.List;

public class CharacterView extends View {
    public void displayCharacterDetails(Character character) {
        displayMessage(character.toString());
        if(character.getWeapon() != null) {
            displayMessage("Weapon equipped: " + character.getWeapon().getName());
        }
    }

    public void displayCharacterList(List<Character> characters) {
        characters.stream()
                .map(Character::getName)
                .forEach(System.out::println);
    }
}