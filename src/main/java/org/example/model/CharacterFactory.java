package org.example.model;

public class CharacterFactory {
    public static Character createCharacter(CharacterType type, String name) {
        return switch (type) {
            case ANIMAL ->  new Animal.AnimalBuilder()
                    .name(name)
                    .build();
            case GIANT -> new Giant.GiantBuilder()
                    .name(name)
                    .build();
            case GOD -> new God.GodBuilder()
                    .name(name)
                    .build();
            case HERO -> new Hero.HeroBuilder()
                    .name(name)
                    .build();
            case HUMAN -> new Human.HumanBuilder()
                    .name(name)
                    .build();
            case MONTSTER -> new Monster.MonsterBuilder()
                    .name(name)
                    .build();
            case TITAN -> new Titan.TitanBuilder()
                    .name(name)
                    .build();
            default -> throw new IllegalArgumentException("Unsupported character type");
        };
    }
}
