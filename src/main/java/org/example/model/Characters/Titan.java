package org.example.model.Characters;

public class Titan extends Character {
    private Titan(TitanBuilder builder) {
        super(builder);
    }

    public static class TitanBuilder extends Builder {
        @Override
        public Character build() {
            return new Titan(this);
        }
    }
}
