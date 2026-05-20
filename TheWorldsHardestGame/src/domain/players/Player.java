package domain.players;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import domain.Element;
import domain.walls.*;

/**
 * Clase base de todos los jugadores del juego.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class Player extends Element {
    private String name;
    private int lifes;
    private int deaths;
    private int coinsCollected;
    private double spawnX;
    private double spawnY;
    protected Color borderColor;
    private ArrayList<int[]> validCells = new ArrayList<>();
    private int hitCooldown = 0;
    private static final int COOLDOWN_TICKS = 60;
    
    /*Constructor de la clase Player
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del jugador.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del jugador.
	 * @param width Numero entero positivo que define el ancho del jugador.
	 * @param height Numero entero positivo que define la altura del jugador.
	 * @param speed Numero entero positivo que define la velocidad base del jugador.
	 * @param color Color actual del cuadrado del jugador.
	 * @param borderColor Color actual del borde del cuadrado del jugador.
	 * @param name String que es el nombre del jugador.
	 */
    public Player(double positionX, double positionY, double width, double height,
                  double speed, Color color, Color borderColor, String name) {
        super(positionX, positionY, width, height, speed, color);
        this.name = name;
        this.borderColor = borderColor;
        lifes = 1;
        deaths = 0;
        coinsCollected = 0;
        spawnX = positionX;
        spawnY = positionY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
        g.setColor(borderColor);
        g.drawRect((int) positionX, (int) positionY, (int) width, (int) height);
    }

    /**
     * Mueve el jugador chequeando bordes del tablero y colision con paredes.
     */
    public void move(int dx, int dy, int boardWidth, int boardHeight, ArrayList<StaticWall> walls) {
        double newX = positionX + dx * speed;
        double newY = positionY + dy * speed;

        if (isInsideValidArea(newX, positionY) && !collidesWithWall(newX, positionY, walls)) {
            positionX = newX;
        }
        if (isInsideValidArea(positionX, newY) && !collidesWithWall(positionX, newY, walls)) {
            positionY = newY;
        }
    }

    private boolean isInsideValidArea(double x, double y) {
        // Checa las 4 esquinas del jugador
        return isCellValid(x, y) &&
               isCellValid(x + width - 1, y) &&
               isCellValid(x, y + height - 1) &&
               isCellValid(x + width - 1, y + height - 1);
    }

    private boolean isCellValid(double px, double py) {
        for (int[] cell : validCells) {
            if (px >= cell[0] && px < cell[0] + cell[2] &&
                py >= cell[1] && py < cell[1] + cell[3]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checa si en la posicion (x, y) el jugador colisiona con alguna pared.
     */
    private boolean collidesWithWall(double x, double y, ArrayList<StaticWall> walls) {
        for (StaticWall wall : walls) {
            if (x < wall.getPositionX() + wall.getWidth() &&
                x + width > wall.getPositionX() &&
                y < wall.getPositionY() + wall.getHeight() &&
                y + height > wall.getPositionY()) {
                return true;
            }
        }
        return false;
    }
    
    public void tick() {
        if (hitCooldown > 0) hitCooldown--;
    }

    public boolean canBeHit() {
        return hitCooldown == 0;
    }

    protected void startHitCooldown() {
        hitCooldown = COOLDOWN_TICKS;
    }

    /**
     * Comportamiento al ser golpeado por un enemigo.
     * Por defecto muere y respawnea. Clyde lo sobreescribe.
     */
    public void onEnemyHit() {
        respawn();
        startHitCooldown();
    }

    public void setSpawn(double x, double y) {
        spawnX = x;
        spawnY = y;
    }

    public void respawn() {
        deaths++;
        positionX = spawnX;
        positionY = spawnY;
    }

    public void addLife() {
    	lifes++; 
    }
    public void addCoinCollected() {
    	coinsCollected++; 
    }
    public int getDeaths() {
    	return deaths; 
    }
    public int getCoinsCollected() {
    	return coinsCollected; 
    }
    public String getName() {
    	return name; 
    }
    public void setValidCells(ArrayList<int[]> cells) {
        this.validCells = cells;
    }
}