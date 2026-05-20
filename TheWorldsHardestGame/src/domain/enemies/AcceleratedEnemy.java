package domain.enemies;

import java.awt.Color;
import java.util.ArrayList;

import domain.walls.StaticWall;

/**
 * Enemigo tipo A. Se mueve al doble de velocidad en linea recta.
 * Dificultad: Alta.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class AcceleratedEnemy extends Enemy {

    private int dx;
    private int dy;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public AcceleratedEnemy(double positionX, double positionY, double width, double height,
                            double speed, int dx, int dy,
                            double minX, double maxX, double minY, double maxY) {
        super(positionX, positionY, width, height, speed * 2.0, Color.BLUE);
        this.dx = dx;
        this.dy = dy;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    @Override
    public void move(int boardWidth, int boardHeight, ArrayList<StaticWall> walls) {
        double newX = positionX + dx * speed;
        double newY = positionY + dy * speed;

        if (newX <= minX || newX + width >= maxX) {
            dx = -dx;
            newX = positionX;
        }
        if (newY <= minY || newY + height >= maxY) {
            dy = -dy;
            newY = positionY;
        }

        positionX = newX;
        positionY = newY;
    }
}