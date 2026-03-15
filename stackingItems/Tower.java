import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
/**
 * La clase Torre cuenta con limites de altura y ancho, contiene a las tazas y tapas apilandolas y puede realizar cambios sobre las mismas.
 * 
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 * @version 1.3
 */
public class Tower
{
    private final String DIMENSION_EXCEEDS_HEIGHT = "Al insertar el nuevo elemento, se supera la altura maxima de la torre.";
    private final String DIMENSION_EXCEEDS_WIDTH = "Al insertar el nuevo elemento, se supera el ancho maximo de la torre.";
    private final String ALREADY_EXISTS = "Ya existe este numero de elemento en la torre.";
    private final String TOWER_EMPTY = "La torre esta vacia.";
    private int maxWidth;
    private int maxHeight;
    private int currentHeight;
    private int currentWidth;
    private ArrayList<Elemento> stack;
    private HashMap<Elemento, Rectangle> visualElements;
    private boolean isVisible;
    private Canvas canvas;
    
    /**
     * Crea una torre con límites máximos de ancho y alto.
     * 
     * @param maxWidth Ancho maximo de la nueva torre.
     * @param maxHeight Altura maxima de la nueva torre.
     */
    public Tower(int maxWidth, int maxHeight)
    {
        if (maxWidth <= 0 || maxHeight <= 0) {
            ok('N');
            throw new IllegalArgumentException("Las dimensiones deben ser numeros enteros positivos.");
        }
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        currentHeight = 0;
        currentWidth = 0;
        stack = new ArrayList<Elemento>();
        visualElements = new HashMap<Elemento, Rectangle>();
        isVisible = false;
        ok(' ');
    }
    
    /**
     * Crea una torre apilando tazas desde la mas grande (cups), hasta la mas pequeña.
     * 
     * @param cups Cantidad de tazas a crear (deben ser positivas).
     */
    public Tower(int cups)
    {
        if (cups <= 0) {
            ok('N');
            throw new IllegalArgumentException("El número de tazas debe ser un numero entero positivo.");
        }
        //Inicializamos valores teniendo en cuenta la altura de las tazas apiladas desde la pequeña a la mas grande (Altura maxima posible)
        maxHeight = cups * cups;
        maxWidth = (2 * cups) - 1;
        currentHeight = (2 * cups) - 1;
        currentWidth = (2 * cups) - 1;
        stack = new ArrayList<Elemento>();
        visualElements = new HashMap<Elemento, Rectangle>();
        isVisible = false;
        for (int i = cups; i >= 1; i--) {
            stack.add(new Cup(i));
        }
        ok(' ');
    }
    
