package domain.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

/* La clase EndZone es una subclase de Zone en la cual el jugador termina el nivel.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class EndZone extends Zone {
	
	/*Constructor de la clase EndZone
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la zona.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la zona.
	 * @param width Numero entero positivo que define el ancho de la zona.
	 * @param height Numero entero positivo que define la altura de la zona.
	 * @param color Color actual de la zona.
	 */
	public EndZone(double positionX, double positionY, double width, double height, Color color) {
		super(positionX, positionY, width, height, color);
	}
	
	@Override
	public void activatedByPlayer(Player player) {
	    // La lógica de completar nivel la maneja Board/CollisionController
	    // Por ahora vacío ya veremos en el futuro 
	}
	
	@Override
	public void draw(Graphics g) {
	    g.setColor(color);
	    g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
	}
}