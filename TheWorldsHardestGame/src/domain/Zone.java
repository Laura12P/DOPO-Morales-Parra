package domain;

import java.awt.Color;

/* La clase Zone es la superclase abstracta que define el comportamiento de cualquier zona en el juego.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public abstract class Zone extends Element {
	
	/*Constructor de la clase Zone
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la zona.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la zona.
	 * @param width Numero entero positivo que define el ancho de la zona.
	 * @param height Numero entero positivo que define la altura de la zona.
	 * @param color Color actual del elemento.
	 */
	public Zone(double positionX, double positionY, double width, double height, Color color) {
		super(positionX, positionY, width, height, 0, color);
		
	}
}