package domain;

import java.awt.Color;

/* La clase MovingEnemy es una subclase de Enemy, la cual permite el movimiento
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class MovingEnemy extends Enemy{
	private int dx; // dirección horizontal: -1, 0 o 1
	private int dy; // dirección vertical: -1, 0 o 1
	
	/*Contructor de la clase MovingEnemy
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del elemento.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del elemento.
	 * @param width Numero entero positivo que define el ancho del elemento.
	 * @param height Numero entero positivo que define la altura del elemento.
	 * @param speed Numero entero positivo que define la velocidad base del elemento.
	 * @param color Color actual del elemento.
	 */
	public MovingEnemy(double positionX, double positionY, double width, double height, double speed, Color color, int dx, int dy) {
	    super(positionX, positionY, width, height, speed, color);
	    this.dx = dx;
	    this.dy = dy;
	}

	@Override
	public void move(int boardWidth, int boardHeight) {
	    positionX += dx * speed;
	    positionY += dy * speed;
	    // Rebote horizontal
	    if (positionX <= 0 || positionX + width >= boardWidth) dx = -dx;
	    // Rebote vertical
	    if (positionY <= 0 || positionY + height >= boardHeight) dy = -dy;
	}
}
