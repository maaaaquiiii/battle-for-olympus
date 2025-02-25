package org.example.model.Characters;

import org.example.model.Potion;
import org.example.model.Weapon;

import java.util.List;
import java.util.ArrayList;

public abstract class Character {
    private final String name;
    private final String type;
    private int health;
    private int attack;
    private int defense;
    private final double luckPercentage;
    private final List<Potion> potions;
    private Weapon weapon;

    protected Character(Builder characterBuilder) {
        this.name = characterBuilder.name;
        this.type = characterBuilder.type;
        this.health = characterBuilder.health;
        this.attack = characterBuilder.attack;
        this.defense = characterBuilder.defense;
        this.luckPercentage = characterBuilder.luckPercentage;
        this.potions = new ArrayList<>();
    }

    public void receiveDamage(int damage) {
        this.health -= (damage - this.defense);
        if (this.health < 0) this.health = 0;
    }

    public void attack(Character opponent, int multiplier) {
        int damage = this.attack * multiplier;
        opponent.receiveDamage(damage);
    }

    public boolean isCriticalHit() {
        return Math.random() < luckPercentage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }


    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public List<Potion> getPotions() {
        return potions;
    }

    public Weapon getWeapon() {
        return weapon;
    }


    public void setPotion(Potion potion) {
        this.health += potion.getHealthBoost();
        this.defense += potion.getDefenseBoost();
    }

    public void removePotion(Potion potion) {
        potions.remove(potion);
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack -= this.weapon.getAttackBonus();
        }
        this.weapon = weapon;
        if (this.weapon != null) {
            this.attack += this.weapon.getAttackBonus();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Character character = (Character) obj;
        return name.equals(character.name);
    }

    @Override
    public String toString() {
        return String.format("Character: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\nPotions:\n%s",
                getName(), getHealth(), getAttack(), getDefense(),
                getWeapon() == null ? "No weapon equipped" : getWeapon(),
                getPotions().isEmpty() ? "No potions assigned" : getPotions().toString());
    }

    public static abstract class Builder {
        private String name;
        private String type;
        private int health;
        private int attack;
        private int defense;
        private double luckPercentage;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder characterType(String type) {
            this.type = type;
            return this;
        }

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder luckPercentage(double luckPercentage) {
            this.luckPercentage = luckPercentage;
            return this;
        }

        public abstract Character build();
    }
}
