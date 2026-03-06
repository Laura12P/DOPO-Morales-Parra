
/**
 * Representa un elemento base del sistema. Contiene el número identificador y sus dimensiones (ancho y alto), es la superclase de lid y cup.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.1
 */
public class Elemento
{
    private int number;
    private int width;      
    private int height;     

    /**
     * Crea un elemento con número y dimensiones especificadas.
     *
     * @param number numero identificador del elemento
     * @param width ancho del elemento
     * @param height altura del elemento
     */
    public Elemento(int number, int width, int height)
    {
        this.number = number;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Retorna el numero identificador del elemento
     * 
     * @return int - número identificador
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * Retorna el ancho del elemento
     * 
     * @return int - ancho del elemento
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Retorna la altura del elemento
     * 
     * @return int - altura del elemento
     */
    public int getHeight()
    {
        return height;
    }
}