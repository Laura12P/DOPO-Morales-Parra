
/**
 * La clase TowerContest resuelve el problema de la maraton Stacking Cups
 * y simula la solucion usando la clase Tower
 * @param n Numero de tazas.
 * @param h Altura deseada.
 * @return String con las alturas en orden, o "impossible".
 *
 * @author Daniel Santiago Morales Perdomo
 * @author Laura Juliana Parra Velandia
 * @version (27/03/26)
 */
public class TowerContest
{
    /**
     * Constructor for objects of class TowerContest
     */
    public TowerContest()
    {
    }

    /**
     * Resuelve el problema de la maraton: dado n tazas y una altura h,
     * retorna el orden de alturas en que deben apilarse las tazas para lograr h.
     * La altura minima posible es 2n-1 todas las tazas dentro de la mas grande.
     * La altura maxima posible es n^2.
     *
     * @param n Numero de tazas.
     * @param h Altura
     * @return String con las alturas en orden separadas por espacio, o "impossible".
     */
    public String solve (int n, long h){
        long minHeight = (2*n)-1;
        long maxHeigth = (long)n * n;
        if (h< minHeight || h > maxHeigth){
            return "imposible";
        }
        long extra = h -  minHeight;
        boolean [] pulled = new boolean [n+1];
        for (int i = n -1;i>= 1; i--){
            long contribution = 2* (i-1);
            if (contribution <= extra){
                pulled[i]=true;
                extra -= contribution;
                if(extra ==0) {
                    break;
                }
            }
        }
        // Construir orden: primero taza n, luego las sacadas de mayor a menor, luego las no sacadas de mayor a menor
        StringBuilder result = new StringBuilder();
        // Taza n va primero (base exterior)
        result.append((2 * n) - 1);
        // Tazas sacadas de mayor a menor
        for (int i = n - 1; i >= 1; i--) {
            if (pulled[i]) {
                result.append(" ").append((2 * i) - 1);
            }
        }
        // Tazas no sacadas de mayor a menor
        for (int i = n - 1; i >= 1; i--) {
            if (!pulled[i]) {
                result.append(" ").append((2 * i) - 1);
            }
        }
        return result.toString();
    }
    
    /**
     * Simula visualmente la solucion del problema usando la clase Tower
     * Si no existe la solucion, imprime "imposible"
     * 
     * @param n Numero de tazas
     * @param h Altura
     */
    public void simulate(int n, int h) {
        String solution = solve(n, h);
        if (solution.equals("imposible")) {
            System.out.println("imposible");
            return;
        }
        String[] heights = solution.split(" ");
        int maxWidth = (2 * n) -1;
        int maxTowerHeight = n*n;
        Tower tower = new Tower(maxWidth, maxTowerHeight);
        for (String height : heights) {
            int cupHeight = Integer.parseInt(height);
            int cupNumber = (Integer.parseInt(height) + 1) / 2;
            tower.pushCup(cupNumber);
        }
        tower.makeVisible();
    }
}