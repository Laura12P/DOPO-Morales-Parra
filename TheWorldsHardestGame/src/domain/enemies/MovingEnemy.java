package domain.enemies;

import java.awt.Color;
import java.util.ArrayList;

import domain.walls.StaticWall;

/* La clase MovingEnemy es una subclase de Enemy, la cual tiene la capacidad de moverse y no de mantenerse estatico.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class MovingEnemy extends Enemy {
    private int dx;
    private int dy;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    
    /* Constructor de la clase MovingEnemy
     * 
     * @param positionX Numero entero positivo que define la posicion en el eje X de un enemigo movible.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de un enemigo movible.
	 * @param width Numero entero positivo que define el ancho de un enemigo movible.
	 * @param height Numero entero positivo que define la altura de un enemigo movible.
	 * @param speed Numero entero positivo que define la velocidad base de un enemigo movible.
	 * @param color Color actual de un enemigo movible.
	 * @param dx Numero entero positivo que define el multiplicador de velocidad en el eje X de un enemigo movible.
	 * @param dy Numero entero positivo que define el multiplicador de velocidad en el eje Y de un enemigo movible.
	 * @param minX Numero decimal de precision doble, que define la posicion minima en el eje X hasta la que un enemigo movible se puede mover.
     * @param maxX Numero decimal de precision doble, que define la posicion maxima en el eje X hasta la que un enemigo movible se puede mover.
     * @param minY Numero decimal de precision doble, que define la posicion minima en el eje Y hasta la que un enemigo movible se puede mover.
     * @param maxY Numero decimal de precision doble, que define la posicion maxima en el eje X hasta la que un enemigo movible se puede mover.
     * 
     * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
     */
    public MovingEnemy(double positionX, double positionY, double width, double height, double speed, Color color, int dx, int dy, 
    		double minX, double maxX, double minY, double maxY) {
        super(positionX, positionY, width, height, speed, color);
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

        // Rebote horizontal dentro del rango
        if (newX <= minX || newX + width >= maxX) {
            dx = -dx;
            newX = positionX + dx * speed;
        }
        // Rebote vertical dentro del rango
        if (newY <= minY || newY + height >= maxY) {
            dy = -dy;
            newY = positionY + dy * speed;
        }

        positionX = newX;
        positionY = newY;
    }
}