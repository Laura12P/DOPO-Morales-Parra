package domain.collectables;

import java.awt.Color;

import domain.players.Player;

/* La clase SkinChanger es una subclase de Collectionable, la cual al ser interactuada por un jugador cambia la skin del mismo por la del color del
 * SkinChanger durante un tiempo determinado.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class SkinChanger extends Collectionable {
	private int duration;
	
	/*Constructor de la clase SkinChanger
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del cambiador de skins.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del cambiador de skins.
	 * @param width Numero entero positivo que define el ancho del cambiador de skins.
	 * @param height Numero entero positivo que define la altura del cambiador de skins.
	 * @param speed Numero entero positivo que define la velocidad base del cambiador de skins.
	 * @param color Color actual del cambiador de skins.
	 */
	public SkinChanger(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
		duration = 30;
	}
	
	public void collect(Player player) {
		setCollectedTrue();
		player.setColor(color);
	}
}