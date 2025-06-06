package org.example.controller;

import org.example.model.Potion;
import org.example.model.Characters.Character;

import java.util.*;

public class PotionController {
    private final List<Potion> potions;
    private  final Random random;

    public PotionController() {
        this.potions = new ArrayList<>();
        this.random = new Random();
        createPredefinedPotions();
    }

    private void createPredefinedPotions() {
        potions.addAll(Arrays.asList(
                new Potion.Builder().name("Nectar of Ambrosia").healthBoost(350).build(),
                new Potion.Builder().name("Heracles' Strength").defenseBoost(25).build(),
                new Potion.Builder().name("Asclepius' Elixir").healthBoost(375).build(),
                new Potion.Builder().name("Hide of the Nemean Lion").defenseBoost(20).build(),
                createPotion("Athena's Shield", 425, 25),
                new Potion.Builder().name("Panacea's Remedy").healthBoost(400).build(),
                new Potion.Builder().name("Rod of Asclepius").healthBoost(375).build(),
                createPotion("Iris Elixir", 325, 15),
                createPotion("Adamantium", 400, 15)
        ));
    }

    private Potion createPotion(String name, int healthBoost, int defenseBoost) {
        return new Potion.Builder()
                .name(name)
                .healthBoost(healthBoost)
                .defenseBoost(defenseBoost)
                .build();
    }

    public void assignRandomPotionToCharacter(Character character) {
        Potion potion = getRandomPotion();
        character.addPotion(potion);
        System.out.println(character.getName() + " has been assigned " + potion.getName());
    }

    public void assignPotionToCharacter(Character character, Potion potion) {
        if (character == null || potion == null) {
            throw new IllegalArgumentException("Character and potion must not be null.");
        }
        character.addPotion(potion);
    }

    public void usePotion(Character character, Potion potion) {
        if (character == null || potion == null) {
            throw new IllegalArgumentException("Character and potion must not be null.");
        }
        if (!character.getPotions().contains(potion)) {
            throw new NoSuchElementException("Potion not found in character's inventory.");
        }
        applyPotionEffects(character, potion);
        character.removePotion(potion);
    }

    public Potion getPotionByIndex(int index) {
        if (index >= 0 && index < potions.size()) {
            return potions.get(index);
        }
        return null;
    }

    private void applyPotionEffects(Character character, Potion potion) {
        character.setPotion(potion);
    }

    public Potion getRandomPotion() {
        return potions.get(random.nextInt(potions.size()));
    }

    public List<Potion> getPotions() {
        return potions;
    }
}
