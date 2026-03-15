
/**
 * La clase tapa (Lid) es una subclase de elemento, su tamaño se calcula en función del número identificador, su altura es siempre 1.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Lid extends Elemento
{
    /**
     * Crea una tapa de altura 1, y de ancho calculado a partir de su numero identificador.
     * 
     * @param number Numero entero positivo, que es un identificador y es usado para calcular el ancho de la tapa.
     */
    public Lid(int number)
    {
        super(number, (2 * number) - 1, 1);
    }
}