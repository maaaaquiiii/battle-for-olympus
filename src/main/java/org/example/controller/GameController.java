package org.example.controller;

import org.example.model.*;
import org.example.model.Character;
import org.example.view.CharacterView;
import org.example.view.MenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
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
        mythologicalAnimals.add(new Character.Builder().name("Centaur").type(CharacterType.MYTHOLOGICAL_ANIMALS).health(60).attack(10).defense(5).build());
        mythologicalAnimals.add(new Character.Builder().name("Griffin").type(CharacterType.MYTHOLOGICAL_ANIMALS).health(80).attack(20).defense(15).build());

        // Crear dioses
        gods.add(new Character.Builder().name("Zeus").type(CharacterType.GOD).health(100).attack(50).defense(40).build());
        gods.add(new Character.Builder().name("Hera").type(CharacterType.GOD).health(80).attack(40).defense(30).build());

        // Crear héroes
        heroes.add(new Character.Builder().name("Hercules").type(CharacterType.HERO).health(120).attack(60).defense(50).build());
        heroes.add(new Character.Builder().name("Perseus").type(CharacterType.HERO).health(100).attack(50).defense(40).build());

        // Crear titanes
        titans.add(new Character.Builder().name("Cronos").type(CharacterType.TITAN).health(200).attack(80).defense(70).build());
        titans.add(new Character.Builder().name("Atlas").type(CharacterType.TITAN).health(180).attack(70).defense(60).build());
    }

    public Character createCharacter(String name, CharacterType type, int health, int attack, int defense) {
        return new Character.Builder()
                .name(name)
                .type(type)
                .health(health)
                .attack(attack)
                .defense(defense)
                .build();
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

    public void restoreHealth(Character player, int healthBonus) {
        player.restoreHealth(healthBonus);
    }

    public void increaseAttack(Character player, int attackBonus) {
        player.increaseAttack(attackBonus);
    }

    public void increaseDefense(Character player, int defenseBonus) {
        player.increaseDefense(defenseBonus);
    }

    public Character selectCharacter() {
        Random random = new Random();
        int listIndex = random.nextInt(4);  // Aleatorio entre 0 y 3 para seleccionar una lista
        List<Character> selectedList;

        switch (listIndex) {
            case 1 -> selectedList = gods;
            case 2 -> selectedList = heroes;
            case 3 -> selectedList = titans;
            default -> selectedList = mythologicalAnimals;
        }

        return selectedList.get(random.nextInt(selectedList.size())); // Selecciona un personaje aleatorio de la lista seleccionada
    }

    private void viewStatistics() {
        if (selectedCharacters.isEmpty()) {
            characterView.displayMessage("No characters selected yet.");
        } else {
            selectedCharacters.forEach(character -> characterView.displayCharacterStats(character));
        }
    }

    public void showMenu() {
        characterView.displayMessage("Choose an option:");
        characterView.displayMessage("1. Select a character");
        characterView.displayMessage("2. Fight against selected character");
        characterView.displayMessage("3. Fight against random character");
        characterView.displayMessage("4. View statistics");
        characterView.displayMessage("5. Exit");

        int choice = menuView.getUserInput(); // Suponemos que hay un método para capturar la opción del usuario

        switch (choice) {
            case 1:
                Character selected = selectCharacter();
                selectedCharacters.add(selected);
                characterView.displayMessage("You selected: " + selected.getName());
                break;
            case 2:
                if (selectedCharacters.size() > 0) {
                    Character opponent = selectCharacter();
                    performCombat(selectedCharacters.get(0), opponent);
                } else {
                    characterView.displayMessage("You need to select a character first.");
                }
                break;
            case 3:
                Character randomOpponent = selectCharacter();
                if (selectedCharacters.size() > 0) {
                    performCombat(selectedCharacters.get(0), randomOpponent);
                } else {
                    characterView.displayMessage("You need to select a character first.");
                }
                break;
            case 4:
                viewStatistics();
                break;
            case 5:
                characterView.displayMessage("Exiting the game...");
                break;
            default:
                characterView.displayMessage("Invalid choice. Try again.");
        }
    }
}

