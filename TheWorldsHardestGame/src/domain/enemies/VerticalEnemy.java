package domain.enemies;

import java.awt.Color;
import java.util.ArrayList;

import domain.walls.StaticWall;

/**
 * Enemigo tipo V. Solo se mueve verticalmente, rebota arriba y abajo.
 * Dificultad: Baja.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class VerticalEnemy extends Enemy {

    private int dy;
    private double minY;
    private double maxY;

    public VerticalEnemy(double positionX, double positionY, double width, double height,
                         double speed, double minY, double maxY) {
        super(positionX, positionY, width, height, speed, Color.BLUE);
        this.dy = 1;
        this.minY = minY;
        this.maxY = maxY;
    }

    @Override
    public void move(int boardWidth, int boardHeight, ArrayList<StaticWall> walls) {
        double newY = positionY + dy * speed;

        if (newY <= minY || newY + height >= maxY) {
            dy = -dy;
            newY = positionY;
        }

        positionY = newY;
    }
}