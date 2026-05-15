package domain;
import domain.gameObjects.*;

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

    public void checkCollisions(ArrayList<Player> players, ArrayList<Enemy> enemies, ArrayList<Collectionable> collectables,
    		ArrayList<EndZone> endZones) {
    	for (Player player : players) {
    		for (Enemy enemy : enemies) {
                if (intersects(player, enemy)) {
                    player.respawn();
                    if (listener != null) {
                    	listener.onPlayerDied(player);
                    }
                    break;
                }
            }

            for (Collectionable c : collectables) {
                if (!c.isCollected() && intersects(player, c)) {
                    c.collect(player);
                    if (listener != null) {
                    	listener.onCoinCollected(player, collectables.size());
                    }
                }
            }

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

    private boolean intersects(Element a, Element b) {
        return a.getPositionX() < b.getPositionX() + b.getWidth() &&
               a.getPositionX() + a.getWidth() > b.getPositionX() &&
               a.getPositionY() < b.getPositionY() + b.getHeight() &&
               a.getPositionY() + a.getHeight() > b.getPositionY();
    }
}
