package org.example.model;

public class Animal extends Character {
    private String species;

    private Animal(AnimalBuilder builder) {
        super(builder);
        this.species = builder.species;
    }

    @Override
    public int calculateDamage(Character enemy) {
        return this.getAttack() + (this.species.equals("Beast") ? 10 : 0) + this.getWeapon().getAttackBoost();
    }

    public static class AnimalBuilder extends Builder<AnimalBuilder> {
        private String species = "Beast";

        public AnimalBuilder setSpecies(String species) {
            this.species = species;
            return this;
        }

        @Override
        protected AnimalBuilder self() {
            return this;
        }

        @Override
        public Animal build() {
            return new Animal(this);
        }
    }
}
