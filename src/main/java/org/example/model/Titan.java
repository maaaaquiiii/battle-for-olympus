package org.example.model;

public class Titan extends Character {
    private int resilience;

    private Titan(TitanBuilder builder) {
        super(builder);
        this.resilience = builder.resilience;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return (this.getAttack() * 2) - enemy.getDefense();
    }

    public static class TitanBuilder extends Builder<TitanBuilder> {
        private int resilience = 100;

        public TitanBuilder setResilience(int resilience) {
            this.resilience = resilience;
            return this;
        }

        @Override
        protected TitanBuilder self() {
            return this;
        }

        @Override
        public Titan build() {
            return new Titan(this);
        }
    }
}
