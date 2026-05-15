package domain;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import domain.gameObjects.Player;
import domain.gameObjects.TWHGException;

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
	public TheDOPOHardestGame(String levelPath) throws TWHGException {
		try {
			board = LevelLoader.loadFromFile(levelPath);
		} catch (IOException e) {
			throw new TWHGException(TWHGException.ERROR_LOADING_THE_LEVEL);
		}
        
    }
	
	public void drawAllElements(Graphics g) {
		board.drawAllElements(g);
	}
	
	public ArrayList<int[]> getCorridors() {
		return board.getCorridors();
	}
	
	public ArrayList<Player> getPlayers() {
		return board.getPlayers();
	}
	
	public int amountOfPlayers() {
		return board.getAmountOfPlayers();
	}
	
	public int getBoardWidth() {
		return board.getWidth();
	}
	
	public int getBoardHeight() {
		return board.getHeight();
	}
}