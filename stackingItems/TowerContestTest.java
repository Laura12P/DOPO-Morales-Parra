import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para TowerContest
 *
 * @author  Daniel Santiago Morales Perdomo, Laura Juliana Parra Velandia
 */
public class TowerContestTest
{
    private TowerContest contest;
    /**
     * Default constructor for test class TowerContestTest
     */
    public TowerContestTest()
    {
    }

    /**
     *
     */
    @BeforeEach
    public void prepare()
    {
        contest = new TowerContest ();
    }

     @Test
    public void shouldSolveKnownCase() {
        String result = contest.solve(4, 9);
        assertNotNull(result);
        assertNotEquals("impossible", result);
    }
 
    @Test
    public void shouldSolveForOneCup() {
        String result = contest.solve(1, 1);
        assertEquals("1", result);
    }
 
    @Test
    public void shouldSolveMinimumHeight() {
        String result = contest.solve(4, 7);
        assertNotNull(result);
        assertNotEquals("impossible", result);
    }
 
    @Test
    public void shouldSolveMaximumHeight() {
        String result = contest.solve(4, 16);
        assertNotNull(result);
        assertNotEquals("impossible", result);
    }
}