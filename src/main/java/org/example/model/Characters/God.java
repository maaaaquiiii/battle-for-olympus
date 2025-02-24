package org.example.model.Characters;

public class God extends Character{
    private God(GodBuilder builder) {
        super(builder);
    }

    public static class GodBuilder extends Builder {
        @Override
        public God build() {
            return new God(this);
        }
    }
}
