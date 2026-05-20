package domain;

import java.awt.Graphics;

import java.util.ArrayList;

import domain.players.Player;
import domain.enemies.Enemy;
import domain.collectables.Collectionable;
import domain.walls.StaticWall;
import domain.zones.StartZone;
import domain.zones.EndZone;
import domain.zones.RespawnZone;

/**
 * Clase que gestiona el tablero y todos sus elementos.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class Board {

    private ArrayList<StartZone> startZones;
    private ArrayList<EndZone> endZones;
    private ArrayList<RespawnZone> respawnZones;
    private ArrayList<Player> players;
    private ArrayList<Collectionable> collectionables;
    private ArrayList<Enemy> enemies;
    private ArrayList<StaticWall> walls;
    private ArrayList<int[]> corridors;
    private CollisionController collisionController;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
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

    public void update() {
        for (Enemy e : enemies) {
            e.move(width, height, walls);
        }
        collisionController.checkCollisions(players, enemies, collectionables, endZones, respawnZones, walls);
    }

    public void drawAllElements(Graphics g) {
        for (StartZone s : startZones) s.draw(g);
        for (EndZone ez : endZones) ez.draw(g);
        for (RespawnZone rz : respawnZones) rz.draw(g);
        for (StaticWall w : walls) w.draw(g);
        for (Collectionable c : collectionables) c.draw(g);
        for (Enemy e : enemies) e.draw(g);
        for (Player p : players) p.draw(g);
    }

  
    public void addStartZone(StartZone s) { 
    	startZones.add(s);
    }
    public void addEndZone(EndZone e) {
    	endZones.add(e); 
    }
    public void addRespawnZone(RespawnZone r) {
    	respawnZones.add(r); 
    }
    public void addPlayer(Player p) {
    	players.add(p);
    }
    public void addEnemy(Enemy e) {
    	enemies.add(e);
    }
    public void addCollectionable(Collectionable c) {
    	collectionables.add(c); 
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

    // Getters
    public ArrayList<Player> getPlayers() {
    	return players; 
    }
    public ArrayList<Enemy> getEnemies() { 
    	return enemies;
    }
    public ArrayList<StaticWall> getWalls() {
    	return walls; 
    }
    public ArrayList<Collectionable> getCollectionables() {
    	return collectionables; 
    }
    public ArrayList<int[]> getCorridors() {
    	return corridors; 
    }
    public int getAmountOfPlayers() {
    	return players.size();
    }
    public int getWidth() {
    	return width;
    }
    public int getHeight() {
    	return height;
    }

    public int totalCoins() {
        return collectionables.size();
    }

    public int getAmountCollectedCoins() {
        int count = 0;
        for (Collectionable c : collectionables) {
            if (c.isCollected()) count++;
        }
        return count;
    }
    
    public ArrayList<int[]> getAllValidCells() {
        ArrayList<int[]> cells = new ArrayList<>();
        cells.addAll(corridors);
        for (StartZone s : startZones)
            cells.add(new int[]{(int)s.getPositionX(), (int)s.getPositionY(), (int)s.getWidth(), (int)s.getHeight()});
        for (EndZone e : endZones)
            cells.add(new int[]{(int)e.getPositionX(), (int)e.getPositionY(), (int)e.getWidth(), (int)e.getHeight()});
        for (RespawnZone r : respawnZones)
            cells.add(new int[]{(int)r.getPositionX(), (int)r.getPositionY(), (int)r.getWidth(), (int)r.getHeight()});
        return cells;
    }
}