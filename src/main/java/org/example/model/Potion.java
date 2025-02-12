package org.example.model;

public class Potion {
    private String name;
    private int healthBonus;
    private int defenseBonus;
    private boolean isHealing;

    public Potion(String name, int healthBonus, int defenseBonus, boolean isHealing) {
        this.name = name;
        this.healthBonus = healthBonus;
        this.defenseBonus = defenseBonus;
        this.isHealing = isHealing;
    }

    public String getName() {
        return name;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public boolean isHealing() {
        return isHealing;
    }

    @Override
    public String toString() {
        if (isHealing) {
            return String.format("Potion: %s\tHealing: %d\n", name, healthBonus);
        } else {
            return String.format("Potion: %s\tDefense Bonus: %d\n", name, defenseBonus);
        }
    }
    
}
