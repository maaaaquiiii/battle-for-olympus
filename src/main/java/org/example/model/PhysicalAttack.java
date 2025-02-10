package org.example.model;

public class PhysicalAttack implements Attack {
    @Override
    public int executeAttack(int attack, int defense) {
        return Math.max(attack - defense, 0);
    }
}
