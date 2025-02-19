package controllerTests;

import org.example.controller.WeaponController;
import org.example.model.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeaponControllerTests {
    private WeaponController weaponController;

    @BeforeEach
    void setUp() {
        weaponController = new WeaponController();
    }

    @Test
    void createWeapon() {
        Weapon weapon = weaponController.createWeapon("Achilles Sword", 30);

        Assertions.assertEquals("Achilles Sword", weapon.getName());
        Assertions.assertEquals(30, weapon.getAttackBonus());
    }

    @Test
    void createWeaponWithoutSpace() {
        weaponController.createWeapon("Achilles Sword", 30);
        Weapon[] weapons = weaponController.getWeapons();

        Assertions.assertEquals(25, weapons.length);

        Assertions.assertEquals("Achilles Sword", weapons[24].getName());
        Assertions.assertEquals(30, weapons[24].getAttackBonus());
    }

    

    @Test
    void getWeapons() {
        Weapon[] weapons = weaponController.getWeapons();

        Assertions.assertEquals(25, weapons.length);
    }
}
