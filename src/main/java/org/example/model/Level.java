package org.example.model;

public class Level {
    private int level;
    private int experience;
    private Character character;

    public Level() {
        this.level = 1;
        this.experience = 0;
    }

    public void gainExperience(int amount) {
        this.experience += amount;
        if (this.experience >= 100) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.experience = 0;
        character.increaseAttack(level * 5);
        character.increaseDefense(level * 3);
        character.restoreHealth(level * 10);
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
