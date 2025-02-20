package org.example.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return name.equals(weapon.name);
    }

    @Override
    public String toString() {
        return String.format("%s\tattack: %d\n", getName(), getAttackBonus());
    }
}
