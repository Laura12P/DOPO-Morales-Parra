
/**
 * Representa un elemento base del sistema. Contiene el número identificador y sus dimensiones (ancho y alto), es la superclase de lid y cup.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Elemento
{
    private int number;
    private int width;      
    private int height;     

    /**
     * Crea un elemento con número y dimensiones especificadas.
     *
     * @param number Numero identificador del elemento.
     * @param width Ancho del elemento.
     * @param height Altura del elemento.
     */
    public Elemento(int number, int width, int height)
    {
        this.number = number;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Retorna el numero identificador del elemento.
     * 
     * @return int - Número identificador.
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * Retorna el ancho del elemento.
     * 
     * @return int - Ancho del elemento.
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Retorna la altura del elemento.
     * 
     * @return int - Altura del elemento.
     */
    public int getHeight()
    {
        return height;
    }
}