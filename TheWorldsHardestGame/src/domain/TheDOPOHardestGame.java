package domain;

import java.io.IOException;

/* La clase TheDOPOHardestGame es la clase principal en el modelo, encargada de gestionar el tablero y sus acciones.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class TheDOPOHardestGame {
	public Board board;
	
	/*Constructor de la clase TheDOPOHardestGame
	 * 
	 * @param levelPath Path correspondiente a la ubicacion del nivel que se desea jugar, y que se carga en el tablero.
	 */
	public TheDOPOHardestGame(String levelPath) throws IOException {
        board = LevelLoader.loadFromFile(levelPath);
    }
}