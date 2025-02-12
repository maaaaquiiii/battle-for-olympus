package org.example.model.Builders;

import org.example.model.Characters.Titan;
import org.example.model.Characters.Character;

public class TitanBuilder extends CharacterBuilder {
    @Override
    public Character build() {
        return new Titan(build().getName(), build().getHealth(), build().getAttack(), build().getDefense(), build().getWeapon(), build().getSpecialAttack());
    }
}
