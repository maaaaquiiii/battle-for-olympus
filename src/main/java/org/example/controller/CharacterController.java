package org.example.controller;

import org.example.model.CharacterFactory;
import org.example.model.Characters.Character;
import org.example.model.Weapon;
import org.example.view.CharacterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterController {
    private List<Character> mythologicalAnimals;
    private List<Character> gods;
    private List<Character> heroes;
    private List<Character> humans;
    private List<Character> titans;
    private List<Character> selectedCharacters;
    private PotionController potionController;
    private CharacterView characterView;
    private Random random;

    public CharacterController() {
        this.mythologicalAnimals = new ArrayList<>();
        this.gods = new ArrayList<>();
        this.heroes = new ArrayList<>();
        this.humans = new ArrayList<>();
        this.titans = new ArrayList<>();
        this.selectedCharacters = new ArrayList<>();
        this.potionController = new PotionController();
        this.characterView = new CharacterView();
        this.random = new Random();
        createPredefinedCharacters();
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
        addCharacter(createCharacter("Aquiles", "hero", 4250, 115, 15));
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
        Character newCharacter = CharacterFactory.createCharacter(name, type, health, attack, defense);
        return newCharacter;
    }

    public void addCharacter(Character character) {
        switch (character.getType().toUpperCase()) {
            case "ANIMAL" -> getMythologicalAnimals().add(character);
            case "GOD" -> getGods().add(character);
            case "HERO" -> getHeroes().add(character);
            case "TITAN" -> getTitans().add(character);
            default -> getHumans().add(character);
        }
    }

    public void assignPotionToCharacter(Character character) {
        characterView.clearBuffer(characterView.getScanner());
        characterView.displayMessage("Do you want to assign a potion manually or randomly? (manually/randomly) ");
        String choice = characterView.getUserString();
        if (choice.equalsIgnoreCase("manually")) {
            potionController.showAllPotions();
            characterView.displayMessage("What potion do you want to assign?");
            int index = characterView.getUserInt();
            potionController.assignPotionToCharacter(character, potionController.getPotionByIndex(index));
            potionController.usePotion(character, potionController.getPotionByIndex(index));
        } else {
            potionController.assignRandomPotionToCharacter(character);
        }
    }

    public List<Character> getMythologicalAnimals() {
        return mythologicalAnimals;
    }

    public List<Character> getGods() {
        return gods;
    }

    public List<Character> getHeroes() {
        return heroes;
    }

    public List<Character> getTitans() {
        return titans;
    }

    public List<Character> getHumans() {
        return humans;
    }

    public List<Character> getSelectedCharacters() {
        return selectedCharacters;
    }

    public void equipWeapon(Character character, Weapon weapon) {
        character.setWeapon(weapon);
    }

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
        return Stream.concat(Stream.concat(
                Stream.concat(
                        mythologicalAnimals.stream(),
                        gods.stream()),
                        heroes.stream()),
                        titans.stream())
                .collect(Collectors.toList());
    }
}