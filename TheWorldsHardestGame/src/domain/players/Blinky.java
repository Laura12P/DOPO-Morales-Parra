package domain.players;

import java.awt.Color;

/**
 * Jugador rojo. Velocidad y tamaño estandar, sin habilidades especiales.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class Blinky extends Player {

    public Blinky(double positionX, double positionY, Color borderColor) {
        super(positionX, positionY, 35, 35, 3.0, Color.RED, borderColor, "Blinky");
    }
}