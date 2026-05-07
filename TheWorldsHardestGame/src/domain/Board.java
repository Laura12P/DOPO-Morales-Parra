package domain;

import java.util.ArrayList;

/* La clase Board es la encargada de generar el tablero del juego y de administrar cada uno de sus elementos, asi como de
 * llamar al colisionador para verificar colisiones y acciones respectivas.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Board {
	private ElementType[][] grid;
	private CollisionController collisionController;
	private StartZone startZone;
	private EndZone endZone;
	private ArrayList<RespawnZone> respawnZones;
	private ArrayList<Player> players;
	private ArrayList<Collectionable> collectionables;
	private ArrayList<Enemy> enemies;
	
	/*Constructor de la clase Board
	 * 
	 * @param ancho Numero entero positivo que define el ancho del tablero.
	 * @param alto Numero entero positivo que define el alto del tablero.
	 * @param startZone Primera zona de respawn donde comienza el jugador.
	 * @param endZona Zona donde se termina el juego si el jugador colisiona con esta.
	 */
	public Board(int ancho, int alto, StartZone startZone, EndZone endZone) {
		grid = new ElementType[ancho][alto];
		collisionController = new CollisionController();
		this.startZone = startZone;
		this.endZone = endZone;
		respawnZones = new ArrayList<RespawnZone>();
		players = new ArrayList<Player>();
		collectionables = new ArrayList<Collectionable>();
		enemies = new ArrayList<Enemy>();
	}
}