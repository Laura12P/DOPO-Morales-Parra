package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GamePanel extends JPanel implements CollisionController.GameEventListener {

    private TheDOPOHardestGame game;
    private javax.swing.Timer gameTimer;
    private boolean paused;
    private boolean levelCompleted;
    private GameWindow gameWindow;

    public GamePanel(GameConfig gameConfig, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.paused = false;
        this.levelCompleted = false;

        setBackground(new Color(100, 100, 200));
        setFocusable(true);

        try {
            game = new TheDOPOHardestGame("levels/level1.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el nivel: " + e.getMessage());
            return;
        }

        game.board.setEventListener(this);

        gameTimer = new javax.swing.Timer(16, e -> {
            if (!paused && !levelCompleted) {
                game.board.update();
                repaint();
            }
        });
        gameTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKey(e.getKeyCode());
            }
        });
    }

    private void handleKey(int key) {
        if (game.board.players.isEmpty()) return;
        Player p = game.board.players.get(0);
        int dx = 0, dy = 0;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) dy = -1;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) dy = 1;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) dx = -1;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) dx = 1;
        p.move(dx, dy, game.board.width, game.board.height);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void handleKeyPressed(int keyCode) {
        handleKey(keyCode);
    }

    @Override
    public void onPlayerDied(Player player) {
        gameWindow.updateDeaths(player.deaths);
    }

    @Override
    public void onCoinCollected(Player player, int totalCoins) {
        gameWindow.updateCoins(player.coinsCollected, totalCoins);
    }

    @Override
    public void onLevelCompleted(Player player) {
        levelCompleted = true;
        gameTimer.stop();
        gameWindow.stopTimer();
        JOptionPane.showMessageDialog(gameWindow,
            "¡Nivel completado!\nMuertes: " + player.deaths,
            "Victoria",
            JOptionPane.INFORMATION_MESSAGE);
        gameWindow.dispose();
        new MainWindow(gameWindow.getWidth(), gameWindow.getHeight()).setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCheckerboard(g);
        game.board.startZone.draw(g);
        game.board.endZone.draw(g);
        for (StaticWall w : game.board.walls) w.draw(g);
        for (Collectionable c : game.board.collectionables) c.draw(g);
        for (Enemy e : game.board.enemies) e.draw(g);
        for (Player p : game.board.players) p.draw(g);
    }

    private void drawCheckerboard(Graphics g) {
        int tileSize = 30;
        int startX = 150;
        int startY = 100;
        int endX = 750;
        int endY = 400;
        for (int x = startX; x < endX; x += tileSize) {
            for (int y = startY; y < endY; y += tileSize) {
                boolean isLight = ((x - startX) / tileSize + (y - startY) / tileSize) % 2 == 0;
                g.setColor(isLight ? new Color(220, 220, 235) : new Color(200, 200, 220));
                g.fillRect(x, y, tileSize, tileSize);
            }
        }
    }
}
