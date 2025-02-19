package org.example.controller;

import org.example.model.CharacterFactory;
import org.example.model.Characters.Character;
import org.example.model.Potion;
import org.example.model.Weapon;

import java.util.*;

public class CharacterController {
    private final Map<String, List<Character>> charactersByType;
    private final List<Character> selectedCharacters;
    private final PotionController potionController;
    private final Random random;

    public CharacterController() {
        this.charactersByType = new HashMap<>();
        this.selectedCharacters = new ArrayList<>();
        this.potionController = new PotionController();
        this.random = new Random();
        initializeCharacterCategories();
        createPredefinedCharacters();
    }

    private void initializeCharacterCategories() {
        charactersByType.put("ANIMAL", new ArrayList<>());
        charactersByType.put("GOD", new ArrayList<>());
        charactersByType.put("HERO", new ArrayList<>());
        charactersByType.put("HUMAN", new ArrayList<>());
        charactersByType.put("TITAN", new ArrayList<>());
    }

    private void createPredefinedCharacters() {
        addCharacter(createCharacter("Centaur", "animal", 3500, 100, 15));
        addCharacter(createCharacter("Cerberus", "animal", 3750, 105, 10));
        addCharacter(createCharacter("Chimera", "animal", 3750, 100, 15));
        addCharacter(createCharacter("Griffin", "animal", 4000, 105, 10));
        addCharacter(createCharacter("Harpy", "animal", 3750, 100, 15));
        addCharacter(createCharacter("Minotaur", "animal", 4000, 105, 15));
        addCharacter(createCharacter("Apollo", "god", 5000, 130, 10));
        addCharacter(createCharacter("Ares", "god", 5500, 125, 20));
        addCharacter(createCharacter("Artemis", "god", 5750, 135, 15));
        addCharacter(createCharacter("Athena", "god", 5500, 130, 10));
        addCharacter(createCharacter("Hades", "god", 5500, 135, 20));
        addCharacter(createCharacter("Poseidon", "god", 5500, 130, 15));
        addCharacter(createCharacter("Zeus", "god", 6000, 125, 20));
        addCharacter(createCharacter("Achilles", "hero", 4250, 115, 15));
        addCharacter(createCharacter("Heracles", "hero", 4500, 110, 20));
        addCharacter(createCharacter("Perseus", "hero", 4750, 120, 10));
        addCharacter(createCharacter("Theseus", "hero", 5000, 110, 15));
        addCharacter(createCharacter("Cronus", "titan", 9000, 115, 20));
        addCharacter(createCharacter("Hyperion", "titan", 7500, 150, 20));
        addCharacter(createCharacter("Oceanus", "titan", 8500, 145, 15));
        addCharacter(createCharacter("Rhea", "titan", 9500, 140, 20));
        addCharacter(createCharacter("Thea", "titan", 9000, 145, 15));
        addCharacter(createCharacter("Themis", "titan", 8000, 140, 20));
    }

    public Character createCharacter(String name, String type, int health, int attack, int defense) {
        return CharacterFactory.createCharacter(name, type, health, attack, defense);
    }

    public void addCharacter(Character character) {
        charactersByType.getOrDefault(character.getType().toUpperCase(), charactersByType.get("HUMAN"))
                .add(character);
    }

//    public void assignPotionToCharacter(Character character, Potion potion) {
//        if (potion != null) {
//            potionController.assignPotionToCharacter(character, potion);
//        }
//    }
//
//    public void usePotion(Character character, Potion potion) {
//        potionController.usePotion(character, potion);
//    }
//
//    public void equipWeapon(Character character, Weapon weapon) {
//        character.setWeapon(weapon);
//    }

    public Character findCharacterByName(String name) {
        return getAllCharacters().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Character getRandomCharacter() {
        List<Character> allCharacters = getAllCharacters();

        if(allCharacters.isEmpty()) {
            System.err.println("Error: No characters available.");
            return null;
        }
        return allCharacters.get(random.nextInt(allCharacters.size()));
    }

    public List<Character> getAllCharacters() {
        return charactersByType.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }
}