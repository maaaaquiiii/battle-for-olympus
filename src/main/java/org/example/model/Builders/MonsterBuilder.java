package org.example.model.Builders;

import org.example.model.Characters.Character;
import org.example.model.Characters.Monster;

public class MonsterBuilder extends CharacterBuilder {
    @Override
    public Character build() {
        return new Monster(build().getName(), build().getHealth(), build().getAttack(), build().getDefense(), build().getWeapon(), build().getSpecialAttack());
    }
}
