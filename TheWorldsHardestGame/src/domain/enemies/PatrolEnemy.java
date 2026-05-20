package domain.enemies;

import java.awt.Color;
import java.util.ArrayList;

import domain.walls.StaticWall;

/**
 * Enemigo patrullero. Sigue una ruta circular con centro y radio definidos.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class PatrolEnemy extends Enemy {

    private double centerX;
    private double centerY;
    private double radius;
    private double angle;
    private double angleSpeed;

    public PatrolEnemy(double centerX, double centerY, double radius,
                       double width, double height, double speed) {
        super(centerX + radius, centerY, width, height, speed, Color.BLUE);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angle = 0;
        this.angleSpeed = speed * 0.03;
    }

    @Override
    public void move(int boardWidth, int boardHeight, ArrayList<StaticWall> walls) {
        angle += angleSpeed;
        positionX = centerX + radius * Math.cos(angle);
        positionY = centerY + radius * Math.sin(angle);
    }
}