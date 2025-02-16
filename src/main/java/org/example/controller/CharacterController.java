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
        addCharacter(createCharacter("Centaur", "animal", 2500, 20, 20));
        addCharacter(createCharacter("Cerberus", "animal", 2750, 25, 10));
        addCharacter(createCharacter("Chimera", "animal", 2750, 30, 15));
        addCharacter(createCharacter("Griffin", "animal", 3000, 25, 10));
        addCharacter(createCharacter("Harpy", "animal", 2750, 30, 15));
        addCharacter(createCharacter("Minotaur", "animal", 3000, 35, 20));
        addCharacter(createCharacter("Apollo", "god", 5000, 40, 30));
        addCharacter(createCharacter("Ares", "god", 5500, 45, 40));
        addCharacter(createCharacter("Artemis", "god", 5750, 55, 45));
        addCharacter(createCharacter("Athena", "god", 5500, 50, 45));
        addCharacter(createCharacter("Hades", "god", 5500, 55, 40));
        addCharacter(createCharacter("Poseidon", "god", 5500, 50, 45));
        addCharacter(createCharacter("Zeus", "god", 6000, 55, 40));
        addCharacter(createCharacter("Aquiles", "hero", 3250, 30, 25));
        addCharacter(createCharacter("Heracles", "hero", 3500, 40, 30));
        addCharacter(createCharacter("Perseus", "hero", 3750, 40, 30));
        addCharacter(createCharacter("Theseus", "hero", 4000, 50, 25));
        addCharacter(createCharacter("Cronus", "titan", 9000, 50, 50));
        addCharacter(createCharacter("Hyperion", "titan", 7500, 50, 40));
        addCharacter(createCharacter("Oceanus", "titan", 8500, 55, 45));
        addCharacter(createCharacter("Rhea", "titan", 9500, 60, 40));
        addCharacter(createCharacter("Thea", "titan", 9000, 65, 45));
        addCharacter(createCharacter("Themis", "titan", 8000, 55, 50));
    }

    public Character createCharacter(String name, String type, int health, int attack, int defense) {
        Character newCharacter = CharacterFactory.createCharacter(name, type, health, attack, defense);
//        assignPotionToCharacter(newCharacter);
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
        characterView.displayMessage("Do you want to assign a potion manually or randomly? (manually/randomly) ");

        if(characterView.getUserString().equalsIgnoreCase("manually")) {
            potionController.listPotions(character);
            characterView.displayMessage("What potion do you want to assign yourself?");
            potionController.assignPotionToCharacter(character, characterView.getUserString());
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

    public void drinkPotion(Character character) {
        potionController.usePotion(character);
    }

//    potionController.selectPotion(character);

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