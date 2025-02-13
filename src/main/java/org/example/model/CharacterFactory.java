package org.example.model;

import org.example.model.Characters.*;
import org.example.model.Characters.Character;

public class CharacterFactory {
    public static Character createCharacter(String name, String type, int health, int attack, int defense) {
        return switch (type.toUpperCase()) {
            case "ANIMAL" ->  new MythologicalAnimal.MythologicalAnimalBuilder()
                    .name(name)
                    .characterType(type)
                    .health(health)
                    .attack(attack)
                    .defense(defense)
                    .build();
            case "GOD" -> new God.GodBuilder()
                    .name(name)
                    .characterType(type)
                    .health(health)
                    .attack(attack)
                    .defense(defense)
                    .build();
            case "HERO" -> new Hero.HeroBuilder()
                    .name(name)
                    .characterType(type)
                    .health(health)
                    .attack(attack)
                    .defense(defense)
                    .build();
            case "TITAN" -> new Titan.TitanBuilder()
                    .name(name)
                    .characterType(type)
                    .health(health)
                    .attack(attack)
                    .defense(defense)
                    .build();
            default -> new Human.HumanBuilder()
                    .name(name)
                    .characterType(type)
                    .health(health)
                    .attack(attack)
                    .defense(defense)
                    .build();
        };
    }
}
