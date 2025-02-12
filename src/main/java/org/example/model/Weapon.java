package org.example.model;

public class Weapon {
    private String name;
    private int attackBonus;
    private int defenseBonus;
    private boolean isAttackWeapon;

    private Weapon(Builder weaponBuilder) {
        this.name = weaponBuilder.name;
        this.attackBonus = weaponBuilder.attackBonus;
        this.defenseBonus = weaponBuilder.defenseBonus;
        this.isAttackWeapon = weaponBuilder.isAttackWeapon;;
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
        if (isAttackWeapon) {
            return String.format("Weapon: %s\tattack: %d\n", getName(), getAttackBonus());
        }

        return String.format("Weapon: %s\tdefense: %d\n", getName(), getDefenseBonus());
    }

    public static class Builder {
        private String name;
        private int attackBonus;
        private int defenseBonus;
        private boolean isAttackWeapon;


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

        public Builder isAttackWeapon(boolean isAttackWeapon) {
            this.isAttackWeapon = isAttackWeapon;
            return this;
        }

        public Weapon build() {
            return new Weapon(this);
        }
    }
}
