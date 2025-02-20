package org.example.controller;

import org.example.model.Weapon;
import org.example.model.Characters.Character;
import java.util.Random;


public class  WeaponController {
    private static final int MAX_WEAPONS = 25;
    private Weapon[] weapons;
    private int counter;
    private Random random;

    public WeaponController() {
        this.weapons = new Weapon[MAX_WEAPONS];
        this.random = new Random();
        this.counter = 0;
        createPredefinedWeapons();
    }

    public void createPredefinedWeapons() {
        addWeapon(createWeapon("Helm of darkness", 65));
        addWeapon(createWeapon("Aegis", 50));
        addWeapon(createWeapon("Trident of Poseidon", 75));
        addWeapon(createWeapon("Bow of Artemis", 60));
        addWeapon(createWeapon("Caduceus", 50));
        addWeapon(createWeapon("Thunderbolt of Zeus", 75));
    }

    public Weapon createWeapon(String name, int attackBonus) {
        return new Weapon(name, attackBonus);
    }

    public boolean addWeapon(Weapon weapon) {
        if (counter >= MAX_WEAPONS) {
            throw new IllegalStateException("Weapon limit reached. Cannot add more weapons.");
        }
        weapons[counter++] = weapon;
        return true;
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
