package Tower;

import Shapes.*;
import java.util.ArrayList;
/**
 * La clase Hierarchical es una subclase de Cup, si el elemento debajo de esta taza, es de menor tamaño que ella, la pone encima suyo, si llega a tocar el fondo
 * de la torre, no se deja mover, pero se puede eliminar en cualquier momento.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Hierarchical extends Cup {
    /**
     * Constructor for objects of class Hierarchical
     * @param number Numero identificador de la taza.
     */
    public Hierarchical(int number) {
        super(number);
    }

    /**
     * Una taza hierarchical no se puede quitar si esta en el fondo de la torre
     * 
     * @param positionInStack Posicion de la taza en el stack.
     * @return boolean - false si esta en el fondo, true en cualquier otro caso. 
     */
    @Override
    public boolean canBeRemoved (int positionInStack){
        return positionInStack !=0;
    }
    
    /**
     * Al entrar a la torre, dezplaza hacia arriba todos los elementos de menor tamaño que ella
     * 
     * @param stack El stack actual de la torre
     */
    @Override
    public void onPush (ArrayList<Element> stack){
        int myWidth = this.getWidth();
        // Recolecta los elementos mas pequelos que estan debajo
        ArrayList <Element> toMove = new ArrayList <Element>();
        for (int i = stack.size() -2; i >= 0; i--){
            Element e = stack.get(i);
            if(e.getWidth() < myWidth){
                toMove.add (0,e);
                stack.remove (i);
            } else {
                break;
            }
        }
        
        for(Element e : toMove){
            stack.add(e);
        }
    }
    
    @Override
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scaledHeight, int scale) {
        Rectangle stripe = new Rectangle(scaledWidth, scale / 2);
        stripe.setPosition(posicionX, scaledY + scaledHeight - (scale / 2));
        stripe.makeVisible("blue");
    }
}
