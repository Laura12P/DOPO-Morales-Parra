package domain;
import domain.gameObjects.*;

import java.util.ArrayList;

/* La clase Board es la clase que gestiona el tablero y sus elementos.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Board {
    public ElementType[][] grid;
    public CollisionController collisionController;
    public ArrayList<StartZone> startZones;
    public ArrayList<EndZone> endZones;
    public ArrayList<RespawnZone> respawnZones;
    public ArrayList<Player> players;
    public ArrayList<Collectionable> collectionables;
    public ArrayList<Enemy> enemies;
    public ArrayList<StaticWall> walls;
    public ArrayList<int[]> corridors;
    public int width;
    public int height;
    
    /* Constructor de la clase Board
     * 
     * @param width Ancho total del tablero.
     * @param height Alto total del tablero.
     * 
     * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new ElementType[width][height];
        collisionController = new CollisionController();
        startZones = new ArrayList<>();
        endZones = new ArrayList<>();
        respawnZones = new ArrayList<>();
        players = new ArrayList<>();
        collectionables = new ArrayList<>();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
        corridors = new ArrayList<>();
    }

    public void addStartZone(StartZone s) { 
    	startZones.add(s); 
    }
    public void addEndZone(EndZone e) {
    	endZones.add(e); 
    }
    public void addPlayer(Player player) {
    	players.add(player); 
    }
    public void addEnemy(Enemy enemy) { 
    	enemies.add(enemy); 
    }
    public void addCollectionable(Collectionable c) {
    	collectionables.add(c); 
    }
    public void addRespawnZone(RespawnZone r) { 
    	respawnZones.add(r); 
    }
    public void addWall(StaticWall w) { 
    	walls.add(w); 
    }
    public void addCorridor(int x, int y, int w, int h) {
        corridors.add(new int[]{x, y, w, h});
    }

    public void setEventListener(CollisionController.GameEventListener listener) {
        collisionController.setListener(listener);
    }

    public void update() {
        for (Enemy e : enemies) {
            e.move(width, height);
        }
        collisionController.checkCollisions(players, enemies, collectionables, endZones);
    }

    public int totalCoins() {
    	return collectionables.size();
    	}

    public int collectedCoins() {
        int count = 0;
        for (Collectionable c : collectionables) {
            if (c.isCollected()) {
            	count++;
            }
        }
        return count;
    }
}