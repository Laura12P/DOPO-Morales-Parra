package domain.players;

import java.awt.Color;

/**
 * Jugador verde. Absorbe el primer golpe enemigo sin morir, bajando su velocidad.
 * Al segundo golpe muere y recupera velocidad original.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class Clyde extends Player {

    private boolean absorbedHit;
    private static final double BASE_SPEED = 3.0;

    public Clyde(double positionX, double positionY, Color borderColor) {
        super(positionX, positionY, 35, 35, BASE_SPEED, Color.GREEN, borderColor, "Clyde");
        absorbedHit = false;
    }

    @Override
    public void onEnemyHit() {
        if (!canBeHit()) return;

        if (!absorbedHit) {
            absorbedHit = true;
            speed = 2.0;
            color = new Color(144, 238, 144);
            startHitCooldown();
        } else {
            absorbedHit = false;
            speed = BASE_SPEED;
            color = Color.GREEN;
            respawn();
            startHitCooldown();
        }
    }
}