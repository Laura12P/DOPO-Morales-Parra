import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para Tower - Ciclo 2.
 * Modo invisible. 2 pruebas por método: debería / no debería.
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class TowerCC2Test
{
    /**
     * Default constructor for test class TowerCC2Test
     */
    public TowerCC2Test()
    {
    }

    @Test
    public void shouldCreateCups() {
        Tower t = new Tower(4);
        assertEquals(4, t.stackingItems().length);
    }

    @Test
    public void shouldNotAcceptZeroCups() {
        Tower t = new Tower(4);
        t.pushCup(0);
        assertFalse(t.ok(' '));
    }   

    @Test
    public void swapShouldExchange() {
        Tower t = new Tower(7, 50);
        t.pushCup(1);
        t.pushCup(2);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "2"});
        assertTrue(t.ok(' '));
        assertEquals("2", t.stackingItems()[0][1]);
        assertEquals("1", t.stackingItems()[1][1]);
    }

    @Test
    public void  swapFails() {
        Tower t = new Tower(7, 50);
        t.pushCup(1);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "99"});
        assertFalse(t.ok(' '));
    }
}
