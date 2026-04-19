package Shapes;

import java.awt.*;

/**
 * Representa un rectángulo dibujable con tamaño, posición y estado de visibilidad.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Rectangle
{
    private int width;
    private int height; 
    private int x;
    private int y;
    private boolean isVisible;

    /**
     * Constructor for objects of class Rectangle
     */
    public Rectangle(int width, int height)
    {
        this.width = width; 
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.isVisible = false;
    }

    /**
     * Establece la posición del rectángulo
     * @param x coordenada horizontal
     * @param y coordenada vertical
     */
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Hace visible el rectángulo en el Canvas
     */
    public void makeVisible(String color)
    {
        isVisible = true;
        draw (color);
    }
    
    /**
     *  Oculta el rectángulo del Canvas
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     *  Dibuja el rectángulo en el Canvas si está visible
     */
    private void draw(String color)
    {
        if (isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.Rectangle(x,y, width, height));
            canvas.wait(10);
        }
    }
    
    /**
     * Borra el rectángulo del Canvas si está visible
     */
    private void erase()
    {
        if (isVisible)
        {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
