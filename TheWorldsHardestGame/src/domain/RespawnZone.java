package domain;

import java.awt.Color;

/*La clase RespawnZone es una subclase de Zone en la cual el jugador puede respawnear.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class RespawnZone extends Zone {
	
	/*Constructor de la clase RespawnZone
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la zona.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la zona.
	 * @param width Numero entero positivo que define el ancho de la zona.
	 * @param height Numero entero positivo que define la altura de la zona.
	 * @param color Color actual del elemento.
	 */
	public RespawnZone(double positionX, double positionY, double width, double height, Color color) {
		super(positionX, positionY, width, height, color);
	}
}