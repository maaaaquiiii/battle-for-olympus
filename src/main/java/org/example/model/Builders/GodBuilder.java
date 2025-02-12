package org.example.model.Builders;

import org.example.model.Characters.Character;
import org.example.model.Characters.God;

public class GodBuilder extends CharacterBuilder {
    @Override
    public Character build() {
        return new God(build().getName(), build().getHealth(), build().getAttack(), build().getDefense(), build().getWeapon(), build().getSpecialAttack());
    }
}
