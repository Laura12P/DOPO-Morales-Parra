
/**
 * La clase taza (cup) es una subclase de Element, su tamaño se calcula en función del número identificador.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Cup extends Element {
    /**
     * Crea una taza con altura y ancho calculado a partir de su numero identificador.
     * 
     * @param number Numero entero positivo, que es un identificador y es usado para calcular las dimensiones de la taza.
     */
    public Cup(int number) {
        super(number, (2 * number) - 1, (2 * number) - 1);
    }
    
    /**
     * Genera la tupla de una Cup, que especifica su subclase, y su ancho.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos de la Cup, para ser procesada en la simulacion.
     */
    public String[] Tuple() {
        String[] tuple = {"C",String.valueOf(width)};
        return tuple;
    }
    
    /**
     * Genera la tupla identificadora del Element, que especifica su subclase y su numero identificador.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos generales de un Element.
     */
    public String[] identifierTuple() {
        String[] identifierTuple = {"cup",String.valueOf(this.number)};
        return identifierTuple;
    }
}