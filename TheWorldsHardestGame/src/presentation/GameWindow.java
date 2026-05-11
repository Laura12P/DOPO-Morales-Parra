package presentation;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
    private JPanel hudPanel;
    private JLabel lblLevel;
    private JLabel lblTime;
    private JLabel lblDeaths;
    private JLabel lblCoins;
    private JButton btnPause;
    private JButton btnExit;

    private javax.swing.Timer countdownTimer;
    private int secondsLeft;
    private boolean paused;
    
    private GamePanel gamePanel;
    
    public GameWindow(int lastWidth, int lastHeight, GameConfig gameConfig) {
        this.secondsLeft = 180;
        this.paused = false;
        gamePanel = new GamePanel(gameConfig); 
        prepareElements(lastWidth, lastHeight);
        prepareActions();
        prepareTimer();
        prepareKeyListener();
        prepareResponsiveGUI();
    }
 
    private void prepareElements(int lastWidth, int lastHeight) {
        setTitle("The World Hardest Game");
        setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
 
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
 
        hudPanel = new JPanel(new BorderLayout());
        hudPanel.setBackground(Color.WHITE);
        hudPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
 
        JPanel hudLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 4));
        hudLeft.setBackground(Color.WHITE);
 
        lblLevel = new JLabel("Level: 1");
        lblLevel.setFont(new Font("Arial", Font.PLAIN, 14));
 
        lblTime = new JLabel("Time: 3:00");
        lblTime.setFont(new Font("Arial", Font.BOLD, 14));
 
        lblDeaths = new JLabel("Deaths: 0");
        lblDeaths.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDeaths.setForeground(new Color(180, 0, 0));
 
        lblCoins = new JLabel("Coins: 0/5");
        lblCoins.setFont(new Font("Arial", Font.PLAIN, 14));
 
        hudLeft.add(lblLevel);
        hudLeft.add(lblTime);
        hudLeft.add(lblDeaths);
        hudLeft.add(lblCoins);
 
        JPanel hudRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        hudRight.setBackground(Color.WHITE);
 
        btnPause = new JButton("Pause");
        btnPause.setFont(new Font("Arial", Font.PLAIN, 13));
        btnPause.setFocusPainted(false);
 
        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.PLAIN, 13));
        btnExit.setForeground(new Color(180, 0, 0));
        btnExit.setFocusPainted(false);
 
        hudRight.add(btnPause);
        hudRight.add(btnExit);
 
        hudPanel.add(hudLeft, BorderLayout.WEST);
        hudPanel.add(hudRight, BorderLayout.EAST);
 
        mainPanel.add(hudPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
 
        add(mainPanel);
    }
    private void prepareActions() {
        btnPause.addActionListener(e -> togglePause());
 
        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                countdownTimer.stop();
                dispose();
                Dimension size = this.getSize();
                new MainWindow(size.width, size.height).setVisible(true);
            }
        });
    }
 
    private void prepareTimer() {
        countdownTimer = new javax.swing.Timer(1000, e -> {
            if (!paused) {
                secondsLeft--;
                updateTimerLabel();
                if (secondsLeft <= 0) {
                    countdownTimer.stop();
                    onTimeOut();
                }
            }
        });
        countdownTimer.start();
    }
 
    private void prepareKeyListener() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    togglePause();
                }
                if (!paused) {
                    gamePanel.handleKeyPressed(e.getKeyCode());
                }
            }
        });
    }
 
    private void prepareResponsiveGUI() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
    }
 
    private void togglePause() {
        paused = !paused;
        gamePanel.setPaused(paused);
        if (paused) {
            btnPause.setText("Resume");
            showPauseDialog();
        } else {
            btnPause.setText("Pause");
        }
    }
 
    private void showPauseDialog() {
        String[] options = {"Resume", "Save Game", "Exit to Menu"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "Game Paused",
            "Pause",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );
        if (choice == 0 || choice == JOptionPane.CLOSED_OPTION) {
            paused = false;
            gamePanel.setPaused(false);
            btnPause.setText("Pause");
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(this, "Save game — coming soon.");
            paused = false;
            gamePanel.setPaused(false);
            btnPause.setText("Pause");
        } else if (choice == 2) {
            countdownTimer.stop();
            dispose();
            Dimension size = this.getSize();
            new MainWindow(size.width, size.height).setVisible(true);
        }
    }
 
    private void onTimeOut() {
        gamePanel.setPaused(true);
        JOptionPane.showMessageDialog(
            this,
            "Time's up! You ran out of time.",
            "Game Over",
            JOptionPane.WARNING_MESSAGE
        );
        dispose();
        Dimension size = this.getSize();
        new MainWindow(size.width, size.height).setVisible(true);
    }
 
    private void updateTimerLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        lblTime.setText(String.format("Time: %d:%02d", minutes, seconds));
        if (secondsLeft <= 30) {
            lblTime.setForeground(new Color(180, 0, 0));
        }
    }
 
    public void updateDeaths(int deaths) {
        lblDeaths.setText("Deaths: " + deaths);
    }
 
    public void updateCoins(int collected, int total) {
        lblCoins.setText("Coins: " + collected + "/" + total);
    }
}
