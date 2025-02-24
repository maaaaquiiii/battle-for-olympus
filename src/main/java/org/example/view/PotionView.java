package org.example.view;

import org.example.model.Potion;
import org.example.model.Characters.Character;
import org.example.controller.PotionController;

import java.util.List;

public class PotionView extends View {
    private static PotionController potionController = new PotionController();

    public void displayPotions(List<Potion> potions) {
        displayMessage("Available Potions:");
        potions.stream()
                .forEach(potion -> displayMessage(potions.indexOf(potion) + 1 + ". " + potion));
    }

    public void displayAssignedPotion(Character character, int index) {
        Potion potion = potionController.getPotionByIndex(index);
        potionController.assignPotionToCharacter(character, potion);
        displayMessage(character.getName() + " has been assigned " + potion.getName());
    }

    public void usePotion(Character character) {
        clearBuffer();
        displayMessage("Do you want to use any potion? (y/n)");
        char answer = getUserString().toLowerCase().charAt(0);
        if(answer == 'y') {
            displayPotions(character.getPotions());
            int index = getUserInt() - 1;
            Potion potion = character.getPotions().get(index);
            potionController.usePotion(character, potion);
            displayMessage(character.getName() + " drank " + potion.getName());
        } else {
            displayMessage(character.getName() + " has decided to save the potion");
        }
    }

    public int getPotionIndexFromUser() {
        displayMessage("Choose a potion from the following list:");
        List<Potion> potions = potionController.getPotions();
        displayPotions(potions);
        displayMessage("Enter the number of the potion you want to use:");
        int userChoice = getUserInt();
        while (userChoice < 1 || userChoice > potions.size()) {
            displayMessage("Invalid choice. Please enter a valid number between 1 and " + potions.size() + ":");
            userChoice = getUserInt();
        }

        return userChoice - 1;
    }
}
