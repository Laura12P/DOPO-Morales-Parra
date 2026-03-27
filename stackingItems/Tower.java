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
    private final static String DIMENSION_EXCEEDS_HEIGHT = "Al insertar el nuevo Element, se supera la altura maxima de la torre.";
    private final static String DIMENSION_EXCEEDS_WIDTH = "Al insertar el nuevo Element, se supera el ancho maximo de la torre.";
    private final static String ALREADY_EXISTS = "Ya existe este numero de Element en la torre.";
    private final static String TOWER_EMPTY = "La torre esta vacia.";
    private int maxWidth;
    private int maxHeight;
    private int currentHeight;
    private int currentWidth;
    private ArrayList<Element> stack;
    private HashMap<Element, Rectangle> visualElements;
    private boolean ok;
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
            throw new IllegalArgumentException("Las dimensiones deben ser numeros enteros positivos.");
        }
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        currentHeight = 0;
        currentWidth = 0;
        stack = new ArrayList<Element>();
        visualElements = new HashMap<Element, Rectangle>();
        isVisible = false;
    }
    
    /**
     * Crea una torre apilando tazas desde la mas grande (cups), hasta la mas pequeña.
     * 
     * @param cups Cantidad de tazas a crear (deben ser positivas).
     */
    public Tower(int cups)
    {
        if (cups <= 0) {
            throw new IllegalArgumentException("El número de tazas debe ser un numero entero positivo.");
        }
        //Inicializamos valores teniendo en cuenta la altura de las tazas apiladas desde la pequeña a la mas grande (Altura maxima posible)
        maxHeight = cups * cups;
        maxWidth = (2 * cups) - 1;
        currentHeight = (2 * cups) - 1;
        currentWidth = (2 * cups) - 1;
        stack = new ArrayList<Element>();
        visualElements = new HashMap<Element, Rectangle>();
        isVisible = false;
        for (int i = cups; i >= 1; i--) {
            stack.add(new Cup(i));
        }
    }
    
    /**
     * Retorna el valor de verdad del estado de la ultima operacion realizada sobre el objeto.
     * 
     * @return boolean - Valor de verdad que responde al estado "ok" de la ultima operacion realizada sobre el objeto.
     */
    public boolean ok() {
        return ok;
    }
    
    /**
     * Realiza la suma total de la altura de todos los Element simulados, de acuerdo a su subclase. 
     * 
     * @param sim Es la simulacion de los Elements de la torre.
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
     * Devuelve la altura de un Element, segun su subclase.
     * 
     * @param element La Tuple con la subclase y ancho del Element.
     * 
     * @return int - Numero entero positivo que representa la altura del Element recibido.
     */
    private int elementHeight(String[] element) {
        if (element[0].equals("L")) {
            return 1;
        } else {
            return Integer.valueOf(element[1]);
        }
    }
    
    /**
     * Añade un elemento a la simulacion de la torre, teniendo en cuenta su subclase y sus dimensiones.
     * 
     * @param element Elemento que se desea añadir a la simulacion.
     */
    private void addToSimulation(ArrayList<String[]> simulation, String[] addElement, int dimension, int space) {
        int startClear = simulation.size();
        int widthLastElement = Integer.valueOf(simulation.get(simulation.size()-1)[1]);
        if (dimension <= widthLastElement) {
            simulation.add(addElement);
        } else {
            String[] extra = {null,null};
            for (int j = simulation.size() - 1; j >= 0; j--) {
                if ((dimension > Integer.valueOf(simulation.get(j)[1])) && !(addElement[0].equals("L"))) {
                    space = space - elementHeight(simulation.get(j));
                    startClear = j;
                    if (space == 0) {
                        break;
                    }else if (space < 0) {
                        extra[0] = "E";
                        extra[1] = String.valueOf(Math.abs(space));
                    }
                } else {
                    break;
                }
            }
            simulation.subList(startClear,simulation.size()).clear();
            if (extra[0] != null) {
                simulation.add(extra);
            }
            simulation.add(addElement);
        }
    }
    
    /**
     * Calcula la altura total actual de la torre.
     * 
     * @return int - Numero entero positivo que representa la altura total actual de la torre.
     */
    private int calculateCurrentHeight() {
        if (stack.isEmpty()) {
            return 0;
        }
        ArrayList<String[]> simulation = new ArrayList<String[]>();
        for (int i = stack.size() - 1;i >= 0; i--) {
            Element element = stack.get(i);
            String[] addElement = element.Tuple();
            int dimension = element.getWidth();
            int space = element.getHeight() - 1;
            if (simulation.isEmpty()) {
                simulation.add(addElement);
            } else {
                addToSimulation(simulation, addElement, dimension, space);
            }
        }
        return sumSimulation(simulation);
    }
    
    /**
     * Calcula la altura total actual de la torre, pero teniendo en cuenta un nuevo Element apilado.
     * 
     * @param nuevo La Tuple con la subclase y ancho del nuevo Element.
     * 
     * @return int - Numero entero positivo que representa la altura total de la torre, en caso de apilarse el nuevo Element.
     */
    private int calculateHeightWithNewElement(String[] newElement) {
        if (stack.isEmpty()) {
            return elementHeight(newElement);
        }
        ArrayList<String[]> simulation = new ArrayList<String[]>();
        simulation.add(newElement);
        for (int i = stack.size() - 1;i >= 0; i--) {
            Element element = stack.get(i);
            String[] addElement = element.Tuple();
            int dimension = element.getWidth();
            int space = element.getHeight() - 1;
            //Inicio de comprobar la altura
            addToSimulation(simulation, addElement, dimension, space);
        }
        return sumSimulation(simulation);
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
     */
    public void pushCup(int i) {
        ok = false;
        try {
            validateNumber(i);
        } catch (Exception e) {
            return;
        }
        int dimension = (2 * i) - 1;
        if (dimension > maxHeight) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        }
        if (dimension > maxWidth) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_WIDTH);
        }
        for (Element e : stack) {
            if (e instanceof Cup && ((Cup) e).getNumber() == i) {
                throw new IllegalArgumentException(ALREADY_EXISTS);
            }
        }
        String[] newElement = {"C",String.valueOf(dimension)};
        int possibleHeight = calculateHeightWithNewElement(newElement);
        if (possibleHeight > maxHeight) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        } else {
            stack.add(new Cup(i));
            currentHeight = possibleHeight;
            currentWidth = calculateCurrentWidth();
        }
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Elimina el Element superior o recientemente apilado, solo si es una taza.
     */
    public void popCup() {
        ok = false;
        if (stack.isEmpty()) {
            throw new IllegalArgumentException(TOWER_EMPTY);
        }
        Element top = stack.get(stack.size() - 1);
        if (!(top instanceof Cup)) {
            throw new IllegalArgumentException("El ultimo Element de la torre no es una taza.");
        }
        stack.remove(stack.size() - 1);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Devuelve los indices del stack que apuntan a la Cup y Lid de igual numero identificador, la posicion 0 del arreglo es el indice de la Cup, la posicion 1 
     * es el indice de la Lid, y la posicion 2 es un bit de verdad, que indica si la Lid esta tapando a la Cup, se devulve -1 en la respectiva posicion del Element
     * que no se haya encontrado dentro del stack.
     * 
     * @return int[] - Arreglo de enteros de 3 posiciones que contiene la posicion de la Cup y Lid de igual number, y en la tercera posicion es un bit de verdad
     * que indica si la Lid esta tapando a la taza.
     */
    public int[] cupAndLidPositions(int number) {
        int[] result = {-1,-1,0};
        if (stack.isEmpty()) {
            return result;
        }
        ArrayList<String[]> simulation = new ArrayList<String[]>();
        for (int i = stack.size() - 1;i >= 0; i--) {
            Element element = stack.get(i);
            if (element instanceof Lid && element.getNumber() == number) {
                result[1] = i;
            }
            if (result[1] != -1 && result[0] < result[1]) {
                String[] addElement = element.Tuple();
                int dimension = element.getWidth();
                int space = element.getHeight() - 1;
                if (simulation.isEmpty()) {
                    simulation.add(addElement);
                } else {
                    addToSimulation(simulation, addElement, dimension, space);
                }
                if (element instanceof Cup && element.getNumber() == number) {
                    result[0] = i;
                    break;
                }
            }
            if (element instanceof Cup && element.getNumber() == number) {
                result[0] = i;
            }
            if (result[1] != -1 && result[0] != -1 && result[0] > result[1]) {
                return result;
            }
        }
        if (simulation.size() == 2) {
            result[2] = 1;
        }
        return result;
    }
    
    /**
     * Elimina la taza con numero identificador i; Si la taza esta cubierta por su tapa, también se elimina.
     *
     * @param i Numero entero positivo, que es el identificador de la taza que se desea eliminar.
     */
    public void removeCup(int i) {
        ok = false;
        if (stack.isEmpty()) {
            throw new IllegalArgumentException(TOWER_EMPTY);
        }
        int[] positions = cupAndLidPositions(i);
        if (positions[2] == 1) {
            stack.remove(positions[1]);
        }
        if (positions[0] == -1) {
            throw new IllegalArgumentException("La taza que se desea eliminar no existe.");
        }
        stack.remove(positions[0]);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Apila una tapa con numero identificador i a la torre, no permite agregar tapas repetidas, ni que superen las dimensiones maximas de la torre.
     * 
     * @param i Numero entero positivo, que es un identificador y es usado para calcular el ancho de la tapa.
     */
    public void pushLid(int i) {
        ok = false;
        int dimension = (2 * (validateNumber(i)) ) - 1;
        if (1 > maxHeight) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        }
        if (dimension > maxWidth) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_WIDTH);
        }
        for (Element e : stack) {
            if (e instanceof Lid && ((Lid) e).getNumber() == i) {
                throw new IllegalArgumentException(ALREADY_EXISTS);
            }
        }
        String[] newElement = {"L",String.valueOf(dimension)};
        int possibleHeight = calculateHeightWithNewElement(newElement);
        if (possibleHeight > maxHeight) {
            throw new IllegalArgumentException(DIMENSION_EXCEEDS_HEIGHT);
        } else {
            stack.add(new Lid(i));
            currentHeight = possibleHeight;
            currentWidth = calculateCurrentWidth();
        }
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Elimina el Element superior o recientemente apilado, solo si es una tapa.
     */
    public void popLid() {
        ok = false;
        if (stack.isEmpty()) {
            throw new IllegalArgumentException(TOWER_EMPTY);
        }
        Element top = stack.get(stack.size() - 1);
        if (!(top instanceof Lid)) {
            throw new IllegalArgumentException("El ultimo Element de la torre no es una tapa.");
        }
        stack.remove(stack.size() - 1);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Elimina la tapa con numero identificador i.
     *
     * @param i Numero entero positivo, que es el identificador de la tapa que se desea eliminar.
     */
    public void removeLid(int i) {
        ok = false;
        int index = -1;
        for (int j = 0; j < stack.size(); j++) {
            Element current = stack.get(j);
            if (current instanceof Lid && current.getNumber() == i) {
                index = j;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("La tapa que se desea eliminar no existe.");
        }
        stack.remove(index);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     *  Ordena la torre por numero identificador (dimension), de mayor a menor, ademas tapando cada taza con su respectiva tapa si existe.
     */
    public void orderTower() {
        ok = false;
        ArrayList<Cup> cups = new ArrayList<Cup>();
        ArrayList<Lid> lids = new ArrayList<Lid>();
        for (int i = 0; i < stack.size(); i++) {
            Element e = stack.get(i);
            if (e instanceof Cup) cups.add((Cup) e);
            else if (e instanceof Lid) lids.add((Lid) e);
        }
        // Crear bloques (taza + posible tapa)
        ArrayList<ArrayList<Element>> blocks = new ArrayList<ArrayList<Element>>();
        for (int i = 0; i < cups.size(); i++) {
            Cup cup = cups.get(i);
            int number = cup.getNumber();
            ArrayList<Element> block = new ArrayList<Element>();
            block.add(cup);
            for (int j = 0; j < lids.size(); j++) {
                if (lids.get(j).getNumber() == number) {
                    block.add(lids.get(j));
                    lids.remove(j);
                    break;
                }
            }
            blocks.add(block);
        }
        // Tapas que quedaron solas
        for (int i = 0; i < lids.size(); i++) {
            ArrayList<Element> block = new ArrayList<Element>();
            block.add(lids.get(i));
            blocks.add(block);
        }
        // Ordenar bloques de mayor a menor
        for (int a = 0; a < blocks.size(); a++) {
            for (int b = a + 1; b < blocks.size(); b++) {
                int numA = blocks.get(a).get(0).getNumber();
                int numB = blocks.get(b).get(0).getNumber();
                if (numB > numA) {
                    ArrayList<Element> temp = blocks.get(a);
                    blocks.set(a, blocks.get(b));
                    blocks.set(b, temp);
                }
            }
        }
        //Reconstruir torre respetando límites
        ArrayList<Element> newStack = new ArrayList<Element>();
        int newHeight = 0;

        for (int i = 0; i < blocks.size(); i++) {
            ArrayList<Element> block = blocks.get(i);
            int blockHeight = 0;
            boolean fit = true;
            for (int j = 0; j < block.size(); j++) {
                Element e = block.get(j);
                if (e.getWidth() > maxWidth) {
                    fit = false;
                    break;
                }
                blockHeight += e.getHeight();
            }
            if (fit && newHeight + blockHeight <= maxHeight) {
                for (int j = 0; j < block.size(); j++) {
                    newStack.add(block.get(j));
                }
                newHeight += blockHeight;
            }
        }
        stack = newStack;
        currentHeight = newHeight;
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Invierte el orden de los bloques (taza con su tapa si existe), respetando los límites de tamaño.
     */
    public void reverseTower() {
        ok = false;
        ArrayList<ArrayList<Element>> blocks = new ArrayList<ArrayList<Element>>();
        int i = 0;
        // Agrupar en bloques relacionados
        while (i < stack.size()) {
            ArrayList<Element> block = new ArrayList<Element>();
            Element current = stack.get(i);
            if (current instanceof Cup && i + 1 < stack.size()) {
                Element next = stack.get(i + 1);
                if (next instanceof Lid && next.getNumber() == current.getNumber()) {
                    block.add(current);
                    block.add(next);
                    blocks.add(block);
                    i = i + 2;
                    continue;
                }
            }
            block.add(current);
            blocks.add(block);
            i = i + 1;
        }
        // Reconstruir en orden inverso
        ArrayList<Element> newStack = new ArrayList<Element>();
        int newHeight = 0;
        for (int j = blocks.size() - 1; j >= 0; j--) {
            ArrayList<Element> block = blocks.get(j);
            int blockHeight = 0;
            boolean fit = true;
            for (int k = 0; k < block.size(); k++) {
                Element e = block.get(k);
                if (e.getWidth() > maxWidth) {
                    fit = false;
                    break;
                }
                blockHeight += e.getHeight();
            }
            if (fit && newHeight + blockHeight <= maxHeight) {
                for (int k = 0; k < block.size(); k++) {
                    newStack.add(block.get(k));
                }
                newHeight += blockHeight;
            }
        }
        stack = newStack;
        currentHeight = newHeight;
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Intercambia dos Elements de la torre según su tipo y número. Si alguno es una taza con su tapa encima, el bloque se mueve completo
     * 
     * @param o1 arreglo con el tipo ("cup" o "lid") y número del primer Element
     * @param o2 arreglo con el tipo ("cup" o "lid") y número del segundo Element
     */
    public void swap(String[] o1, String[] o2) {
        ok = false;
        if (o1 == null || o2 == null || o1.length < 2 || o2.length < 2) {
            return;
        }

        int idx1 = findIndex(o1[0], o1[1]);
        int idx2 = findIndex(o2[0], o2[1]);

        if (idx1 == -1 || idx2 == -1 || idx1 == idx2) {
            return;
        }
        //Verificar si alguno esta tapado 
        boolean cup1Lidded = isCupLidded(idx1);
        boolean cup2Lidded = isCupLidded(idx2);
        
        if (idx1 > idx2) {
            int temp = idx1; idx1 = idx2; idx2 = temp;
            boolean tempB = cup1Lidded; cup1Lidded = cup2Lidded; cup2Lidded = tempB;
        }
        ArrayList<Element> block2 = extractBlock(idx2, cup2Lidded);
        ArrayList<Element> block1 = extractBlock(idx1, cup1Lidded);
        int insertPos1 = idx1;
        for (int k = 0; k < block2.size(); k++) {
            stack.add(insertPos1 + k, block2.get(k));
        }
        int insertPos2 = idx2 - block1.size() + block2.size();
        for (int k = 0; k < block1.size(); k++) {
            stack.add(insertPos2 + k, block1.get(k));
        }

        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Tapa todas las tazas que tienen su tapa disponible en la torre, colocando la tapa inmediatamente encima de su taza correspondiente.
     * Si la tapa ya está sobre la taza, no hace nada.
     */
    public void cover() {
        ok = false;
        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < stack.size(); i++) {
                Element current = stack.get(i);
                if (!(current instanceof Cup)) continue;
                    int num = current.getNumber();
                    // Buscar si ya tiene tapa encima
                    if (i + 1 < stack.size()) {
                        Element next = stack.get(i + 1);
                        if (next instanceof Lid && next.getNumber() == num) {
                            continue; // Ya está tapada
                        }
                    }
                    // Buscar la tapa con el mismo número en otro lugar de la pila
                    int lidIdx = -1;
                    for (int j = 0; j < stack.size(); j++) {
                        if (j == i) continue;
                        Element e = stack.get(j);
                        if (e instanceof Lid && e.getNumber() == num) {
                            lidIdx = j;
                            break;
                        }
                    }
                    if (lidIdx == -1) continue;
                        // Mover la tapa justo encima de la taza
                    Element lid = stack.get(lidIdx);
                    stack.remove(lidIdx);
                    int newCupIdx = stack.indexOf(current);
                    stack.add(newCupIdx + 1, lid);
                    int newHeight = calculateCurrentHeight();
                    if (newHeight > maxHeight) {
                    // Revertir si no cabe
                        stack.remove(newCupIdx + 1);
                        stack.add(lidIdx, lid);
                    } else {
                        currentHeight = newHeight;
                        change = true;
                        break;
                }
            }
        }
        ok = true;
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
        ok = false;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < stack.size() - 1; i++) {
            Element current = stack.get(i);
            Element next = stack.get(i + 1);
            if (current instanceof Cup && next instanceof Lid && current.getNumber() == next.getNumber()) {
                numbers.add(current.getNumber());
            }
        }

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(j) < numbers.get(i)) {
                    int aux = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, aux);
                }
            }
        }
        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        ok = true;
        return result;
    }
    
    /**
     * Retorna una representación de los Elements apilados en la torre. Cada fila del arreglo contiene: [0]-> tipo de Element ("cup" o "lid")
     * y [1] -> número del Element.
     *
     * @return String[][] - Matriz con la información de los Elements en orden de apilamiento.
     */
    public String[][] stackingItems() {
        ok = false;
        // Crear matriz con tantas filas como Elements haya en la torre
        String[][] result = new String[stack.size()][2];
        // Recorrer la pila en el orden actual
        for (int i = 0; i < stack.size(); i++) {
            Element current = stack.get(i);
            // Determinar el tipo de Element
            if (current instanceof Cup) {
                result[i][0] = "cup";
            } else {
                result[i][0] = "lid";
            }
            // Guardar el número del Element como texto
            result[i][1] = String.valueOf(current.getNumber());
        }
        ok = true;
        return result;
    }
    
    /**
     * Busca el primer par de Elements cuyo intercambio reduzca la altura de la torre no realiza el intercambio.
     * 
     * @return String[][] - Matriz con el tipo y número de los dos Elements a intercambiar, si no existe ningún par que reduzca la altura, 
     * retorna una matriz vacia.
     */
    public String[][] swapToReduce() {
        ok = false;
        int currentHeightVariable = calculateCurrentHeight();
        // Probar todos los pares posibles de índices distintos
        for (int i = 0; i < stack.size(); i++) {
            for (int j = i + 1; j < stack.size(); j++) {
                // Simular el intercambio
                Element temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                int newHeight = calculateCurrentHeight();
                // Revertir el intercambio
                temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                // Si la nueva altura es menor, retornar este par
                if (newHeight < currentHeightVariable) {
                    Element e1 = stack.get(i);
                    Element e2 = stack.get(j);
                    String[][] result = new String[2][2];
                    result[0][0] = (e1 instanceof Cup) ? "cup" : "lid";
                    result[0][1] = String.valueOf(e1.getNumber());
                    result[1][0] = (e2 instanceof Cup) ? "cup" : "lid";
                    result[1][1] = String.valueOf(e2.getNumber());
                    return result;
                }
            }
        }
        ok = true;
        return new String[0][0];
    }
    
    /**
     * Hace visible la torre en el canvas. Solo se permite si las dimensiones no superan 600x600. Actualiza el estado de la operación.
     */
    public void makeVisible() {
        ok = false;
        // Obtener instancia del canvas y activar visualización
        canvas = Canvas.getCanvas();
        isVisible = true;
        // Dibujar la torre actual
        redraw();
        ok = true;
    }
    
    /**
     * Oculta la visualización de la torre.
     */
    public void makeInvisible() {
        ok = false;
        // Si ya está invisible, no hacer nada
        if (!isVisible){
            return;
        }
        canvas.setVisible(false);
        isVisible = false;
        ok = true;
    }
    
    /**
     * Cierra completamente la aplicación. Si la torre está visible, primero oculta el canvas.
     */
    public void exit() {
        if (isVisible) {
            canvas.setVisible(false);
        }
        System.exit(0);
    }
    
    /**
     * Dibuja la estructura de la torre, tanto su piso como su regla con unidades en su lado izquierdo.
     * 
     * @param canvasWidth Altura en pixeles del canvas usado.
     * @param canvasHeight Ancho en pixeles del canvas usado.
     * @param escala Cantidad de pixeles del canvas, que equivalen a una unidad de medida de la torre.
     */
    private void drawTower(int canvasWidth, int canvasHeight, int scale) {
        Rectangle floor = new Rectangle(canvasWidth, (int)(canvasHeight / 80));
        floor.setPosition(0, canvasHeight - (int)(canvasHeight / 20));
        floor.makeVisible("black");
    
        int ruleWidth = (int)(canvasWidth / 160);
        Rectangle rule = new Rectangle(ruleWidth, canvasHeight - (canvasHeight / 20));
        rule.setPosition((int)(canvasWidth / 20), 0);
        rule.makeVisible("black");
    
        for(int y = canvasHeight - (int)(canvasHeight / 20); y >= 0; y -= scale){
            Rectangle mark = new Rectangle((int)(canvasWidth / 20), (int)(canvasHeight / 320));
            mark.setPosition(0, y);
            mark.makeVisible("black");
        }
    }
    
    /**
     * Redibuja completamente la torre en el canvas, centrando cada Element horizontalmente.
     */
    private void redraw() {
        int canvasWidthForRule = canvas.getWidth();
        int canvasHeightForRule = canvas.getHeight();
        int canvasHeight = canvas.getHeight() - (canvas.getHeight() / 20);
        int canvasWidth = canvas.getWidth() - (canvas.getWidth() / 20) - (canvas.getWidth() / 160);
        int scale = (int) canvasHeight / maxHeight;
        
        if (!isVisible) return;
        canvas.ClearAll();
        drawTower(canvasWidthForRule, canvasHeightForRule, scale);
        int y = canvasHeight;
        for (int i = 0; i < stack.size(); i++) {
            Element ElementActual = stack.get(i);
            int number = ElementActual.getNumber();
            String color = getColorForNumber(number);
            // Posicionar desde la base hacia arriba
            int scaledWidth = ElementActual.getWidth() * scale;
            int scaledHeight = ElementActual.getHeight() * scale;
            y -= scaledHeight;
            Rectangle rectangle = new Rectangle(scaledWidth,scaledHeight);
            // Centrar horizontalmente
            int posicionX = ((canvasWidth - scaledWidth) / 2) + (canvas.getWidth() / 20) + (canvas.getWidth() / 160);
            rectangle.setPosition(posicionX, y);
            visualElements.put(ElementActual, rectangle);
            rectangle.makeVisible(color);
            if (ElementActual instanceof Cup) {
                Rectangle blank = new Rectangle( scaledWidth - (2 * scale), scaledHeight - scale);
                blank.setPosition(posicionX + scale,y);
                blank.makeVisible("white");
            }
        }
    }
    
    /**
     * Valida que el numero identificador de un Element sea mayor o igual a 1.
     * 
     * @param number Numero a validar segun la subclase de Element.
     * 
     * @return int - Devuelve el mismo numero identificador que recibio.
     */
    private int validateNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Los numeros identificadores comienzan desde el numero 1.");
        }
        return number;
    }
    
    /**
     * Encuentra el índice en la pila de un Element por tipo y número.
     * 
     * @param type "cup" o "lid".
     * @param number Número del Element como String.
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
            Element e = stack.get(i);
            boolean typeOk = (type.equals("cup") && e instanceof Cup) ||
                             (type.equals("lid") && e instanceof Lid);
            if (typeOk && e.getNumber() == num) {
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
        Element next = stack.get(idx + 1);
        return next instanceof Lid && next.getNumber() == stack.get(idx).getNumber();
    }
    
    /**
     * Extrae de la pila el bloque del Element en la posición indicada,si es una taza tapada, extrae la taza y su tapa como bloque.
     * 
     * @param idx Índice del Element a extraer.
     * @param lidded true si la taza tiene su tapa encima y se extrae como bloque.
     * 
     * @return ArrayList<Element> - Lista con el bloque extraído (uno o dos Elements).
     */
    private ArrayList<Element> extractBlock(int idx, boolean lidded) {
        ArrayList<Element> block = new ArrayList<Element>();
        if (lidded) {
            block.add(stack.remove(idx));
            block.add(stack.remove(idx)); // tapa que quedó en idx tras remover la taza
        } else {
            block.add(stack.remove(idx));
        }
        return block;
    }
    
    /**
     * Para cada numero identificador de un Element (Independientemente si es Cup o Lid), le asigna un color.
     * 
     * @param number Numero identificador de un Element.
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
