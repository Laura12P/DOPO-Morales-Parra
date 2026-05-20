package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import presentation.enums.GameConfig;
import presentation.enums.PlayerType;

public class CustomizationTwoPlayersWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
	private JPanel playersPanel;
	private JPanel playerOnePanel;
	private JPanel playerTwoPanel;
	private JLabel[] playerNameLabels;
	private JPanel[] skinsPlayerOne;
	private JPanel[] skinsPlayerTwo;
	private JLabel[] skinsNames;
	private JLabel[] skinsSkills;
	private JLabel[] playerNameTextFieldLabels;
	private JTextField[] playerTextFields;
	private JLabel[] playerControlsLabels;
	
	private JPanel buttonsPanel;
	private JButton btnBack;
	private JButton btnContinue;
	
    private PlayerType selectedCharacterPO;
    private PlayerType selectedCharacterPT;

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
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        
        playersPanel = new JPanel(new GridLayout(1, 2));
        playersPanel.setBackground(Color.WHITE);
        
        PlayerType[] playerTypes= PlayerType.values();
        playerNameLabels = new JLabel[2];
    	skinsPlayerOne = new JPanel[playerTypes.length];
    	skinsPlayerTwo = new JPanel[playerTypes.length];
    	skinsNames = new JLabel[playerTypes.length * 2];
        skinsSkills = new JLabel[playerTypes.length * 2];
    	playerNameTextFieldLabels = new JLabel[2];
    	playerTextFields = new JTextField[2];
    	playerControlsLabels = new JLabel[2];
    	
        playerOnePanel = buildPlayerPanel("Player One", "WASD", 1, playerTypes, lastWidth, lastHeight);
        playersPanel.add(playerOnePanel);
        playerTwoPanel = buildPlayerPanel("Player Two", "Up/Down/\nLeft/Right", 2, playerTypes, lastWidth, lastHeight);
        playersPanel.add(playerTwoPanel);
        
        mainPanel.add(playersPanel, gbc);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.1 * lastWidth), 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (0.04 * lastHeight), 0));

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonsPanel, gbc);

        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnBack.addActionListener(e -> {
            dispose();
            new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        });

        btnContinue.addActionListener(e -> {
        	String namePO = playerTextFields[0].getText();
        	String namePT = playerTextFields[1].getText();
        	
        	if ((namePO.isBlank() || selectedCharacterPO == null) && (namePT.isBlank() || selectedCharacterPT == null)) {
                JOptionPane.showMessageDialog(this, "Please enter valid names and select skins for both Players.");
                return;
            } else if (namePO.isBlank() || selectedCharacterPO == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a skin for Player One.");
                return;
            } else if (namePT.isBlank() || selectedCharacterPT == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a skin for Player Two.");
                return;
            }
        	
        	dispose();
        	GameConfig gameConfig = GameConfig.getInstance();
            GameConfig.initDoublePlayer(namePO, selectedCharacterPO, namePT, selectedCharacterPT);
            new LevelSelectorWindow(getWidth(), getHeight(), gameConfig).setVisible(true);
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	        
    	        for (JLabel nameLabel : playerNameLabels) {
    	        	if (nameLabel != null) {
                		nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.06 * newWidth / 2)));
                	}
    	        }
    	        
    	        for (JPanel skin : skinsPlayerOne) {
    	        	Color skinColor = skin.getBackground();
    	        	PlayerType type = PlayerType.getPlayerTypeFromColor(skinColor);
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	int size = (int) (type.getSizeMultiplier() * 0.08 * newWidth);
    	        	skin.setPreferredSize(new Dimension(size, size));
    	        	if (selectedCharacterPO == null) {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));
    	        	} else if (skinColor == selectedCharacterPO.getColor()){
        	        	skin.setBorder(BorderFactory.createLineBorder(Color.ORANGE, (int) (0.01 * getWidth() / 2)));
    	        	} else {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));
    	        	}
    	        }
    	        
    	        for (JPanel skin : skinsPlayerTwo) {
    	        	Color skinColor = skin.getBackground();
    	        	PlayerType type = PlayerType.getPlayerTypeFromColor(skinColor);
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	int size = (int) (type.getSizeMultiplier() * 0.08 * newWidth);
    	        	skin.setPreferredSize(new Dimension(size, size));
    	        	if (selectedCharacterPT == null) {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));
    	        	} else if (skinColor == selectedCharacterPT.getColor()){
        	        	skin.setBorder(BorderFactory.createLineBorder(Color.ORANGE, (int) (0.01 * getWidth() / 2)));
    	        	} else {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));
    	        	}
    	        }
    	        
    	        for (JLabel name: skinsNames) {
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	name.setFont(new Font("Arial", Font.PLAIN, (int) (0.03 * newWidth / 2)));
    	        	name.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * newHeight / 2), 0, (int) (0.01 * newHeight / 2), 0));
                }
    	        
    	        for (JLabel skills: skinsSkills) {
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	skills.setFont(new Font("Arial", Font.PLAIN, (int) (0.025 * newWidth / 2)));
    	            skills.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * newHeight / 2), 0, (int) (0.01 * newHeight / 2), 0));
                }
    	        
    	        for (JLabel controls: playerControlsLabels) {
    	        	controls.setFont(new Font("Arial", Font.PLAIN, (int) (0.035 * newWidth / 2)));
                }
    	        
    	        for (JLabel nameTextField : playerNameTextFieldLabels) {
    	        	nameTextField.setFont(new Font("Arial", Font.PLAIN, (int) (0.04 * newWidth / 2)));
    	        }
    	        
    	        for (JTextField textField: playerTextFields) {
    	        	textField.setPreferredSize(new Dimension((int) (0.08 * newWidth / 2), (int) (0.04 * newWidth / 2)));
    	        }
    	        
    	        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (0.04 * newHeight), 0));
    	        
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

    private JPanel buildPlayerPanel(String playerTitle, String controls, int player, PlayerType[] playerTypes, int lastWidth, int lastHeight) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        GridBagConstraints gbcPlayersPanel = new GridBagConstraints();
        gbcPlayersPanel.gridx = 0;
        gbcPlayersPanel.gridy = 0;
        gbcPlayersPanel.weighty = 1;
        
        JLabel title = new JLabel(playerTitle, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, (int) (0.06 * lastWidth / 2) ));
        panel.add(title,gbcPlayersPanel);
        
        if (player == 1) {
        	playerNameLabels[0] = title;
        } else {
        	playerNameLabels[1] = title;
        }
        
        JPanel charactersPanel = new JPanel(new GridBagLayout());
        charactersPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcCharactersPanel = new GridBagConstraints();
        gbcCharactersPanel.gridx = 0;
        gbcCharactersPanel.insets = new Insets(0, (int) (0.02 * lastWidth / 2), 0, (int) (0.02 * lastWidth / 2)); 
        gbcCharactersPanel.anchor = GridBagConstraints.CENTER;  
        gbcCharactersPanel.weighty = 1;
        for (int i = 0; i < playerTypes.length; i++) {
            PlayerType type = playerTypes[i];
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            int size = (int) (type.getSizeMultiplier() * 0.08 * lastWidth / 2);

            JPanel square = new JPanel();
            square.setBackground(type.getColor());
            square.setPreferredSize(new Dimension(size, size));
            square.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * lastWidth / 2)));
            square.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (player == 1) {
            	skinsPlayerOne[i] = square;
            } else {
            	skinsPlayerTwo[i] = square;
            }

            JLabel skinName = new JLabel(type.toString(), SwingConstants.CENTER);
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            skinName.setFont(new Font("Arial", Font.PLAIN, (int) (0.03 * lastWidth / 2)));
            skinName.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight / 2), 0, (int) (0.01 * lastHeight / 2), 0));
            if (player == 1) {
            	skinsNames[i] = skinName;
            } else {
            	skinsNames[i+3] = skinName;
            }
            
            JLabel skills = new JLabel(type.getSkillDescription(), SwingConstants.CENTER);
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            skills.setFont(new Font("Arial", Font.PLAIN, (int) (0.025 * lastWidth / 2)));
            skills.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight / 2), 0, (int) (0.01 * lastHeight / 2), 0));
            if (player == 1) {
            	skinsSkills[i] = skills;
            } else {
            	skinsSkills[i+3] = skills;
            }
            
            gbcCharactersPanel.gridy = 0;
            charactersPanel.add(square, gbcCharactersPanel);
            gbcCharactersPanel.gridy = 1;
            charactersPanel.add(skinName, gbcCharactersPanel);
            gbcCharactersPanel.gridy = 2;
            charactersPanel.add(skills, gbcCharactersPanel);
            
            prepareActionsSkins(square, type);
            gbcCharactersPanel.gridx += 1;
        }
        
        gbcPlayersPanel.gridy = 1;
        panel.add(charactersPanel,gbcPlayersPanel);
        
        JLabel controlsLabel = new JLabel("<html><center>Controls: " + controls.replace("\n", "<br>") + "</center></html>", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.035 * lastWidth / 2)));
        controlsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (player == 1) {
        	playerControlsLabels[0] = controlsLabel;
        } else {
        	playerControlsLabels[1] = controlsLabel;
        }
        
        gbcPlayersPanel.gridy = 2;
        panel.add(controlsLabel,gbcPlayersPanel);
        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        namePanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.04 * lastWidth / 2)));
        if (player == 1) {
        	playerNameTextFieldLabels[0] = nameLabel;
        } else {
        	playerNameTextFieldLabels[1] = nameLabel;
        }
        
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension((int) (0.08 * lastWidth / 2), (int) (0.04 * lastWidth / 2)));
        if (player == 1) {
        	playerTextFields[0] = nameField;
        } else {
        	playerTextFields[1] = nameField;
        }
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        gbcPlayersPanel.gridy = 3;
        panel.add(namePanel,gbcPlayersPanel);
        
        return panel;
    }
    
    private void prepareActionsSkins(JPanel square, PlayerType type) {
    	square.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	if (jPanelArrayContains(skinsPlayerOne, square)) {
            		selectedCharacterPO = type;
                    for (JPanel skin : skinsPlayerOne) {
                    	skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));  
                    }
            	} else {
            		selectedCharacterPT = type;
                    for (JPanel skin : skinsPlayerTwo) {
                    	skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth() / 2)));  
                    }
            	}
                square.setBorder(BorderFactory.createLineBorder(Color.ORANGE, (int) (0.01 * getWidth() / 2)));
            }
        });
    }
    
    private boolean jPanelArrayContains(JPanel[] array, JPanel wantedPanel) {
    	boolean contains = false;
    	for (JPanel panel : array) {
    	    if (panel == wantedPanel) {
    	        contains = true;
    	        return contains;
    	    }
    	}
    	return contains;
    }
}
