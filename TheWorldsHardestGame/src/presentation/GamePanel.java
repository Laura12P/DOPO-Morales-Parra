package presentation;

import domain.CollisionController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import domain.TheDOPOHardestGame;
import domain.TWHGException;
import domain.players.Player;
import presentation.enums.GameConfig;

/**
 * Panel principal del juego. Maneja el loop de juego, input y dibujado.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class GamePanel extends JPanel implements CollisionController.GameEventListener {

    private static final long serialVersionUID = 1L;

    private GameWindow gameWindow;
    private TheDOPOHardestGame game;

    private Timer gameTimer;
    private boolean paused;
    private boolean levelCompleted;

    private boolean up, down, left, right;
    private boolean wKey, sKey, aKey, dKey;

    public GamePanel(GameConfig gameConfig, GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        try {
            game = new TheDOPOHardestGame("levels/level1.txt");
        } catch (TWHGException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        paused = false;
        levelCompleted = false;

        setBackground(new Color(180, 181, 254));
        setFocusable(true);
        setPreferredSize(new Dimension(game.getBoardWidth(), game.getBoardHeight()));

        game.setEventListener(this);

        gameTimer = new Timer(16, e -> {
            if (!paused && !levelCompleted) {
                handleMovement();
                game.update();
                repaint();
            }
        });
        gameTimer.start();
    }

    private void handleMovement() {
        if (game.amountOfPlayers() == 0) return;

        Player p1 = game.getPlayers().get(0);
        int dx1 = 0, dy1 = 0;
        if (wKey) dy1 = -1;
        if (sKey) dy1 = 1;
        if (aKey) dx1 = -1;
        if (dKey) dx1 = 1;
        p1.move(dx1, dy1, game.getBoardWidth(), game.getBoardHeight(), game.getWalls());

        if (game.amountOfPlayers() > 1) {
            Player p2 = game.getPlayers().get(1);
            int dx2 = 0, dy2 = 0;
            if (up) dy2 = -1;
            if (down) dy2 = 1;
            if (left) dx2 = -1;
            if (right) dx2 = 1;
            p2.move(dx2, dy2, game.getBoardWidth(), game.getBoardHeight(), game.getWalls());
        }
    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                wKey = true;
                break;
            case KeyEvent.VK_S:
                sKey = true;
                break;
            case KeyEvent.VK_A:
                aKey = true;
                break;
            case KeyEvent.VK_D:
                dKey = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                wKey = false;
                break;
            case KeyEvent.VK_S:
                sKey = false;
                break;
            case KeyEvent.VK_A:
                aKey = false;
                break;
            case KeyEvent.VK_D:
                dKey = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void onPlayerDied(Player player) {
        gameWindow.updateDeaths(player.getDeaths());
    }

    @Override
    public void onCoinCollected(Player player, int totalCoins) {
        gameWindow.updateCoins(player.getCoinsCollected(), totalCoins);
    }

    @Override
    public void onLevelCompleted(Player player) {
        levelCompleted = true;
        gameTimer.stop();
        gameWindow.stopTimer();
        gameWindow.showWinWindow(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCheckerboard(g);
        game.drawAllElements(g);
        drawBorder(g);
    }

    private void drawCheckerboard(Graphics g) {
        int tileSize = 48;
        for (int[] corridor : game.getCorridors()) {
            int startX = corridor[0];
            int startY = corridor[1];
            int endX = startX + corridor[2];
            int endY = startY + corridor[3];
            for (int x = startX; x < endX; x += tileSize) {
                for (int y = startY; y < endY; y += tileSize) {
                    boolean isLight = ((x - startX) / tileSize + (y - startY) / tileSize) % 2 == 0;
                    g.setColor(isLight ? new Color(220, 220, 235) : new Color(200, 200, 220));
                    g.fillRect(x, y, tileSize, tileSize);
                }
            }
        }
    }

    private void drawBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(0, 0, game.getBoardWidth() - 1, game.getBoardHeight() - 1);
    }
    
    public void restartLevel() {
        for (Player p : game.getPlayers()) {
            p.respawn();
            gameWindow.updateDeaths(p.getDeaths());
        }
    }
}