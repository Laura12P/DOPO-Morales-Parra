package Tower;

import Shapes.*;
import java.util.ArrayList;
/**
 * La clase Crazy es una subclase de Lid, esta en vez de tapar a su correspondiente taza se pone en la base de esta, cada vez que traten de taparla ya sea con
 * cover o swap o los dos tipos de order.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Crazy extends Lid {
    /**
     * Constructor for objects of class Crazy
     */
    public Crazy(int number) {
        super(number);
    }

    /**
     * En lugar de apilarse arriba, se ubica en la base de la torre (posicion 0).
     * 
     * @param stack El stack actual de la torre.
     */
    @Override
    public void addToStack(ArrayList<Element> stack) {
        stack.add(0, this);
    }
    
    @Override
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scale) {
        Rectangle stripe = new Rectangle(scaledWidth, Math.max(scale / 4, 2));
        stripe.setPosition(posicionX, scaledY + (scale / 2));
        stripe.makeVisible("yellow");
    }
}
