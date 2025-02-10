package org.example.model;

public class MagicAttack implements Attack{
    @Override
    public int executeAttack(int attackValue, int defenseValue) {
        return Math.max(0, attackValue - (defenseValue / 2)); // Magia ignora parcialmente la defensa
    }
}
