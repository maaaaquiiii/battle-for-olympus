package org.example.model;

import org.example.model.Characters.Character;

public class Combat {
    public static Character fight(Character player1, Character player2) {
        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            int damageToPlayer2 = Math.max(0, player1.getAttack() - player2.getDefense());
            player2.receiveDamage(damageToPlayer2);
            int damageToPlayer1 = Math.max(0, player2.getAttack() - player1.getDefense());
            player1.receiveDamage(damageToPlayer1);
        }

        return player1.getHealth() > 0 ? player1 : player2;
    }

    public void adjustOpponentHealthAfterVictory(Character winner, Character loser) {
        int victories = winner.getVictories().getOrDefault(loser.getType(), 0);
        if (victories > 10) {
            int damageReduction = (int) (loser.getHealth() * 0.25);
            loser.receiveDamage(damageReduction);
        }
    }
}
