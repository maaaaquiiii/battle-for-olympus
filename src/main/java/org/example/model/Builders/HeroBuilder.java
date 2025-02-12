package org.example.model.Builders;

import org.example.model.Characters.Character;
import org.example.model.Characters.Hero;

public class HeroBuilder extends CharacterBuilder {
    @Override
    public Character build() {
        return new Hero(build().getName(), build().getHealth(), build().getAttack(), build().getDefense(), build().getWeapon(), build().getSpecialAttack());
    }
}
