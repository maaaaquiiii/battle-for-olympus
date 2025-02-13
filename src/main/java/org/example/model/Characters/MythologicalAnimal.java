package org.example.model.Characters;

public class MythologicalAnimal extends Character {
    public MythologicalAnimal(Builder<MythologicalAnimalBuilder> builder) {
        super(builder);
    }

    public static class MythologicalAnimalBuilder extends Builder<MythologicalAnimalBuilder> {
        @Override
        protected MythologicalAnimalBuilder self() {
            return this;
        }

        @Override
        public Character build() {
            return new MythologicalAnimal(this);
        }
    }
}
