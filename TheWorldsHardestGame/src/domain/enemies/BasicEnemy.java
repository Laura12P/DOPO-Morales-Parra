package domain.enemies;

import java.awt.Color;
import java.util.ArrayList;

import domain.walls.StaticWall;

/**
 * Enemigo basico. Se desplaza en linea recta horizontal o vertical
 * y rebota al chocar con paredes o bordes.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class BasicEnemy extends Enemy {

    private int dx;
    private int dy;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public BasicEnemy(double positionX, double positionY, double width, double height,
                      double speed, int dx, int dy,
                      double minX, double maxX, double minY, double maxY) {
        super(positionX, positionY, width, height, speed, Color.BLUE);
        this.dx = dx;
        this.dy = dy;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    @Override
    public void move(int boardWidth, int boardHeight, ArrayList<StaticWall> walls) {
        positionX += dx * speed;
        positionY += dy * speed;

        if (positionX < minX) {
            positionX = minX;
            dx = 1;
        } else if (positionX + width > maxX) {
            positionX = maxX - width;
            dx = -1;
        }

        if (positionY < minY) {
            positionY = minY;
            dy = 1;
        } else if (positionY + height > maxY) {
            positionY = maxY - height;
            dy = -1;
        }
    }

    private boolean collidesWithWall(double x, double y, ArrayList<StaticWall> walls) {
        for (StaticWall wall : walls) {
            if (x < wall.getPositionX() + wall.getWidth() &&
                x + width > wall.getPositionX() &&
                y < wall.getPositionY() + wall.getHeight() &&
                y + height > wall.getPositionY()) {
                return true;
            }
        }
        return false;
    }
}