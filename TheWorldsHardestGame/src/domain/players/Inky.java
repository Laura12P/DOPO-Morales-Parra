package domain.players;

import java.awt.Color;

/**
 * Jugador azul. Mas rapido y mas grande que Blinky.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class Inky extends Player {

    public Inky(double positionX, double positionY, Color borderColor) {
        super(positionX, positionY, 45, 45, 4.5, Color.BLUE, borderColor, "Inky");
    }
}