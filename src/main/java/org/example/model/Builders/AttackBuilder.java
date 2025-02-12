package org.example.model.Builders;

import org.example.model.Attacks.Attack;

public abstract class AttackBuilder {
    private String name;
    private int damage;

    public AttackBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AttackBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    public abstract Attack build();
}
