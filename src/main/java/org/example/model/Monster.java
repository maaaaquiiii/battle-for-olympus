package org.example.model;

public class Monster extends Character {
    private int rage;

    private Monster(MonsterBuilder builder) {
        super(builder);
        this.rage = builder.rage;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return (this.getAttack() * 2) + (this.rage / 10) + this.getWeapon().getAttackBoost();
    }

    public static class MonsterBuilder extends Builder<MonsterBuilder> {
        private int rage = 50;

        public MonsterBuilder setRage(int rage) {
            this.rage = rage;
            return this;
        }

        @Override
        protected MonsterBuilder self() {
            return this;
        }

        @Override
        public Monster build() {
            return new Monster(this);
        }
    }
}
