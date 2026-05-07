package domain;

import java.awt.Color;

/* La clase StartZone es una subclase de RespawnZone en la cual el jugador inicia el nivel.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class StartZone extends RespawnZone {
	
	/*Constructor de la clase StartZone
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la zona.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la zona.
	 * @param width Numero entero positivo que define el ancho de la zona.
	 * @param height Numero entero positivo que define la altura de la zona.
	 * @param color Color actual del elemento.
	 */
	public StartZone(double positionX, double positionY, double width, double height, Color color) {
		super(positionX, positionY, width, height, color);
	}
}