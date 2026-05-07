package domain;

import java.awt.Color;

/* La clase Collectionable es una subclase de Element, el cual es un colecionable interactuable por los jugadores, y que 
 * es obligatorio recolectar todos los existentes en el nivel para que un jugador pase de nivel.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Collectionable extends Element{
	private boolean collected;
	private boolean visible;
	
	/*Constructor de la clase Collectionable
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del elemento.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del elemento.
	 * @param width Numero entero positivo que define el ancho del elemento.
	 * @param height Numero entero positivo que define la altura del elemento.
	 * @param speed Numero entero positivo que define la velocidad base del elemento.
	 * @param color Color actual del elemento.
	 */
	public Collectionable(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
		collected = false;
		visible = true;
	}
}