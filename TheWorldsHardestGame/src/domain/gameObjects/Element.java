package domain.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

/* La clase Element es la superclase abstracta que define los atributos y metodos comunes de todo elemento dentro del juego.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public abstract class Element {
	protected double positionX;
	protected double positionY;
	protected double width;
	protected double height;
	protected double speed;
	protected Color color;
	
	/*Constructor de la clase Element
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del elemento.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del elemento.
	 * @param width Numero entero positivo que define el ancho del elemento.
	 * @param height Numero entero positivo que define la altura del elemento.
	 * @param speed Numero entero positivo que define la velocidad base del elemento.
	 * @param color Color actual del elemento.
	 */
	public Element(double positionX, double positionY, double width, double height, double speed, Color color) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.color = color;
	}
	
	/* Getter de la posicion en el eje X del elemento.
	 * 
	 * @return double - Numero decimal de precision doble, que representa la posicion en el eje X del elemento.
	 */
	public double getPositionX() {
		return positionX;
	}
	
	/* Getter de la posicion en el eje Y del elemento.
	 * 
	 * @return double - Numero decimal de precision doble, que representa la posicion en el eje Y del elemento.
	 */
	public double getPositionY() {
		return positionY;
	}
	
	/* Getter del ancho del elemento.
	 * 
	 * @return double - Numero decimal de precision doble, que representa el ancho del elemento.
	 */
	public double getWidth() {
		return width;
	}
	
	/* Getter del alto del elemento.
	 * 
	 * @return double - Numero decimal de precision doble, que representa el alto del elemento.
	 */
	public double getHeight() {
		return height;
	}
	
	public abstract void draw(Graphics g);
	
}