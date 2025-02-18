package org.example.view;

import org.example.model.Potion;
import org.example.controller.PotionController;

import java.util.List;

public class PotionView extends View {
    private static PotionController potionController = new PotionController();
    private String getPotionDescription(Potion potion) {
        StringBuilder description = new StringBuilder();
        if (potion.getHealthBoost() > 0) {
            description.append(" - Health Boost: ").append(potion.getHealthBoost());
        }
        if (potion.getDefenseBoost() > 0) {
            description.append(" - Defense Boost: ").append(potion.getDefenseBoost());
        }
        if (description.length() == 0) {
            description.append(" - No boosts available.");
        }

        return description.toString();
    }

    public void displayPotions(List<Potion> potions) {
        displayMessage("Available Potions:");
        potions.forEach(potion -> {
            if (potion != null) {
                displayMessage(potion.getName() + getPotionDescription(potion));
            }
        });
    }

    public void displayPotionUsed(Potion potion) {
        displayMessage("You used " + potion.getName() + "!");
    }

    public int getPotionIndexFromUser() {
        displayMessage("Choose a potion from the following list:");
        List<Potion> potions = potionController.getPotions();
        potions.stream()
                .forEach(potion -> displayMessage(potions.indexOf(potion) + 1 + ". " + potion.getName() + getPotionDescription(potion)));

        displayMessage("Enter the number of the potion you want to use:");
        int userChoice = getUserInt();

        // Validación simple para asegurarnos de que el usuario elija una opción válida
        while (userChoice < 1 || userChoice > potions.size()) {
            displayMessage("Invalid choice. Please enter a valid number between 1 and " + potions.size() + ":");
            userChoice = getUserInt();
        }

        return userChoice - 1; // Restar 1 para el índice 0
    }

}
