package Tower;


import java.util.ArrayList;
/**
 * La clase tapa (Lid) es una subclase de Element, su tamaño se calcula en función del número identificador, su altura es siempre 1.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Lid extends Element {
    /**
     * Crea una tapa de altura 1, y de ancho calculado a partir de su numero identificador.
     * 
     * @param number Numero entero positivo, que es un identificador y es usado para calcular el ancho de la tapa.
     */
    public Lid(int number) {
        super(number, (2 * number) - 1, 1);
    }
    
    /**
     * Genera la tupla de una Lid, que especifica su subclase, y su ancho.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos de la Lid, para ser procesada en la simulacion.
     */
    public String[] Tuple() {
        String[] tuple = {"L",String.valueOf(width)};
        return tuple;
    }
    
    /**
     * Genera la tupla identificadora del Element, que especifica su subclase y su numero identificador.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos generales de un Element.
     */
    public String[] identifierTuple() {
        String[] identifierTuple = {"lid",String.valueOf(this.number)};
        return identifierTuple;
    }
    
    /**
     * Indica si la tapa puede entrar a la torre. Una tapa normal siempre puede entrar.
     * 
     * @param stack El stack actual de la torre.
     * @param number Numero identificador de la tapa.
     * @return boolean - true siempre para una tapa normal.
     */
    public boolean canEnter(ArrayList<Element> stack, int number) {
        return true;
    }
    
    /**
     * Indica si la tapa puede salir de la torre. Una tapa normal siempre puede salir.
     * 
     * @param cupAndLidPositions Arreglo con las posiciones de la taza y tapa de igual numero.
     * @return boolean - true siempre para una tapa normal.
     */
    public boolean canExit(int[] cupAndLidPositions) {
        return true;
    }
    
    /**
     * Agrega la tapa al stack. Una tapa normal se agrega arriba.
     * 
     * @param stack El stack actual de la torre.
     */
    public void addToStack(ArrayList<Element> stack) {
        stack.add(this);
    }
    
    public void drawPattern(int posicionX, int scaledY, int scaledWidth, int scale) {
    
    }
}
