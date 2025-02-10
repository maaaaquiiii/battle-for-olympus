package org.example.model;

public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;

    protected Character(Builder<?> characterBuilder) {
        this.name = characterBuilder.name;
        this.health = characterBuilder.health;
        this.attack = characterBuilder.attack;
        this.defense = characterBuilder.defense;
        this.weapon = characterBuilder.weapon;
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

    public abstract int calculateDamage(Character enemy);

    public boolean isAlive() {
        return this.health > 0;
    }

    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\thealth: %d\tattack: %d\tdefense: %d\tweapon: %s\n",
                getName(), getHealth(), getAttack(), getDefense(), getWeapon());
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private int health;
        private int attack;
        private int defense;
        private Weapon weapon;


        public T name(String name) {
            this.name = name;
            return self();
        }

        public T health(int health) {
            this.health = health;
            return self();
        }

        public T attack(int attack) {
            this.attack = attack;
            return self();
        }

        public T defense(int defense) {
            this.defense = defense;
            return self();
        }

        public T weapon(Weapon weapon) {
            this.weapon = weapon;
            return self();
        }

        protected abstract  T self();

        public abstract Character build();
    }
}



