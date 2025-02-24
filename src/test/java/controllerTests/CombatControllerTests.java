package controllerTests;

import org.example.controller.CombatController;
import org.example.model.Characters.Character;
import org.example.model.CharacterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatControllerTests {
    private CombatController combatController;
    private Character attacker;
    private Character defender;

    @BeforeEach
    void setUp() {
        combatController = new CombatController();
        attacker = CharacterFactory.createCharacter("Zeus", "god");
        defender = CharacterFactory.createCharacter("Hades", "god");
    }

    @Test
    void testAttack() {
        int initialHealth = defender.getHealth();
        combatController.attack(attacker, defender);
        assertTrue(defender.getHealth() < initialHealth);
    }

    @Test
    void testEquipManualWeapon() {
        combatController.equipManualWeapon(attacker, 0);
        assertNotNull(attacker.getWeapon());
    }
}
