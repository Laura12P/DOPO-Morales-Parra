package domain.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

/* La clase Player es una subclase de Element, la cual es controlado por el jugador o usuario, al chocar contra un enemigo muere y se respawnea, puede
 * interactuar con distintos coleccionables que tienen distintos efectos.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class Player extends Element {
    private String name;
    private int totalTime;
    private int lifes;
    private int deaths;
    private RespawnZone respawnZone;
    private int totalCoinsCollected;
    private int coinsCollected;
    private double spawnX;
    private double spawnY;
    
    /*Constructor de la clase Player
	 * 
	 * @param positionX Numero entero positivo que define la posicion en el eje X del jugador.
	 * @param positionY Numero entero positivo que define la posicion en el eje Y del jugador.
	 * @param width Numero entero positivo que define el ancho del jugador.
	 * @param height Numero entero positivo que define la altura del jugador.
	 * @param speed Numero entero positivo que define la velocidad base del jugador.
	 * @param color Color actual del jugador.
	 * @param name String que especifica el nombre del jugador.
	 */
    public Player(double positionX, double positionY, double width, double height, double speed, Color color, String name) {
        super(positionX, positionY, width, height, speed, color);
        this.name = name;
        totalTime = 0;
        lifes = 1;
        deaths = 0;
        respawnZone = null; // Pendiente
        totalCoinsCollected = 0;
        coinsCollected = 0;
        spawnX = positionX;
        spawnY = positionY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
        g.setColor(Color.BLACK);
        g.drawRect((int) positionX, (int) positionY, (int) width, (int) height);
    }

    public void move(int dx, int dy, int boardWidth, int boardHeight) {
        double newX = positionX + dx * speed;
        double newY = positionY + dy * speed;
        if (newX >= 0 && newX + width <= boardWidth) {
        	positionX = newX;
        }
        if (newY >= 0 && newY + height <= boardHeight) {
        	positionY = newY;
        }
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
}