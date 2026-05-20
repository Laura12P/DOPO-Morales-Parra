package domain.enemies;

import java.awt.Color;
import java.awt.Graphics;

import domain.Element;
import domain.walls.StaticWall;
import java.util.ArrayList;

/**
 * Clase base abstracta de todos los enemigos.
 * Al tocar un jugador lo elimina.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public abstract class Enemy extends Element {

    public Enemy(double positionX, double positionY, double width, double height, double speed, Color color) {
        super(positionX, positionY, width, height, speed, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) positionX, (int) positionY, (int) width, (int) height);
        g.setColor(Color.BLACK);
        g.drawOval((int) positionX, (int) positionY, (int) width, (int) height);
    }

    public abstract void move(int boardWidth, int boardHeight, ArrayList<StaticWall> walls);
}