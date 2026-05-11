package domain;

import java.util.ArrayList;

public class Board {
    public ElementType[][] grid;
    public CollisionController collisionController;
    public StartZone startZone;
    public EndZone endZone;
    public ArrayList<RespawnZone> respawnZones;
    public ArrayList<Player> players;
    public ArrayList<Collectionable> collectionables;
    public ArrayList<Enemy> enemies;
    public ArrayList<StaticWall> walls;
    public int width;
    public int height;

    public Board(int width, int height, StartZone startZone, EndZone endZone) {
        this.width = width;
        this.height = height;
        grid = new ElementType[width][height];
        collisionController = new CollisionController();
        this.startZone = startZone;
        this.endZone = endZone;
        respawnZones = new ArrayList<>();
        players = new ArrayList<>();
        collectionables = new ArrayList<>();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
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

    public void setEventListener(CollisionController.GameEventListener listener) {
        collisionController.setListener(listener);
    }

    public void update() {
        for (Enemy e : enemies) {
            e.move(width, height);
        }
        collisionController.checkCollisions(players, enemies, collectionables, endZone);
    }

    public int totalCoins() { return collectionables.size(); }

    public int collectedCoins() {
        int count = 0;
        for (Collectionable c : collectionables) {
            if (c.isCollected()) count++;
        }
        return count;
    }
}
