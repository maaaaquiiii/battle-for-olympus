package org.example.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Character {
    private String name;
    private CharacterType type;
    private int health;
    private int attack;
    private int defense;
    private Map<CharacterType, Integer> victories;
    private Set<Skill> skills;
    private Set<Potion> potions;

    private Weapon weapon;

    private Character(Builder characterBuilder) {
        this.name = characterBuilder.name;
        this.type = characterBuilder.type;
        this.health = characterBuilder.health;
        this.attack = characterBuilder.attack;
        this.defense = characterBuilder.defense;
        this.victories = new HashMap<>();
        this.skills = new HashSet<>();
        this.potions = new HashSet<>();
    }



    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public void learnSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void usePotion(Potion potion) {
        if (potion.isHealing()) {
            this.health += potion.getHealthBonus();
        } else {
            this.defense += potion.getDefenseBonus();
        }
        this.potions.remove(potion); // Usar una sola vez
    }

    public void addVictory(CharacterType opponentType) {
        this.victories.put(opponentType, this.victories.getOrDefault(opponentType, 0) + 1);
    }

    public boolean hasSkill() {
        return !skills.isEmpty();
    }

    public void useSkill(Character target) {
        if (!skills.isEmpty()) {
            Skill skill = skills.iterator().next();
            skill.apply(this, target);
            skills.remove(skill);
        }
    }

    public boolean hasPotion() {
        return !potions.isEmpty();
    }

    public void increaseAttack(int attackBonus) {
        this.attack += attackBonus;
    }

    public void restoreHealth(int healthBonus) {
        this.health += healthBonus;
    }

    public void increaseDefense(int defenseBonus) {
        this.defense += defenseBonus;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
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

    public Map<CharacterType, Integer> getVictories() {
        return victories;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public Set<Potion> getPotions() {
        return potions;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.attack += weapon.getAttackBonus();
        this.defense += weapon.getDefenseBonus();
    }

    public int getTotalAttack() {
        return this.attack + (weapon != null ? weapon.getAttackBonus() : 0);
    }

    public int getTotalDefense() {
        return this.defense + (weapon != null ? weapon.getDefenseBonus() : 0);
    }

    @Override
    public String toString() {
        return String.format("Character: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon() == null ? "None" : getWeapon());
    }


    public static class Builder {
        private String name;
        private CharacterType type;
        private int health;
        private int attack;
        private int defense;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(CharacterType type) {
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

        public Character build() {
            return new Character(this);
        }
    }
}
