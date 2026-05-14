package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
    
    public GameWindow(int lastWidth, int lastHeight, GameConfig gameConfig) {
    	secondsLeft = 180;
        paused = false;
        gamePanel = new GamePanel(gameConfig, this);
        prepareElements(lastWidth, lastHeight);
        prepareActions();
        prepareResponsiveGUI();
        prepareTimer();
        prepareKeyListener();
        SwingUtilities.invokeLater(() -> requestFocus());
    }

    private void prepareElements(int lastWidth, int lastHeight) {
        setTitle("The World Hardest Game");
        setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(180,181,254));

        hudPanel = new JPanel(new GridBagLayout());
        hudPanel.setBackground(Color.BLACK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 0;
        gbc.weightx = 1;
        
        btnPause = new JLabel("Pause");
        btnPause.setForeground(Color.WHITE);
        btnPause.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        
        lblTime = new JLabel("Time: 3:00");
        lblTime.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblTime.setForeground(Color.WHITE);
        
        lblCoins = new JLabel("Coins: 0 / "+ "0");
        lblCoins.setFont(new Font("Arial", Font.PLAIN, (int) (0.019 * lastWidth)));
        lblCoins.setForeground(Color.WHITE);
        
        lblLevel = new JLabel("Level: " + "1" + " / " + "3");
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
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.weightx = 0;
        mainPanel.add(hudPanel, gbc);
        
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        mainPanel.add(gamePanel, gbc);

        add(mainPanel);
    }

    private void prepareActions() {
        btnPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        countdownTimer = new javax.swing.Timer(1000, e -> {
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
                	gamePanel.handleKeyPressed(e.getKeyCode());
                }
            }
        });
    }

    private void togglePause() {
    	paused = true;
    	gamePanel.setPaused(paused);
    	PauseWindow pw = new PauseWindow(this);
    	int ans = pw.showMessageDialog();
    	if (ans == PauseWindow.RESUME) {
    		paused = false;
    		gamePanel.setPaused(paused);
    		this.requestFocusInWindow();
    	} else if (ans == PauseWindow.MENU) {
    		countdownTimer.stop();
    		dispose();
    		new MainWindow(getWidth(), getHeight()).setVisible(true);
    	} else if (ans == PauseWindow.SAVE) {
    		JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
    	} else if (ans == PauseWindow.LOAD) {
    		JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
    	}
    }

    private void loseByTimeOut() {
    	paused = true;
        gamePanel.setPaused(paused);
        JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo!", "Game Over", JOptionPane.WARNING_MESSAGE);
        dispose();
        new MainWindow(getWidth(), getHeight()).setVisible(true);
    }

    private void updateTimerLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        lblTime.setText(String.format("Time: %d:%02d", minutes, seconds));
        if (secondsLeft <= 30) lblTime.setForeground(new Color(180, 0, 0));
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
}