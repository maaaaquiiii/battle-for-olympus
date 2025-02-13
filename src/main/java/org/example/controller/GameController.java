package org.example.controller;

import org.example.model.Characters.Character;
import org.example.model.CharacterFactory;
import org.example.model.*;
import org.example.view.CharacterView;
import org.example.view.MenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

public class GameController {
    private CharacterFactory characterFactory;
    private CharacterView characterView;
    private MenuView menuView;
    private List<Character> mythologicalAnimals;
    private List<Character> gods;
    private List<Character> heroes;
    private List<Character> titans;
    private List<Character> selectedCharacters;

    public GameController() {
        this.characterView = new CharacterView();
        this.menuView = new MenuView();
        this.mythologicalAnimals = new ArrayList<>();
        this.gods = new ArrayList<>();
        this.heroes = new ArrayList<>();
        this.titans = new ArrayList<>();
        this.selectedCharacters = new ArrayList<>();

        createPredefinedCharacters();
    }

    private void createPredefinedCharacters() {
        mythologicalAnimals.add(createCharacter("Centaur", "animal", 2000, 10, 15));
        mythologicalAnimals.add(createCharacter("Cerberus", "animal", 2500, 15, 10));
        mythologicalAnimals.add(createCharacter("Chimera", "animal", 2750, 10, 15));
        mythologicalAnimals.add(createCharacter("Griffin", "animal", 3000, 15, 10));
        mythologicalAnimals.add(createCharacter("Harpy", "animal", 2750, 20, 10));
        mythologicalAnimals.add(createCharacter("Minotaur", "animal", 2500, 15, 15));

        heroes.add(createCharacter("Aquiles", "hero", 3250, 20, 15));
        heroes.add(createCharacter("Heracles", "hero", 3500, 20, 20));
        heroes.add(createCharacter("Perseus", "hero", 3750, 20, 10));
        heroes.add(createCharacter("Theseus", "hero", 4000, 30, 20));

        gods.add(createCharacter("Apollo", "god", 5000, 30, 20));
        gods.add(createCharacter("Ares", "god", 5500, 35, 20));
        gods.add(createCharacter("Artemis", "god", 5750, 35, 25));
        gods.add(createCharacter("Athena", "god", 5500, 30, 15));
        gods.add(createCharacter("Hades", "god", 5500, 35, 25));
        gods.add(createCharacter("Poseidon", "god", 5500, 35, 25));
        gods.add(createCharacter("Zeus", "god", 6000, 40, 20));

        titans.add(createCharacter("Cronus", "titan", 10000, 40, 30));
        titans.add(createCharacter("Hyperion", "titan", 7500, 50, 30));
        titans.add(createCharacter("Oceanus", "titan", 8500, 45, 35));
        titans.add(createCharacter("Rhea", "titan", 9500, 50, 30));
        titans.add(createCharacter("Thea", "titan", 9000, 45, 35));
        titans.add(createCharacter("Themis", "titan", 8000, 45, 35));
    }

    public Character createCharacter(String name, String type, int health, int attack, int defense) {
        return CharacterFactory.createCharacter(name, type, health, attack, defense);
    }

    public Character performCombat(Character player1, Character player2) {
        if (player1.getHealth() <= 0) {
            return player2;
        }
        if (player2.getHealth() <= 0) {
            return player1;
        }

        return Combat.fight(player1, player2);
    }

    public void usePotion(Character player, Potion potion) {
        if (player.hasPotion()) {
            player.usePotion(potion);
        }
    }

    public void teachSkill(Character player, Skill skill) {
        player.learnSkill(skill);
    }

    public void useSkill(Character player, Character target) {
        if (player.hasSkill()) {
            player.useSkill(target);
        }
    }

    public void addVictory(Character winner, Character loser) {
        winner.addVictory(loser.getType());
    }

//    public void restoreHealth(Character player, int healthBonus) {
//        player.restoreHealth(healthBonus);
//    }
//
//    public void increaseAttack(Character player, int attackBonus) {
//        player.increaseAttack(attackBonus);
//    }
//
//    public void increaseDefense(Character player, int defenseBonus) {
//        player.increaseDefense(defenseBonus);
//    }

    private Character selectCharacter(int option) {
        List<Character> selectedList;
        Character selectedCharacter = null;

        switch (option) {
            case 1 -> {
                selectedList = mythologicalAnimals;
                selectedList.forEach(character -> characterView.displayCharactersByName(character));
                menuView.displayMessage("Select a mythological animal:");
                int characterOption = menuView.getUserInt();
                selectedCharacter = selectedList.get(characterOption);
            }
            case 2 -> {
                selectedList = gods;
                selectedList.forEach(character -> characterView.displayCharactersByName(character));
                menuView.displayMessage("Select a mythological animal:");
                int characterOption = menuView.getUserInt();
                selectedCharacter = selectedList.get(characterOption);
            }
            case 3 -> {
                selectedList = heroes;
                selectedList.forEach(character -> characterView.displayCharactersByName(character));
                menuView.displayMessage("Select a mythological animal:");
                int characterOption = menuView.getUserInt();
                selectedCharacter = selectedList.get(characterOption);
            }
            case 4 -> {
                selectedList = titans;
                selectedList.forEach(character -> characterView.displayCharactersByName(character));
                menuView.displayMessage("Select a mythological animal:");
                int characterOption = menuView.getUserInt();
                selectedCharacter = selectedList.get(characterOption);
            }
            default -> throw new IllegalArgumentException("Invalid Option");
        }

        return selectedCharacter;
    }

