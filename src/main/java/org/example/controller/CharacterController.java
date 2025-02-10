package org.example.controller;

import org.example.model.Character;

import java.util.ArrayList;
import java.util.List;


public class CharacterController {
    private List<Character> characters;

    public CharacterController() {
        this.characters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public Character findCharacterByName(String name) {
        return characters.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

}
