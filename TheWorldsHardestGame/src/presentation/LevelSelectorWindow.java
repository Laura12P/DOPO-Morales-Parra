package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

public class LevelSelectorWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel buttonsRow;
	private JPanel bottomPanel;
    private JButton btnNormalLevels;
    private JButton btnSpecificLevel;
    private JButton btnBack;
    private GameConfig gameConfig;

    public LevelSelectorWindow(int lastWidth, int lastHeight, GameConfig gameConfig) {
    	this.gameConfig = gameConfig;
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	setTitle("Level Selector");
    	setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        
        buttonsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.06 * lastWidth), 0));
        buttonsRow.setBackground(Color.WHITE);

        btnNormalLevels = new JButton("Normal Levels");
        btnNormalLevels.setPreferredSize(new Dimension((int) (0.32 * lastWidth), (int) (0.17 * lastHeight)));
        btnNormalLevels.setBackground(new Color(70, 130, 200));
        btnNormalLevels.setForeground(Color.WHITE);
        btnNormalLevels.setFocusPainted(false);
        int btnNLWidth = btnNormalLevels.getWidth();
        btnNormalLevels.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnNLWidth)));
        btnNormalLevels.setBorderPainted(false);

        btnSpecificLevel = new JButton("Specific Level");
        btnSpecificLevel.setPreferredSize(new Dimension((int) (0.32 * lastWidth), (int) (0.17 * lastHeight)));
        btnSpecificLevel.setBackground(new Color(220, 80, 80));
        btnSpecificLevel.setForeground(Color.WHITE);
        btnSpecificLevel.setFocusPainted(false);
        int btnSLWidth = btnSpecificLevel.getWidth();
        btnSpecificLevel.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnSLWidth)));
        btnSpecificLevel.setBorderPainted(false);
        
        buttonsRow.add(btnNormalLevels);
        buttonsRow.add(btnSpecificLevel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;

        mainPanel.add(buttonsRow, gbc);

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        bottomPanel.setBackground(Color.WHITE);

        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension((int) (0.15 * lastWidth), (int) (0.16 * lastHeight)));
        btnBack.setFocusPainted(false);
        int btnBWidth = btnBack.getWidth();
        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.22 * btnBWidth)));

        bottomPanel.add(btnBack);
        
        gbc.weighty = 0;
        gbc.gridy = 1;
        mainPanel.add(bottomPanel, gbc);

        add(mainPanel);
    }
    
    private void prepareActions() {
        btnNormalLevels.addActionListener(e -> {
        	dispose();
        	new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        });

        btnSpecificLevel.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
        });
        
        btnBack.addActionListener(e -> {
            dispose();
            if (gameConfig.getGameMode() == GameMode.SINGLE_PLAYER) {
            	new CustomizationOnePlayerWindow(getWidth(), getHeight(), MachineDifficulty.NONE).setVisible(true);
            } else if (gameConfig.getGameMode() == GameMode.DOUBLE_PLAYER) {
            	new CustomizationTwoPlayersWindow(getWidth() , getHeight()).setVisible(true);
            } else {
            	new CustomizationOnePlayerWindow(getWidth(), getHeight(), gameConfig.getMachineDifficulty()).setVisible(true);
            }
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	
    	        btnNormalLevels.setPreferredSize(new Dimension((int) (0.32 * newWidth), (int) (0.17 * newHeight)));
    	        int btnNGWidth = btnNormalLevels.getWidth();
    	        btnNormalLevels.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnNGWidth)));
    	        
    	        btnSpecificLevel.setPreferredSize(new Dimension((int) (0.32 * newWidth), (int) (0.17 * newHeight)));
    	        int btnLGWidth = btnSpecificLevel.getWidth();
    	        btnSpecificLevel.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnLGWidth)));
    	        
    	        btnBack.setPreferredSize(new Dimension((int) (0.15 * newWidth), (int) (0.16 * newHeight)));
    	        int btnBWidth = btnBack.getWidth();
    	        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.22 * btnBWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }
}