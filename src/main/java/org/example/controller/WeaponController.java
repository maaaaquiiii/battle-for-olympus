package org.example.controller;

import org.example.model.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class WeaponController {
    private List<Weapon> weapons;
    private Random random;

    public WeaponController() {
        this.weapons = new ArrayList<>();
        this.random = new Random();
        createPredefinedWeapons();
    }

    public void createPredefinedWeapons() {
        weapons.addAll(Arrays.asList(
                createWeapon("Zeus' Thunderbolt", 75),
                createWeapon("Harpe", 50),
                createWeapon("Cronus' Scythe", 75),
                createWeapon("Bow of Artemis", 60),
                createWeapon("Achilles' Spear", 65),
                createWeapon("Eurytus' Bow", 50)
        ));
    }

    public Weapon createWeapon(String name, int attackBonus) {
        return new Weapon(name, attackBonus);
    }

    public void showWeapon(Weapon weapon) {
        System.out.println(weapon);
    }

    public void showAllWeapons() {
        System.out.println("All weapons");
        getWeapons().forEach(this::showWeapon);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public Weapon getWeaponByIndex(int index) {
        if (index >= 0 && index < weapons.size()) {
            return weapons.get(index);
        } else {
            System.out.println("Invalid index, please select a valid weapon number.");
            return null;
        }
    }

    public Weapon getRandomWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }
}
