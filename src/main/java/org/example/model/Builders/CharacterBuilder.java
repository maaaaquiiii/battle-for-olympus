package org.example.model.Builders;

import org.example.model.Attacks.Attack;
import org.example.model.Characters.Character;
import org.example.model.Weapon;

public abstract class CharacterBuilder {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;
    private Attack specialAttack;

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder health(int health) {
        this.health = health;
        return this;
    }

    public CharacterBuilder attack(int attack) {
        this.attack = attack;
        return this;
    }

    public CharacterBuilder defense(int defense) {
        this.defense = defense;
        return this;
    }

    public CharacterBuilder weapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public CharacterBuilder specialAttack(Attack specialAttack) {
        this.specialAttack = specialAttack;
        return this;
    }

    public abstract Character build();
}