    public Character selectCharacterRandomly() {
        Random random = new Random();
        int listIndex = random.nextInt(4);
        List<Character> selectedList;

        switch (listIndex) {
            case 2 -> selectedList = gods;
            case 3 -> selectedList = heroes;
            case 4 -> selectedList = titans;
            default -> selectedList = mythologicalAnimals;
        }

        return selectedList.get(random.nextInt(selectedList.size()));
    }

    private void viewStatistics() {
        if (selectedCharacters.isEmpty()) {
            characterView.displayMessage("No characters selected yet.");
        } else {
            selectedCharacters.forEach(character -> characterView.displayCharacterStats(character));
        }
    }

    private void viewSkills() {
        if (selectedCharacters.isEmpty()) {
            characterView.displayMessage("No characters selected yet.");
        } else {
            selectedCharacters.forEach(character -> characterView.viewCharacterSkills(character));
        }
    }

    private void viewPotions() {
        if (selectedCharacters.isEmpty()) {
            characterView.displayMessage("No characters selected yet.");
        } else {
            selectedCharacters.forEach(character -> characterView.viewCharacterPotions(character));
        }
    }


    public void showMenu() {
        menuView.displayMenu();
        int choice = menuView.getUserInt();
        switch (choice) {
            case 1 -> {
                characterView.displayMessage("Introduce the name");
                String name = menuView.getUserString();
                characterView.displayMessage("Introduce the type (hero/god/titan/mythologicalAnimal)");
                String type = menuView.getUserString();
                characterView.displayMessage("Introduce the health");
                int health = menuView.getUserInt();
                characterView.displayMessage("Introduce the attack");
                int attack = menuView.getUserInt();
                characterView.displayMessage("Introduce the defense");
                int defense = menuView.getUserInt();
                selectedCharacters.add(createCharacter(name, type, health, attack, defense));
            }
            case 2 -> {
                menuView.displayLists();
                Character selected = selectCharacter(menuView.getUserInt());
                selectedCharacters.add(selected);
                characterView.displayMessage("You selected: " + selected.getName());
            }
            case 3 -> {
                Character randomCharacter = selectCharacterRandomly();
                selectedCharacters.add(randomCharacter);
                characterView.displayMessage("You selected: " + randomCharacter.getName());
            }
            case 4 -> {
                Character randomOpponent = selectCharacterRandomly();
                if (selectedCharacters.size() > 0) {
                    performCombat(selectedCharacters.get(0), randomOpponent);
                } else {
                    characterView.displayMessage("You need to select a character first.");
                }
            }
            case 5 -> {
                viewStatistics();
            }
            case 6 -> {
                characterView.displayMessage("Exiting the game...");
                exit(0);
            }
            default -> characterView.displayMessage("Invalid choice. Try again.");
        }
    }

//    System.out.println("Do you want to create an opponent? (yes/no)");
//    String createOpponent = menuView.getUserString();
//                if (createOpponent.equalsIgnoreCase("yes")) {
//        String opponentName = menuView.getUserString();
//        characterView.displayMessage("Introduce the type (hero/god/titan/mythologicalAnimal)");
//        String opponentType = menuView.getUserString();
//        characterView.displayMessage("Introduce the health");
//        int opponentHealth = menuView.getUserInt();
//        characterView.displayMessage("Introduce the attack");
//        int opponentAttack = menuView.getUserInt();
//        characterView.displayMessage("Introduce the defense");
//        int opponentDefense = menuView.getUserInt();
//        Character opponent = createCharacter(opponentName, opponentType, opponentHealth, opponentAttack, opponentDefense);
//        characterView.displayMessage("Opponent created: Opponent");
//
//        if (selectedCharacters.size() > 0) {
//            performCombat(selectedCharacters.get(0), opponent);
//        } else {
//            characterView.displayMessage("You need to select a character first.");
//        }
//    } else {
//        // TODO: ask if the opponent is created randomly
//        characterView.displayMessage("Do you want to select your opponent? (yes/no)");
//        String selectOpponent = menuView.getUserString();
//        if(selectOpponent.equalsIgnoreCase("yes")) {
//            // TODO: implement selecting opponent
//            if (selectedCharacters.size() > 0) {
//                Character opponent = selectCharacter();
//                performCombat(selectedCharacters.get(0), opponent);
//            } else {
//                characterView.displayMessage("You need to select a character first.");
//            }
//        }
//    }
}

