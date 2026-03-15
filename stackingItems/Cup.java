
/**
 * La clase taza (cup) es una subclase de elemento, su tamaño se calcula en función del número identificador.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Cup extends Elemento
{
    /**
     * Crea una taza con altura y ancho calculado a partir de su numero identificador.
     * 
     * @param number Numero entero positivo, que es un identificador y es usado para calcular las dimensiones de la taza.
     */
    public Cup(int number)
    {
        super(number, (2 * number) - 1, (2 * number) - 1);
    }
}