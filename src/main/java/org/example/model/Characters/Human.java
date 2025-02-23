package org.example.model.Characters;

public class Human extends Character {
    private Human(HumanBuilder builder) {
        super(builder);
    }

    public static class HumanBuilder extends Builder {
//        @Override
//        protected HumanBuilder self() {
//            return this;
//        }

        @Override
        public Character build() {
            return new Human(this);
        }
    }
}
