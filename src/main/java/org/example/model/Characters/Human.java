package org.example.model.Characters;

public class Human extends Character {
    private Human(HumanBuilder builder) {
        super(builder);
    }

    public static class HumanBuilder extends Builder {
        @Override
        public Character build() {
            return new Human(this);
        }
    }
}
