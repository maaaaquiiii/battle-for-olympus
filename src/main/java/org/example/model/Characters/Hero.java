package org.example.model.Characters;

import org.example.model.Attacks.Attack;
import org.example.model.Weapon;

public class Hero extends Character {
    public Hero(String name, int health, int attack, int defense, Weapon weapon, Attack attackStrategy) {
        super(name, health, attack, defense, weapon, attackStrategy);
    }

    @Override
    public void attack(Character opponent) {
        int totalAttack = this.getAttack() + (getWeapon() != null ? getWeapon().getAttackBonus() : 0);
        opponent.receiveDamage(totalAttack);
    }
}
