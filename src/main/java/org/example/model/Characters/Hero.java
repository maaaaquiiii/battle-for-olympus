package org.example.model.Characters;

public class Hero extends Character {
    private Hero(HeroBuilder builder) {
        super(builder);
    }

    public static class HeroBuilder extends Builder {
//        @Override
//        protected HeroBuilder self() {
//            return this;
//        }
        @Override
        public Hero build() {
            return new Hero(this);
        }
    }
}
