package Tower;

/**
 * Representa un elemento base del sistema. Contiene el número identificador y sus dimensiones (ancho y alto), es la superclase de lid y cup.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public abstract class Element {
    protected int number;
    protected int width;
    protected int height;

    /**
     * Crea un Element con número y dimensiones especificadas.
     *
     * @param number Numero identificador del elemento.
     * @param width Ancho del elemento.
     * @param height Altura del elemento.
     */
    public Element(int number, int width, int height)
    {
        this.number = number;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Retorna el numero identificador del Element.
     * 
     * @return int - Número identificador.
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Retorna el ancho del Element.
     * 
     * @return int - Ancho del Element.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Retorna la altura del Element.
     * 
     * @return int - Altura del Element.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Genera la tupla del Element, que especifica su subclase y su ancho.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos de un Element, para ser procesado en la simulacion.
     */
    public abstract String[] Tuple();
    
    /**
     * Genera la tupla identificadora del Element, que especifica su subclase y su numero identificador.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos generales de un Element.
     */
    public abstract String[] identifierTuple();
}
