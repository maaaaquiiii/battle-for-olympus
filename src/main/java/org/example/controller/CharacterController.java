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
        addCharacter(createCharacter("Centaur", "animal", 1500, 110, 15));
        addCharacter(createCharacter("Cerberus", "animal", 1500, 105, 10));
        addCharacter(createCharacter("Chimera", "animal", 2000, 110, 15));
        addCharacter(createCharacter("Griffin", "animal", 1000, 105, 10));
        addCharacter(createCharacter("Harpy", "animal", 1550, 115, 15));
        addCharacter(createCharacter("Minotaur", "animal", 1750, 110, 15));
        addCharacter(createCharacter("Apollo", "god", 3250, 140, 10));
        addCharacter(createCharacter("Ares", "god", 3500, 135, 20));
        addCharacter(createCharacter("Artemis", "god", 3500, 140, 15));
        addCharacter(createCharacter("Athena", "god", 3500, 145, 10));
        addCharacter(createCharacter("Hades", "god", 3750, 140, 20));
        addCharacter(createCharacter("Poseidon", "god", 3750, 150, 15));
        addCharacter(createCharacter("Zeus", "god", 3750, 145, 20));
        addCharacter(createCharacter("Achilles", "hero", 2500, 125, 15));
        addCharacter(createCharacter("Heracles", "hero", 2250, 120, 20));
        addCharacter(createCharacter("Perseus", "hero", 3750, 125, 10));
        addCharacter(createCharacter("Theseus", "hero", 2500, 130, 15));
        addCharacter(createCharacter("Cronus", "titan", 5000, 175, 20));
        addCharacter(createCharacter("Hyperion", "titan", 4500, 160, 20));
        addCharacter(createCharacter("Oceanus", "titan", 4250, 170, 15));
        addCharacter(createCharacter("Rhea", "titan", 4750, 160, 20));
        addCharacter(createCharacter("Thea", "titan", 4500, 165, 15));
        addCharacter(createCharacter("Themis", "titan", 4250, 175, 20));
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
                .orElseThrow(() -> new NoSuchElementException("Character not found: " + name));
    }

    public Character getRandomCharacter() {
        List<Character> allCharacters = getAllCharacters();

        if(allCharacters.isEmpty()) {
            throw new IllegalStateException("No characters available.");
        }
        return allCharacters.get(random.nextInt(allCharacters.size()));
    }

    public List<Character> getAllCharacters() {
        return charactersByType.values().stream()
                .flatMap(Collection::stream)
                .toList();
    }
}