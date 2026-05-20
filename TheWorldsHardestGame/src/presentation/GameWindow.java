package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import domain.players.Player;
import presentation.enums.GameConfig;

/**
 * Ventana principal del juego. Contiene el HUD y el GamePanel.
 *
 * @author Laura Juliana Parra Velandia y Daniel Santiago Morales Perdomo
 */
public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JPanel hudPanel;
    private JLabel btnPause;
    private JLabel lblTime;
    private JLabel lblCoins;
    private JLabel lblLevel;
    private JLabel lblDeaths;

    private Timer countdownTimer;
    private int secondsLeft;
    private boolean paused;

    private GamePanel gamePanel;
    private GameConfig gameConfig;

    public GameWindow(int lastWidth, int lastHeight, GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        secondsLeft = 180;
        paused = false;
        gamePanel = new GamePanel(gameConfig, this);
        prepareElements(lastWidth, lastHeight);
        prepareActions();
        prepareResponsiveGUI();
        prepareTimer();
        prepareKeyListener();
        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
    }

    private void prepareElements(int lastWidth, int lastHeight) {
        setTitle("The DOPO Hardest Game");
        setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setFocusable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(180, 181, 254));

        hudPanel = new JPanel(new GridBagLayout());
        hudPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.insets = new Insets(4, 16, 4, 16);

        btnPause = new JLabel("Pause");
        btnPause.setForeground(Color.WHITE);
        btnPause.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        btnPause.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblTime = new JLabel("Time: 3:00");
        lblTime.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblTime.setForeground(Color.WHITE);

        lblCoins = new JLabel("Coins: 0/0");
        lblCoins.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblCoins.setForeground(Color.WHITE);

        lblLevel = new JLabel("Level: 1/3");
        lblLevel.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblLevel.setForeground(Color.WHITE);

        lblDeaths = new JLabel("Deaths: 0");
        lblDeaths.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblDeaths.setForeground(Color.WHITE);

        hudPanel.add(btnPause, gbc);
        gbc.gridx = 1;
        hudPanel.add(lblTime, gbc);
        gbc.gridx = 2;
        hudPanel.add(lblCoins, gbc);
        gbc.gridx = 3;
        hudPanel.add(lblLevel, gbc);
        gbc.gridx = 4;
        hudPanel.add(lblDeaths, gbc);

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.weightx = 1;
        gbcMain.weighty = 0;
        mainPanel.add(hudPanel, gbcMain);

        gbcMain.gridy = 1;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weighty = 1;
        mainPanel.add(gamePanel, gbcMain);

        add(mainPanel);
    }

    private void prepareActions() {
        btnPause.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                togglePause();
            }
        });
    }

    private void prepareResponsiveGUI() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = getWidth();
                btnPause.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * newWidth)));
                lblTime.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * newWidth)));
                lblCoins.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * newWidth)));
                lblLevel.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * newWidth)));
                lblDeaths.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * newWidth)));
                revalidate();
                repaint();
            }
        });
    }

    private void prepareTimer() {
        countdownTimer = new Timer(1000, e -> {
            if (!paused) {
                secondsLeft--;
                updateTimerLabel();
                if (secondsLeft <= 0) {
                    countdownTimer.stop();
                    loseByTimeOut();
                }
            }
        });
        countdownTimer.start();
    }

    private void prepareKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    togglePause();
                }
                if (!paused) {
                    gamePanel.keyPressed(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gamePanel.keyReleased(e.getKeyCode());
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void togglePause() {
        paused = true;
        gamePanel.setPaused(true);
        PauseWindow pw = new PauseWindow(this);
        int ans = pw.showMessageDialog();
        if (ans == PauseWindow.RESUME) {
            paused = false;
            gamePanel.setPaused(false);
            requestFocusInWindow();
        } else if (ans == PauseWindow.MENU) {
            countdownTimer.stop();
            dispose();
            new MainWindow(getWidth(), getHeight()).setVisible(true);
        } else if (ans == PauseWindow.SAVE) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Partida guardada: " + file.getName());
            }
            paused = false;
            gamePanel.setPaused(false);
            requestFocusInWindow();
        } else if (ans == PauseWindow.LOAD) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Partida cargada: " + file.getName());
            }
            paused = false;
            gamePanel.setPaused(false);
            requestFocusInWindow();
        }
    }

    private void loseByTimeOut() {
        paused = true;
        gamePanel.setPaused(true);
        JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo!", "Game Over", JOptionPane.WARNING_MESSAGE);
        gamePanel.restartLevel();
        secondsLeft = 180;
        updateTimerLabel();
        paused = false;
        gamePanel.setPaused(false);
        countdownTimer.restart();
        requestFocusInWindow();
    }
    
    private void updateTimerLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        lblTime.setText(String.format("Time: %d:%02d", minutes, seconds));
        if (secondsLeft <= 30) {
            lblTime.setForeground(new Color(180, 0, 0));
        }
    }

    public void showWinWindow(Player player) {
        int coins = player.getCoinsCollected();
        int deaths = player.getDeaths();
        int timeUsed = 180 - secondsLeft;
        
        String message = "¡Nivel completado!\n" +
                         "Monedas: " + coins + "\n" +
                         "Muertes: " + deaths + "\n" +
                         "Tiempo: " + (timeUsed / 60) + ":" + String.format("%02d", timeUsed % 60);

        int option = JOptionPane.showConfirmDialog(this,
            message,
            "¡Victoria!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE);

        countdownTimer.stop();
        dispose();
        if (option == JOptionPane.YES_OPTION) {
            // continuar al siguiente nivel — por ahora vuelve al menu
            new MainWindow(getWidth(), getHeight()).setVisible(true);
        } else {
            new MainWindow(getWidth(), getHeight()).setVisible(true);
        }
    }

    public void updateDeaths(int deaths) {
        lblDeaths.setText("Deaths: " + deaths);
    }

    public void updateCoins(int collected, int total) {
        lblCoins.setText("Coins: " + collected + "/" + total);
    }

    public void stopTimer() {
        countdownTimer.stop();
    }
    
    public String getTotalTimePO() {
        int elapsed = 180 - secondsLeft;
        int minutes = elapsed / 60;
        int seconds = elapsed % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    public String getLevelTimePO() {
        return getTotalTimePO(); // por ahora es el mismo, cuando haya múltiples niveles se diferencia
    }

    public void nextLevel() {
        dispose();
        new MainWindow(getWidth(), getHeight()).setVisible(true);
    }
}