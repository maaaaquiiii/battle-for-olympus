package org.example.controller;

import org.example.model.Builders.*;
import org.example.model.Attacks.Attack;
import org.example.model.Characters.Character;
import org.example.view.AttackView;

public class AttackController {
    private AttackView attackView;

    public AttackController(AttackView attackView) {
        this.attackView = attackView;
    }

    public void performAttack(Character attacker, Character defender, Attack attack) {
    }
}
