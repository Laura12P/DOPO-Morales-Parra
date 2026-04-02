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
    private Tower doubleLidded;
    private Tower doubleLiddedOrdered;
    private Tower doubleLiddedReverse;
    private Tower onlyCups;
    private Tower onlyCupsOrdered;
    private Tower onlyCupsReverse;
    private Tower perfectlyLidded;
    private Tower perfectlyLiddedOrdered;
    private Tower perfectlyLiddedReverse;
    private Tower bigCase;
    private Tower bigCaseOrdered;
    private Tower bigCaseReverse;
    private Tower cupLidOne;
    private Tower cupLidOneOrdered;
    private Tower cupLidOneReverse;
    private Tower lidCupOne;
    private Tower lidCupOneOrdered;
    private Tower lidCupOneReverse;
    private Tower cupOverLid;
    private Tower cupOverLidOrdered;
    private Tower cupOverLidReverse;
    private Tower cupLidLid;
    private Tower cupLidLidOrdered;
    private Tower cupLidLidReverse;
    private Tower lidLidCup;
    private Tower lidLidCupOrdered;
    private Tower lidLidCupReverse;
    private Tower cupLidInside;
    private Tower cupLidInsideOrdered;
    private Tower cupLidInsideReverse;
    private Tower lidCupInside;
    private Tower lidCupInsideOrdered;
    private Tower lidCupInsideReverse;
    private Tower smallerLidOverInside;
    private Tower smallerLidOverInsideOrdered;
    private Tower smallerLidOverInsideReverse;
    private Tower biggerLidOverInside;
    private Tower biggerLidOverInsideOrdered;
    private Tower biggerLidOverInsideReverse;
    private Tower perfectlyLiddedInside;
    private Tower perfectlyLiddedInsideOrdered;
    private Tower perfectlyLiddedInsideReverse;
    
    @BeforeEach
    public void prepare() {
        //Torre con 4 tazas y 2 tapas, donde dos de las tazas estan tapas, ambas con otros elementos dentro
        doubleLidded = new Tower(7,18);
        doubleLidded.pushCup(3);
        doubleLidded.pushCup(4);
        doubleLidded.pushCup(2);
        doubleLidded.pushCup(1);
        doubleLidded.pushLid(2);
        doubleLidded.pushLid(4);
        
        //doubleLidded despues de aplicar el orderTower()
        doubleLiddedOrdered = new Tower(7,18);
        doubleLiddedOrdered.pushCup(4);
        doubleLiddedOrdered.pushLid(4);
        doubleLiddedOrdered.pushCup(3);
        doubleLiddedOrdered.pushCup(2);
        doubleLiddedOrdered.pushLid(2);
        doubleLiddedOrdered.pushCup(1);
        
        //doubleLidded despues de aplicar el reverseTower()
        doubleLiddedReverse = new Tower(7,18);
        doubleLiddedReverse.pushCup(1);
        doubleLiddedReverse.pushCup(2);
        doubleLiddedReverse.pushLid(2);
        doubleLiddedReverse.pushCup(3);
        doubleLiddedReverse.pushCup(4);
        doubleLiddedReverse.pushLid(4);        
        
        //Torre con solo 5 tazas
        onlyCups = new Tower(9,25);
        onlyCups.pushCup(5);
        onlyCups.pushCup(4);
        onlyCups.pushCup(2);
        onlyCups.pushCup(3);
        onlyCups.pushCup(1);
        
        //onlyCups despues de aplicar el orderTower()
        onlyCupsOrdered = new Tower(9,25);
        onlyCupsOrdered.pushCup(5);
        onlyCupsOrdered.pushCup(4);
        onlyCupsOrdered.pushCup(3);
        onlyCupsOrdered.pushCup(2);
        onlyCupsOrdered.pushCup(1);
        
        //onlyCups despues de aplicar el reverseTower()
        onlyCupsReverse = new Tower(9,25);
        onlyCupsReverse.pushCup(1);
        onlyCupsReverse.pushCup(2);
        onlyCupsReverse.pushCup(3);
        onlyCupsReverse.pushCup(4);
        onlyCupsReverse.pushCup(5);
        
        //Torre con 3 tazas y 1 tapa, donde los elementos dentro de la taza mas grande alcanzan la altura exacta del interior
        perfectlyLidded = new Tower(7,14);
        perfectlyLidded.pushCup(4);
        perfectlyLidded.pushCup(1);
        perfectlyLidded.pushCup(3);
        perfectlyLidded.pushLid(4);
        
        //perfectlyLidded despues de aplicar el orderTower()
        perfectlyLiddedOrdered = new Tower(7,14);
        perfectlyLiddedOrdered.pushCup(4);
        perfectlyLiddedOrdered.pushLid(4);
        perfectlyLiddedOrdered.pushCup(3);
        perfectlyLiddedOrdered.pushCup(1);
        
        //perfectlyLidded despues de aplicar el reverseTower()
        perfectlyLiddedReverse = new Tower(7,14);
        perfectlyLiddedReverse.pushCup(1);
        perfectlyLiddedReverse.pushCup(3);
        perfectlyLiddedReverse.pushCup(4);
        perfectlyLiddedReverse.pushLid(4);
        
        //Torre con 5 tazas y 2 tapas, donde varias tazas tienen distintos elementos dentro en distinto orden.
        bigCase = new Tower(9,27);
        bigCase.pushCup(5);
        bigCase.pushCup(4);
        bigCase.pushCup(2);
        bigCase.pushCup(3);
        bigCase.pushCup(1);
        bigCase.pushLid(2);
        bigCase.pushLid(1);
        
        //bigCase despues de aplicar orderTower()
        bigCaseOrdered = new Tower(9,27);
        bigCaseOrdered.pushCup(5);
        bigCaseOrdered.pushCup(4);
        bigCaseOrdered.pushCup(3);
        bigCaseOrdered.pushCup(2);
        bigCaseOrdered.pushLid(2);
        bigCaseOrdered.pushCup(1);
        bigCaseOrdered.pushLid(1);
        
        //bigCase despues de aplicar reverseTower()
        bigCaseReverse = new Tower(9,27);
        bigCaseReverse.pushCup(1);
        bigCaseReverse.pushLid(1);
        bigCaseReverse.pushCup(2);
        bigCaseReverse.pushLid(2);
        bigCaseReverse.pushCup(3);
        bigCaseReverse.pushCup(4);
        bigCaseReverse.pushCup(5);
        
        //Probamos el comportamiento de la taza y tapa con numero identificador 1, colocados correctamente
        cupLidOne = new Tower(1,2);
        cupLidOne.pushCup(1);
        cupLidOne.pushLid(1);
        
        //cupLidOne despues de aplicar orderTower()
        cupLidOneOrdered = new Tower(1,2);
        cupLidOneOrdered.pushCup(1);
        cupLidOneOrdered.pushLid(1);
        
        //cupLidOne despues de aplicar reverseTower()
        cupLidOneReverse = cupLidOneOrdered;
        
        //Probamos el comportamiento de la taza y tapa con numero identificador 1, colocados de forma inversa
        lidCupOne = new Tower(1,2);
        lidCupOne.pushLid(1);
        lidCupOne.pushCup(1);
        
        //LidCupOne despues de aplicar orderTower()
        lidCupOneOrdered = cupLidOneOrdered;
        
        //LidCupOne despues de aplicar reverseTower()
        lidCupOneReverse = cupLidOneReverse;
        
        //Probamos el comportamiento de colocar una taza sobre una tapa
        cupOverLid = new Tower(5,4);
        cupOverLid.pushLid(3);
        cupOverLid.pushCup(2);
        
        //cupOverLid despues de aplicar orderTower()
        cupOverLidOrdered = new Tower(5,4);
        cupOverLidOrdered.pushLid(3);
        cupOverLidOrdered.pushCup(2);
        
        //cupOverLid despues de aplicar reverseTower()
        cupOverLidReverse = new Tower(5,4);
        cupOverLidReverse.pushCup(2);
        cupOverLidReverse.pushLid(3);
        
        //Probamos el comportamiento de dos tapas y una taza, donde una tapa siempre esta en medio de una taza y una tapa
        cupLidLid = new Tower(3,3);
        cupLidLid.pushCup(1);
        cupLidLid.pushLid(2);
        cupLidLid.pushLid(1);
        
        //cupLidLid despues de aplicar orderTower()
        cupLidLidOrdered = new Tower(3,3);
        cupLidLidOrdered.pushLid(2);
        cupLidLidOrdered.pushCup(1);
        cupLidLidOrdered.pushLid(1);
        
        //cupLidLid despues de aplicar reverseTower()
        cupLidLidReverse = new Tower(3,3);
        cupLidLidReverse.pushCup(1);
        cupLidLidReverse.pushLid(1);
        cupLidLidReverse.pushLid(2);
        
        //Probamos el comportamiento de dos tapas y una taza, donde una tapa siempre esta en medio de una tapa y una taza
        lidLidCup = new Tower(3,3);
        lidLidCup.pushLid(1);
        lidLidCup.pushLid(2);
        lidLidCup.pushCup(1);
        
        //lidLidCup despues de aplicar orderTower()
        lidLidCupOrdered = cupLidLidOrdered;
        
        //lidLidCup despues de aplicar reverseTower()
        lidLidCupReverse = cupLidLidReverse;
        
        //Probamos el comportamiento de una taza que contiene otra taza con una tapa mas grande encima
        cupLidInside = new Tower(5,7);
        cupLidInside.pushCup(3);
        cupLidInside.pushCup(1);
        cupLidInside.pushLid(2);
        
        //cupLidInside despues de aplicar orderTower()
        cupLidInsideOrdered = new Tower(5,7);
        cupLidInsideOrdered.pushCup(3);
        cupLidInsideOrdered.pushLid(2);
        cupLidInsideOrdered.pushCup(1);
        
        //cupLidInside despues de aplicar reverseTower()
        cupLidInsideReverse = new Tower(5,7);
        cupLidInsideReverse.pushCup(1);
        cupLidInsideReverse.pushLid(2);
        cupLidInsideReverse.pushCup(3);
        
        //Probamos el comportamiento de una taza que contiene una tapa con una taza mas pequeña encima
        lidCupInside = new Tower(5,7);
        lidCupInside.pushCup(3);
        lidCupInside.pushLid(2);
        lidCupInside.pushCup(1);
        
        //lidCupInside despues de aplicar orderTower()
        lidCupInsideOrdered = cupLidInsideOrdered;
        
        //lidCupInside despues de aplicar reverseTower()
        lidCupInsideReverse = cupLidInsideReverse;
        
        //Probamos el comportamiento de una taza que contiene a dos tapas, la tapa mas pequeña encima de la tapa mas grande
        smallerLidOverInside = new Tower(5,7);
        smallerLidOverInside.pushCup(3);
        smallerLidOverInside.pushLid(2);
        smallerLidOverInside.pushLid(1);
        
        //smallerLidOverInside despues de aplicar orderTower()
        smallerLidOverInsideOrdered = new Tower(5,7);
        smallerLidOverInsideOrdered.pushCup(3);
        smallerLidOverInsideOrdered.pushLid(2);
        smallerLidOverInsideOrdered.pushLid(1);
        
        //smallerLidOverInside despues de aplicar reverseTower()
        smallerLidOverInsideReverse = new Tower(5,7);
        smallerLidOverInsideReverse.pushLid(1);
        smallerLidOverInsideReverse.pushLid(2);
        smallerLidOverInsideReverse.pushCup(3);
        
        //Probamos el comportamiento de una taza que contiene a dos tapas, la tapa mas grande encima de la tapa mas pequeña
        biggerLidOverInside = new Tower(5,7);
        biggerLidOverInside.pushCup(3);
        biggerLidOverInside.pushLid(1);
        biggerLidOverInside.pushLid(2);
        
        //biggerLidOverInside despues de aplicar el orderTower()
        biggerLidOverInsideOrdered = smallerLidOverInsideOrdered;
        
        //biggerLidOverInside despues de aplicar el reverseTower()
        biggerLidOverInsideReverse = smallerLidOverInsideReverse;
        
        //Probamos el comportamiento de una taza que contiene a una taza con su respectiva tapa, y que ocupan la altura exacta del interior
        perfectlyLiddedInside = new Tower(5,9);
        perfectlyLiddedInside.pushCup(3);
        perfectlyLiddedInside.pushCup(2);
        perfectlyLiddedInside.pushLid(2);
        
        //perfectlyLiddedInside despues de aplicar orderTower()
        perfectlyLiddedInsideOrdered = new Tower(5,9);
        perfectlyLiddedInsideOrdered.pushCup(3);
        perfectlyLiddedInsideOrdered.pushCup(2);
        perfectlyLiddedInsideOrdered.pushLid(2);
        
        //perfectlyLiddedInside despues de aplicar reverseTower()
        perfectlyLiddedInsideReverse = new Tower(5,9);
        perfectlyLiddedInsideReverse.pushCup(2);
        perfectlyLiddedInsideReverse.pushLid(2);
        perfectlyLiddedInsideReverse.pushCup(3);
    }
    
    @Test
    public void shouldCreateCups() {
        Tower t = new Tower(4);
        assertEquals(4, t.stackingItems().length);
    }
    
    @Test //Esta es  la clase que suelta el mensaje de JOption Pane, de que el numero debe ser mayor a 1, osea es correcto que salga el JOption Pane
    public void shouldNotCreateCups() {
        Tower t = new Tower(4);
        t.pushCup(0);
        assertFalse(t.ok());
    }
    
    @Test
    public void shouldBeCorrectHeightDoubleLidded() {
        Tower t = new Tower(7,13);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushCup(4);
            if (t.height() != 12) correct = false;
        }
        if (correct) {
            t.pushCup(2);
            if (t.height() != 12) correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 12) correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 12) correct = false;
        }
        if (correct) {
            t.pushLid(4);
            if (t.height() != 13) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightOnlyCups() {
        Tower t = new Tower(9,10);
        boolean correct = true;
        t.pushCup(5);
        if (t.height() != 9) {
            correct = false;
        }
        if (correct) {
            t.pushCup(4);
            if (t.height() != 9) correct = false;
        }
        if (correct) {
            t.pushCup(2);
            if (t.height() != 9) correct = false;
        }
        if (correct) {
            t.pushCup(3);
            if (t.height() != 10) correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 10) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightPerfectlyLidded() {
        Tower t = new Tower(7,8);
        boolean correct = true;
        t.pushCup(4);
        if (t.height() != 7) {
            correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 7) correct = false;
        }
        if (correct) {
            t.pushCup(3);
            if (t.height() != 7) correct = false;
        }
        if (correct) {
            t.pushLid(4);
            if (t.height() != 8) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightBigCase() {
        Tower t = new Tower(9,10);
        boolean correct = true;
        t.pushCup(5);
        if (t.height() != 9) {
            correct = false;
        }
        if (correct) {
            t.pushCup(4);
            if (t.height() != 9) correct = false;
        }
        if (correct) {
            t.pushCup(2);
            if (t.height() != 9) correct = false;
        }
        if (correct) {
            t.pushCup(3);
            if (t.height() != 10) correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 10) correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 10) correct = false;
        }
        if (correct) {
            t.pushLid(1);
            if (t.height() != 10) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidOne() {
        Tower t = new Tower(1,2);
        boolean correct = true;
        t.pushCup(1);
        if (t.height() != 1) {
            correct = false;
        }
        if (correct) {
            t.pushLid(1);
            if (t.height() != 2) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightLidCupOne() {
        Tower t = new Tower(1,2);
        boolean correct = true;
        t.pushLid(1);
        if (t.height() != 1) {
            correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 2) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightCupOverLid() {
        Tower t = new Tower(5,4);
        boolean correct = true;
        t.pushLid(3);
        if (t.height() != 1) {
            correct = false;
        }
        if (correct) {
            t.pushCup(2);
            if (t.height() != 4) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidLid() {
        Tower t = new Tower(3,3);
        boolean correct = true;
        t.pushCup(1);
        if (t.height() != 1) {
            correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 2) correct = false;
        }
        if (correct) {
            t.pushLid(1);
            if (t.height() != 3) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightLidLidCup() {
        Tower t = new Tower(3,3);
        boolean correct = true;
        t.pushLid(1);
        if (t.height() != 1) {
            correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 2) correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 3) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidInside() {
        Tower t = new Tower(5,5);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 5) correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightLidCupInside() {
        Tower t = new Tower(5,5);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 5) correct = false;
        }
        if (correct) {
            t.pushCup(1);
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightSmallLidOverInside() {
        Tower t = new Tower(5,5);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 5) correct = false;
        }
        if (correct) {
            t.pushLid(1);
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightBigLidOverInside() {
        Tower t = new Tower(5,5);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushLid(1);
            if (t.height() != 5) correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void shouldBeCorrectHeightPerfectlyLiddedInside() {
        Tower t = new Tower(5,5);
        boolean correct = true;
        t.pushCup(3);
        if (t.height() != 5) {
            correct = false;
        }
        if (correct) {
            t.pushCup(2);
            if (t.height() != 5) correct = false;
        }
        if (correct) {
            t.pushLid(2);
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterPopCupShouldBeCorrectHeightLidCupInside() {
        boolean correct = true;
        lidCupInside.popCup();
        if (lidCupInside.height() != 5) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterPopCupShouldBeCorrectHeightExtraCase() {
        Tower t = new Tower(5,6);
        boolean correct = true;
        t.pushCup(3);
        t.pushCup(2);
        t.pushLid(2);
        t.pushCup(1);
        if (t.height() != 6) {
            correct = false;
        }
        if (correct) {
            t.popCup();
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightCupLidInside() {
        boolean correct = true;
        cupLidInside.removeCup(3);
        if (cupLidInside.height() != 2) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightBigCase() {
        boolean correct = true;
        bigCase.removeCup(3);
        if (bigCase.height() != 9) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightLiddedEmptyCupCase() {
        boolean correct = true;
        doubleLiddedOrdered.removeCup(2);
        if (doubleLiddedOrdered.height() != 13) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightLiddedAndFilledCupCase() {
        boolean correct = true;
        doubleLidded.removeCup(2);
        if (doubleLidded.height() != 13 || doubleLidded.getStackSize() != 3) {
            correct = false;
        }
        if (correct) {
            doubleLidded.popLid();
            doubleLidded.pushCup(2);
            doubleLidded.pushCup(1);
            doubleLidded.pushLid(2);
            doubleLidded.pushLid(4);
            doubleLidded.removeCup(4);
            if (doubleLidded.height() != 5 || doubleLidded.getStackSize() != 1) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterPopLidShouldBeCorrectHeightCupLidInside() {
        boolean correct = true;
        cupLidInside.popLid();
        if (cupLidInside.height() != 5) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterPopLidShouldBeCorrectHeightExtraCase() {
        Tower t = new Tower(5,6);
        boolean correct = true;
        t.pushCup(3);
        t.pushCup(2);
        t.pushLid(2);
        t.pushLid(1);
        if (t.height() != 6) {
            correct = false;
        }
        if (correct) {
            t.popLid();
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveLidShouldBeCorrectHeightCupLidInside() {
        boolean correct = true;
        cupLidInside.popLid();
        if (cupLidInside.height() != 5) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterRemoveLidShouldBeCorrectHeightExtraCase() {
        Tower t = new Tower(5,6);
        boolean correct = true;
        t.pushCup(3);
        t.pushLid(3);
        if (t.height() != 6) {
            correct = false;
        }
        if (correct) {
            t.popLid();
            if (t.height() != 5) correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualDoubleLidded() {
        doubleLidded.orderTower();
        boolean correct = true;
        if (!(doubleLidded.equals(doubleLiddedOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualOnlyCups() {
        onlyCups.orderTower();
        boolean correct = true;
        if (!(onlyCups.equals(onlyCupsOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualPerfectlyLidded() {
        perfectlyLidded.orderTower();
        boolean correct = true;
        if (!(perfectlyLidded.equals(perfectlyLiddedOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualBigCase() {
        bigCase.orderTower();
        boolean correct = true;
        if (!(bigCase.equals(bigCaseOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidOne() {
        cupLidOne.orderTower();
        boolean correct = true;
        if (!(cupLidOne.equals(cupLidOneOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidCupOne() {
        lidCupOne.orderTower();
        boolean correct = true;
        if (!(lidCupOne.equals(lidCupOneOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupOverLid() {
        cupOverLid.orderTower();
        boolean correct = true;
        if (!(cupOverLid.equals(cupOverLidOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidLid() {
        cupLidLid.orderTower();
        boolean correct = true;
        if (!(cupLidLid.equals(cupLidLidOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidLidCup() {
        lidLidCup.orderTower();
        boolean correct = true;
        if (!(lidLidCup.equals(lidLidCupOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidInside() {
        cupLidInside.orderTower();
        boolean correct = true;
        if (!(cupLidInside.equals(cupLidInsideOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidCupInside() {
        lidCupInside.orderTower();
        boolean correct = true;
        if (!(lidCupInside.equals(lidCupInsideOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualSmallerLidOverInside() {
        smallerLidOverInside.orderTower();
        boolean correct = true;
        if (!(smallerLidOverInside.equals(smallerLidOverInsideOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualBiggerLidOverInside() {
        biggerLidOverInside.orderTower();
        boolean correct = true;
        if (!(biggerLidOverInside.equals(biggerLidOverInsideOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualPerfectlyLiddedInside() {
        perfectlyLiddedInside.orderTower();
        boolean correct = true;
        if (!(perfectlyLiddedInside.equals(perfectlyLiddedInsideOrdered))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualDoubleLidded() {
        doubleLidded.reverseTower();
        boolean correct = true;
        if (!(doubleLidded.equals(doubleLiddedReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualOnlyCups() {
        onlyCups.reverseTower();
        boolean correct = true;
        if (!(onlyCups.equals(onlyCupsReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualPerfectlyLidded() {
        perfectlyLidded.reverseTower();
        boolean correct = true;
        if (!(perfectlyLidded.equals(perfectlyLiddedReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualBigCase() {
        bigCase.reverseTower();
        boolean correct = true;
        if (!(bigCase.equals(bigCaseReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidOne() {
        cupLidOne.reverseTower();
        boolean correct = true;
        if (!(cupLidOne.equals(cupLidOneReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidCupOne() {
        lidCupOne.reverseTower();
        boolean correct = true;
        if (!(lidCupOne.equals(lidCupOneReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupOverLid() {
        cupOverLid.reverseTower();
        boolean correct = true;
        if (!(cupOverLid.equals(cupOverLidReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidLid() {
        cupLidLid.reverseTower();
        boolean correct = true;
        if (!(cupLidLid.equals(cupLidLidReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidLidCup() {
        lidLidCup.reverseTower();
        boolean correct = true;
        if (!(lidLidCup.equals(lidLidCupReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidInside() {
        cupLidInside.reverseTower();
        boolean correct = true;
        if (!(cupLidInside.equals(cupLidInsideReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidCupInside() {
        lidCupInside.reverseTower();
        boolean correct = true;
        if (!(lidCupInside.equals(lidCupInsideReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualSmallerLidOverInside() {
        smallerLidOverInside.reverseTower();
        boolean correct = true;
        if (!(smallerLidOverInside.equals(smallerLidOverInsideReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualBiggerLidOverInside() {
        biggerLidOverInside.reverseTower();
        boolean correct = true;
        if (!(biggerLidOverInside.equals(biggerLidOverInsideReverse))) {
            correct = false;
        }
        assertTrue(correct);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualPerfectlyLiddedInside() {
        perfectlyLiddedInside.reverseTower();
        boolean correct = true;
        if (!(perfectlyLiddedInside.equals(perfectlyLiddedInsideReverse))) {
            correct = false;
        }
        assertTrue(correct);
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
    
    @Test
    public void testSwapToReduceReturnsSuggestion() {
        Tower t = new Tower(9, 20);
        t.pushCup(3);
        t.pushCup(5);
        t.pushLid(3);
        String[][] suggestion = t.swapToReduce();
        assertNotNull(suggestion);
        assertEquals(2, suggestion.length);
    }
    
    @Test
    public void shouldCoverAllCups() {
        Tower t = new Tower(7, 13);
        t.pushCup(3);
        t.pushCup(4);
        t.pushCup(2);
        t.pushCup(1);
        t.pushLid(2);
        t.pushLid(4);
        t.cover();
        assertTrue(t.ok());
    }
 
    @Test
    public void shouldNotSwapNonExistentCup() {
        Tower t = new Tower(7, 50);
        t.pushCup(1);
        t.swap(new String[]{"cup", "9"}, new String[]{"cup", "1"});
        assertFalse(t.ok());
    }
 
    @Test
    public void shouldStartWithNoLids() {
        Tower t = new Tower(5);
        int[] lided = t.lidedCups();
        assertNotNull(lided);
        assertEquals(0, lided.length);
    }    
    
    @Test
    public void testCoverNoPairsShouldNotChange() {
        Tower t = new Tower(9, 20);
        t.pushCup(4);
        t.pushLid(2);
        int h = t.height();
        t.cover();
        assertTrue(t.ok());
        assertEquals(h, t.height());
        assertEquals(0, t.lidedCups().length);
    }
}
