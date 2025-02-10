package org.example.model;

public class Giant extends Character {
    private int size;

    private Giant(GiantBuilder builder) {
        super(builder);
        this.size = builder.size;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return this.getAttack() + (this.size * 2) + this.getWeapon().getAttackBoost();
    }

    public static class GiantBuilder extends Builder<GiantBuilder> {
        private int size = 200;

        public GiantBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        @Override
        protected GiantBuilder self() {
            return this;
        }

        @Override
        public Giant build() {
            return new Giant(this);
        }
    }
}
