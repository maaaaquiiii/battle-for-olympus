package org.example.model;

import java.util.Objects;

public class Potion {
    private final String name;
    private final int healthBoost;
    private final int defenseBoost;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Potion potion = (Potion) o;
        return name.equals(potion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName());
        if (getHealthBoost() > 0) {
            sb.append(" - Health Boost: ").append(getHealthBoost());
        } if (getDefenseBoost() > 0) {
            sb.append(" - Defense Boost: ").append(getDefenseBoost());
        } if (sb.isEmpty()) {
            sb.append(" - No boosts available.");
        }
        return sb.toString();
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
