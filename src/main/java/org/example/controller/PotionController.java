package org.example.controller;

import org.example.model.Potion;
import org.example.model.Characters.Character;

import java.util.*;

public class PotionController {
    private List<Potion> potions;
    private Random random;

    public PotionController() {
        this.potions = new ArrayList<>();
        this.random = new Random();
        createPredefinedPotions();
    }

    public void createPredefinedPotions() {
        potions.addAll(Arrays.asList(
                new Potion.Builder().name("Nectar of Ambrosia").healthBoost(250).build(),
                new Potion.Builder().name("Heracles' Strength").defenseBoost(15).build(),
                new Potion.Builder().name("Asclepius' Elixir").healthBoost(275).build(),
                createPotion("Athena's Shield", 10, 25),
                new Potion.Builder().name("Panacea's Remedy").healthBoost(300).build(),
                createPotion("Iris Elixir", 325, 15)
        ));
    }

    public Potion createPotion(String name, int healthBoost, int defenseBoost) {
        return new Potion.Builder()
                .name(name)
                .healthBoost(healthBoost)
                .defenseBoost(defenseBoost)
                .build();
    }

    public void showPotion(Potion potion) {
        System.out.println(potion);
    }

    public void showAllPotions() {
        System.out.println("All potions");
        getPotions().forEach(this::showPotion);
    }

    public void assignRandomPotionToCharacter(Character character) {
        Potion randomPotion = getRandomPotion();
        character.addPotion(randomPotion);
        //usePotion(character, randomPotion);
    }

    public void assignPotionToCharacter(Character character, Potion potion) {
        if (potion != null) {
            character.addPotion(potion);
        } else {
            System.out.println("Potion not found.");
        }
    }

    public void usePotion(Character character, Potion potion) {
        applyPotionEffects(character, potion);
        character.getPotions().remove(potion);
        System.out.printf("%s drunk the potion %s, new stats | health: %d, defense: %d\n", character.getName(), potion.getName(), character.getHealth(), character.getDefense());
    }

    public void listPotions(Character character) {
        System.out.printf("\nAvailable Potions for %s:\n", character.getName());
        character.getPotions().forEach(potion -> System.out.println(potion.getName()));
    }

    public Potion getPotionByIndex(int index) {
        if (index >= 0 && index < potions.size()) {
            return potions.get(index);
        }
        return null;  // Si el índice está fuera de rango, devolver null
    }

    private Potion findCharactersPotion(Character character, String potionName) {
        return character.getPotions().stream()
               .filter(potion -> potion.getName().equalsIgnoreCase(potionName))
               .findFirst()
               .orElse(null);
    }

    private void applyPotionEffects(Character character, Potion potion) {
        character.setPotion(potion);
    }


    public List<Potion> getPotions() {
        return potions;
    }

    public Potion getRandomPotion() {
        return potions.get(random.nextInt(potions.size()));
    }
}
