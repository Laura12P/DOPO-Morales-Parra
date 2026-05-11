package domain;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Element {
    public String name;
    public int deaths;
    public int coinsCollected;
    private double spawnX;
    private double spawnY;

    public Player(double positionX, double positionY, double width, double height, double speed, Color color, String name) {
        super(positionX, positionY, width, height, speed, color);
        this.name = name;
        this.spawnX = positionX;
        this.spawnY = positionY;
        this.deaths = 0;
        this.coinsCollected = 0;
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
        if (newX >= 0 && newX + width <= boardWidth) positionX = newX;
        if (newY >= 0 && newY + height <= boardHeight) positionY = newY;
    }

    public void setSpawn(double x, double y) {
        this.spawnX = x;
        this.spawnY = y;
    }

    public void respawn() {
        positionX = spawnX;
        positionY = spawnY;
        deaths++;
    }

    public void coinCollected() {
        coinsCollected++;
    }
}
