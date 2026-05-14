package domain.gameObjects;

import java.awt.Color;

/* La clase StaticEnemy es una subclase de Enemy, la cual no permite el movimiento
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class StaticEnemy extends Enemy{
	
	/*Contructor de la clase StaticEnemy
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del elemento.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del elemento.
	 * @param width Numero entero positivo que define el ancho del elemento.
	 * @param height Numero entero positivo que define la altura del elemento.
	 * @param speed Numero entero positivo que define la velocidad base del elemento.
	 * @param color Color actual del elemento.
	 */
	public StaticEnemy(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
	}
	
	@Override
	public void move(int boardWidth, int boardHeight) {
	    // No se mueve
	}
}