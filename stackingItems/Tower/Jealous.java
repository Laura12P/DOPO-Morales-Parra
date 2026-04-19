package Tower;

import Shapes.*;
import java.util.ArrayList;
/**
 * La clase Jealous es una subclase de Lid, si esta encima de una tapa que esta tapando a una taza, entonces elimina a todo el conjunto de la taza tapada.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Jealous extends Lid {
    /**
     * Constructor for objects of class Fearful
     */
    public Jealous(int number) {
        super(number);
    }
    
    @Override
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scale) {
        Rectangle stripe = new Rectangle(scaledWidth, Math.max(scale / 4, 2));
        stripe.setPosition(posicionX, scaledY + (scale / 2));
        stripe.makeVisible("#00FFFF");
    }
}
