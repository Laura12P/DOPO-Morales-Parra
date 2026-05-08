package domain;

import java.util.ArrayList;

/* La clase CollisionController es la encargada de calcular y determinar las colisiones entre elementos del tablero.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */

public class CollisionController {

    public CollisionController() {
    }

    public void checkCollisions(ArrayList<Player> players, ArrayList<Enemy> enemies,
                                 ArrayList<Collectionable> collectables, EndZone endZone) {
        for (Player player : players) {
            // Colisión con enemigos
            for (Enemy enemy : enemies) {
                if (intersects(player, enemy)) {
                    player.respawn(player.positionX, player.positionY);
                }
            }
            // Colisión con monedas
            for (Collectionable c : collectables) {
                if (!c.isCollected() && intersects(player, c)) {
                    c.collect();
                    player.coinCollected();
                }
            }
            // Colisión con zona final
            if (intersects(player, endZone)) {
                // TODO: notificar que el nivel terminó
            }
        }
    }

    private boolean intersects(Element a, Element b) {
        return a.positionX < b.positionX + b.width &&
               a.positionX + a.width > b.positionX &&
               a.positionY < b.positionY + b.height &&
               a.positionY + a.height > b.positionY;
    }
}
