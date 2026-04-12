import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.util.Arrays;
import javax.swing.JOptionPane;

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
    private final static String TOP_NOT_CUP = "El ultimo Element de la torre no es una taza.";
    private final static String TOP_NOT_LID = "El ultimo Element de la torre no es una tapa.";
    private final static String CUP_NOT_FOUND = "La taza que se desea eliminar no existe.";
    private final static String LID_NOT_FOUND = "La tapa que se desea eliminar no existe.";
    private final static String INVALID_INPUT = "El input ingresado es invalido.";
    private final static String EQUAL_INPUT = "No se realizo swap, porque se intenta intercambiar consigo mismo.";
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
        ok = true;
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
        maxHeight = cups * cups;
        maxWidth = (2 * cups) - 1;
        currentHeight = (2 * cups) - 1;
        currentWidth = (2 * cups) - 1;
        stack = new ArrayList<Element>();
        visualElements = new HashMap<Element, Rectangle>();
        ok = true;
        isVisible = false;
        for (int i = cups; i >= 1; i--) {
            stack.add(new Cup(i));
        }
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
     * Valida que el numero identificador de un Element sea mayor o igual a 1.
     * 
     * @param number Numero a validar segun la subclase de Element.
     */
    private void validateNumber(int number) throws Exception {
        if (number < 1) {
            throw new IllegalArgumentException("Los numeros identificadores comienzan desde el numero 1.");
        }
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
            JOptionPane.showMessageDialog(null,e.getMessage());
            return;
        }
        int dimension = (2 * i) - 1;
        if (dimension > maxHeight) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_HEIGHT);
            return;
        }
        if (dimension > maxWidth) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_WIDTH);
            return;
        }
        for (Element e : stack) {
            if (e instanceof Cup && ((Cup) e).getNumber() == i) {
                JOptionPane.showMessageDialog(null,ALREADY_EXISTS);
                return;
            }
        }
        String[] newElement = {"C",String.valueOf(dimension)};
        int possibleHeight = calculateHeightWithNewElement(newElement);
        if (possibleHeight > maxHeight) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_HEIGHT);
            return;
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
            JOptionPane.showMessageDialog(null,TOWER_EMPTY);
            return;
        }
        Element top = stack.get(stack.size() - 1);
        if (!(top instanceof Cup)) {
            JOptionPane.showMessageDialog(null,TOP_NOT_CUP);
            return;
        }
        stack.remove(stack.size() - 1);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Devuelve los indices del stack que apuntan a la Cup y Lid de igual numero identificador, la posicion 0 del arreglo es el indice de la Cup, la posicion 1 
     * es el indice de la Lid, y la posicion 2 es un bit de verdad, que indica si la Lid esta tapando a la Cup, se devuelve -1 en la respectiva posicion del Element
     * que no se haya encontrado dentro del stack.
     * 
     * @return int[] - Arreglo de enteros de 3 posiciones que contiene la posicion de la Cup y Lid de igual number, y en la tercera posicion es un bit de verdad
     * que indica si la Lid esta tapando a la Cup.
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
            JOptionPane.showMessageDialog(null,TOWER_EMPTY);
            return;
        }
        int[] positions = cupAndLidPositions(i);
        if (positions[0] == -1) {
            JOptionPane.showMessageDialog(null,"La taza que se desea eliminar no existe.");
            return;
        }
        if (positions[2] == 1) {
            for (int j = positions[1]; j > positions[0]; j--) {
                stack.remove(j);
            }
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
        try {
            validateNumber(i);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return;
        }
        int dimension = (2 * i) - 1;
        if (1 > maxHeight) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_HEIGHT);
            return;
        }
        if (dimension > maxWidth) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_WIDTH);
            return;
        }
        for (Element e : stack) {
            if (e instanceof Lid && ((Lid) e).getNumber() == i) {
                JOptionPane.showMessageDialog(null,ALREADY_EXISTS);
                return;
            }
        }
        String[] newElement = {"L",String.valueOf(dimension)};
        int possibleHeight = calculateHeightWithNewElement(newElement);
        if (possibleHeight > maxHeight) {
            JOptionPane.showMessageDialog(null,DIMENSION_EXCEEDS_HEIGHT);
            return;
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
            JOptionPane.showMessageDialog(null,TOWER_EMPTY);
            return;
        }
        Element top = stack.get(stack.size() - 1);
        if (!(top instanceof Lid)) {
            JOptionPane.showMessageDialog(null,TOP_NOT_LID);
            return;
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
            JOptionPane.showMessageDialog(null,LID_NOT_FOUND);
            return;
        }
        stack.remove(index);
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Inicializa variables, cambia variables a medida que recorre el stack, y tiene en cuenta los casos mas basicos e iniciales
     * tanto de orderTower() como de reverseTower(), por tal razon es usado por ambos metodos, adicionalmente, llama al metodo
     * que realiza el verdadero proceso de ordenado segun el tipo de ordenado; "order" o "reverse".
     * 
     * @param orderType String que indica si el ordenado que se quiere hacer es de mayor a menor ("order") o menor a mayor ("reverse")
     * 
     * @return ArrayList<Element> - Nuevo stack ya ordenado segun el tipo de orden.
     */
    private ArrayList<Element> initializeOrderOrReverse(String orderType) {
        ArrayList<Integer> alreadyAdded = new ArrayList<Integer>();
        ArrayList<Element> newStack = new ArrayList<Element>();
        for (int i = 0; i < stack.size(); i++) {
            int currentElementNumber = stack.get(i).getNumber();
            if (alreadyAdded.contains(currentElementNumber)) {
                continue;
            }
            int[] positions = cupAndLidPositions(currentElementNumber);
            if (newStack.isEmpty()) {
                if (positions[0] != -1) {
                    newStack.add(stack.get(positions[0]));
                }
                if (positions[1] != -1) {
                    newStack.add(stack.get(positions[1]));
                }
                alreadyAdded.add(currentElementNumber);
            } else {
                if (orderType.equals("order")) {
                    orderByOrder(newStack,currentElementNumber,positions,alreadyAdded);
                } else if (orderType.equals("reverse")) {
                    orderByReverse(newStack,currentElementNumber,positions,alreadyAdded);
                }
            }
        }
        return newStack;
    }
    
    /**
     * Ordena el newStack de mayor a menor, al adicionar el nuevo Element en la posicion correcta.
     * 
     * @param newStack Nuevo ArrayList de Element que se desea ordenar de mayor a menor, añadiendole nuevos Elements.
     * @param currentElementNumber Numero identificador de los Elements que se desean adicionar de forma ordenada.
     * @param positions Arreglo de 3 posiciones, que contiene los indices de la taza y tapa de mismo numero identificador.
     * @param alreadyAdded ArrayList de Integer que contiene los numeros identificadores de los elementos ya adicionados y ordenados en el newStack.
     */
    private void orderByOrder(ArrayList<Element> newStack, int currentElementNumber, int[] positions, ArrayList<Integer> alreadyAdded) {
        boolean added = false;
        for (int j = 0; j < newStack.size(); j++) {
            int NewStackElementNumber = newStack.get(j).getNumber();
            if (NewStackElementNumber < currentElementNumber) {
                if (positions[1] != -1) {
                    newStack.add(j,stack.get(positions[1]));
                }
                if (positions[0] != -1) {
                    newStack.add(j,stack.get(positions[0]));
                }
                alreadyAdded.add(currentElementNumber);
                added = true;
                break;
            }
        }
        notAddedInNewStack(newStack,currentElementNumber,positions,alreadyAdded,added);
    }
    
    /**
     * Ordena el newStack de menor a mayor, al adicionar el nuevo Element en la posicion correcta.
     * 
     * @param newStack Nuevo ArrayList de Element que se desea ordenar de menor a mayor, añadiendole nuevos Elements.
     * @param currentElementNumber Numero identificador de los Elements que se desean adicionar de forma ordenada.
     * @param positions Arreglo de 3 posiciones, que contiene los indices de la taza y tapa de mismo numero identificador.
     * @param alreadyAdded ArrayList de Integer que contiene los numeros identificadores de los elementos ya adicionados y ordenados en el newStack.
     */
    private void orderByReverse(ArrayList<Element> newStack, int currentElementNumber, int[] positions, ArrayList<Integer> alreadyAdded) {
        boolean added = false;
        for (int j = 0; j < newStack.size(); j++) {
            int NewStackElementNumber = newStack.get(j).getNumber();
            if (NewStackElementNumber > currentElementNumber) {
                if (positions[1] != -1) {
                    newStack.add(j,stack.get(positions[1]));
                }
                if (positions[0] != -1) {
                    newStack.add(j,stack.get(positions[0]));
                }
                alreadyAdded.add(currentElementNumber);
                added = true;
                break;
            }
        }
        notAddedInNewStack(newStack,currentElementNumber,positions,alreadyAdded,added);
    }
    
    /**
     * Añade los nuevos Elements al final del newStack, solo si no fue añadido porque no se cumplio la condicion correcta segun el tipo de ordenado en todas las iteraciones.
     * 
     * @param newStack Nuevo ArrayList de Element al que se le desea añadir los nuevos Elements al final.
     * @param currentElementNumber Numero identificador de los Elements que se desean adicionar al final del newStack.
     * @param positions Arreglo de 3 posiciones, que contiene los indices de la taza y tapa de mismo numero identificador.
     * @param alreadyAdded ArrayList de Integer que contiene los numeros identificadores de los elementos ya adicionados y ordenados en el newStack.
     */
    private void notAddedInNewStack(ArrayList<Element> newStack, int currentElementNumber, int[] positions, ArrayList<Integer> alreadyAdded, boolean added) {
        if (!(added)) {
            if (positions[0] != -1) {
                newStack.add(stack.get(positions[0]));
            }
            if (positions[1] != -1) {
                newStack.add(stack.get(positions[1]));
            }
            alreadyAdded.add(currentElementNumber);
        }
    }

    /**
     * Ordena la torre por numero identificador, de mayor a menor, ademas tapando cada taza con su respectiva tapa si existe.
     */
    public void orderTower() {
        ok = false;
        if (stack.isEmpty()) {
            JOptionPane.showMessageDialog(null,TOWER_EMPTY);
            return;
        }
        stack = initializeOrderOrReverse("order");
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    } 
    
    /**
     * Ordena la torre por numero identificador, de menor a mayor, ademas tapando cada taza con su respectiva tapa si existe.
     */
    public void reverseTower() {
        ok = false;
        if (stack.isEmpty()) {
            JOptionPane.showMessageDialog(null,TOWER_EMPTY);
            return;
        }
        stack = initializeOrderOrReverse("reverse");
        currentHeight = calculateCurrentHeight();
        currentWidth = calculateCurrentWidth();
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
        if (o1 == null || o2 == null || o1.length < 2 || o2.length < 2) return;
        int idx1 = findIndex(o1[0], o1[1]);
        int idx2 = findIndex(o2[0], o2[1]);
        if (idx1 == -1 || idx2 == -1 || idx1 == idx2) return;
        if (idx1 > idx2) {
            int tmp = idx1; idx1 = idx2; idx2 = tmp;
            String[] tmpS = o1; o1 = o2; o2 = tmpS;
        }
        boolean lidded1 = isCupLidded(idx1);
        boolean lidded2 = isCupLidded(idx2);
        ArrayList<Element> block2 = extractBlock(idx2, lidded2);
        ArrayList<Element> block1 = extractBlock(idx1, lidded1);
        for (int k = 0; k < block2.size(); k++) stack.add(idx1 + k, block2.get(k));
        int newIdx2 = idx2 - block1.size() + block2.size();
        for (int k = 0; k < block1.size(); k++) stack.add(newIdx2 + k, block1.get(k));
        currentHeight = calculateCurrentHeight();
        currentWidth  = calculateCurrentWidth();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Tapa todas las tazas que tienen su tapa disponible en la torre, colocando la tapa inmediatamente encima de su taza correspondiente.
     * Si la tapa ya está sobre la taza, no hace nada.
     */
    public void cover() {
        ok = false;
        coverSilent();
        ok = true;
        if (isVisible) redraw();
    }
    
    /**
     * Logica interna de cover sin afectar el ok ni redraw
     * Usa buildBlocks() para detectar tazas con tapas sueltas y las mueve encima
     * Se repite hasta que no haya mas movimientos posibles
     */
    private void coverSilent() {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (ArrayList<Element> block : buildBlocks()) {
                Element cup = block.get(0);
                if (!(cup instanceof Cup) || isCovered(block)) continue;
                int ci = stack.indexOf(cup), li = -1;
                for (int j = ci + 1; j < stack.size(); j++)
                    if (stack.get(j) instanceof Lid && stack.get(j).getNumber() == cup.getNumber()) { li = j; break; }
                if (li == -1) continue;
                stack.add(ci + 1, stack.remove(li));
                currentHeight = calculateCurrentHeight();
                moved = true; break;
            }
        }
        currentWidth = calculateCurrentWidth();
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
        ArrayList<ArrayList<Element>> blocks = buildBlocks();
        for (ArrayList<Element> block : blocks) {
            if (isCovered(block)) numbers.add(block.get(0).getNumber());
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
        for (int i = 0; i < numbers.size(); i++) result[i] = numbers.get(i);
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
        String[][] result = new String[stack.size()][2];
        for (int i = 0; i < stack.size(); i++) {
            Element current = stack.get(i);
            if (current instanceof Cup) {
                result[i][0] = "cup";
            } else {
                result[i][0] = "lid";
            }
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
        for (int i = 0; i < stack.size(); i++) {
            for (int j = i + 1; j < stack.size(); j++) {
                Element temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                int newHeight = calculateCurrentHeight();
                temp = stack.get(i);
                stack.set(i, stack.get(j));
                stack.set(j, temp);
                if (newHeight < currentHeightVariable) {
                    ok = true;
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
        canvas = Canvas.getCanvas();
        isVisible = true;
        redraw();
        ok = true;
    }
    
    /**
     * Oculta la visualización de la torre.
     */
    public void makeInvisible() {
        ok = false;
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
     * Retorna el valor de verdad del estado de la ultima operacion realizada sobre el objeto.
     * 
     * @return boolean - Valor de verdad que responde al estado "ok" de la ultima operacion realizada sobre el objeto.
     */
    public boolean ok() {
        return ok;
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
     * Calcula la posicion Y (en unidades de torre) de cada elemento en el stack,
     * teniendo en cuenta el encaje fisico de tazas dentro de otras.
     *
     * @return int[] - Arreglo con la posicion Y de cada elemento en el stack.
     */
    private int[] calculatePositions() {
        int[] positions = new int[stack.size()];
        for (int i = 1; i < stack.size(); i++) {
            Element current  = stack.get(i);
            int containerIdx = -1;
            for (int j = i - 1; j >= 0; j--) {
                boolean esLidDeSuCup = current instanceof Lid
                                    && stack.get(j).getNumber() == current.getNumber();
                if (current.getWidth() < stack.get(j).getWidth() && !esLidDeSuCup) {
                    containerIdx = j;
                    break;
                }
            }
            int topY = (containerIdx == -1) ? 0 : positions[containerIdx] + 1;
            for (int j = containerIdx + 1; j < i; j++) {
                Element inner  = stack.get(j);
                boolean inside = containerIdx == -1 || inner.getWidth() < stack.get(containerIdx).getWidth();
                int innerTop   = positions[j] + inner.getHeight();
                if (inside && innerTop > topY) topY = innerTop;
            }
            positions[i] = topY;
        }
        return positions;
    }
    
    /**
     * Redibuja completamente la torre en el canvas, centrando cada Element horizontalmente.
     * Tiene en cuenta el encaje fisico de tazas dentro de otras.
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
 
        int[] positions = calculatePositions();
 
        for (int i = 0; i < stack.size(); i++) {
            Element elementActual = stack.get(i);
            int number = elementActual.getNumber();
            String color = getColorForNumber(number);
            int scaledWidth = elementActual.getWidth() * scale;
            int scaledHeight = elementActual.getHeight() * scale;
            int scaledY = canvasHeight - (positions[i] * scale) - scaledHeight;
            Rectangle rectangle = new Rectangle(scaledWidth, scaledHeight);
            int posicionX = ((canvasWidth - scaledWidth) / 2) + (canvas.getWidth() / 20) + (canvas.getWidth() / 160);
            rectangle.setPosition(posicionX, scaledY);
            visualElements.put(elementActual, rectangle);
            rectangle.makeVisible(color);
            if (elementActual instanceof Cup && scaledHeight - scale > 0) {
                Rectangle blank = new Rectangle(scaledWidth - (2 * scale), scaledHeight - scale);
                blank.setPosition(posicionX + scale, scaledY);
                blank.makeVisible("white");
            }
        }
    }
    
    /**
     * Retorna el tamaño del stack de la torre.
     * 
     * @return int - Tamaño del stack de la torre.
     */
    public int getStackSize() {
        return stack.size();
    }
    
    @Override
    /**
     * Sobre escritura del metodo equals, ya que una torre es igual a otra solo si los elementos de la misma posicion de cada stack tiene la misma Tuple(),
     * en otras palabras si su subclase de Element y dimension coinciden para cada elemento de los dos stacks.
     * 
     * @param o Objeto con el que se desea comparar si la torre actual es igual.
     * 
     * @return boolean - Valor de verdad de la afirmacion "La torre actual es igual a la torre del parametro"
     */
    public boolean equals(Object o) {
        if (!(o instanceof Tower)) {
            return false;
        }
        Tower towerC = (Tower) o;
        if (this.height() != towerC.height() || this.stack.size() != towerC.getStackSize()) {
            return false;
        }
        String [][] itemsInThis = this.stackingItems();
        String [][] itemsInTowerC = towerC.stackingItems();
        if (!(Arrays.deepEquals(itemsInThis,itemsInTowerC))) {
            return false;
        }
        return true;
    }
    
    /**
     * Construye y devuelve la lista de bloques del stack actual
     * Cada bloque agrupa una taza con sus elementos internos y su tapa si la tiene encima
     * los elementos sueltos forman bloques de un solo elemento
     * 
     * @return ArrayList de bloques, cada bloque es un ArrayList de Elements
     */
    private ArrayList<ArrayList<Element>> buildBlocks (){
        ArrayList <ArrayList<Element>> blocks = new ArrayList<ArrayList<Element>>();
        boolean [] visited = new boolean [stack.size()];
        for (int i = 0; i <stack.size(); i ++){
            if (visited[i]) continue;
            ArrayList<Element> block = new ArrayList<Element>();
            Element current = stack.get(i);
            block.add(current);
            visited[i] = true;
            if(current instanceof Cup){
                int lidIdx = -1;
                for(int j = i +1; j < stack.size(); j ++){
                    if (stack.get(j) instanceof Lid && stack.get(j).getNumber()== current.getNumber()){
                        lidIdx = j;
                        break;
                    }
                }
                if (lidIdx != -1){
                    for(int j = i +1; j<lidIdx; j++){
                        if(!visited[j] && stack.get(j).getWidth()<current.getWidth()){
                            block.add(stack.get(j));
                            visited[j] = true;
                        }
                    }
                    block.add(stack.get(lidIdx));
                    visited[lidIdx] = true;
                }
            }
            blocks.add(block);
        }
        return blocks;
    }
    
    /**
     * Indica si un bloque representa una taza tapada por su Lid
     * Un bloque esta tapada si el numero de Lid y Cup coincide 
     * 
     * @param block Bloque evaluar
     * @return boolean true si la cup del bloque esta tapada
     */
    private boolean isCovered(ArrayList<Element> block) {
        if (block.size() < 2) return false;
        Element cup = block.get(0);
        Element last = block.get(block.size() - 1);
        if (!(cup instanceof Cup) || !(last instanceof Lid)) return false;
        if (last.getNumber() != cup.getNumber()) return false;
        int cupIdx = stack.indexOf(cup);
        int lidIdx = stack.indexOf(last);
        return lidIdx == cupIdx + 1; 
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
     * Verifica si la taza en la posición indicada tiene su tapa en algun lugar encima de ella en el stack.
     * 
     * @param idx Índice de la taza en la pila.
     * 
     * @return boolean - true si tiene tapa encima, false en caso contrario.
     */
    private boolean isCupLidded(int idx) {
        if (!(stack.get(idx) instanceof Cup)) return false;
        int cupNumber = stack.get(idx).getNumber();
        for (int j = idx + 1; j < stack.size(); j++) {
            Element e = stack.get(j);
            if (e instanceof Lid && e.getNumber() == cupNumber) return true;
        }
        return false;
    }
    
    /**
     * Extrae de la pila el bloque del Element en la posición indicada. Si es una taza tapada, 
     * extrae la taza, sus elementos internos y su tapa como bloque.
     * 
     * @param idx Índice del Element a extraer.
     * @param lidded true si la taza tiene su tapa en algun lugar encima y se extrae como bloque.
     * 
     * @return ArrayList<Element> - Lista con el bloque extraído.
     */
    private ArrayList<Element> extractBlock(int idx, boolean lidded) {
        ArrayList<Element> block = new ArrayList<Element>();
        if (lidded) {
            Element cup = stack.get(idx);
            int cupNumber = cup.getNumber();
            // Encontrar indice de la tapa
            int lidIdx = -1;
            for (int j = idx + 1; j < stack.size(); j++) {
                if (stack.get(j) instanceof Lid && stack.get(j).getNumber() == cupNumber) {
                    lidIdx = j;
                    break;
                }
            }
            if (lidIdx != -1) {
                // Guardar en orden: taza, internos, tapa
                block.add(cup);
                for (int j = idx + 1; j < lidIdx; j++) {
                    if (stack.get(j).getWidth() < cup.getWidth()) {
                        block.add(stack.get(j));
                    }
                }
                block.add(stack.get(lidIdx));
                // Remover del stack de atras hacia adelante para no alterar indices
                stack.remove(lidIdx);
                for (int j = lidIdx - 1; j > idx; j--) {
                    if (stack.get(j).getWidth() < cup.getWidth()) {
                        stack.remove(j);
                    }
                }
                stack.remove(idx);
            } else {
                block.add(stack.remove(idx));
            }
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
