import javax.swing.JOptionPane;

/**
 * La clase TowerContest resuelve el problema de la maraton Stacking Cups
 * y simula la solucion usando la clase Tower.
 *
 * Logica de apilamiento:
 *   - La taza mas grande (n) siempre va primero (es la base exterior).
 *   - Las tazas "pulled" (sacadas hacia afuera) van encima de la grande,
 *     de mayor a menor.
 *   - Las tazas restantes van dentro de la grande, de mayor a menor.
 *
 * @author Daniel Santiago Morales Perdomo
 * @author Laura Juliana Parra Velandia
 * @version (27/03/26)
 */
public class TowerContest {

    public TowerContest() {
    }

    /**
     * retorna el orden de alturas en que deben apilarse las tazas para lograr h.
     * Altura minima posible: todas dentro de las tazas mas grandes  (2n - 1)
     * Altura maxima posible: Se ponen encima de las tazas, no caben (n^2)
     *
     * @param n Numero de tazas.
     * @param h Altura deseada.
     * @return String con las alturas en orden separadas por espacio, o "impossible".
     */
    public String solve(int n, long h) {
        long minHeight = (2 * n) - 1;
        long maxHeight = (long) n * n;

        if (h < minHeight || h > maxHeight) {
            return "impossible";
        }

        long extra = h - minHeight;
        // Cada taza sacada aporta un numero par de cm, por lo que extra debe ser par
        if (extra % 2 != 0) {
            return "impossible";
        }
        // Sacar las tazas de mayor tamaño primero 
        boolean[] pulled = new boolean[n + 1];
        for (int i = n - 1; i >= 1 && extra > 0; i--) {
            long contribution = 2 * (i - 1);
            if (contribution > 0 && contribution <= extra) {
                pulled[i] = true;
                extra -= contribution;
            }
        }
        if (extra != 0) {
            return "impossible";
        }
        StringBuilder result = new StringBuilder();
        // 1. Taza n primero (la mas grande, base exterior)
        result.append((2 * n) - 1);
        // 2. Tazas "pulled" de MAYOR a MENOR (van encima de la grande, hacia afuera)
        for (int i = n - 1; i >= 1; i--) {
            if (pulled[i]) {
                result.append(" ").append((2 * i) - 1);
            }
        }
        // 3. Tazas NO "pulled" de MAYOR a MENOR (van dentro de la grande)
        for (int i = n - 1; i >= 1; i--) {
            if (!pulled[i]) {
                result.append(" ").append((2 * i) - 1);
            }
        }
        return result.toString();
    }

    /**
     * Simula visualmente la solucion del problema usando la clase Tower.
     * Muestra mensajes mediante JOptionPane.
     * Si no existe solucion, muestra "impossible"-
     *
     * @param n Numero de tazas.
     * @param h Altura deseada.
     */
    public void simulate(int n, int h) {
        String solution = solve(n, (long) h);
        if (solution.equals("impossible")) {
            JOptionPane.showMessageDialog(
                null,
                "No es posible construir una torre de altura " + h + " con " + n + " tazas.",
                "Imposible",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        int maxWidth = (2 * n) - 1;
        int maxTowerHeight = n * n;
        Tower tower = new Tower(maxWidth, maxTowerHeight);
        String[] heights = solution.split(" ");
        for (String heightStr : heights) {
            int cupNumber = (Integer.parseInt(heightStr) + 1) / 2;
            tower.pushCup(cupNumber);
        }
        tower.makeVisible();
    }
}
