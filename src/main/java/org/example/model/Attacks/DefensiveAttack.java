package org.example.model.Attacks;

import org.example.model.Characters.Character;

public class DefensiveAttack extends Attack {
    public DefensiveAttack(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void executeAttack(Character attacker, Character target) {
        int finalDamage = getDamage() - target.getDefense();
        target.receiveDamage(Math.max(finalDamage, 0));
    }
}
