package org.example.model;

public class Human extends Character {
    private int intelligence;

    private Human(HumanBuilder builder) {
        super(builder);
        this.intelligence = builder.intelligence;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return this.getAttack() + (int) (this.intelligence * 0.5) + this.getWeapon().getAttackBoost();
    }

    public static class HumanBuilder extends Builder<HumanBuilder> {
        private int intelligence = 10;

        public HumanBuilder setIntelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        @Override
        protected HumanBuilder self() {
            return this;
        }

        @Override
        public Human build() {
            return new Human(this);
        }
    }
}
