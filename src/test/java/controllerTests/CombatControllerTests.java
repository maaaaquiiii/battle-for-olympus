package controllerTests;

import org.example.model.CharacterFactory;
import org.example.controller.CombatController;
import org.example.model.Characters.Character;
import org.example.model.Characters.God;
import org.example.model.Characters.Titan;
import org.example.model.Weapon;
import org.example.view.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CombatControllerTests {
    private CombatController combatController;
    private Character character1;
    private Character character2;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        combatController = new CombatController();
        scanner = new Scanner(System.in);

        character1 = new God.GodBuilder()
                .name("Zeus")
                .characterType("God")
                .health(100)
                .attack(20)
                .defense(5)
                .build();
        character2 = new Titan.TitanBuilder()
                .name("Cronus")
                .characterType("Titan")
                .health(150)
                .attack(25)
                .defense(10)
                .build();
    }

    @Test
    void testCombatEndsWhenOneCharacterDies() {
        System.out.println(character1);
        System.out.println(character2);

        character1.attack(character2, 10);
        character2.attack(character1, 15);

        combatController.initiateCombat(character1, character2);

        assertTrue(character1.isAlive() || character2.isAlive(), "Uno de los personajes debe seguir vivo");
        assertFalse(character1.isAlive() && character2.isAlive(), "Ambos personajes no pueden estar vivos al final");
    }

    @Test
    void testCharacter1TurnAttacks() {
        int initialHealth = character2.getHealth();
        combatController.character1Turn(character1, character2);
        assertTrue(character2.getHealth() < initialHealth, "El personaje 2 debe perder vida después del ataque");
    }

    @Test
    void testCharacter2TurnAttacks() {
        int initialHealth = character1.getHealth();
        combatController.character2Turn(character2, character1);
        assertTrue(character1.getHealth() < initialHealth, "El personaje 1 debe perder vida después del ataque");
    }

    @Test
    void testCharacterUsesPotion() {
        int initialHealth = character1.getHealth();
        combatController.character1UsePotion(character1);
        assertTrue(character1.getHealth() > initialHealth, "La salud debe aumentar después de usar una poción");
    }

    @Test
    void testEquipWeapon() {
        Weapon sword = new Weapon("Sword of Ares", 25);
        character1.setWeapon(sword);
        assertEquals("Sword of Ares", character1.getWeapon().getName(), "El personaje debe equipar la espada correctamente");
    }
}
