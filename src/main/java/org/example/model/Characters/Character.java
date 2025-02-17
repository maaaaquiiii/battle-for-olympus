package org.example.model.Characters;

import org.example.model.Potion;
import org.example.model.Weapon;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Character {
    private String name;
    private String type;
    private int health;
    private int attack;
    private int defense;
    private double luckPercentage;
    private List<Potion> potions;
    private Weapon weapon;

    protected Character(Builder<?> characterBuilder) {
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

    public void attack(Character opponent) {
        attack(opponent, 1);
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
        if (potions.contains(potion)) {
            potions.remove(potion);
            if (potion.getHealthBoost() != 0) this.defense += potion.getDefenseBoost();
            else if (potion.getDefenseBoost() != 0) this.health += potion.getHealthBoost();
            else {
                this.health += potion.getHealthBoost();
                this.defense += potion.getDefenseBoost();
            }
        }
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
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("Character: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon() == null ? "None" : getWeapon());
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private String type;
        private int health;
        private int attack;
        private int defense;
        private double luckPercentage;

        public T name(String name) {
            this.name = name;
            return self();
        }

        public T characterType(String type) {
            this.type = type;
            return self();
        }

        public T health(int health) {
            this.health = health;
            return self();
        }

        public T attack(int attack) {
            this.attack = attack;
            return self();
        }

        public T defense(int defense) {
            this.defense = defense;
            return self();
        }

        public T luckPercentage(double luckPercentage) {
            this.luckPercentage = luckPercentage;
            return self();
        }

        protected abstract T self();
        public abstract Character build();
    }
}
