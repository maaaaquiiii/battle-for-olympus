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
        addWeapon(createWeapon("Harpe", 50));
        addWeapon(createWeapon("Cronus Scythe", 75));
        addWeapon(createWeapon("Bow of Artemis", 60));
        addWeapon(createWeapon("Achilles Spear", 65));
        addWeapon(createWeapon("Eurytus Bow", 50));
        addWeapon(createWeapon("Zeus Thunderbolt", 75));
    }

    public Weapon createWeapon(String name, int attackBonus) {
        return new Weapon(name, attackBonus);
    }

    public boolean addWeapon(Weapon weapon) {
        if (counter < MAX_WEAPONS) {
            weapons[counter++] = weapon;
            return true;
        } else {
            System.out.println("Weapon limit reached. Cannot add more weapons.");
            return false;
        }
    }

    public void equipWeapon(Character character, Weapon weapon) {
        character.setWeapon(weapon);
    }

    public Weapon getWeaponByIndex(int index) {
        if(index >= 0 && index < counter) {
            return weapons[index];
        } else {
            System.out.println("Invalid index. Returning null.");
            return null;
        }
    }

    public Weapon getRandomWeapon() {
        if(counter == 0) {
            System.out.println("No weapons available. Returning null.");
            return null;
        }
        return weapons[random.nextInt(counter)];
    }

    public Weapon[] getWeapons() {
        return weapons;
    }
}
