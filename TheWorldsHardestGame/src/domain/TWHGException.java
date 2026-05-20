package domain;

/* La clase TWHGException es la clase encargada de los mensajes de las excepciones propias del proyecto.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class TWHGException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_LOADING_THE_LEVEL = "Ocurrio un error al tratar de cargar el nivel solicitado.";
	
	public TWHGException(String message) {
		super(message);
	}
}