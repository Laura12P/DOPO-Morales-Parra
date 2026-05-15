package domain.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

/* La clase Explosion es una subclase de Element, que se genera 
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Explosion extends Element {
	
	/*Constructor de la clase Bomb
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X de la explosion.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y de la explosion.
	 * @param width Numero entero positivo que define el ancho de la explosion.
	 * @param height Numero entero positivo que define la altura de la explosion.
	 * @param speed Numero entero positivo que define la velocidad base de la explosion.
	 * @param color Color actual de la explosion.
	 */
	public Explosion(double positionX, double positionY, double width, double height, double speed, Color color) {
		super(positionX, positionY, width, height, speed, color);
	}
	
	@Override
	public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) positionX, (int) positionY, (int) width, (int) height);
	}
}