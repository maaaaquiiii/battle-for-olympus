package controllerTests;

import org.example.controller.WeaponController;
import org.example.model.CharacterFactory;
import org.example.model.Characters.Character;
import org.example.model.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeaponControllerTests {
    private WeaponController weaponController;
    private Character character;
    private Weapon weapon;

    @BeforeEach
    void setUp() {
        weaponController = new WeaponController();
        character = CharacterFactory.createCharacter("Hermes", "god");
        weapon = weaponController.createWeapon("Helm of darkness", 65);
    }

    @Test
    void createWeapon() {
        Assertions.assertEquals("Helm of darkness", weapon.getName());
        Assertions.assertEquals(65, weapon.getAttackBonus());
    }

    @Test
    void equipWeapon() {
        weaponController.equipWeapon(character, weapon);

        Assertions.assertEquals("Helm of darkness", character.getWeapon().getName());
    }

    @Test
    void getWeaponByIndex() {
        weaponController.addWeapon(weapon);

        Assertions.assertEquals(weapon, weaponController.getWeaponByIndex(0));
    }
}
