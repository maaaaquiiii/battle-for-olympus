package controllerTests;

import org.example.controller.CharacterController;
import org.example.model.Characters.Character;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class CharacterControllerTests {
    private CharacterController characterController;

    @BeforeEach
    public void setUp() {
        characterController = new CharacterController();
    }

    @Test
    void addCharacter() {
        Character centaur = characterController.getAllCharacters().get(0);

        Assertions.assertNotNull(centaur);
        Assertions.assertEquals("Centaur", centaur.getName());
        Assertions.assertEquals("animal", centaur.getType());
        Assertions.assertEquals(3500, centaur.getHealth());
        Assertions.assertEquals(100, centaur.getAttack());
        Assertions.assertEquals(15, centaur.getDefense());
    }

    @Test
    void findCharacterByName() {
        Character found = characterController.findCharacterByName("Thea");
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Thea", found.getName());
    }
    @Test
    void findCharacterByName_NotFound() {
        Character found = characterController.findCharacterByName("Unknown");
        Assertions.assertNull(found);
    }

    @Test
    void getRandomCharacter() {
        Character randomCharacter = characterController.getRandomCharacter();
        Assertions.assertNotNull(randomCharacter);
    }

    @Test
    void getAllCharacters() {
        List<Character> allCharacters = characterController.getAllCharacters();
        Assertions.assertFalse(allCharacters.isEmpty());
    }

}
