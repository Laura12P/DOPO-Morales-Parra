package domain.collectables;

import java.awt.Color;

import java.awt.Graphics;

import domain.Element;
import domain.players.Player;

/* La clase Collectionable es una subclase de Element, el cual es un colecionable interactuable por los jugadores, y que 
 * es obligatorio recolectar todos los existentes en el nivel para que un jugador pase de nivel.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public abstract class Collectionable extends Element{
	private boolean collected;
	
	/*Constructor de la clase Collectionable
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del coleccionable.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del coleccionable.
	 * @param width Numero entero positivo que define el ancho del coleccionable.
	 * @param height Numero entero positivo que define la altura del coleccionable.
	 * @param speed Numero entero positivo que define la velocidad base del coleccionable.
	 * @param color Color actual del coleccionable.
	 */
	public Collectionable(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
		collected = false;
	}
	
	@Override
	public void draw(Graphics g) {
	    if (!collected) {
	        g.setColor(color);
	        g.fillOval((int) positionX, (int) positionY, (int) width, (int) height);
	    }
	}

	public abstract void collect(Player player);
	
	/**
     * Indica si este coleccionable cuenta como moneda para completar el nivel.
     * Las subclases que NO son monedas (Bomb, ExtraLife) deben sobreescribir esto y retornar false.
     */
    public boolean isCoin() {
        return true;
    }
	
	public void setCollectedTrue() {
		collected = true;
	}

	public boolean isCollected() {
		return collected; 
	}
}