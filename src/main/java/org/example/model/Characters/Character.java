package org.example.model.Characters;

import org.example.model.Potion;
import org.example.model.Skill;
import org.example.model.Weapon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Character {
    private String name;
    private String type;
    private int health;
    private int attack;
    private int defense;
    private Map<String, Integer> victories;
    private Set<Skill> skills;
    private Set<Potion> potions;

    private Weapon weapon;

    protected Character(Builder<?> characterBuilder) {
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
        this.potions.remove(potion);
    }

    public void addVictory(String opponentType) {
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

    public Map<String, Integer> getVictories() {
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

        protected abstract T self();
        public abstract Character build();
    }
}
