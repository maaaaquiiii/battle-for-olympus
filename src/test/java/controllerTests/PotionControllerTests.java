package controllerTests;

import org.example.controller.PotionController;
import org.example.model.CharacterFactory;
import org.example.model.Potion;
import org.example.model.Characters.Character;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PotionControllerTests {
    private PotionController potionController;
    private Potion potion;
    private Character character;

    @BeforeEach
    void setUp() {
        potionController = new PotionController();
        character = CharacterFactory.createCharacter("Hermes", "god", 4500, 30, 10);
    }

    @Test
    void assignPotionToCharacter() {
        System.out.println("List of potions");
        System.out.println(potionController.getPotions());
        Potion potion = potionController.getPotionByIndex(4);
        System.out.println("Potion to be assigned");
        System.out.println(potion);
        potionController.assignPotionToCharacter(character, potion);
        System.out.println("List of character potions");
        System.out.println(character.getPotions());
        Assertions.assertFalse(character.getPotions().isEmpty());
        Assertions.assertEquals(potion, character.getPotions().get(0));
    }

    @Test
    void usePotion() {
        Potion potion = potionController.getPotionByIndex(4);
        character.addPotion(potion);
        int initialHealth =  character.getHealth();
        potionController.usePotion(character, potion);
        Assertions.assertTrue(character.getHealth() > initialHealth);
        Assertions.assertTrue(character.getPotions().isEmpty());
    }

    @Test
    void removePotionFromCharacter() {
        character.addPotion(potionController.getPotionByIndex(4));
        character.addPotion(potionController.getPotionByIndex(1));
        character.addPotion(potionController.getPotionByIndex(3));
        System.out.println("List of character potions");
        System.out.println(character.getPotions());
        Assertions.assertEquals(3, character.getPotions().size());
        character.removePotion(potionController.getPotionByIndex(1));
        System.out.println("List of character potions");
        System.out.println(character.getPotions());
        Assertions.assertEquals(2, character.getPotions().size());
        Assertions.assertFalse(character.getPotions().contains(potionController.getPotionByIndex(1)));

        //The positions of the remaining potions are verified to be correct.
        Assertions.assertEquals(potionController.getPotionByIndex(4), character.getPotions().get(0));
        Assertions.assertEquals(potionController.getPotionByIndex(3), character.getPotions().get(1));
    }
}
