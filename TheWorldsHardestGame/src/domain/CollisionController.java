package domain;

import java.util.ArrayList;

import domain.players.Player;
import domain.enemies.Enemy;
import domain.collectables.Collectionable;
import domain.walls.StaticWall;
import domain.zones.EndZone;
import domain.zones.RespawnZone;

/**
 * Clase encargada de detectar y manejar todas las colisiones del juego.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class CollisionController {

    public interface GameEventListener {
        void onPlayerDied(Player player);
        void onCoinCollected(Player player, int totalCoins);
        void onLevelCompleted(Player player);
    }

    private GameEventListener listener;

    public CollisionController() {}

    public void setListener(GameEventListener listener) {
        this.listener = listener;
    }

    public void checkCollisions(ArrayList<Player> players, ArrayList<Enemy> enemies,
                                 ArrayList<Collectionable> collectables,
                                 ArrayList<EndZone> endZones,
                                 ArrayList<RespawnZone> respawnZones,
                                 ArrayList<StaticWall> walls) {
        for (Player player : players) {

            // Colision con enemigos
            for (Enemy enemy : enemies) {
                if (intersects(player, enemy)) {
                    player.onEnemyHit();
                    if (listener != null) {
                        listener.onPlayerDied(player);
                    }
                    break;
                }
            }

            // Colision con coleccionables
            for (Collectionable c : collectables) {
                if (!c.isCollected() && intersects(player, c)) {
                    c.collect(player);
                    if (listener != null) {
                        listener.onCoinCollected(player, collectables.size());
                    }
                }
            }

            // Colision con zona de respawn — actualiza punto de reaparicion
            for (RespawnZone respawnZone : respawnZones) {
                if (intersects(player, respawnZone)) {
                    respawnZone.activatedByPlayer(player);
                }
            }

            // Colision con zona final — solo si recolecto todas las monedas
            boolean allCollected = collectables.stream().allMatch(Collectionable::isCollected);
            if (allCollected) {
                for (EndZone endZone : endZones) {
                    if (intersects(player, endZone)) {
                        if (listener != null) {
                            listener.onLevelCompleted(player);
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean intersects(Element a, Element b) {
        return a.getPositionX() < b.getPositionX() + b.getWidth() &&
               a.getPositionX() + a.getWidth() > b.getPositionX() &&
               a.getPositionY() < b.getPositionY() + b.getHeight() &&
               a.getPositionY() + a.getHeight() > b.getPositionY();
    }
}