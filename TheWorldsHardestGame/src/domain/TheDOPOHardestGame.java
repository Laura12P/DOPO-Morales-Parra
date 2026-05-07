package domain;

/* La clase TheDOPOHardestGame es la clase principal en el modelo, encargada de gestionar el tablero y sus acciones.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class TheDOPOHardestGame {
	private Board board;
	
	/*Constructor de la clase TheDOPOHardestGame
	 * 
	 * @param ancho Numero entero positivo que define el ancho del tablero.
	 * @param alto Numero entero positivo que define el alto del tablero.
	 * @param startZone Primera zona de respawn donde comienza el jugador.
	 * @param endZona Zona donde se termina el juego si el jugador colisiona con esta.
	 */
	public TheDOPOHardestGame (int ancho, int alto, StartZone startZone, EndZone endZone) {
		board = new Board(ancho, alto, startZone, endZone);
	}
	
}