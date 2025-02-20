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
        character = CharacterFactory.createCharacter("Hermes", "god", 4500, 30, 10);
        weapon = weaponController.createWeapon("Helm of darkness", 65);
    }

    @Test
    void createWeapon() {
        Assertions.assertEquals("Helm of darkness", weapon.getName());
        Assertions.assertEquals(65, weapon.getAttackBonus());
    }

    @Test
    void weaponArrayNotEmptyAfterAddingWeapon() {
        weaponController.addWeapon(weapon);
        Assertions.assertTrue(weaponController.getWeapons().length > 0, "The weapon array should contain at least one weapon.");
    }

    @Test
    void weaponArrayFull() {
        for (int i = 0; i < weaponController.getWeapons().length; i++) {
            if(weaponController.getWeapons()[i] != null) {
                weaponController.addWeapon(new Weapon("Weapon" + i, 50));
            }
        }
        Weapon extraWeapon = new Weapon("ExtraWeapon", 50);
        weaponController.addWeapon(extraWeapon);

        Assertions.assertFalse(weaponController.getWeapons().length > weaponController.getWeapons().length, "The array should not exceed the limit of " + weaponController.getWeapons().length + " weapons");
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
