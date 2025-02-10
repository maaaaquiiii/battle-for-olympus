package org.example.model;

public class Warrior extends Character {
    public Warrior(String name, int health, int attack, int defense, Weapon weapon, Attack attackStrategy) {
        super(name, health, attack, defense, weapon, attackStrategy);
    }
}
