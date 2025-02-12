package org.example.model.Attacks;

import org.example.model.Characters.Character;

public abstract class Attack {
    private String name;
    private int damage;

    public Attack(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public abstract void executeAttack(Character attacker, Character target);

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