    /**
     * Retorna un valor de verdad, respecto al exito de una operación.
     * 
     * @param operacion Afirmacion sobre el exito de la operacion, 'N' para No y caracter vacio para Si.
     * 
     * @return boolean - Valor de verdad segun la afirmacion de la operacion, 'N' retorna false, ' ' retorna true.
     */
    public static boolean ok(char operacion) {
        if (operacion == 'N') {//Si 'N'o se completo la operacion retornar false, caso contrario se completo retornar true
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Genera una tupla a partir de un Element, que especifica si es una taza o tapa, y su ancho.
     * 
     * @param e Es un Element del cual se desea , obtener dos datos; su subclase y su ancho, para almacenarlos en un ArrayList de String.
     * 
     * @return String[] - Arreglo de dos pocisiones que contiene los dos datos de un elemento, para ser procesado en la simulacion.
     */
    private String[] Tupla(Elemento e) {
        String[] tupla =new String[2];
        if (e instanceof Cup) {
                tupla[0] = "C";
            } else {
                tupla[0] = "L";
            }
            tupla[1] = String.valueOf(e.getWidth());
        return tupla;
    }
    
    /**
     * Realiza la suma total de la altura de todos los Element simulados, de acuerdo a su subclase. 
     * 
     * @param sim Es la simulacion de los elementos de la torre.
     * 
     * @return int - Numero entero positivo que representa la altura total actual de la torre.
     */
    private int sumSimulation(ArrayList<String[]> sim) {
        int newCurrentHeight = 0;
        for (int k = 0; k < sim.size(); k++) {
            if (sim.get(k)[0].equals("L")) {
                newCurrentHeight = newCurrentHeight + 1;
            } else {
                newCurrentHeight = newCurrentHeight + Integer.valueOf(sim.get(k)[1]);
            }
        }
        return newCurrentHeight;
    }
    
    /**
     * Devuelve la altura de un elemento, segun su subclase.
     * 
     * @param element La tupla con la subclase y ancho del elemento.
     * 
     * @return int - Numero entero positivo que representa la altura del elemento recibido.
     */
    private int elementHeight(String[] element) {
        if (element[0].equals("L")) return 1;
        else return Integer.valueOf(element[1]);
    }
    
    /**
     * Calcula la altura total actual de la torre.
     * 
     * @return int - Numero entero positivo que representa la altura total actual de la torre.
     */
    private int calculateCurrentHeight() {
        if (stack.isEmpty()) return 0;
        ArrayList<String[]> simulacion = new ArrayList<String[]>();
        for (int i = stack.size() - 1;i >= 0; i--) {
            Elemento elemento = stack.get(i);
            String[] agregar = Tupla(elemento);
            int dimension = elemento.getWidth();
            int espacio = elemento.getHeight() - 1;
            if (simulacion.isEmpty()) {
                simulacion.add(agregar);
            } else {
                int inicioEliminar = simulacion.size();
                int anchoUltimoElemento = Integer.valueOf(simulacion.get(simulacion.size()-1)[1]);
                if (dimension <= anchoUltimoElemento) {
                    simulacion.add(agregar);
                } else {
                    String[] extra = {null,null};
                    for (int j = simulacion.size() - 1; j >= 0; j--) {
                        if ((dimension > Integer.valueOf(simulacion.get(j)[1])) && !(agregar[0].equals("L"))) {
                            espacio = espacio - elementHeight(simulacion.get(j));
                            inicioEliminar = j;
                            if (espacio == 0) {
                                break;
                            }else if (espacio < 0) {
                                extra[0] = "E";
                                extra[1] = String.valueOf(Math.abs(espacio));
                            }
                        } else {
                            break;
                        }
                    }
                    simulacion.subList(inicioEliminar,simulacion.size()).clear();
                    if (extra[0] != null) {
                        simulacion.add(extra);
                    }
                    simulacion.add(agregar);
                }
            }
        }
        return sumSimulation(simulacion);
    }
    
    /**
     * Calcula la altura total actual de la torre, pero teniendo en cuenta un nuevo Element apilado.
     * 
     * @param nuevo La tupla con la subclase y ancho del nuevo elemento.
     * 
     * @return int - Numero entero positivo que representa la altura total de la torre, en caso de apilarse el nuevo elemento.
     */
    private int calculateHeightWithNewElement(String[] nuevo) {
        if (stack.isEmpty()) {
            return elementHeight(nuevo);
        }
        ArrayList<String[]> simulacion = new ArrayList<String[]>();
        simulacion.add(nuevo);
        for (int i = stack.size() - 1;i >= 0; i--) {
            Elemento elemento = stack.get(i);
            String[] agregar = Tupla(elemento);
            int dimension = elemento.getWidth();
            int espacio = elemento.getHeight() - 1;
            //Inicio de comprobar la altura
            int inicioEliminar = simulacion.size();
            int anchoUltimoElemento = Integer.valueOf(simulacion.get(simulacion.size()-1)[1]);
            if (dimension <= anchoUltimoElemento) {
                simulacion.add(agregar);
            } else {
                String[] extra = {null,null};
                for (int j = simulacion.size() - 1; j >= 0; j--) {
                    if ((dimension > Integer.valueOf(simulacion.get(j)[1])) && !(agregar[0].equals("L"))) {
                        espacio = espacio - elementHeight(simulacion.get(j));
                        inicioEliminar = j;
                        if (espacio == 0) {
                            break;
                        }else if (espacio < 0) {
                            extra[0] = "E";
                            extra[1] = String.valueOf(Math.abs(espacio));
                        }
                    } else {
                        break;
                    }
                }
                simulacion.subList(inicioEliminar,simulacion.size()).clear();
                if (extra[0] != null) {
                    simulacion.add(extra);
                }
                simulacion.add(agregar);
            }
        }
        return sumSimulation(simulacion);
    }
    
    /**
     * Calcula el ancho total actual de la torre.
     * 
     * @return int - Numero entero positivo que representa el ancho total actual de la torre.
     */
    private int calculateCurrentWidth() {
        if (stack.isEmpty()) {
            return 0;
        }
        int newCurrentWidth = 0;
        for (int i = 0;i < stack.size();i++) {
            int elementWidth = stack.get(i).getWidth();
            if (elementWidth > newCurrentWidth) {
                newCurrentWidth = elementWidth;
            }
        }
        return newCurrentWidth;
    }
    
    /**
     * Apila una taza con numero identificador i a la torre, no permite agregar tazas repetidas, ni que superen las dimensiones maximas de la torre.
     * 
     * @param i Numero entero positivo, que es un identificador y es usado para calcular las dimensiones de la taza.
     * 
     * @return void
     */
    public void pushCup(int i) {
        int dimension = (2 * (validateNumber(i)) ) - 1;
        if (dimension > maxHeight) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        }
        if (dimension > maxWidth) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_WIDTH);
        }
        for (Elemento e : stack) {
            if (e instanceof Cup && ((Cup) e).getNumber() == i) {
                ok('N');
                throw new IllegalArgumentException(ALREADY_EXISTS);
            }
        }
        String[] nuevo = {"C",String.valueOf(dimension)};
        int alturaPosible = calculateHeightWithNewElement(nuevo);
        if (alturaPosible > maxHeight) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        } else {
            stack.add(new Cup(i));
            currentHeight = alturaPosible;
            currentWidth = calculateCurrentWidth();
            ok(' ');
        }
        if (isVisible) redraw();
    }
    
    /**
     * Elimina el elemento superior o recientemente apilado, solo si es una taza.
     * 
     * @return void
     */
    public void popCup() {
        if (stack.isEmpty()) {
            ok('N');
            throw new IllegalArgumentException(TOWER_EMPTY);
        }
        Elemento top = stack.get(stack.size() - 1);
        if (!(top instanceof Cup)) {
            ok('N');
            throw new IllegalArgumentException("El ultimo elemento de la torre no es una taza.");
        }
        stack.remove(stack.size() - 1);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok(' ');
        if (isVisible) redraw();
    }
    
    /**
     * Elimina la taza con numero identificador i; Si la taza esta cubierta por su tapa, también se elimina.
     *
     * @param i Numero entero positivo, que es el identificador de la taza que se desea eliminar.
     * 
     * @return void
     */
    public void removeCup(int i) {
        for (int j = 0; j < stack.size(); j++) {
            Elemento actual = stack.get(j);
            if (actual instanceof Cup && actual.getNumber() == i) {
                // Si tiene tapa encima
                if (j + 1 < stack.size()) {
                    Elemento arriba = stack.get(j + 1);
                    if (arriba instanceof Lid && arriba.getNumber() == i) {
                        stack.remove(j + 1);
                    }
                }
                stack.remove(j);
                currentHeight = calculateCurrentHeight();
                currentWidth = calculateCurrentWidth();
                if (isVisible) redraw();
                ok(' ');
                return;
            }
        }
        ok('N');
    }
    
    /**
     * Apila una tapa con numero identificador i a la torre, no permite agregar tapas repetidas, ni que superen las dimensiones maximas de la torre.
     * 
     * @param i Numero entero positivo, que es un identificador y es usado para calcular el ancho de la tapa.
     * 
     * @return void
     */
    public void pushLid(int i) {
        int dimension = (2 * (validateNumber(i)) ) - 1;
        if (1 > maxHeight) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        }
        if (dimension > maxWidth) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_WIDTH);
        }
        for (Elemento e : stack) {
            if (e instanceof Lid && ((Lid) e).getNumber() == i) {
                ok('N');
                throw new IllegalArgumentException(ALREADY_EXISTS);
            }
        }
        String[] nuevo = {"L",String.valueOf(dimension)};
        int alturaPosible = calculateHeightWithNewElement(nuevo);
        if (alturaPosible > maxHeight) {
            ok('N');
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        } else {
            stack.add(new Lid(i));
            currentHeight = alturaPosible;
            currentWidth = calculateCurrentWidth();
            ok(' ');
        }
        if (isVisible) redraw();
    }
    
    /**
     * Elimina el elemento superior o recientemente apilado, solo si es una tapa.
     * 
     * @return void
     */
    public void popLid() {
        if (stack.isEmpty()) {
            ok('N');
            throw new IllegalArgumentException(TOWER_EMPTY);
        }
        Elemento top = stack.get(stack.size() - 1);
        if (!(top instanceof Lid)) {
            ok('N');
            throw new IllegalArgumentException("El ultimo elemento de la torre no es una tapa.");
        }
        stack.remove(stack.size() - 1);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok(' ');
        if (isVisible) redraw();
    }
    
    /**
     * Elimina la tapa con numero identificador i.
     *
     * @param i Numero entero positivo, que es el identificador de la tapa que se desea eliminar.
     * 
     * @return void
     */
    public void removeLid(int i) {
        int index = -1;
        for (int j = 0; j < stack.size(); j++) {
            Elemento actual = stack.get(j);
            if (actual instanceof Lid && actual.getNumber() == i) {
                index = j;
                break;
            }
        }
        if (index == -1) {
            ok('N');
            throw new IllegalArgumentException("La tapa que se desea eliminar no existe.");
        }
        stack.remove(index);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok(' ');
        if (isVisible) redraw();
    }
    
    /**
     *  Ordena la torre por numero identificador (dimension), de mayor a menor, ademas tapando cada taza con su respectiva tapa si existe.
     *  
     *  @return void
     */
    public void orderTower() {
        ArrayList<Cup> cups = new ArrayList<Cup>();
        ArrayList<Lid> lids = new ArrayList<Lid>();
        for (int i = 0; i < stack.size(); i++) {
            Elemento e = stack.get(i);
            if (e instanceof Cup) cups.add((Cup) e);
            else if (e instanceof Lid) lids.add((Lid) e);
        }
        // Crear bloques (taza + posible tapa)
        ArrayList<ArrayList<Elemento>> bloques = new ArrayList<ArrayList<Elemento>>();
        for (int i = 0; i < cups.size(); i++) {
            Cup cup = cups.get(i);
            int numero = cup.getNumber();
            ArrayList<Elemento> bloque = new ArrayList<Elemento>();
            bloque.add(cup);
            for (int j = 0; j < lids.size(); j++) {
                if (lids.get(j).getNumber() == numero) {
                    bloque.add(lids.get(j));
                    lids.remove(j);
                    break;
                }
            }
            bloques.add(bloque);
        }
        // Tapas que quedaron solas
        for (int i = 0; i < lids.size(); i++) {
            ArrayList<Elemento> bloque = new ArrayList<Elemento>();
            bloque.add(lids.get(i));
            bloques.add(bloque);
        }
        // Ordenar bloques de mayor a menor
        for (int a = 0; a < bloques.size(); a++) {
            for (int b = a + 1; b < bloques.size(); b++) {
                int numA = bloques.get(a).get(0).getNumber();
                int numB = bloques.get(b).get(0).getNumber();
                if (numB > numA) {
                    ArrayList<Elemento> temp = bloques.get(a);
                    bloques.set(a, bloques.get(b));
                    bloques.set(b, temp);
                }
            }
        }
        //Reconstruir torre respetando límites
        ArrayList<Elemento> nueva = new ArrayList<Elemento>();
        int nuevaAltura = 0;

        for (int i = 0; i < bloques.size(); i++) {
            ArrayList<Elemento> bloque = bloques.get(i);
            int alturaBloque = 0;
            boolean cabe = true;
            for (int j = 0; j < bloque.size(); j++) {
                Elemento e = bloque.get(j);
                if (e.getWidth() > maxWidth) {
                    cabe = false;
                    break;
                }
                alturaBloque += e.getHeight();
            }
            if (cabe && nuevaAltura + alturaBloque <= maxHeight) {
                for (int j = 0; j < bloque.size(); j++) {
                    nueva.add(bloque.get(j));
                }
                nuevaAltura += alturaBloque;
            }
        }
        stack = nueva;
        currentHeight = nuevaAltura;
        ok(' ');
        if (isVisible) {
            redraw();
        }
    }
    
    /**
     * Invierte el orden de los bloques (taza con su tapa si existe), respetando los límites de tamaño.
     * 
     * @return void
     */
    public void reverseTower() {
        ArrayList<ArrayList<Elemento>> bloques = new ArrayList<ArrayList<Elemento>>();
        int i = 0;
        // Agrupar en bloques relacionados
        while (i < stack.size()) {
            ArrayList<Elemento> bloque = new ArrayList<Elemento>();
            Elemento actual = stack.get(i);
            if (actual instanceof Cup && i + 1 < stack.size()) {
                Elemento siguiente = stack.get(i + 1);
                if (siguiente instanceof Lid &&
                    siguiente.getNumber() == actual.getNumber()) {
                    bloque.add(actual);
                    bloque.add(siguiente);
                    bloques.add(bloque);
                    i = i + 2;
                    continue;
                }
            }
            bloque.add(actual);
            bloques.add(bloque);
            i = i + 1;
        }
        // Reconstruir en orden inverso
        ArrayList<Elemento> nueva = new ArrayList<Elemento>();
        int nuevaAltura = 0;
        for (int j = bloques.size() - 1; j >= 0; j--) {
            ArrayList<Elemento> bloque = bloques.get(j);
            int alturaBloque = 0;
            boolean cabe = true;
            for (int k = 0; k < bloque.size(); k++) {
                Elemento e = bloque.get(k);
                if (e.getWidth() > maxWidth) {
                    cabe = false;
                    break;
                }
                alturaBloque += e.getHeight();
            }
            if (cabe && nuevaAltura + alturaBloque <= maxHeight) {
                for (int k = 0; k < bloque.size(); k++) {
                    nueva.add(bloque.get(k));
                }
                nuevaAltura += alturaBloque;
            }
        }
        stack = nueva;
        currentHeight = nuevaAltura;
        ok(' ');
        if (isVisible) {
            redraw();
        }
    }
    
    /**
     * Intercambia dos elementos de la torre según su tipo y número. Si alguno es una taza con su tapa encima, el bloque se mueve completo
     * 
     * @param o1 arreglo con el tipo ("cup" o "lid") y número del primer elemento
     * @param o2 arreglo con el tipo ("cup" o "lid") y número del segundo elemento
     */
    public void swap(String[] o1, String[] o2) 
    {
        if (o1 == null || o2 == null || o1.length < 2 || o2.length < 2) {
            ok('N');
            return;
        }

        int idx1 = findIndex(o1[0], o1[1]);
        int idx2 = findIndex(o2[0], o2[1]);

        if (idx1 == -1 || idx2 == -1 || idx1 == idx2) {
            ok('N');
            return;
        }
        //Verificar si alguno esta tapado 
        boolean cup1Lidded = isCupLidded(idx1);
        boolean cup2Lidded = isCupLidded(idx2);
        
        if (idx1 > idx2) {
            int temp = idx1; idx1 = idx2; idx2 = temp;
            boolean tempB = cup1Lidded; cup1Lidded = cup2Lidded; cup2Lidded = tempB;
        }
        ArrayList<Elemento> bloque2 = extractBlock(idx2, cup2Lidded);
        ArrayList<Elemento> bloque1 = extractBlock(idx1, cup1Lidded);
        int insertPos1 = idx1;
        for (int k = 0; k < bloque2.size(); k++) {
            stack.add(insertPos1 + k, bloque2.get(k));
        }
        int insertPos2 = idx2 - bloque1.size() + bloque2.size();
        for (int k = 0; k < bloque1.size(); k++) {
            stack.add(insertPos2 + k, bloque1.get(k));
        }

        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok(' ');
        if (isVisible) redraw();
    }
    
    /**
     * Tapa todas las tazas que tienen su tapa disponible en la torre, colocando la tapa inmediatamente encima de su taza correspondiente.
     * Si la tapa ya está sobre la taza, no hace nada.
     * 
     * @return void
     */
    public void cover() {
        boolean cambio = true;
        while (cambio) {
            cambio = false;
            for (int i = 0; i < stack.size(); i++) {
                Elemento actual = stack.get(i);
                if (!(actual instanceof Cup)) continue;
                    int num = actual.getNumber();
                    // Buscar si ya tiene tapa encima
                    if (i + 1 < stack.size()) {
                        Elemento siguiente = stack.get(i + 1);
                        if (siguiente instanceof Lid && siguiente.getNumber() == num) {
                            continue; // Ya está tapada
                        }
                    }
                    // Buscar la tapa con el mismo número en otro lugar de la pila
                    int lidIdx = -1;
                    for (int j = 0; j < stack.size(); j++) {
                        if (j == i) continue;
                        Elemento e = stack.get(j);
                        if (e instanceof Lid && e.getNumber() == num) {
                            lidIdx = j;
                            break;
                        }
                    }
                    if (lidIdx == -1) continue;
                        // Mover la tapa justo encima de la taza
                    Elemento lid = stack.get(lidIdx);
                    stack.remove(lidIdx);
                    int newCupIdx = stack.indexOf(actual);
                    stack.add(newCupIdx + 1, lid);
                    int nuevaAltura = calculateCurrentHeight();
                    if (nuevaAltura > maxHeight) {
                    // Revertir si no cabe
                        stack.remove(newCupIdx + 1);
                        stack.add(lidIdx, lid);
                    } else {
                        currentHeight = nuevaAltura;
                        cambio = true;
                        break;
                }
            }
        }
        ok(' ');
        if (isVisible) redraw();
    }
    
    /**
     * Retorna la altura actual de la torre.
     * 
     * @return int - Altura actual de la torre.
     */
    public int height() {
        return currentHeight;
    }
    
    /**
     * Retorna los números de las tazas que tienen su tapa colocada encima, ordenados de menor a mayor.
     * 
     * @return int[] - Arreglo con los números de las tazas tapadas, si no hay ninguna, retorna un arreglo vacío.
     */
    public int[] lidedCups() {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for (int i = 0; i < stack.size() - 1; i++) {
            Elemento actual = stack.get(i);
            Elemento siguiente = stack.get(i + 1);
            if (actual instanceof Cup && siguiente instanceof Lid && actual.getNumber() == siguiente.getNumber()) {
                numeros.add(actual.getNumber());
            }
        }

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(j) < numeros.get(i)) {
                    int aux = numeros.get(i);
                    numeros.set(i, numeros.get(j));
                    numeros.set(j, aux);
                }
            }
        }
        int[] resultado = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            resultado[i] = numeros.get(i);
        }
        ok(' ');
        return resultado;
    }
    
    /**
     * Retorna una representación de los elementos apilados en la torre. Cada fila del arreglo contiene: [0]-> tipo de elemento ("cup" o "lid")
     * y [1] -> número del elemento.
     *
     * @return String[][] - Matriz con la información de los elementos en orden de apilamiento.
     */
    public String[][] stackingItems() {
        // Crear matriz con tantas filas como elementos haya en la torre
        String[][] resultado = new String[stack.size()][2];
        // Recorrer la pila en el orden actual
        for (int i = 0; i < stack.size(); i++) {
            Elemento actual = stack.get(i);
            // Determinar el tipo de elemento
            if (actual instanceof Cup) {
                resultado[i][0] = "cup";
            } else {
                resultado[i][0] = "lid";
            }
            // Guardar el número del elemento como texto
            resultado[i][1] = String.valueOf(actual.getNumber());
        }
        ok(' ');
        return resultado;
    }
    
    /**
     * Busca el primer par de elementos cuyo intercambio reduzca la altura de la torre no realiza el intercambio.
     * 
     * @return String[][] - Matriz con el tipo y número de los dos elementos a intercambiar, si no existe ningún par que reduzca la altura, 
     * retorna una matriz vacia.
     */
    public String[][] swapToReduce() {
        int alturaActual = calculateCurrentHeight();
        // Probar todos los pares posibles de índices distintos
        for (int i = 0; i < stack.size(); i++) {
            for (int j = i + 1; j < stack.size(); j++) {
                // Simular el intercambio
                Elemento temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                int nuevaAltura = calculateCurrentHeight();
                // Revertir el intercambio
                temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                // Si la nueva altura es menor, retornar este par
                if (nuevaAltura < alturaActual) {
                    Elemento e1 = stack.get(i);
                    Elemento e2 = stack.get(j);
                    String[][] resultado = new String[2][2];
                    resultado[0][0] = (e1 instanceof Cup) ? "cup" : "lid";
                    resultado[0][1] = String.valueOf(e1.getNumber());
                    resultado[1][0] = (e2 instanceof Cup) ? "cup" : "lid";
                    resultado[1][1] = String.valueOf(e2.getNumber());
                    ok(' ');
                    return resultado;
                }
            }
        }
        ok(' ');
        return new String[0][0];
    }
    
    /**
     * Hace visible la torre en el canvas. Solo se permite si las dimensiones no superan 600x600. Actualiza el estado de la operación.
     * 
     * @return void
     */
    public void makeVisible() {
        // Obtener instancia del canvas y activar visualización
        canvas = Canvas.getCanvas();
        isVisible = true;
        // Dibujar la torre actual
        redraw();
        ok(' ');
    }
    
    /**
     * Oculta la visualización de la torre.
     * 
     * @return void
     */
    public void makeInvisible()
    {
        // Si ya está invisible, no hacer nada
        if (!isVisible){
            ok('N');
            return;
        }
        canvas.setVisible(false);
        isVisible = false;
        ok(' ');
    }
    
    /**
     * Cierra completamente la aplicación. Si la torre está visible, primero oculta el canvas.
     * 
     * @return void
     */
    public void exit() 
    {
        if (isVisible) {
            canvas.setVisible(false);
        }
        ok(' ');
        System.exit(0); 
    }
    
    /**
     * Dibuja la estructura de la torre, tanto su piso como su regla con unidades en su lado izquierdo.
     * 
     * @param canvasWidth Altura en pixeles del canvas usado.
     * @param canvasHeight Ancho en pixeles del canvas usado.
     * @param escala Cantidad de pixeles del canvas, que equivalen a una unidad de medida de la torre.
     * 
     * @return void
     */
    private void drawTower(int canvasWidth, int canvasHeight, int escala) {
        Rectangle floor = new Rectangle(canvasWidth, (int)(canvasHeight / 80));
        floor.setPosition(0, canvasHeight - (int)(canvasHeight / 20));
        floor.makeVisible("black");
    
        int ruleWidth = (int)(canvasWidth / 160);
        Rectangle rule = new Rectangle(ruleWidth, canvasHeight - (canvasHeight / 20));
        rule.setPosition((int)(canvasWidth / 20), 0);
        rule.makeVisible("black");
    
        for(int y = canvasHeight - (int)(canvasHeight / 20); y >= 0; y -= escala){
            Rectangle mark = new Rectangle((int)(canvasWidth / 20), (int)(canvasHeight / 320));
            mark.setPosition(0, y);
            mark.makeVisible("black");
        }
    }
    
    /**
     * Redibuja completamente la torre en el canvas, centrando cada elemento horizontalmente.
     * 
     * @return void
     */
    private void redraw() {
        int canvasWidthForRule = canvas.getWidth();
        int canvasHeightForRule = canvas.getHeight();
        int canvasHeight = canvas.getHeight() - (canvas.getHeight() / 20);
        int canvasWidth = canvas.getWidth() - (canvas.getWidth() / 20) - (canvas.getWidth() / 160);
        int escala = (int) canvasHeight / maxHeight;
        
        if (!isVisible) return;
        canvas.ClearAll();
        drawTower(canvasWidthForRule, canvasHeightForRule, escala);
        int y = canvasHeight;
        for (int i = 0; i < stack.size(); i++) {
            Elemento elementoActual = stack.get(i);
            int number = elementoActual.getNumber();
            String color = getColorForNumber(number);
            // Posicionar desde la base hacia arriba
            int widthEscalado = elementoActual.getWidth() * escala;
            int heightEscalado = elementoActual.getHeight() * escala;
            y -= heightEscalado;
            Rectangle rectangulo = new Rectangle(widthEscalado,heightEscalado);
            // Centrar horizontalmente
            int posicionX = ((canvasWidth - widthEscalado) / 2) + (canvas.getWidth() / 20) + (canvas.getWidth() / 160);
            rectangulo.setPosition(posicionX, y);
            visualElements.put(elementoActual, rectangulo);
            rectangulo.makeVisible(color);
            if (elementoActual instanceof Cup) {
                Rectangle vacio = new Rectangle( widthEscalado - (2 * escala), heightEscalado - escala);
                vacio.setPosition(posicionX + escala,y);
                vacio.makeVisible("white");
            }
        }
    }
    
    /**
     * Valida que el numero identificador de un elemento sea mayor o igual a 1.
     * 
     * @param number Numero a validar segun la subclase de elemento.
     * 
     * @return int - Devuelve el mismo numero identificador que recibio.
     */
    private int validateNumber(int number)
    {
        if (number < 1) {
            ok('N');
            throw new IllegalArgumentException("Los numeros identificadores comienzan desde el numero 1.");
        }
        return number;
    }
    
    /**
     * Encuentra el índice en la pila de un elemento por tipo y número.
     * 
     * @param type "cup" o "lid".
     * @param number Número del elemento como String.
     * 
     * @return int - Índice en la pila, o -1 si no existe
     */
    private int findIndex(String type, String number) {
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return -1;
        }
        for (int i = 0; i < stack.size(); i++) {
            Elemento e = stack.get(i);
            boolean tipoOk = (type.equals("cup") && e instanceof Cup) ||
                             (type.equals("lid") && e instanceof Lid);
            if (tipoOk && e.getNumber() == num) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Verifica si la taza en la posición indicada tiene su tapa encima.
     * 
     * @param idx Índice de la taza en la pila.
     * 
     * @return boolean - true si tiene tapa encima, false en caso contrario.
     */
    private boolean isCupLidded(int idx) {
        if (!(stack.get(idx) instanceof Cup)) return false;
        if (idx + 1 >= stack.size()) return false;
        Elemento next = stack.get(idx + 1);
        return next instanceof Lid && next.getNumber() == stack.get(idx).getNumber();
    }
    
    /**
     * Extrae de la pila el bloque del elemento en la posición indicada,si es una taza tapada, extrae la taza y su tapa como bloque.
     * 
     * @param idx Índice del elemento a extraer.
     * @param lidded true si la taza tiene su tapa encima y se extrae como bloque.
     * 
     * @return ArrayList<Elemento> - Lista con el bloque extraído (uno o dos elementos).
     */
    private ArrayList<Elemento> extractBlock(int idx, boolean lidded) {
        ArrayList<Elemento> bloque = new ArrayList<Elemento>();
        if (lidded) {
            bloque.add(stack.remove(idx));
            bloque.add(stack.remove(idx)); // tapa que quedó en idx tras remover la taza
        } else {
            bloque.add(stack.remove(idx));
        }
        return bloque;
    }
    
    /**
     * Para cada numero identificador de un elemento (Independientemente si es Cup o Lid), le asigna un color.
     * 
     * @param number Numero identificador de un elemento.
     * 
     * @return String - Nombre del color en minusculas, se pasa como parametro para el canvas.
     */
    private String getColorForNumber(int number) {
        float goldenRatio = 0.618033988749895f;
        float hue = (number * goldenRatio) % 1.0f;
        java.awt.Color color = java.awt.Color.getHSBColor(hue, 0.65f, 0.85f);
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
