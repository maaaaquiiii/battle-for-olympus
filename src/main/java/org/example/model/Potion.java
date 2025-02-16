package org.example.model;

public class Potion {
    private String name;
    private int healthBoost;
    private int defenseBoost;

    private Potion(Builder potionBuilder) {
        this.name = potionBuilder.name;
        this.healthBoost = potionBuilder.healthBoost;
        this.defenseBoost = potionBuilder.defenseBoost;
    }

    public int getHealthBoost() {
        return healthBoost;
    }
    public int getDefenseBoost() {
        return defenseBoost;
    }
    public String getName() {
        return name;
    }

    public static class Builder {
        private String name;
        private int healthBoost;
        private int defenseBoost;

        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder healthBoost(int healthBoost) {
            this.healthBoost = healthBoost;
            return this;
        }

        public Builder defenseBoost(int defenseBoost) {
            this.defenseBoost = defenseBoost;
            return this;
        }

        public Potion build(){
            return new Potion(this);
        }
    }
}
