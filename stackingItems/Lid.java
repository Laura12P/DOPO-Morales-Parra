
/**
 * La clase tapa (Lid) es una subclase de Element, su tamaño se calcula en función del número identificador, su altura es siempre 1.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Lid extends Element
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
    
    /**
     * Genera la tupla de una Lid, que especifica su subclase, y su ancho.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos de la Lid, para ser procesada en la simulacion.
     */
    public String[] Tuple() {
        String[] tuple = {"L",String.valueOf(width)};
        return tuple;
    }
}