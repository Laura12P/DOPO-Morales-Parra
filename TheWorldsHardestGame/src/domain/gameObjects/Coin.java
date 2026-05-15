package domain.gameObjects;

import java.awt.Color;

/* La clase Coin es una subclase de Collectionable, la cual debe de ser recolectado por el jugador para poder pasar el nivel.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Coin extends Collectionable {
	
	/*Constructor de la clase Coin
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la moneda.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la moneda.
	 * @param width Numero entero positivo que define el ancho de la moneda.
	 * @param height Numero entero positivo que define la altura de la moneda.
	 * @param speed Numero entero positivo que define la velocidad base de la moneda.
	 * @param color Color actual de la moneda.
	 */
	public Coin(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
	}
	
	public void collect(Player player) {
		setCollectedTrue();
		player.addCoinCollected();
	}
}