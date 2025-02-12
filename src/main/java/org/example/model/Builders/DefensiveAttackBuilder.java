package org.example.model.Builders;

import org.example.model.Attacks.Attack;
import org.example.model.Attacks.DefensiveAttack;

public class DefensiveAttackBuilder extends AttackBuilder {
    @Override
    public Attack build() {
        return new DefensiveAttack(build().getName(), build().getDamage());
    }
}
