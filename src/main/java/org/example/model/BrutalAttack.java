package org.example.model;

public class BrutalAttack implements CombatStrategy {
    @Override
    public int calculateDamage(Character attacker, Character enemy) {
        return attacker.getAttack() * 2;
    }
}
