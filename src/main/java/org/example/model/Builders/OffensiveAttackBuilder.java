package org.example.model.Builders;

import org.example.model.Attacks.Attack;
import org.example.model.Attacks.OffensiveAttack;

public class OffensiveAttackBuilder extends AttackBuilder {
    @Override
    public Attack build() {
        return new OffensiveAttack(build().getName(), build().getDamage());
    }
}
