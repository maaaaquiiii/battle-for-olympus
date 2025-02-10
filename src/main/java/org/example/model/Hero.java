package org.example.model;

public class Hero extends Character{
    private int honorPoints;

    private Hero(HeroBuilder builder) {
        super(builder);
        this.honorPoints = builder.honorPoints;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return this.getAttack() + this.getWeapon().getAttackBoost();
    }

    public static class HeroBuilder extends Builder<HeroBuilder> {
        private int honorPoints = 50;

        public HeroBuilder honorPoints(int honorPoints) {
            this.honorPoints = honorPoints;
            return this;
        }

        @Override
        public HeroBuilder self() {
            return this;
        }

        @Override
        public Hero build() {
            return new Hero(this);
        }
    }
}
