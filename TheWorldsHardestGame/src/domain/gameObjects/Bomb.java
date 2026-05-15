package domain.gameObjects;

import java.awt.Color;

/* La clase Bomb es una subclase de Collectionable, que al ser interactuado por un jugador o enemigo, explota, generando daño en un radio determinado.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Bomb extends Collectionable {
	
	/*Constructor de la clase Bomb
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la bomba.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la bomba.
	 * @param width Numero entero positivo que define el ancho de la bomba.
	 * @param height Numero entero positivo que define la altura de la bomba.
	 * @param speed Numero entero positivo que define la velocidad base de la bomba.
	 * @param color Color actual de la bomba.
	 */
	public Bomb(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
	}
	
	public void collect(Player player) {
		setCollectedTrue();
		player.respawn();
	}
}