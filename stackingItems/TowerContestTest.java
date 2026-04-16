import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para TowerContest.solve(n, h).
 *
 * @author Daniel Santiago Morales Perdomo
 * @author Laura Juliana Parra Velandia
 * @version (27/03/26)
 */
public class TowerContestTest {

    private TowerContest contest;

    @Before
    public void setUp() {
        contest = new TowerContest();
    }

    /**
     * Test 1 - Caso de muestra oficial 1 del enunciado.
     * n=4 tazas, h=9: resultado esperado "7 3 5 1".
     * La taza grande va primero, cup2 sale afuera, cup3 y cup1 quedan adentro.
     */
    @Test
    public void testSampleCase1() {
        String result = contest.solve(4, 9);
        assertEquals("7 3 5 1", result);
    }

    /**
     * Test 2 - h muy superior al maximo alcanzable.
     * n=4 tazas, h=100: imposible porque max(n=4) = 13.
     */
    @Test
    public void testHGreaterThanMaximum() {
        String result = contest.solve(4, 100);
        assertEquals("impossible", result);
    }

    /**
     * Test 3 - n=1, unica taza, unica altura posible.
     * Con una sola taza de altura 1, la unica salida valida es "1".
     */
    @Test
    public void TestaCupHeightValid() {
        String result = contest.solve(1, 1);
        assertEquals("1", result);
    }

    /**
     * Test 4 - n=1, altura imposible.
     * Con una sola taza la altura siempre es 1; cualquier otro valor es imposible.
     */
    @Test
    public void testOneTazaHeightImpossible() {
        String result = contest.solve(1, 2);
        assertEquals("impossible", result);
    }

    /**
     * Test 5 - n=2, unica altura posible.
     * Con 2 tazas: min = max = 3 (cup2 siempre contiene a cup1).
     * Solo h=3 es valido; resultado: "3 1".
     */
    @Test
    public void testTwoCupsUniqueHeight() {
        String result = contest.solve(2, 3);
        assertEquals("3 1", result);
    }

    /**
     * Test 6 - n=3, altura minima (todas las tazas anidadas).
     * h=5 es el minimo para n=3; ninguna taza se saca afuera.
     * Resultado esperado: "5 3 1".
     */
    @Test
    public void testMinimumHeightN3() {
        String result = contest.solve(3, 5);
        assertEquals("5 3 1", result);
    }

    /**
     * Test 7 - Extra impar: siempre imposible.
     * n=3, h=6: extra = 6-5 = 1 (impar), por lo que no existe solucion.
     */
    @Test
    public void testExtraImparImposible() {
        String result = contest.solve(3, 6);
        assertEquals("impossible", result);
    }

    /**
     * Test 8 - n=4, altura minima (todas anidadas).
     * h=7 es el minimo para n=4; ninguna taza se saca afuera.
     * Resultado esperado: "7 5 3 1".
     */
    @Test
    public void testMinimumHeightN4() {
        String result = contest.solve(4, 7);
        assertEquals("7 5 3 1", result);
    }

    /**
     * Test 9 - n=5, altura valida con tazas "pulled".
     * h=11: extra=2. La taza cup2 (contribucion=2) sale afuera.
     * Resultado esperado: "9 3 7 5 1".
     */
    @Test
    public void testHeightValidWithPulledN5() {
        String result = contest.solve(5, 11);
        assertEquals("9 3 7 5 1", result);
    }

    /**
     * Test 10 - h=0: siempre imposible para cualquier n.
     * La altura minima posible es siempre >= 1, por lo que h=0 es invalido.
     */
    @Test
    public void testHCeroAlwaysImpossible() {
        String result = contest.solve(4, 0);
        assertEquals("impossible", result);
    }
}
