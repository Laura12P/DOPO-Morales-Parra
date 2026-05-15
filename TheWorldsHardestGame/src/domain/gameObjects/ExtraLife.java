package domain.gameObjects;

import java.awt.Color;

/* La clase ExtraLife es una subclase de Collectionable, la cual puede ser recolectada por el jugador para obtener una vida extra.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class ExtraLife extends Collectionable {
	
	/*Constructor de la clase ExtraLife
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la vida extra.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la vida extra.
	 * @param width Numero entero positivo que define el ancho de la vida extra.
	 * @param height Numero entero positivo que define la altura de la vida extra.
	 * @param speed Numero entero positivo que define la velocidad base de la vida extra.
	 * @param color Color actual de la vida extra.
	 */
	public ExtraLife(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
	}
	
	public void collect(Player player) {
		setCollectedTrue();
		player.addLife();
	}
}