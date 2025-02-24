package org.example.model;

import org.example.model.Characters.*;
import org.example.model.Characters.Character;

public class CharacterFactory {
    public static Character createCharacter(String name, String type) {
        return switch (type.toUpperCase()) {
            case "ANIMAL" ->  new MythologicalAnimal.MythologicalAnimalBuilder()
                    .name(name)
                    .characterType(type)
                    .health(1750)
                    .attack(115)
                    .defense(10)
                    .luckPercentage(0.2)
                    .build();
            case "GOD" -> new God.GodBuilder()
                    .name(name)
                    .characterType(type)
                    .health(2500)
                    .attack(130)
                    .defense(20)
                    .luckPercentage(0.25)
                    .build();
            case "HERO" -> new Hero.HeroBuilder()
                    .name(name)
                    .characterType(type)
                    .health(2250)
                    .attack(125)
                    .defense(15)
                    .luckPercentage(0.15)
                    .build();
            case "TITAN" -> new Titan.TitanBuilder()
                    .name(name)
                    .characterType(type)
                    .health(3000)
                    .attack(135)
                    .defense(20)
                    .luckPercentage(0.3)
                    .build();
            default -> new Human.HumanBuilder()
                    .name(name)
                    .characterType(type)
                    .health(2000)
                    .attack(115)
                    .defense(15)
                    .luckPercentage(0.2)
                    .build();
        };
    }
}
