package org.example.model.Characters;

public class MythologicalAnimal extends Character {
    public MythologicalAnimal(Builder builder) {
        super(builder);
    }

    public static class MythologicalAnimalBuilder extends Builder {
        @Override
        public Character build() {
            return new MythologicalAnimal(this);
        }
    }
}
