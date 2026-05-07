package domain;

import java.awt.Color;

/* La clase Player es una subclase de Element cuyo movimiento es controlado por el usuario
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Player extends Element {
	private String name;
	private int totalTime;
	private int deaths;
	private int coinsCollected;
	
	/*Constructor de la clase Player
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del elemento.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del elemento.
	 * @param width Numero entero positivo que define el ancho del elemento.
	 * @param height Numero entero positivo que define la altura del elemento.
	 * @param speed Numero entero positivo que define la velocidad base del elemento.
	 * @param color Color actual del elemento.
	 * @param name Nombre del jugador.
	 */
	public Player(double positionX, double positionY, double width, double height, double speed, Color color, String name) {
		super(positionX, positionY, width, height, speed, color);
		this.name = name;
		totalTime = 0;
		deaths = 0;
		coinsCollected = 0;
	}
}