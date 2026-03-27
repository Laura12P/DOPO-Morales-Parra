import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para Tower, cuenta con 2 pruebas minimo por método: debería / no debería, ademas de pruebas adicionales de casos especiales.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class TowerTest {
    @BeforeEach
    public void prepare() {
        //Torre con 4 tazas y 2 tapas, donde dos de las tazas estan tapas, ambas con otros elementos dentro
        Tower doubleLidded = new Tower(7,13);
        doubleLidded.pushCup(3);
        doubleLidded.pushCup(4);
        doubleLidded.pushCup(2);
        doubleLidded.pushCup(1);
        doubleLidded.pushLid(2);
        doubleLidded.pushLid(4);
        
        //Torre con solo 5 tazas
        Tower onlyCups = new Tower(9,10);
        onlyCups.pushCup(5);
        onlyCups.pushCup(4);
        onlyCups.pushCup(2);
        onlyCups.pushCup(3);
        onlyCups.pushCup(1);
        
        //Torre con 3 tazas y 1 tapa, donde los elementos dentro de la taza mas grande alcanzan la altura exacta del interior
        Tower perfectlyLidded = new Tower(7,8);
        perfectlyLidded.pushCup(4);
        perfectlyLidded.pushCup(1);
        perfectlyLidded.pushCup(3);
        perfectlyLidded.pushLid(4);
        
        //Torre con 5 tazas y 2 tapas, donde varias tazas tienen distintos elementos dentro en distinto orden.
        Tower bigCase = new Tower(9,10);
        bigCase.pushCup(5);
        bigCase.pushCup(4);
        bigCase.pushCup(2);
        bigCase.pushCup(3);
        bigCase.pushCup(1);
        bigCase.pushLid(2);
        bigCase.pushLid(1);
        
        //Probamos el comportamiento de un mismo numero de taza y tapa colocados correctamente
        Tower lidCup = new Tower(1,2);
        
    }
    
    @Test
    public void shouldCreateCups() {
        Tower t = new Tower(4);
        assertEquals(4, t.stackingItems().length);
    }
    
    @Test
    public void shouldNotCreateCups() {
        Tower t = new Tower(4);
        t.pushCup(0);
        assertFalse(t.ok());
    }   

    @Test
    public void swapShouldExchange() {
        Tower t = new Tower(7, 50);
        t.pushCup(1);
        t.pushCup(2);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "2"});
        assertTrue(t.ok());
        assertEquals("2", t.stackingItems()[0][1]);
        assertEquals("1", t.stackingItems()[1][1]);
    }

    @Test
    public void  swapFails() {
        Tower t = new Tower(7, 50);
        t.pushCup(1);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "99"});
        assertFalse(t.ok());
    }
}
