package org.example.model.Characters;

import org.example.model.Attacks.Attack;
import org.example.model.Weapon;

public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;
    private Attack specialAttack;

    public Character(String name, int health, int attack, int defense, Weapon weapon, Attack specialAttack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.weapon = weapon;
        this.specialAttack = specialAttack;
    }

    public abstract void attack(Character opponent);

    public void receiveDamage(int damage) {
        int actualDamage = Math.max(damage - this.getDefense(), 0);
        this.health -= actualDamage;
        if (this.health < 0) this.health = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Attack getSpecialAttack() {
        return specialAttack;
    }

    @Override
    public String toString() {
        return String.format("Character: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\tattack: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon(), getSpecialAttack());
    }
}
