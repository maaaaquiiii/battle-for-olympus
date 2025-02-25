package org.example.controller;

import org.example.model.CharacterFactory;
import org.example.model.Characters.*;
import org.example.model.Characters.Character;

import java.util.*;

public class CharacterController {
    private final Map<String, List<Character>> charactersByType;
    private final Random random;

    public CharacterController() {
        this.charactersByType = new HashMap<>();
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
        //Create a list of animals
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Centaur").characterType("animal").health(1250).attack(110).defense(15).build());
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Cerberus").characterType("animal").health(1200).attack(105).defense(10).build());
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Chimera").characterType("animal").health(1350).attack(110).defense(15).build());
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Griffin").characterType("animal").health(1300).attack(105).defense(10).build());
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Harpy").characterType("animal").health(1275).attack(115).defense(15).build());
        addCharacter(new MythologicalAnimal.MythologicalAnimalBuilder().name("Minotaur").characterType("animal").health(1325).attack(110).defense(15).build());

        //Crating a list of gods
        addCharacter(new God.GodBuilder().name("Ares").characterType("god").health(1750).attack(140).defense(15).build());
        addCharacter(new God.GodBuilder().name("Artemis").characterType("god").health(1800).attack(135).defense(20).build());
        addCharacter(new God.GodBuilder().name("Athena").characterType("god").health(1725).attack(140).defense(15).build());
        addCharacter(new God.GodBuilder().name("Hades").characterType("god").health(1825).attack(145).defense(20).build());
        addCharacter(new God.GodBuilder().name("Poseidon").characterType("god").health(1775).attack(140).defense(20).build());
        addCharacter(new God.GodBuilder().name("Zeus").characterType("god").health(1850).attack(145).defense(15).build());

        //Creating a list of heroes
        addCharacter(new Hero.HeroBuilder().name("Achilles").characterType("Hero").health(1500).attack(125).defense(15).build());
        addCharacter(new Hero.HeroBuilder().name("Heracles").characterType("Hero").health(1550).attack(120).defense(20).build());
        addCharacter(new Hero.HeroBuilder().name("Perseus").characterType("Hero").health(1575).attack(125).defense(10).build());
        addCharacter(new Hero.HeroBuilder().name("Theseus").characterType("Hero").health(1600).attack(130).defense(15).build());

        //Creating a list of titans
        addCharacter(new Titan.TitanBuilder().name("Cronus").characterType("Titan").health(1900).attack(150).defense(20).build());
        addCharacter(new Titan.TitanBuilder().name("Hyperion").characterType("Titan").health(2000).attack(155).defense(15).build());
        addCharacter(new Titan.TitanBuilder().name("Oceanus").characterType("Titan").health(1950).attack(150).defense(20).build());
        addCharacter(new Titan.TitanBuilder().name("Rhea").characterType("Titan").health(1975).attack(155).defense(15).build());
        addCharacter(new Titan.TitanBuilder().name("Thea").characterType("Titan").health(1925).attack(150).defense(15).build());
        addCharacter(new Titan.TitanBuilder().name("Themis").characterType("Titan").health(1950).attack(160).defense(20).build());
    }

    public Character createCharacter(String name, String type) {
        return CharacterFactory.createCharacter(name, type);
    }

    public void addCharacter(Character character) {
        charactersByType.getOrDefault(character.getType().toUpperCase(), charactersByType.get("HUMAN"))
                .add(character);
    }

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