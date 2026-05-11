package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private Board board;
    private javax.swing.Timer gameTimer;
    private boolean paused;

    public GamePanel(GameConfig gameConfig) {
        setBackground(Color.WHITE);
        setFocusable(true);

        // Crear nivel básico
        StartZone start = new StartZone(50, 200, 80, 80, Color.GREEN);
        EndZone end = new EndZone(750, 200, 80, 80, Color.GREEN);
        board = new Board(900, 500, start, end);

        // Jugador
        Player player = new Player(70, 220, 30, 30, 3, Color.RED, "Player");
        board.addPlayer(player);

        // Monedas
        board.addCollectionable(new Collectionable(300, 230, 20, 20, 0, Color.YELLOW));
        board.addCollectionable(new Collectionable(450, 230, 20, 20, 0, Color.YELLOW));
        board.addCollectionable(new Collectionable(600, 230, 20, 20, 0, Color.YELLOW));

        // Enemigo
        board.addEnemy(new MovingEnemy(400, 100, 20, 20, 3, Color.YELLOW, 1, 0));

        // Timer del juego
        gameTimer = new javax.swing.Timer(16, e -> {
            if (!paused) {
                board.update();
                repaint();
            }
        });
        gameTimer.start();

        // Teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKey(e.getKeyCode());
            }
        });
    }

    private void handleKey(int key) {
        if (board.players.isEmpty()) return;
        Player p = board.players.get(0);
        int dx = 0, dy = 0;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) dy = -1;
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) dy = 1;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) dx = -1;
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) dx = 1;
        p.move(dx, dy, getWidth(), getHeight());
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void handleKeyPressed(int keyCode) {
        handleKey(keyCode);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.startZone.draw(g);
        board.endZone.draw(g);
        for (Collectionable c : board.collectionables) c.draw(g);
        for (Enemy e : board.enemies) e.draw(g);
        for (Player p : board.players) p.draw(g);
    }
}
