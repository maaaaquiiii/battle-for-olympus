package org.example.model;

public class Weapon {
    private String name;
    private int attackBonus;

    public Weapon(String name, int attackBonus) {
        this.name = name;
        this.attackBonus = attackBonus;
    }

    public String getName() {
        return name;
    }

    public int getAttackBonus() {
        return attackBonus;
    }


    @Override
    public String toString() {
        return String.format("Weapon: %s\tattack: %d\n", getName(), getAttackBonus());
    }
}
