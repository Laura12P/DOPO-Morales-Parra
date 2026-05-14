package domain.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

public class StaticWall extends Element {

    public StaticWall(double positionX, double positionY, double width, double height, Color color) {
        super(positionX, positionY, width, height, 0, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
    }
}