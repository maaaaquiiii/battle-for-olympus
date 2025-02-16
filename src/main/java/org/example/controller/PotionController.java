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
                new Potion.Builder().name("Nectar of Ambrosia").healthBoost(150).build(),
                new Potion.Builder().name("Heracles' Strength").defenseBoost(15).build(),
                new Potion.Builder().name("Asclepius' Elixir").healthBoost(175).build(),
                new Potion.Builder().name("Athena's Shield").healthBoost(10).defenseBoost(25).build(),
                new Potion.Builder().name("Panacea's Remedy").healthBoost(200).build(),
                new Potion.Builder().name("Iris' Elixir").healthBoost(125).defenseBoost(15).build()
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
        if (potions.isEmpty()) {
            System.out.println("No potions available to assign.");
            return;
        }
        Potion randomPotion = getRandomPotion();
        character.addPotion(randomPotion);
        System.out.println(character.getName() + " received the potion: " + randomPotion.getName());
    }

    public void assignPotionToCharacter(Character character, String potionName) {
        Potion potion = findPotionByName(character, potionName);

        if (potion != null) {
            character.addPotion(potion);
            System.out.println(character.getName() + " received the potion: " + potion.getName());
        } else {
            System.out.println("Potion not found.");
        }
    }

    public void usePotion(Character character) {
        if (character.getPotions().isEmpty()) {
            System.out.println(character.getName() + " has no potions.");
            return;
        }

        listPotions(character);
        System.out.println("\nEnter the name of the potion you want to use (or type 'cancel' to cancel):");
        String potionName = new Scanner(System.in).nextLine();

        if (potionName.equalsIgnoreCase("cancel")) {
            System.out.println("You chose not to use any potion.");
            return;
        }

        Potion potion = findPotionByName(character, potionName);
        if (potion != null) {
            applyPotionEffects(character, potion);
            character.getPotions().remove(potion);
            System.out.println(character.getName() + " used " + potion.getName());
        } else {
            System.out.println("Potion not found.");
        }
    }

    public void listPotions(Character character) {
        System.out.printf("\nAvailable Potions for %s:\n", character.getName());
        character.getPotions().forEach(potion -> System.out.println(potion.getName()));
    }

    private Potion findPotionByName(Character character, String potionName) {
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
