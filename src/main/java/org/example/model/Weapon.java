package org.example.model;

public class Weapon {
    private String name;
    private int attackBoost;
    private int defenseBoost;

    public Weapon(String name, int attackBoost, int defenseBoost) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
    }

    public int getAttackBoost() {
        return attackBoost;
    }
}