package domain;

import java.util.ArrayList;

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
                                ArrayList<Collectionable> collectables, EndZone endZone) {
        for (Player player : players) {

            for (Enemy enemy : enemies) {
                if (intersects(player, enemy)) {
                    player.respawn();
                    if (listener != null) listener.onPlayerDied(player);
                    break;
                }
            }

            for (Collectionable c : collectables) {
                if (!c.isCollected() && intersects(player, c)) {
                    c.collect();
                    player.coinCollected();
                    if (listener != null) listener.onCoinCollected(player, collectables.size());
                }
            }

            if (intersects(player, endZone)) {
                if (listener != null) listener.onLevelCompleted(player);
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
