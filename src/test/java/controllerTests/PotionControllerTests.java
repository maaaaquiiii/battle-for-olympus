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
        potion = new Potion.Builder()
                .name("Nectar of Ambrosia")
                .healthBoost(250)
                .build();
        character = CharacterFactory.createCharacter("Hermes", "god", 4500, 30, 10);
    }

    @Test
    void assignPotionToCharacter() {
        potionController.assignPotionToCharacter(character, potion);
        Assertions.assertFalse(character.getPotions().isEmpty());
        Assertions.assertEquals(potion, character.getPotions().get(0));
    }

    @Test
    void usePotion() {
        character.addPotion(potion);
        int initialHealth =  character.getHealth();
        potionController.usePotion(character, potion);
        Assertions.assertTrue(character.getHealth() > initialHealth);
        Assertions.assertTrue(character.getPotions().isEmpty());
    }
}
