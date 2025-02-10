package org.example.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;
    private CharacterType type;
    private Map<CharacterType, Integer> attackBonuses;
    private Map<CharacterType, Integer> defenseBonuses;

    protected Character(Builder<?> characterBuilder) {
        this.name = characterBuilder.name;
        this.health = characterBuilder.health;
        this.attack = characterBuilder.attack;
        this.defense = characterBuilder.defense;
        this.weapon = characterBuilder.weapon;
        this.type = characterBuilder.type;
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

    public CharacterType getType() {
        return type;
    }

    public Map<CharacterType, Integer> getAttackBonuses() {
        return attackBonuses;
    }

    public Map<CharacterType, Integer> getDefenseBonuses() {
        return defenseBonuses;
    }

    public abstract int calculateDamage(Character enemy);

    public void addAttackBonus(CharacterType defeatedType, int bonus) {
        attackBonuses.put(defeatedType, attackBonuses.getOrDefault(defeatedType, 0) + bonus);
    }

    public void addDefenseBonus(CharacterType defeatedType, int bonus) {
        defenseBonuses.put(defeatedType, defenseBonuses.getOrDefault(defeatedType, 0) + bonus);
    }

    public int getModifiedAttack(CharacterType opponentType) {
        return attack + weapon.getAttackBoost() + attackBonuses.getOrDefault(opponentType, 0);
    }

    public int getModifiedDefense(CharacterType opponentType) {
        return defense + weapon.getDefenseModifier() + defenseBonuses.getOrDefault(opponentType, 0);
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon());
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private int health;
        private int attack;
        private int defense;
        private Weapon weapon;
        private CharacterType type;
        private Map<CharacterType, Integer> attackBonuses;
        private Map<CharacterType, Integer> defenseBonuses;


        public T name(String name) {
            this.name = name;
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

        public T weapon(Weapon weapon) {
            this.weapon = weapon;
            return self();
        }

        public T type(CharacterType type) {
            this.type = type;
            return self();
        }

        public T attackBonuses(Map<CharacterType, Integer> attackBonuses) {
            this.attackBonuses = attackBonuses;
            return self();
        }

        public T defenseBonuses(Map<CharacterType, Integer> defenseBonuses) {
            this.defenseBonuses = defenseBonuses;
            return self();
        }

        protected abstract T self();

        public abstract Character build();
    }
}



