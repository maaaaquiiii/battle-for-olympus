package controllerTests;

import org.example.model.Characters.Character;
import org.example.controller.CharacterController;
import org.example.model.Characters.God;
import org.example.model.Characters.Titan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CharacterControllerTest {
    private CharacterController characterController;

    @BeforeEach
    void setUp() {
        characterController = new CharacterController();
    }

    @Test
    void createCharacter() {
        Character typhon = characterController.createCharacter("Typhon", "animal", 2750, 15, 10);
        System.out.println(typhon);
    }

    @Test
    void addCharacter() {
        System.out.println("List with default characters");
        characterController.getAllCharacters().forEach(System.out::println);

        Character persephone = characterController.createCharacter("Persephone", "hero",3500, 20, 15);
        characterController.addCharacter(persephone);
        System.out.println("Edited list");
        characterController.getAllCharacters().forEach(System.out::println);

    }

    @Test
    void findCharacterByName() {
        Character rhea = new Titan.TitanBuilder()
                .name("Rhea")
                .characterType("titan")
                .health(9500)
                .attack(50)
                .defense(30)
                .build();
        Character zeus = new God.GodBuilder()
                .name("Zeus")
                .characterType("God")
                .build();
        System.out.println(rhea);
        System.out.println(characterController.findCharacterByName("Rhea"));
        System.out.println(zeus);
        System.out.println(characterController.findCharacterByName("Zeus"));
//        characterController.addCharacter(rhea);
//        characterController.addCharacter(zeus);
        assertEquals(rhea, characterController.findCharacterByName("Rhea"));
        assertEquals(zeus, characterController.findCharacterByName("Zeus"));
    }

    @Test
    void drinkPotion() {
        Character apollo = characterController.findCharacterByName("Apollo");
        System.out.println("Before having assigned a potion");
        System.out.println(apollo);
        characterController.assignPotionToCharacter(apollo);
        System.out.println("After having assigned a potion");
        System.out.println(apollo.getName() + " - Potions: " + apollo.getPotions().get(0).getName());
//        System.out.println("After drink the potion");
        characterController.drinkPotion(apollo);
        System.out.println(apollo.getName() + " - Health: " + apollo.getHealth() + " - Defense Boost: " + apollo.getDefense());
    }
}
