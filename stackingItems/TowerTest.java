import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

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
    private Tower specialSwap;
    private Tower specialSwapOrdered;
    private Tower specialSwapReverse;
    private Tower specialCover;
    private Tower specialCoverOrdered;
    private Tower specialCoverReverse;
    
    //Definimos variables para las pruebas de nuestros compañeros
    private Tower tower;
    
    @BeforeEach
    public void prepare() {
        //Torre con 4 tazas y 2 tapas, donde dos de las tazas estan tapas, ambas con otros elementos dentro, util para probar tazas con objetos dentro
        doubleLidded = new Tower(7,18);
        doubleLidded.pushCup("normal", 3);
        doubleLidded.pushCup("normal", 4);
        doubleLidded.pushCup("normal", 2);
        doubleLidded.pushCup("normal", 1);
        doubleLidded.pushLid("normal", 2);
        doubleLidded.pushLid("normal", 4);
        
        //doubleLidded despues de aplicar el orderTower()
        doubleLiddedOrdered = new Tower(7,18);
        doubleLiddedOrdered.pushCup("normal", 4);
        doubleLiddedOrdered.pushLid("normal", 4);
        doubleLiddedOrdered.pushCup("normal", 3);
        doubleLiddedOrdered.pushCup("normal", 2);
        doubleLiddedOrdered.pushLid("normal", 2);
        doubleLiddedOrdered.pushCup("normal", 1);
        
        //doubleLidded despues de aplicar el reverseTower()
        doubleLiddedReverse = new Tower(7,18);
        doubleLiddedReverse.pushCup("normal", 1);
        doubleLiddedReverse.pushCup("normal", 2);
        doubleLiddedReverse.pushLid("normal", 2);
        doubleLiddedReverse.pushCup("normal", 3);
        doubleLiddedReverse.pushCup("normal", 4);
        doubleLiddedReverse.pushLid("normal", 4);        
        
        //Torre con solo 5 tazas, util para probar funcionamiento de codigo ante solo tazas
        onlyCups = new Tower(9,25);
        onlyCups.pushCup("normal", 5);
        onlyCups.pushCup("normal", 4);
        onlyCups.pushCup("normal", 2);
        onlyCups.pushCup("normal", 3);
        onlyCups.pushCup("normal", 1);
        
        //onlyCups despues de aplicar el orderTower()
        onlyCupsOrdered = new Tower(9,25);
        onlyCupsOrdered.pushCup("normal", 5);
        onlyCupsOrdered.pushCup("normal", 4);
        onlyCupsOrdered.pushCup("normal", 3);
        onlyCupsOrdered.pushCup("normal", 2);
        onlyCupsOrdered.pushCup("normal", 1);
        
        //onlyCups despues de aplicar el reverseTower()
        onlyCupsReverse = new Tower(9,25);
        onlyCupsReverse.pushCup("normal", 1);
        onlyCupsReverse.pushCup("normal", 2);
        onlyCupsReverse.pushCup("normal", 3);
        onlyCupsReverse.pushCup("normal", 4);
        onlyCupsReverse.pushCup("normal", 5);
        
        //Torre con 3 tazas y 1 tapa, donde los elementos dentro de la taza mas grande alcanzan la altura exacta del interior, util para probar cambios internos
        //en la taza y que se mantenga tapada
        perfectlyLidded = new Tower(7,14);
        perfectlyLidded.pushCup("normal", 4);
        perfectlyLidded.pushCup("normal", 1);
        perfectlyLidded.pushCup("normal", 3);
        perfectlyLidded.pushLid("normal", 4);
        
        //perfectlyLidded despues de aplicar el orderTower()
        perfectlyLiddedOrdered = new Tower(7,14);
        perfectlyLiddedOrdered.pushCup("normal", 4);
        perfectlyLiddedOrdered.pushLid("normal", 4);
        perfectlyLiddedOrdered.pushCup("normal", 3);
        perfectlyLiddedOrdered.pushCup("normal", 1);
        
        //perfectlyLidded despues de aplicar el reverseTower()
        perfectlyLiddedReverse = new Tower(7,14);
        perfectlyLiddedReverse.pushCup("normal", 1);
        perfectlyLiddedReverse.pushCup("normal", 3);
        perfectlyLiddedReverse.pushCup("normal", 4);
        perfectlyLiddedReverse.pushLid("normal", 4);
        
        //Torre con 5 tazas y 2 tapas, donde varias tazas tienen distintos elementos dentro en distinto orden, util para probar metodos en general
        bigCase = new Tower(9,27);
        bigCase.pushCup("normal", 5);
        bigCase.pushCup("normal", 4);
        bigCase.pushCup("normal", 2);
        bigCase.pushCup("normal", 3);
        bigCase.pushCup("normal", 1);
        bigCase.pushLid("normal", 2);
        bigCase.pushLid("normal", 1);
        
        //bigCase despues de aplicar orderTower()
        bigCaseOrdered = new Tower(9,27);
        bigCaseOrdered.pushCup("normal", 5);
        bigCaseOrdered.pushCup("normal", 4);
        bigCaseOrdered.pushCup("normal", 3);
        bigCaseOrdered.pushCup("normal", 2);
        bigCaseOrdered.pushLid("normal", 2);
        bigCaseOrdered.pushCup("normal", 1);
        bigCaseOrdered.pushLid("normal", 1);
        
        //bigCase despues de aplicar reverseTower()
        bigCaseReverse = new Tower(9,27);
        bigCaseReverse.pushCup("normal", 1);
        bigCaseReverse.pushLid("normal", 1);
        bigCaseReverse.pushCup("normal", 2);
        bigCaseReverse.pushLid("normal", 2);
        bigCaseReverse.pushCup("normal", 3);
        bigCaseReverse.pushCup("normal", 4);
        bigCaseReverse.pushCup("normal", 5);
        
        //Probamos el comportamiento de la taza y tapa con numero identificador 1, colocados correctamente, primer caso mas base, taza y tapa 1 tapadas, esta 
        //para probar mas que todo los metodos que ofrecen informacion, como height, liddedCups, stacking Items, etc..
        cupLidOne = new Tower(1,2);
        cupLidOne.pushCup("normal", 1);
        cupLidOne.pushLid("normal", 1);
        
        //cupLidOne despues de aplicar orderTower()
        cupLidOneOrdered = new Tower(1,2);
        cupLidOneOrdered.pushCup("normal", 1);
        cupLidOneOrdered.pushLid("normal", 1);
        
        //cupLidOne despues de aplicar reverseTower()
        cupLidOneReverse = cupLidOneOrdered;
        
        //Probamos el comportamiento de la taza y tapa con numero identificador 1, colocados de forma inversa, caso base anterior pero invertido, esta para probar
        //mas que todo los metodos que ofrecen informacion, como height, liddedCups, stacking Items, etc..
        lidCupOne = new Tower(1,2);
        lidCupOne.pushLid("normal", 1);
        lidCupOne.pushCup("normal", 1);
        
        //LidCupOne despues de aplicar orderTower()
        lidCupOneOrdered = cupLidOneOrdered;
        
        //LidCupOne despues de aplicar reverseTower()
        lidCupOneReverse = cupLidOneReverse;
        
        //Probamos el comportamiento de colocar una taza sobre una tapa, caso base para probar el comportamiento de una taza sobre tapa, se hizo porque al inicio
        //del proyecto se tuvieron problemas con codigos que asumian que la altura de una tapa era su mismo ancho, como si fuera una taza, por esta misma razon 
        //en otras torres de prueba se tiene algo similar, una taza sobre una tapa
        cupOverLid = new Tower(5,4);
        cupOverLid.pushLid("normal", 3);
        cupOverLid.pushCup("normal", 2);
        
        //cupOverLid despues de aplicar orderTower()
        cupOverLidOrdered = new Tower(5,4);
        cupOverLidOrdered.pushLid("normal", 3);
        cupOverLidOrdered.pushCup("normal", 2);
        
        //cupOverLid despues de aplicar reverseTower()
        cupOverLidReverse = new Tower(5,4);
        cupOverLidReverse.pushCup("normal", 2);
        cupOverLidReverse.pushLid("normal", 3);
        
        //Probamos el comportamiento de dos tapas y una taza, donde una tapa siempre esta en medio de una taza y una tapa, de manera similar al caso base anterior
        //se hizo como caso extra y un poco mas complejo para probar que los codigos no asuman que la altura de una tapa es su mismo ancho, los siguientes casos
        //de prueba, buscan algo similar, tapa sobre tapa, o asi, para probar lo mismo pero en diferente orden
        cupLidLid = new Tower(3,3);
        cupLidLid.pushCup("normal", 1);
        cupLidLid.pushLid("normal", 2);
        cupLidLid.pushLid("normal", 1);
        
        //cupLidLid despues de aplicar orderTower()
        cupLidLidOrdered = new Tower(3,3);
        cupLidLidOrdered.pushLid("normal", 2);
        cupLidLidOrdered.pushCup("normal", 1);
        cupLidLidOrdered.pushLid("normal", 1);
        
        //cupLidLid despues de aplicar reverseTower()
        cupLidLidReverse = new Tower(3,3);
        cupLidLidReverse.pushCup("normal", 1);
        cupLidLidReverse.pushLid("normal", 1);
        cupLidLidReverse.pushLid("normal", 2);
        
        //Probamos el comportamiento de dos tapas y una taza, donde una tapa siempre esta en medio de una tapa y una taza, revisar comentario de cupLidLid
        lidLidCup = new Tower(3,3);
        lidLidCup.pushLid("normal", 1);
        lidLidCup.pushLid("normal", 2);
        lidLidCup.pushCup("normal", 1);
        
        //lidLidCup despues de aplicar orderTower()
        lidLidCupOrdered = cupLidLidOrdered;
        
        //lidLidCup despues de aplicar reverseTower()
        lidLidCupReverse = cupLidLidReverse;
        
        //Probamos el comportamiento de una taza que contiene otra taza con una tapa mas grande encima, probamos de nuevo el codigo con la altura de la tapa y taza
        //pero esta vez dentro de otros objetos, y de hecho las siguientes pruebas con Inside, es para probar mas que todo los metodos que se encargan del atributo
        //height, donde cualquier cambio al interior de una taza, si el cambio no supera la altura de la taza, la altura debe ser igual a la altura de la taza
        cupLidInside = new Tower(5,7);
        cupLidInside.pushCup("normal", 3);
        cupLidInside.pushCup("normal", 1);
        cupLidInside.pushLid("normal", 2);
        
        //cupLidInside despues de aplicar orderTower()
        cupLidInsideOrdered = new Tower(5,7);
        cupLidInsideOrdered.pushCup("normal", 3);
        cupLidInsideOrdered.pushLid("normal", 2);
        cupLidInsideOrdered.pushCup("normal", 1);
        
        //cupLidInside despues de aplicar reverseTower()
        cupLidInsideReverse = new Tower(5,7);
        cupLidInsideReverse.pushCup("normal", 1);
        cupLidInsideReverse.pushLid("normal", 2);
        cupLidInsideReverse.pushCup("normal", 3);
        
        //Probamos el comportamiento de una taza que contiene una tapa con una taza mas pequeña encima, revisar comentario cupLidInside
        lidCupInside = new Tower(5,7);
        lidCupInside.pushCup("normal", 3);
        lidCupInside.pushLid("normal", 2);
        lidCupInside.pushCup("normal", 1);
        
        //lidCupInside despues de aplicar orderTower()
        lidCupInsideOrdered = cupLidInsideOrdered;
        
        //lidCupInside despues de aplicar reverseTower()
        lidCupInsideReverse = cupLidInsideReverse;
        
        //Probamos el comportamiento de una taza que contiene a dos tapas, la tapa mas pequeña encima de la tapa mas grande, revisar comentario cupLidInside
        smallerLidOverInside = new Tower(5,7);
        smallerLidOverInside.pushCup("normal", 3);
        smallerLidOverInside.pushLid("normal", 2);
        smallerLidOverInside.pushLid("normal", 1);
        
        //smallerLidOverInside despues de aplicar orderTower()
        smallerLidOverInsideOrdered = new Tower(5,7);
        smallerLidOverInsideOrdered.pushCup("normal", 3);
        smallerLidOverInsideOrdered.pushLid("normal", 2);
        smallerLidOverInsideOrdered.pushLid("normal", 1);
        
        //smallerLidOverInside despues de aplicar reverseTower()
        smallerLidOverInsideReverse = new Tower(5,7);
        smallerLidOverInsideReverse.pushLid("normal", 1);
        smallerLidOverInsideReverse.pushLid("normal", 2);
        smallerLidOverInsideReverse.pushCup("normal", 3);
        
        //Probamos el comportamiento de una taza que contiene a dos tapas, la tapa mas grande encima de la tapa mas pequeña, revisar comentario cupLidInside
        biggerLidOverInside = new Tower(5,7);
        biggerLidOverInside.pushCup("normal", 3);
        biggerLidOverInside.pushLid("normal", 1);
        biggerLidOverInside.pushLid("normal", 2);
        
        //biggerLidOverInside despues de aplicar el orderTower()
        biggerLidOverInsideOrdered = smallerLidOverInsideOrdered;
        
        //biggerLidOverInside despues de aplicar el reverseTower()
        biggerLidOverInsideReverse = smallerLidOverInsideReverse;
        
        //Probamos el comportamiento de una taza que contiene a una taza con su respectiva tapa, y que ocupan la altura exacta del interior, revisar comentario 
        //cupLidInside
        perfectlyLiddedInside = new Tower(5,9);
        perfectlyLiddedInside.pushCup("normal", 3);
        perfectlyLiddedInside.pushCup("normal", 2);
        perfectlyLiddedInside.pushLid("normal", 2);
        
        //perfectlyLiddedInside despues de aplicar orderTower()
        perfectlyLiddedInsideOrdered = new Tower(5,9);
        perfectlyLiddedInsideOrdered.pushCup("normal", 3);
        perfectlyLiddedInsideOrdered.pushCup("normal", 2);
        perfectlyLiddedInsideOrdered.pushLid("normal", 2);
        
        //perfectlyLiddedInside despues de aplicar reverseTower()
        perfectlyLiddedInsideReverse = new Tower(5,9);
        perfectlyLiddedInsideReverse.pushCup("normal", 2);
        perfectlyLiddedInsideReverse.pushLid("normal", 2);
        perfectlyLiddedInsideReverse.pushCup("normal", 3);
        
        //Torre con varias tazas tanto tapadas como no tapadas, las tapadas tienen cosas por dentro, es especial para swap, ya que busca probar ese metodo para
        //tazas tapadas y sus objetos internos.
        specialSwap = new Tower(11,31);
        specialSwap.pushCup("normal", 6);
        specialSwap.pushCup("normal", 4);
        specialSwap.pushCup("normal", 2);
        specialSwap.pushCup("normal", 3);
        specialSwap.pushCup("normal", 1);
        specialSwap.pushLid("normal", 2);
        specialSwap.pushLid("normal", 1);
        specialSwap.pushLid("normal", 3);
        specialSwap.pushLid("normal", 6);
        
        //specialSwap despues de aplicar el orderTower()
        specialSwapOrdered = new Tower(11,31);
        specialSwapOrdered.pushCup("normal", 6);
        specialSwapOrdered.pushLid("normal", 6);
        specialSwapOrdered.pushCup("normal", 4);
        specialSwapOrdered.pushCup("normal", 3);
        specialSwapOrdered.pushLid("normal", 3);
        specialSwapOrdered.pushCup("normal", 2);
        specialSwapOrdered.pushLid("normal", 2);
        specialSwapOrdered.pushCup("normal", 1);
        specialSwapOrdered.pushLid("normal", 1);
                
        //specialSwap despues de aplicar el reverseTower()
        specialSwapReverse = new Tower(11,31);
        specialSwapReverse.pushCup("normal", 1);
        specialSwapReverse.pushLid("normal", 1);
        specialSwapReverse.pushCup("normal", 2);
        specialSwapReverse.pushLid("normal", 2);
        specialSwapReverse.pushCup("normal", 3);
        specialSwapReverse.pushLid("normal", 3);
        specialSwapReverse.pushCup("normal", 4);
        specialSwapReverse.pushCup("normal", 6);
        specialSwapReverse.pushLid("normal", 6);        
        //Torre con una taza tapada, con dos tazas y tapas de igual numero; pero que tienen una su tapa mas arriba sin taparla, y otra tiene la tapa abajo,
        //es especial para cover() ya que posiciona las tapas tanto arriba como abajo de tazas, y estan separadas por varios objetos de la taza de igual numero
        specialCover = new Tower(9,21);
        specialCover.pushLid("normal",5);
        specialCover.pushCup("normal",5);
        specialCover.pushCup("normal",3);
        specialCover.pushCup("normal",2);
        specialCover.pushCup("normal",1);
        specialCover.pushLid("normal",3);
        specialCover.pushLid("normal",2);
        
        //specialCover despues de aplicar el orderTower()
        specialCoverOrdered = new Tower(9,21);
        specialCoverOrdered.pushCup("normal",5);
        specialCoverOrdered.pushLid("normal",5);
        specialCoverOrdered.pushCup("normal",3);
        specialCoverOrdered.pushLid("normal",3);
        specialCoverOrdered.pushCup("normal",2);
        specialCoverOrdered.pushLid("normal",2);
        specialCoverOrdered.pushCup("normal",1);
        
        //specialCover despues de aplicar el reverseTower()
        specialCoverReverse = new Tower(9,21);
        specialCoverReverse.pushCup("normal",1);
        specialCoverReverse.pushCup("normal",2);
        specialCoverReverse.pushLid("normal",2);
        specialCoverReverse.pushCup("normal",3);
        specialCoverReverse.pushLid("normal",3);
        specialCoverReverse.pushCup("normal",5);
        specialCoverReverse.pushLid("normal",5);
        
        //Inicializamos variables para las pruebas de nuestros compañeros
        tower = new Tower(100,100);
    }
    
    //Ponemos las pruebas de Stacking Items de primero dado que son importantes, porque usamos este metodo constantemente para conocer si es correcta una prueba
    //con precision, al darnos; las posiciones, tipo y numero de los objetos de la torre de forma exacta, por lo que de igual manera podemos comparar el resultado
    //con lo que esperamos de forma exacta
    @Test
    public void shouldBeCorrectFormatForCupStackingItems() {//Comprobamos que el formato para una Cup sea correcto
        Tower t = new Tower(1,1);
        t.pushCup("normal",1);
        assertTrue(Arrays.deepEquals(new String[][]{{"cup","1"}},t.stackingItems()));
    }
    
    @Test
    public void shouldBeCorrectFormatForLidStackingItems() {//Comprobamos que el formato para una Lid sea correcto
        Tower t = new Tower(1,1);
        t.pushLid("normal",1);
        assertTrue(Arrays.deepEquals(new String[][]{{"lid","1"}},t.stackingItems()));
    }
    
    @Test
    public void shouldBeCorrectPositionsStackingItemsOfSpecialSwap() { //Elegimos Special Swap ya que es la prueba base que mas elementos tiene
        assertTrue(Arrays.deepEquals(new String[][]{{"cup","6"},{"cup","4"},{"cup","2"},{"cup","3"},{"cup","1"},{"lid","2"},{"lid","1"},{"lid","3"},{"lid","6"}}
        ,specialSwap.stackingItems()));
    }
    
    //Continuamos ahora si con las demas pruebas en orden
    
    @Test
    public void shouldNotCreateTowerByNegativeNumberOfDimension() {
        try {
            Tower t = new Tower(-1,1);
            fail();
        } catch (Exception e){
            assertEquals(e.getMessage(),Tower.INVALID_NUMBERS);
        }
        try {
            Tower t = new Tower(-1);
            fail();
        } catch (Exception e){
            assertEquals(e.getMessage(),Tower.INVALID_NUMBERS);
        }
    }
    
    @Test
    public void shouldCreateTowerFirstConstructor() {
        Tower t = new Tower(1,1);
        assertTrue(t.ok());
    }
    
    @Test
    public void shouldCreateCupsSecondConstructor() {
        Tower t = new Tower(4);
        assertTrue(Arrays.deepEquals(new String[][]{{"cup","4"},{"cup","3"},{"cup","2"},{"cup","1"}},t.stackingItems()));
    }
    
    @Test
    public void shouldNotCreateCupsByNegativeNumber() {
        Tower t = new Tower(4);
        t.pushCup("normal", 0);
        assertFalse(t.ok());
    }
    
    @Test
    public void shouldNotCreateCupsByNumberAlreadyInUse() {
        Tower t = new Tower(4);
        t.pushCup("normal", 1);
        assertFalse(t.ok());
    }
    
    @Test
    public void shouldNotCreateCupsByHeightExceed() {
        Tower t = new Tower(3,1);
        t.pushCup("normal", 1);
        t.pushCup("normal", 2);
        assertFalse(t.ok());
    }
    
    //Aprovechando que tenemos una gran cantidad de casos, probamos con todos ellos los metodos que calculan altura, y de paso los push
    
    @Test
    public void shouldBeCorrectHeightDoubleLidded() {
        Tower t = new Tower(7,13);
        t.pushCup("normal", 3);
        assertEquals(5,t.height());
        
        t.pushCup("normal", 4);
        assertEquals(12,t.height());
        
        t.pushCup("normal", 2);
        assertEquals(12,t.height());
        
        t.pushCup("normal", 1);
        assertEquals(12,t.height());
        
        t.pushLid("normal", 2);
        assertEquals(12,t.height());
    
        t.pushLid("normal", 4);
        assertEquals(13,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightOnlyCups() {
        Tower t = new Tower(9,10);
        t.pushCup("normal",5);
        assertEquals(9,t.height());
        
        t.pushCup("normal",4);
        assertEquals(9,t.height());
    
        t.pushCup("normal",2);
        assertEquals(9,t.height());
    
        t.pushCup("normal",3);
        assertEquals(10,t.height());
    
        t.pushCup("normal",1);
        assertEquals(10,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightPerfectlyLidded() {
        Tower t = new Tower(7,8);
        t.pushCup("normal",4);
        assertEquals(7,t.height());
        
        t.pushCup("normal",1);
        assertEquals(7,t.height());
    
        t.pushCup("normal",3);
        assertEquals(7,t.height());
    
        t.pushLid("normal",4);
        assertEquals(8,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightBigCase() {
        Tower t = new Tower(9,10);
        t.pushCup("normal",5);
        assertEquals(9,t.height());
        
        t.pushCup("normal",4);
        assertEquals(9,t.height());
    
        t.pushCup("normal",2);
        assertEquals(9,t.height());
    
        t.pushCup("normal",3);
        assertEquals(10,t.height());
    
        t.pushCup("normal",1);
        assertEquals(10,t.height());
    
        t.pushLid("normal",2);
        assertEquals(10,t.height());
    
        t.pushLid("normal",1);
        assertEquals(10,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidOne() {
        Tower t = new Tower(1,2);
        t.pushCup("normal",1);
        assertEquals(1,t.height());
        
        t.pushLid("normal",1);
        assertEquals(2,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightLidCupOne() {
        Tower t = new Tower(1,2);
        t.pushLid("normal",1);
        assertEquals(1,t.height());
        
        t.pushCup("normal",1);
        assertEquals(2,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightCupOverLid() {
        Tower t = new Tower(5,4);
        t.pushLid("normal",3);
        assertEquals(1,t.height());
        
        t.pushCup("normal",2);
        assertEquals(4,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidLid() {
        Tower t = new Tower(3,3);
        t.pushCup("normal",1);
        assertEquals(1,t.height());
        
        t.pushLid("normal",2);
        assertEquals(2,t.height());
    
        t.pushLid("normal",1);
        assertEquals(3,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightLidLidCup() {
        Tower t = new Tower(3,3);
        t.pushLid("normal",1);
        assertEquals(1,t.height());
        
        t.pushLid("normal",2);
        assertEquals(2,t.height());
    
        t.pushCup("normal",1);
        assertEquals(3,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightCupLidInside() {
        Tower t = new Tower(5,5);
        t.pushCup("normal",3);
        assertEquals(5,t.height());
        
        t.pushCup("normal",1);
        assertEquals(5,t.height());
    
        t.pushLid("normal",2);
        assertEquals(5,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightLidCupInside() {
        Tower t = new Tower(5,5);
        t.pushCup("normal",3);
        assertEquals(5,t.height());
        
        t.pushLid("normal",2);
        assertEquals(5,t.height());
    
        t.pushCup("normal",1);
        assertEquals(5,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightSmallLidOverInside() {
        Tower t = new Tower(5,5);
        t.pushCup("normal",3);
        assertEquals(5,t.height());
        
        t.pushLid("normal",2);
        assertEquals(5,t.height());
    
        t.pushLid("normal",1);
        assertEquals(5,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightBigLidOverInside() {
        Tower t = new Tower(5,5);
        t.pushCup("normal",3);
        assertEquals(5,t.height());
        
        t.pushLid("normal",1);
        assertEquals(5,t.height());
    
        t.pushLid("normal",2);
        assertEquals(5,t.height());
    }
    
    @Test
    public void shouldBeCorrectHeightPerfectlyLiddedInside() {
        Tower t = new Tower(5,5);
        t.pushCup("normal",3);
        assertEquals(5,t.height());
        
        t.pushCup("normal",2);
        assertEquals(5,t.height());
    
        t.pushLid("normal",2);
        assertEquals(5,t.height());
    }
    
    @Test
    public void afterPopCupShouldBeOkInFalse() {//Esta es la prueba que suelta dos JOptionPane, que la torre esta vacia, y que el elemento superior no es una taza
        //Es correcto que suelte esos dos JOptionPane
        Tower t = new Tower(5,5);
        t.popCup();
        assertFalse(t.ok());
        
        t.pushLid("normal",1);
        t.popCup();
        assertFalse(t.ok());
    }
    
    @Test
    public void afterPopLidShouldBeOkInFalse() {//Esta es la prueba que suelta dos JOptionPane, que la torre esta vacia, y que el elemento superior no es una tapa
        //Es correcto que suelte esos dos JOptionPane
        Tower t = new Tower(5,5);
        t.popLid();
        assertFalse(t.ok());
        t.pushCup("normal",1);
        t.popLid();
        assertFalse(t.ok());
    }
    
    //Comprobamos que tras el borrado con pop..() o remove..() se corriga la altura correctamente
    
    @Test
    public void afterPopCupShouldBeCorrectHeightLidCupInside() {
        lidCupInside.popCup();
        assertEquals(5,lidCupInside.height());
    }
    
    @Test
    public void afterPopCupShouldBeCorrectHeightExtraCase() {
        Tower t = new Tower(5,6);
        t.pushCup("normal",3);
        t.pushCup("normal",2);
        t.pushLid("normal",2);
        t.pushCup("normal",1);
        
        t.popCup();
        assertEquals(5,t.height());
    }
    
    @Test
    public void afterRemoveCupShouldBeOkInFalse() {//Esta es la prueba que suelta dos JOptionPane, que la torre esta vacia, y que no se encontro la taza
        //Es correcto que suelte esos dos JOptionPane
        Tower t = new Tower(5,5);
        t.removeCup(1);
        assertFalse(t.ok());
        t.pushCup("normal",1);
        t.removeCup(2);
        assertFalse(t.ok());
    }
    
    @Test
    public void afterRemoveLidShouldBeOkInFalse() {//Esta es la prueba que suelta dos JOptionPane, que la torre esta vacia, y que no se encontro la tapa
        //Es correcto que suelte esos dos JOptionPane
        Tower t = new Tower(5,5);
        t.removeLid(1);
        assertFalse(t.ok());
        t.pushLid("normal",1);
        t.removeLid(2);
        assertFalse(t.ok());
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightCupLidInside() {//Prueba de que la altura cambia tras remover una taza que no estaba en el top
        cupLidInside.removeCup(3);
        assertEquals(2,cupLidInside.height());
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightBigCase() {//Prueba de que tras ese remove cambie la altura de la torre
        bigCase.removeCup(3);
        assertEquals(9,bigCase.height());
    }
    
    @Test
    public void afterRemoveCupShouldBeEqualHeightDoubleLiddedFilledCupCase() {//Probamos eliminacion de taza tapada con cosas dentro que no modifica la altura
        doubleLidded.removeCup(2);
        assertEquals(13,doubleLidded.height());
        assertEquals(3,doubleLidded.getStackSize());
    }
    
    @Test
    public void afterRemoveCupShouldBeCorrectHeightDoubleLiddedFilledCupCase() {//Probamos eliminacion de taza tapada con cosas dentro que modifica la altura
        doubleLidded.removeCup(4);
        
        assertEquals(5,doubleLidded.height());
        assertEquals(1,doubleLidded.getStackSize());
    }
    
    @Test
    public void afterPopLidShouldBeCorrectHeightCupLidInside() {//Probamos que tras este popLid no se cambie la altura
        cupLidInside.popLid();
        assertEquals(5,cupLidInside.height());
    }
    
    @Test
    public void afterPopLidShouldBeCorrectHeightDoubleLidded() {// Probamos que este popLid si cambie la altura
        doubleLidded.popLid();
        assertEquals(12,doubleLidded.height());
    }
    
    @Test
    public void afterRemoveLidInTopShouldBeCorrectHeightDoubleLidded() {// Probamos que este removeLid no cambie la altura
        doubleLidded.removeLid(2);
        assertEquals(13,doubleLidded.height());
    }
    
    @Test
    public void afterRemoveLidShouldBeCorrectHeightDoubleLidded() {// Probamos que este removeLid si cambie la altura
        doubleLidded.removeLid(4);
        assertEquals(12,doubleLidded.height());
    }
    
    @Test
    public void afterRemoveLidShouldBeCorrectHeightCupOverLid() {// Probamos que este removeLid si cambie la altura
        cupOverLid.removeLid(3);
        assertEquals(3,cupOverLid.height());
    }
    
    @Test
    public void afterRemoveLidShouldBeEqualHeightDoubleLiddedFilledCupCase() {//Probamos eliminacion de una tapa que destapa su taza
        doubleLidded.removeLid(2);
        assertEquals(13,doubleLidded.height());
        assertTrue(Arrays.equals(new int[]{4},doubleLidded.liddedCups()));
    }
    
    //Se implemento un metodo equals especial para Tower que se aplica para las pruebas de orderTower y reverseTower
    
    @Test
    public void afterOrderTowerShouldBeEqualDoubleLidded() {
        doubleLidded.orderTower();
        
        assertEquals(doubleLidded,doubleLiddedOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualOnlyCups() {
        onlyCups.orderTower();
        
        assertEquals(onlyCups,onlyCupsOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualPerfectlyLidded() {
        perfectlyLidded.orderTower();
        
        assertEquals(perfectlyLidded,perfectlyLiddedOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualBigCase() {
        bigCase.orderTower();
        
        assertEquals(bigCase,bigCaseOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidOne() {
        cupLidOne.orderTower();
        
        assertEquals(cupLidOne,cupLidOneOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidCupOne() {
        lidCupOne.orderTower();
        
        assertEquals(lidCupOne,lidCupOneOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupOverLid() {
        cupOverLid.orderTower();
        
        assertEquals(cupOverLid,cupOverLidOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidLid() {
        cupLidLid.orderTower();
        
        assertEquals(cupLidLid,cupLidLidOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidLidCup() {
        lidLidCup.orderTower();
        
        assertEquals(lidLidCup,lidLidCupOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualCupLidInside() {
        cupLidInside.orderTower();
        
        assertEquals(cupLidInside,cupLidInsideOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualLidCupInside() {
        lidCupInside.orderTower();
        
        assertEquals(lidCupInside,lidCupInsideOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualSmallerLidOverInside() {
        smallerLidOverInside.orderTower();
        
        assertEquals(smallerLidOverInside,smallerLidOverInsideOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualBiggerLidOverInside() {
        biggerLidOverInside.orderTower();
        
        assertEquals(biggerLidOverInside,biggerLidOverInsideOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualPerfectlyLiddedInside() {
        perfectlyLiddedInside.orderTower();
        
        assertEquals(perfectlyLiddedInside,perfectlyLiddedInsideOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualSpecialSwap() {
        specialSwap.orderTower();
        
        assertEquals(specialSwap,specialSwapOrdered);
    }
    
    @Test
    public void afterOrderTowerShouldBeEqualSpecialCover() {
        specialCover.orderTower();
        
        assertEquals(specialCover,specialCoverOrdered);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualDoubleLidded() {
        doubleLidded.reverseTower();
        
        assertEquals(doubleLidded,doubleLiddedReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualOnlyCups() {
        onlyCups.reverseTower();
        
        assertEquals(onlyCups,onlyCupsReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualPerfectlyLidded() {
        perfectlyLidded.reverseTower();
        
        assertEquals(perfectlyLidded,perfectlyLiddedReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualBigCase() {
        bigCase.reverseTower();
        
        assertEquals(bigCase,bigCaseReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidOne() {
        cupLidOne.reverseTower();
        
        assertEquals(cupLidOne,cupLidOneReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidCupOne() {
        lidCupOne.reverseTower();
        
        assertEquals(lidCupOne,lidCupOneReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupOverLid() {
        cupOverLid.reverseTower();
        
        assertEquals(cupOverLid,cupOverLidReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidLid() {
        cupLidLid.reverseTower();
        
        assertEquals(cupLidLid,cupLidLidReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidLidCup() {
        lidLidCup.reverseTower();
        
        assertEquals(lidLidCup,lidLidCupReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualCupLidInside() {
        cupLidInside.reverseTower();
        
        assertEquals(cupLidInside,cupLidInsideReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualLidCupInside() {
        lidCupInside.reverseTower();
        
        assertEquals(lidCupInside,lidCupInsideReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualSmallerLidOverInside() {
        smallerLidOverInside.reverseTower();
        
        assertEquals(smallerLidOverInside,smallerLidOverInsideReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualBiggerLidOverInside() {
        biggerLidOverInside.reverseTower();
        
        assertEquals(biggerLidOverInside,biggerLidOverInsideReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualPerfectlyLiddedInside() {
        perfectlyLiddedInside.reverseTower();
        
        assertEquals(perfectlyLiddedInside,perfectlyLiddedInsideReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualSpecialSwap() {
        specialSwap.reverseTower();
        
        assertEquals(specialSwap,specialSwapReverse);
    }
    
    @Test
    public void afterReverseTowerShouldBeEqualSpecialCover() {
        specialCover.reverseTower();
        
        assertEquals(specialCover,specialCoverReverse);
    }
    
    @Test
    public void swapShouldExchange() {
        Tower t = new Tower(7, 50);
        t.pushCup("normal",1);
        t.pushCup("normal",2);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "2"});
        assertTrue(t.ok());
        assertTrue(Arrays.deepEquals(new String[][]{{"cup","2"},{"cup","1"}},t.stackingItems()));
    }

    @Test
    public void swapFails() {
        Tower t = new Tower(7, 50);
        t.pushCup("normal",1);
        t.swap(new String[]{"cup", "1"}, new String[]{"cup", "99"});
        assertFalse(t.ok());
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightdoubleLidded() {
        doubleLidded.swap(new String[]{"cup","4"},new String[]{"cup","2"});
        assertFalse(doubleLidded.ok()); //No se permite intercambio entre taza tapada y algo de adentro de si misma
        
        doubleLidded.swap(new String[]{"lid","2"},new String[]{"cup","1"});
        assertFalse(doubleLidded.ok());//No se permite intercambio entre taza tapada y algo de adentro de si misma, pero esta vez tomando a la tapa
        
        doubleLidded.swap(new String[]{"cup","1"},new String[]{"cup","3"});//No se permite el intercambio de algo interno de una taza tapada con el exterior
        assertFalse(doubleLidded.ok());
        
        doubleLidded.swap(new String[]{"cup","2"},new String[]{"lid","2"});//Se puede destapar una taza intercambiandola con su tapa
        assertEquals(13,doubleLidded.height());
        assertTrue(Arrays.deepEquals(doubleLidded.stackingItems(),new String[][] {{"cup","3"},{"cup","4"},{"lid","2"},{"cup","1"},{"cup","2"},{"lid","4"}}));
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightSpecialSwap() {
        //No se permite intercambio entre taza tapada y algo de adentro de si misma
        specialSwap.swap(new String[]{"lid","6"},new String[]{"lid","3"});
        assertFalse(specialSwap.ok());
        
        specialSwap.swap(new String[]{"cup","3"},new String[]{"lid","1"});
        assertFalse(specialSwap.ok());
        
        //No se permite el intercambio de algo interno de una taza tapada con el exterior
        specialSwap.swap(new String[]{"lid","1"},new String[]{"cup","2"});
        assertFalse(specialSwap.ok());
        
        specialSwap.swap(new String[]{"lid","2"},new String[]{"cup","4"});
        assertFalse(specialSwap.ok());
        
        //No se permite el intercambio interno en tazas tapas, solo y solo si, el intercambio supera hace que los objetos internos superen la capacidad de la taza
        //tapada
        specialSwap.swap(new String[]{"cup","3"},new String[]{"cup","4"});
        assertFalse(specialSwap.ok());
        
        //Se permite el intercambio interno en tazas tapadas        
        specialSwap.swap(new String[]{"cup","2"},new String[]{"cup","4"});
        assertTrue(specialSwap.ok());
        assertTrue(Arrays.deepEquals(specialSwap.stackingItems(),
        new String[][]{{"cup","6"},{"cup","2"},{"cup","4"},{"cup","3"},{"cup","1"},{"lid","2"},{"lid","1"},{"lid","3"},{"lid","6"}}));
        
        specialSwap.swap(new String[]{"lid","1"},new String[]{"lid","2"});
        assertTrue(specialSwap.ok());
        assertTrue(Arrays.deepEquals(specialSwap.stackingItems(),
        new String[][]{{"cup","6"},{"cup","2"},{"cup","4"},{"cup","3"},{"cup","1"},{"lid","1"},{"lid","2"},{"lid","3"},{"lid","6"}}));
        
        //Se puede destapar una taza intercambiandola con su tapa
        specialSwap.swap(new String[]{"cup","6"},new String[]{"lid","6"});
        assertTrue(specialSwap.ok());
        assertTrue(Arrays.deepEquals(specialSwap.stackingItems(),
        new String[][]{{"lid","6"},{"cup","2"},{"cup","4"},{"cup","3"},{"cup","1"},{"lid","1"},{"lid","2"},{"lid","3"},{"cup","6"}}));
        assertTrue(Arrays.equals(specialSwap.liddedCups(),new int[]{1,3}));//Verificamos que se destapo
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightSpecialCover() {
        //No se permite intercambio entre taza tapada y algo de adentro de si misma
        specialCover.swap(new String[]{"lid","3"},new String[]{"cup","2"});
        assertFalse(specialCover.ok());
        
        //No se permite el intercambio de algo interno de una taza tapada con el exterior
        specialCover.swap(new String[]{"cup","1"},new String[]{"lid","2"});
        assertFalse(specialCover.ok());
        
        specialCover.swap(new String[]{"cup","2"},new String[]{"lid","5"});
        assertFalse(specialCover.ok());
        
        //Se permite el intercambio interno en tazas tapadas
        specialCover.swap(new String[]{"cup","1"},new String[]{"cup","2"});
        assertTrue(specialCover.ok());
        assertTrue(Arrays.deepEquals(specialCover.stackingItems(),
        new String[][]{{"lid","5"},{"cup","5"},{"cup","3"},{"cup","1"},{"cup","2"},{"lid","3"},{"lid","2"}}));
        
        //Se puede destapar una taza intercambiandola con su tapa
        specialCover.swap(new String[]{"cup","3"},new String[]{"lid","3"});
        assertTrue(specialCover.ok());
        assertTrue(Arrays.deepEquals(specialCover.stackingItems(),
        new String[][]{{"lid","5"},{"cup","5"},{"lid","3"},{"cup","1"},{"cup","2"},{"cup","3"},{"lid","2"}}));
        assertTrue(Arrays.equals(specialCover.liddedCups(),new int[]{}));//Verificamos que se destapo
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightOnlyCups() {//Comprobamos por cambios de altura un swap correcto, como es solo tazas comprobamos lo mas basico
        onlyCups.swap(new String[]{"cup","1"},new String[]{"cup","2"});
        assertEquals(9,onlyCups.height());
        onlyCups.swap(new String[]{"cup","1"},new String[]{"cup","2"});
        
        onlyCups.swap(new String[]{"cup","5"},new String[]{"cup","3"});
        assertEquals(21,onlyCups.height());
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightPerfectlyLidded() {
        perfectlyLidded.swap(new String[]{"cup","1"},new String[]{"cup","3"}); //Se permite intercambios internos en una taza tapada
        assertEquals(8,perfectlyLidded.height());
        assertTrue(perfectlyLidded.ok());
        
        perfectlyLidded.swap(new String[]{"cup","1"},new String[]{"lid","4"}); //De nuevo no se permite intercambios entre taza tapada y sus objetos internos
        assertFalse(perfectlyLidded.ok());
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightBigCase() {//Comprobamos de nuevo los cambios de altura basicos, ademas de que se haya tapado una taza
        bigCase.swap(new String[]{"cup","3"},new String[]{"lid","2"});
        assertEquals(12,bigCase.height());//Comprobamos intercambio correcto
        assertEquals(new int[]{2},bigCase.liddedCups());//Comprobamos taza tapada
        
        bigCase.swap(new String[]{"cup","3"},new String[]{"cup","5"});
        assertEquals(21,bigCase.height());//Comprobamos otro intercambio correcto
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightCupLidOne() {//Verificamos que se pueda hacer un intercambio entre su taza con su tapa si estan tapados y que se
        //destapo
        cupLidOne.swap(new String[]{"cup","1"},new String[]{"lid","1"});
        assertTrue(cupLidOne.ok());
        assertEquals(new int[]{},cupLidOne.liddedCups());//Comprobamos que se destapo
    }
    
    @Test
    public void afterSwapShouldBeCorrectHeightPerfectlyLiddedInside() {
        perfectlyLiddedInside.swap(new String[]{"cup","3"},new String[]{"lid","2"});
        assertEquals(9,perfectlyLiddedInside.height());//Intercambiamos taza por taza tapada, pero solicitandolo por su tapa
        
        perfectlyLiddedInside.swap(new String[]{"cup","2"},new String[]{"lid","2"});
        assertEquals(9,perfectlyLiddedInside.height());
        assertTrue(cupLidOne.ok()); //Verificamos que se pueda hacer un intercambio entre su taza con su tapa si estan tapados
    }
    
    @Test
    public void shouldCoverAllCups() {
        Tower t = new Tower(7, 13);
        t.pushCup("normal",3);
        t.pushCup("normal",4);
        t.pushCup("normal",2);
        t.pushCup("normal",1);
        t.pushLid("normal",2);
        t.pushLid("normal",4);
        t.cover();
        assertTrue(t.ok());
    }
    
    @Test
    public void afterCoverShouldBeCorrectHeightDoubleLidded() {//No debe de cambiar algo porque ya todas las tazas que se podian tapar, estan tapadas
        doubleLidded.cover();
        assertTrue(Arrays.deepEquals(doubleLidded.stackingItems(),new String[][]{{"cup","3"},{"cup","4"},{"cup","2"},{"cup","1"},{"lid","2"},{"lid","4"}}));
        
        assertTrue(doubleLidded.ok());
    }
    
    @Test
    public void afterCoverShouldBeCorrectHeightOnlyCups() {//No debe de cambiar algo porque no existe ni una tapa
        onlyCups.cover();
        assertTrue(Arrays.deepEquals(onlyCups.stackingItems(),new String[][]{{"cup","5"},{"cup","4"},{"cup","2"},{"cup","3"},{"cup","1"}}));
        
        assertTrue(onlyCups.ok());
    }
    
    @Test
    public void afterCoverShouldBeCorrectHeightBigCase() {
        bigCase.cover();
        assertTrue(Arrays.deepEquals(bigCase.stackingItems(),new String[][]{{"cup","5"},{"cup","4"},{"cup","2"},{"lid","2"},{"cup","3"},{"cup","1"},{"lid","1"}}));
        
        assertTrue(bigCase.ok());
    }
    
    @Test
    public void afterCoverShouldBeCorrectHeightSpecialSwap() { //Caso muy especial que encontramos, tapar una taza en el interior implica superar la altura interna
        //de una taza que ya estaba tapada, elegimos que el elemento mas alto o susperior dentro de la taza ya tapada, se desplaza hacia afuera y encima de la taza
        //que se reboso
        specialSwap.cover();
        assertTrue(Arrays.deepEquals(specialSwap.stackingItems(),
        new String[][]{{"cup","6"},{"cup","4"},{"cup","2"},{"lid","2"},{"lid","6"},{"cup","3"},{"cup","1"},{"lid","1"},{"lid","3"}}));
        assertTrue(specialSwap.ok());
    }
    
    @Test
    public void afterCoverShouldBeCorrectHeightSpecialCover() {
        specialCover.cover();
        assertTrue(Arrays.deepEquals(specialCover.stackingItems(),
        new String[][]{{"cup","5"},{"lid","5"},{"cup","3"},{"cup","2"},{"cup","1"},{"lid","2"},{"lid","3"}}));
        
        assertTrue(specialCover.ok());
    }
    
    //Comprobamos los liddedCups con todos los casos que tenemos
    @Test
    public void liddedCupsDoubleLidded() {
        assertTrue(Arrays.equals(new int[]{2,4},doubleLidded.liddedCups()));
    }
    
    @Test
    public void liddedCupsOnlyCups() {
        assertTrue(Arrays.equals(new int[]{},onlyCups.liddedCups()));
    }
    
    @Test
    public void liddedCupsOnlyLids() {
        Tower onlyLids = new Tower (7,3);
        onlyLids.pushLid("normal",4);
        onlyLids.pushLid("normal",3);
        onlyLids.pushLid("normal",1);
        
        assertTrue(Arrays.equals(new int[]{},onlyLids.liddedCups()));
    }
    
    @Test
    public void liddedCupsPerfectlyLidded() {
        assertTrue(Arrays.equals(new int[]{4},perfectlyLidded.liddedCups()));
    }
    
    @Test
    public void liddedCupsBigCase() {
        assertTrue(Arrays.equals(new int[]{},bigCase.liddedCups()));
    }
    
    @Test
    public void liddedCupsCupLidOne() {
        assertTrue(Arrays.equals(new int[]{1},cupLidOne.liddedCups()));
    }
    
    @Test
    public void liddedCupsLidCupOne() {
        assertTrue(Arrays.equals(new int[]{},lidCupOne.liddedCups()));
    }
    
    @Test
    public void liddedCupsPerfectlyLiddedInside() {
        assertTrue(Arrays.equals(new int[]{2},perfectlyLiddedInside.liddedCups()));
    }
    
    @Test
    public void liddedCupsSpecialSwap() {
        assertTrue(Arrays.equals(new int[]{3,6},specialSwap.liddedCups()));
    }
    
    @Test
    public void liddedCupsSpecialCover() {
        assertTrue(Arrays.equals(new int[]{3},specialCover.liddedCups()));
    }
    
    //Probamos los swapToReduce() con algunos de nuestros casos base
    
    @Test
    public void swapToReduceCorrectFormat() {
        String [][] answer = doubleLidded.swapToReduce();
        assertTrue(answer.length == 2);
        assertTrue(answer[0].length == 2);
        assertTrue(answer[1].length == 2);
        assertTrue(answer[0][0].equals("cup") || answer[0][0].equals("lid"));
        assertTrue(answer[1][0].equals("cup") || answer[1][0].equals("lid"));
        try {
            int firstNumber = Integer.valueOf(answer[0][1]);
        } catch (Exception e) {
            fail();
        }
        try {
            int secondNumber = Integer.valueOf(answer[1][1]);
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void swapToReduceDoubleLidded() { //Primer caso donde si se debe de reducir la altura, ademas tiene tazas tapadas
        int beforeSwap = doubleLidded.height();
        doubleLidded.swap(doubleLidded.swapToReduce()[0],doubleLidded.swapToReduce()[1]);
        assertTrue(doubleLidded.height() < beforeSwap);
    }
    
    @Test
    public void swapToReduceOnlyCups() { //Segundo caso donde si se debe de reducir la altura, solo tiene tazas
        int beforeSwap = onlyCups.height();
        onlyCups.swap(onlyCups.swapToReduce()[0],onlyCups.swapToReduce()[1]);
        assertTrue(onlyCups.height() < beforeSwap);
    }
    
    @Test
    public void swapToReduceBigCase() { //Tercer caso donde si se debe de reducir la altura
        int beforeSwap = bigCase.height();
        bigCase.swap(bigCase.swapToReduce()[0],bigCase.swapToReduce()[1]);
        assertTrue(bigCase.height() < beforeSwap);
    }
    
    @Test
    public void swapToReducePerfectlyLidded() { //Primer caso donde no es posible reducir la altura
        assertTrue(perfectlyLidded.swapToReduce().length == 0);
    }
    
    @Test
    public void swapToReducePerfectlyLiddedInside() { //Segundo caso donde no es posible reducir la altura
        assertTrue(perfectlyLiddedInside.swapToReduce().length == 0);
    }
    
    @Test
    public void swapToReduceSpecialCover() { //Tercer caso donde no es posible reducir la altura
        assertTrue(specialCover.swapToReduce().length == 0);
    }
    
    //Pruebas de nuestros compañeros
    
    /**
     * Una torre con 2 tazas debe tener altura 4 (1+3). //Debe de ser 3, porque nosotros lo metemos en order y no en reverse.
     */
    @Test
    public void accordingMV_ShouldHaveHeightFourWithTwoCups() { //Se mantiene el nombre de la prueba apesar del cambio de abajo
        Tower t = new Tower(2);
        assertEquals(3, t.height()); //Esta prueba se modifico por las diferencias de diseño, nuestros compañeros añaden las tazas en reverse, y nosotros en order
        assertTrue(t.ok());
    }
    
    /**
     * swapToReduce debe retornar exactamente 2 objetos (los dos a intercambiar).
     */
    @Test
    public void accordingMV_ShouldReturnTwoObjectsInSwapToReduce() {
        Tower t = new Tower(5, 30);
        t.pushCup("normal",1);
        t.pushCup("normal",3);
        t.pushCup("normal",2);
 
        String[][] swap = t.swapToReduce();
        assertTrue(t.ok());
 
        if (swap.length > 0) {
            assertEquals(2, swap.length);
        }
    }
    
    /**
     * No se añadieron los tests compartidos por PAULA ALEJANDRA DÍAZ ARREDONDO ya que estos presentaban distintos desafios, porque no se realizo la documentacion
     * o no se presento el codigo del atributo publico cups de Tower, ni del metodo getLid() de Cup, por lo que no sabiamos implementar estos tests facilmente, 
     * por lo que optamos por descartarlos.
     */
    
    /**
     * cover() en una torre que no tenga parejan no debería cambiar.
     */
    @Test
    public void accordingOR_testCover_noparejas_noHayCambios()
    {
        tower.pushCup("normal",4);
        tower.pushLid("normal",2);   
        int h = tower.height();
        tower.cover();
        assertTrue(tower.ok());
        assertEquals(h, tower.height());
        assertEquals(0, tower.liddedCups().length);
    }
    
    /**
     * Cuando la tapa de la copa están separadas debe retornar un no-nulo
     */
    @Test
    public void accordingOR_testSwapToReduce_returnsSwap()
    {
        tower.pushCup("normal",3);
        tower.pushCup("normal",5);
        tower.pushLid("normal",3);   
        String[][] suggestion = tower.swapToReduce();
        assertNotNull(suggestion);
        assertEquals(2, suggestion.length);
    }
    
    @Test
    public void accordingSAMUELSERRATO_shouldStartWithNoLids() { //La persona que publico el test no dejo el primer apellido de su pareja
        Tower t = new Tower(5);
        int[] lided = t.liddedCups();
        assertNotNull(lided);
        assertEquals(0, lided.length); //Se quito el mensaje del primer parametro ya que el compilador mostraba un error
    }
    
    /**
     * No se añadio el segundo test compartido por SAMUEL MENA SERRATO, ya que este no contenia la documentacion o no se presento el codigo del metodo 
     * sameIdentifier(), por lo que desconociamos la manera en que debiamos de implementar este metodo, asi que optamos por descartarlo.
     */
    
    /**
     * Verifica que cover tapa todas las tazas correctamente.
     */
    @Test
    public void accordingBG_ShouldCoverAllCups() {
        tower.cover();
        assertTrue(tower.ok());
    }
    
    /**
     * Verifica que swap falla cuando una de las tazas no existe.
     */
    @Test
    public void accordingBG_ShouldNotSwapNonExistentCup() {
        tower.swap(new String[]{"cup","9"}, new String[]{"cup","1"});
        assertFalse(tower.ok());
    }
}
