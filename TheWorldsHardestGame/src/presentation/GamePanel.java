package presentation;
import domain.gameObjects.*;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GamePanel extends JPanel implements CollisionController.GameEventListener {
	private static final long serialVersionUID = 1L;
	
	private GameWindow gameWindow;
	private TheDOPOHardestGame game;
	
    private Timer gameTimer;
    private boolean paused;
    private boolean levelCompleted;

    public GamePanel(GameConfig gameConfig, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        
        try {
            game = new TheDOPOHardestGame("levels/level1.txt");
        } catch (TWHGException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        
        gameTimer = new javax.swing.Timer(16, e -> {
            if (!paused && !levelCompleted) {
                game.board.update();
                repaint();
            }
        });
        
        paused = false;
        levelCompleted = false;

        setBackground(new Color(180,181,254));
        setFocusable(true);
        
        setPreferredSize(new Dimension(game.getBoardWidth(),game.getBoardHeight()));

        game.board.setEventListener(this);
        gameTimer.start();

    }

    private void handleKey(int key) {
        if (game.amountOfPlayers() == 0) {
        	return;
        }
        Player p = game.getPlayers().get(0);
        int dx = 0;
        int dy = 0;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	dy = -1;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	dy = 1;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
        	dx = -1;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	dx = 1;
        }
        p.move(dx, dy, game.getBoardWidth(), game.getBoardHeight());
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void handleKeyPressed(int keyCode) {
        handleKey(keyCode);
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
        JOptionPane.showMessageDialog(gameWindow,
            "¡Nivel completado!\nMuertes: " + player.getDeaths(),
            "Victoria",
            JOptionPane.INFORMATION_MESSAGE);
        gameWindow.dispose();
        new MainWindow(gameWindow.getWidth(), gameWindow.getHeight()).setVisible(true);
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
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(0, 0, game.getBoardWidth() - 1, game.getBoardHeight() - 1);
    }
}