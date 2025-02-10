package org.example.model;

public class God extends Character {
    private boolean divinePower;

    public God(GodBuilder builder) {
        super(builder);
        this.divinePower = builder.divinePower;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return (int) (this.getAttack() * 1.5) + this.getWeapon().getAttackBoost();
    }

    public static class GodBuilder extends Builder<GodBuilder> {
        private boolean divinePower;

        public GodBuilder divinePower(boolean divinePower) {
            this.divinePower = divinePower;
            return this;
        }

        @Override
        protected GodBuilder self() {
            return this;
        }

        @Override
        public God build() {
            return new God(this);
        }
    }
}
