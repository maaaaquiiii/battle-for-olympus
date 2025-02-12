package org.example.controller;

import org.example.model.Builders.*;
import org.example.model.Characters.Character;
import org.example.view.CharacterView;


public class CharacterController {
    private Character character;
    private CharacterView characterView;

    public CharacterController(CharacterView characterView) {
        this.characterView = characterView;
    }

    public Character createCharacter(String name, int option) {
        CharacterBuilder character = switch (option) {
            case 1 -> new GodBuilder()
                    .name(name)
                    .health(175)
                    .attack(25)
                    .defense(15);

            case 2 -> new HeroBuilder()
                    .name(name)
                    .health(100)
                    .attack(10)
                    .defense(5);

            case 3 -> new MonsterBuilder()
                    .name(name)
                    .health(125)
                    .attack(15)
                    .defense(10);

            case 4 -> new TitanBuilder()
                    .name(name)
                    .health(150)
                    .attack(20)
                    .defense(10);
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };

        return character.build();
    }

    public Character getCharacter() {
        return character;
    }
}
