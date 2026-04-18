import java.util.ArrayList;
/**
 * La clase Opener es una subclase de Cup, si esta encima de una tapa, la borrara hasta llegar a posicionarse sobre otra taza o el fondo de la torre.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo 
 * @version 1.3
 */
public class Opener extends Cup {
    
    /**
     * Constructor for objects of class Opener
     */
    public Opener(int number) {
        super(number);
    }

    /**
     * Al entrar a la torre, elimina todas las tapas que son mas pequeñas que ella y estan debajo.
     * 
     * @param stack El stack actual de la torre
     */
    @Override
    public void onPush(ArrayList<Element> stack) {
        int myWidth = this.getWidth();
        int myIndex = stack.indexOf(this);
        for (int i = myIndex - 1; i >= 0; i--) {
            Element e = stack.get(i);
            if (e instanceof Lid && e.getWidth() < myWidth) {
                stack.remove(i);
            }
        }
    }
    
    @Override
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scaledHeight, int scale) {
        Rectangle stripe = new Rectangle(scaledWidth, scale / 2);
        stripe.setPosition(posicionX, scaledY + scaledHeight - (scale / 2));
        stripe.makeVisible("red");
    }
}
