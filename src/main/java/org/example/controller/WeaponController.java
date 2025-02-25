package org.example.controller;

import org.example.model.Weapon;
import org.example.model.Characters.Character;
import java.util.Random;


public class  WeaponController {
    private static final int MAX_WEAPONS = 25;
    private final Weapon[] weapons;
    private int counter;
    private final Random random;

    public WeaponController() {
        this.weapons = new Weapon[MAX_WEAPONS];
        this.random = new Random();
        this.counter = 0;
        createPredefinedWeapons();
    }

    public void createPredefinedWeapons() {
        addWeapon(createWeapon("Helm of darkness", 85));
        addWeapon(createWeapon("Achilles' Spear", 80));

        addWeapon(createWeapon("Aegis", 90));
        addWeapon(createWeapon("Trident of Poseidon", 95));
        addWeapon(createWeapon("Bow of Artemis", 80));
        addWeapon(createWeapon("Caduceus", 75));
        addWeapon(createWeapon("Thunderbolt of Zeus", 95));
        addWeapon(createWeapon("Cronus' Scythe", 100));
        addWeapon(createWeapon("Hades's Hairpin", 95));
    }

    public Weapon createWeapon(String name, int attackBonus) {
        return new Weapon(name, attackBonus);
    }

    public void addWeapon(Weapon weapon) {
        if (counter >= MAX_WEAPONS) {
            throw new IllegalStateException("Weapon limit reached. Cannot add more weapons.");
        }
        weapons[counter++] = weapon;
    }

    public void equipWeapon(Character character, Weapon weapon) {
        character.setWeapon(weapon);
    }

    public Weapon getWeaponByIndex(int index) {
        if (index < 0 || index >= counter) {
            throw new IndexOutOfBoundsException("Invalid weapon index: " + index);
        }
        return weapons[index];
    }

    public Weapon getRandomWeapon() {
        if (counter == 0) {
            throw new IllegalStateException("No weapons available.");
        }
        return weapons[random.nextInt(counter)];
    }

    public Weapon[] getWeapons() {
        return weapons;
    }
}
