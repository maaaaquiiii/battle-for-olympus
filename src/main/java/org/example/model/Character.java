package org.example.model;

public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;
    private Attack attackStrategy;

    private Character(Builder characterBuilder) {
        this.name = characterBuilder.name;
        this.health = characterBuilder.health;
        this.attack = characterBuilder.attack;
        this.defense = characterBuilder.defense;
        this.weapon = characterBuilder.weapon;
        this.attackStrategy = characterBuilder.attackStrategy;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Attack getAttackStrategy() {
        return attackStrategy;
    }

    public void attack(Character enemy) {
        int damage = attackStrategy.executeAttack(this.attack + weapon.getAttackBoost(), enemy.defense);
        enemy.receiveDamage(damage);
    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getStatus() {
        return name + " - Health: " + health + " | Attack: " + attack + " | Defense: " + defense;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\tattack: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon(), getAttackStrategy());
    }
}

public static class Builder {
    private String name;
    private CharacterType type;
    private int health = 100;
    private int attack = 20;
    private int defense = 10;
    private Weapon weapon = new Weapon("Basic Weapon", 5, 2);
    private Attack attackStrategy = new PhysicalAttack();

    public Builder() {

    }

    public Builder name(String name) {
        this.name = name;
        return this;
    }

    public Builder type(CharacterType type) {
        this.type = type;
        return this;
    }

    public Builder health(int health) {
        this.health = health;
        return this;
    }

    public Builder attack(int attack) {
        this.attack = attack;
        return this;
    }

    public Builder defense(int defense) {
        this.defense = defense;
        return this;
    }

    public Builder weapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public Builder attackStrategy(Attack attackStrategy) {
        this.attackStrategy = attackStrategy;
        return this;
    }

    public Character build() {
        return switch (type) {
            case WARRIOR -> new Warrior(name, health, attack, defense, weapon, attackStrategy);
            case GOD -> new God(name, health + 20, attack + 5, defense + 5, weapon, attackStrategy);
        };
    }
}
