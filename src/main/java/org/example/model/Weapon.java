package org.example.model;

public class Weapon {
    private String name;
    private int attackBonus;
    private int defenseBonus;

    private Weapon(Builder weaponBuilder) {
        this.name = weaponBuilder.name;
        this.attackBonus = weaponBuilder.attackBonus;
        this.defenseBonus = weaponBuilder.defenseBonus;
    }

    public String getName() {
        return name;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public String toString() {
        return String.format("Weapon: %s\tattack: %d\tdefense: %d\n", getName(), getAttackBonus(), getDefenseBonus());
    }

    public static class Builder {
        private String name;
        private int attackBonus;
        private int defenseBonus;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder attackBonus(int attackBonus) {
            this.attackBonus = attackBonus;
            return this;
        }

        public Builder defenseBonus(int defenseBonus) {
            this.defenseBonus = defenseBonus;
            return this;
        }

        public Weapon build() {
            return new Weapon(this);
        }
    }
}
