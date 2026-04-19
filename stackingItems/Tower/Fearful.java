package Tower;

import Shapes.*;
import java.util.ArrayList;
/**
 * La clase Fearful es una subclase de Lid, si su numero de taza no esta en la torre, no se le permitira ingresar a la misma, ademas si llega a tapar su taza de
 * igual numero, no podra ser eliminada, la unica forma de borrarla mientras tapa a su taza es eliminando a su taza.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Fearful extends Lid {
    /**
     * Constructor for objects of class Fearful
     */
    public Fearful(int number) {
        super(number);
    }

    /**
     *  Una tapa fearful no puede entrar si su taza compañera no esta en el stack.
     * 
     * @param stack El stack actual de la torre.
     * @param number Numero identificador de la tapa.
     * @return boolean  true si su taza compañera esta en el stack, false en caso contrario.
     */
    @Override
    public boolean canEnter(ArrayList<Element> stack, int number) {
        for (Element e : stack) {
            if (e instanceof Cup && e.getNumber() == number) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Una tapa fearful no puede salir si esta tapando a su taza.
     * 
     * @param cupAndLidPositions Arreglo con las posiciones de la taza y tapa de igual numero.
     * @return boolean - false si esta tapando a su taza, true en caso contrario.
     */
    @Override
    public boolean canExit(int[] cupAndLidPositions) {
        return cupAndLidPositions[2] != 1;
    }
    
    @Override
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scale) {
        Rectangle stripe = new Rectangle(scaledWidth, Math.max(scale / 4, 2));
        stripe.setPosition(posicionX, scaledY + (scale / 2));
        stripe.makeVisible("#8B4513");
    }
}
