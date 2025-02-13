package org.example.model;

import org.example.model.Characters.Character;

public class Skill {
    private String name;
    private String description;
    private int bonus;

    public Skill(String name, String description, int bonus) {
        this.name = name;
        this.description = description;
        this.bonus = bonus;
    }

    public void apply(org.example.model.Characters.Character attacker, Character target) {
        attacker.increaseAttack(bonus);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return String.format("Skill: %s\tDescription: %s\tBonus: %d\n", name, description, bonus);
    }
}
