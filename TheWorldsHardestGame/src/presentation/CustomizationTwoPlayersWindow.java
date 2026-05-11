package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomizationTwoPlayersWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel playerOnePanel;
	private JPanel playerTwoPanel;
	private JPanel buttonsPanel;
	private JButton btnBack;
	private JButton btnContinue;
	private JLabel[] playerNameLabels = new JLabel[2];
	private JPanel[] playerColorsRows = new JPanel[2];
	private JPanel[] playerNameFields = new JPanel[2];
	private JLabel[] playerControlsLabels = new JLabel[2];
    private Color selectedColorPO;
    private Color selectedColorPT;

    public CustomizationTwoPlayersWindow(int lastWidth, int lastHeight) {
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	setTitle("Two Players Mode");
    	setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        
        centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBackground(Color.WHITE);
        playerOnePanel = buildPlayerPanel("Player One", "WASD", 1, lastWidth, lastHeight);
        centerPanel.add(playerOnePanel);
        playerTwoPanel = buildPlayerPanel("Player Two", "Up, Down,\nLeft, Right", 2, lastWidth, lastHeight);
        centerPanel.add(playerTwoPanel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        mainPanel.add(centerPanel, gbc);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.1 * lastWidth), 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension((int) (0.12 * lastWidth), (int) (0.12 * lastHeight)));
        btnBack.setFocusPainted(false);
        int btnBWidth = btnBack.getWidth();
        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.20 * btnBWidth)));

        btnContinue = new JButton("Continue");
        btnContinue.setPreferredSize(new Dimension((int) (0.146 * lastWidth), (int) (0.12 * lastHeight)));
        btnContinue.setFocusPainted(false);
        int btnCWidth = btnContinue.getWidth();
        btnContinue.setFont(new Font("Arial", Font.PLAIN, (int) (0.16 * btnCWidth)));

        buttonsPanel.add(btnBack);
        buttonsPanel.add(btnContinue);
        
        gbc.gridy = 1;
        gbc.weighty = 0;
        mainPanel.add(buttonsPanel, gbc);

        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnBack.addActionListener(e -> {
            dispose();
            new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        });

        btnContinue.addActionListener(e -> {
        	JTextField pOField = (JTextField) playerNameFields[0].getComponent(1);
        	String namePO = pOField.getText();
        	
        	JTextField pTField = (JTextField) playerNameFields[1].getComponent(1);
        	String namePT = pTField.getText();
        	
        	if ((namePO.isBlank() || selectedColorPO == null) && (namePT.isBlank() || selectedColorPT == null)) {
                JOptionPane.showMessageDialog(this, "Please enter valid names and select colors for both Players.");
                return;
            } else if (namePO.isBlank() || selectedColorPO == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a color for Player One.");
                return;
            } else if (namePT.isBlank() || selectedColorPT == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a color for Player Two.");
                return;
            }
        	
        	dispose();
        	GameConfig gameConfig = GameConfig.getInstance();
            GameConfig.initDoublePlayer(namePO, selectedColorPO, namePT, selectedColorPT);
            new LevelSelectorWindow(getWidth(), getHeight(), gameConfig).setVisible(true);
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	        
                for (int i = 0; i < 2; i++) {
                	if (playerNameLabels[i] != null) {
                		playerNameLabels[i].setFont(new Font("Arial", Font.PLAIN, (int) (0.1 * newWidth / 2)));
                	}
                	
                    if (playerColorsRows[i] != null) {
                        for (Component comp : playerColorsRows[i].getComponents()) {
                            if (comp instanceof JPanel) {
                                JPanel colorBox = (JPanel) comp;
                                colorBox.setPreferredSize(new Dimension((int) (0.08 * newWidth), (int) (0.08 * newWidth)));
                                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.01 * newWidth / 2)));
                            }
                        }
                    }
                    
                    if (playerNameFields[i] != null) {
                        for (Component comp : playerNameFields[i].getComponents()) {
                            if (comp instanceof JLabel) {
                                JLabel nameLabel = (JLabel) comp;
                                nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.05 * newWidth / 2)) );
                            } else if (comp instanceof JTextField){
                            	JTextField nameField = (JTextField) comp;
                            	nameField.setPreferredSize(new Dimension((int) (0.08 * newWidth / 2), (int) (0.05 * newWidth / 2)) );
                            }
                        }
                    }
                    
                    if (playerControlsLabels[i] != null) {
                    	playerControlsLabels[i].setFont(new Font("Arial", Font.PLAIN, (int) (0.06 * newWidth / 2)));
                    }
                }
    	        
    	        btnBack.setPreferredSize(new Dimension((int) (0.12 * newWidth), (int) (0.12 * newHeight)));
    	        int btnBWidth = btnBack.getWidth();
    	        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.20 * btnBWidth)));
    	        
    	        btnContinue.setPreferredSize(new Dimension((int) (0.146 * newWidth), (int) (0.12 * newHeight)));
    	        int btnCWidth = btnContinue.getWidth();
    	        btnContinue.setFont(new Font("Arial", Font.PLAIN, (int) (0.16 * btnCWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }

    private JPanel buildPlayerPanel(String playerTitle, String controls, int player, int lastWidth, int lastHeight) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        JLabel title = new JLabel(playerTitle, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, (int) (0.1 * (lastWidth / 2)) ));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(
        		(int) (0.04 * lastHeight),
        		(int) (0.12 * lastWidth),
        		(int) (0.08 * lastHeight),
        		(int) (0.12 * lastWidth)));
        panel.add(title);
        
        if (player == 1) {
        	playerNameLabels[0] = title;
        } else {
        	playerNameLabels[1] = title;
        }
        
        JPanel colorsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.06 * lastWidth), 0));
        colorsRow.setBackground(Color.WHITE);
        colorsRow.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight), 0, (int) (0.02 * lastHeight), 0));

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
        for (Color color : colors) {
            JPanel colorBox = new JPanel();
            colorBox.setBackground(color);
            colorBox.setPreferredSize(new Dimension((int) (0.08 * lastWidth), (int) (0.08 * lastWidth)));
            colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.01 * lastWidth / 2)));
            colorBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
            prepareActionsColorBox(colorBox, color, colorsRow, player);
            colorsRow.add(colorBox);
        }
        panel.add(colorsRow);
        
        if (player == 1) {
        	playerColorsRows[0] = colorsRow;
        } else {
        	playerColorsRows[1] = colorsRow;
        }
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        namePanel.setBackground(Color.WHITE);
        namePanel.setBorder(BorderFactory.createEmptyBorder((int) (0.04 * lastHeight), 0, (int) (0.04 * lastHeight), 0));
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.05 * lastWidth / 2)));
        
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension((int) (0.08 * lastWidth / 2), (int) (0.05 * lastWidth / 2)));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        panel.add(namePanel);
        
        if (player == 1) {
        	playerNameFields[0] = namePanel;
        } else {
        	playerNameFields[1] = namePanel;
        }
        
        JLabel controlsLabel = new JLabel("<html>Controls: " + controls.replace("\n", "<br>") + "</html>", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.06 * lastWidth / 2)));
        controlsLabel.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight), 0, (int) (0.01 * lastHeight), 0));
        controlsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(controlsLabel);
        
        if (player == 1) {
        	playerControlsLabels[0] = controlsLabel;
        } else {
        	playerControlsLabels[1] = controlsLabel;
        }
        
        return panel;
    }
    
    private void prepareActionsColorBox(JPanel colorBox, Color color, JPanel colorsRow, int player) {
    	colorBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	int newWidth = getWidth();
            	if (player == 1) {
            		selectedColorPO = color;
            	} else {
            		selectedColorPT = color;
            	}
            	for (Component comp : colorsRow.getComponents()) {
                	if (comp instanceof JPanel) {
    	                JPanel colorBox = (JPanel) comp;
    	                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.01 * newWidth / 2)));
    	            }
    	        }
                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.02 * newWidth / 2)));
            }
        });
    }
}
