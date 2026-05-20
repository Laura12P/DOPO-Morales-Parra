package presentation;

import javax.swing.*;

import presentation.enums.GameConfig;
import presentation.enums.MachineDifficulty;
import presentation.enums.PlayerType;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomizationOnePlayerWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    private JPanel mainPanel;
    
    private JLabel selectionLabel;
    
	private JPanel charactersPanel;
	private JPanel[] skins;
	private JLabel[] skinsNames;
	private JLabel[] skinsSkills;
	
	private JLabel controlsLabel;
	
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameField;
	
	private JPanel buttonsPanel;
	private JButton btnBack;
	private JButton btnContinue;
	
	private PlayerType selectedCharacter;
	private MachineDifficulty machineDifficulty;

    public CustomizationOnePlayerWindow(int lastWidth, int lastHeight, MachineDifficulty machineDifficulty) {
    	this.machineDifficulty = machineDifficulty;
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	if (machineDifficulty == MachineDifficulty.NONE) {
    		setTitle("One Player Mode");
    	} else {
    		setTitle("Player VS Machine Mode");
    	}
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        
        
        selectionLabel = new JLabel("Choose your character:", SwingConstants.CENTER);
        selectionLabel.setFont(new Font("Arial", Font.BOLD, (int) (0.025 * lastWidth)));
        selectionLabel.setBorder(BorderFactory.createEmptyBorder((int) (0.02 * lastHeight), 0, (int) (0.015 * lastHeight), 0));
        mainPanel.add(selectionLabel, gbc);
        
        charactersPanel = new JPanel(new GridBagLayout());
        charactersPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcCP = new GridBagConstraints();
        gbcCP.gridx = 0;
        gbcCP.insets = new Insets(0, (int) (0.02 * lastWidth), 0, (int) (0.02 * lastWidth)); 
        gbcCP.anchor = GridBagConstraints.CENTER;  
        gbcCP.weighty = 1;
        
        PlayerType[] playerTypes= PlayerType.values();
        skins = new JPanel[playerTypes.length];
        skinsNames = new JLabel[playerTypes.length];
        skinsSkills = new JLabel[playerTypes.length];
        for (int i = 0; i < playerTypes.length; i++) {
            PlayerType type = playerTypes[i];
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            int size = (int) (type.getSizeMultiplier() * 0.08 * lastWidth);

            JPanel square = new JPanel();
            square.setBackground(type.getColor());
            square.setPreferredSize(new Dimension(size, size));
            square.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * lastWidth)));
            square.setCursor(new Cursor(Cursor.HAND_CURSOR));
            skins[i] = square;

            JLabel skinName = new JLabel(type.toString(), SwingConstants.CENTER);
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            skinName.setFont(new Font("Arial", Font.PLAIN, (int) (0.02 * lastWidth)));
            skinName.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight), 0, (int) (0.01 * lastHeight), 0));
            skinsNames[i] = skinName;
            
            JLabel skills = new JLabel(type.getSkillDescription(), SwingConstants.CENTER);
            //En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
            skills.setFont(new Font("Arial", Font.PLAIN, (int) (0.015 * lastWidth)));
            skills.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * lastHeight), 0, (int) (0.01 * lastHeight), 0));
            skinsSkills[i] = skills;
            
            gbcCP.gridy = 0;
            charactersPanel.add(square, gbcCP);
            gbcCP.gridy = 1;
            charactersPanel.add(skinName, gbcCP);
            gbcCP.gridy = 2;
            charactersPanel.add(skills, gbcCP);
            
            prepareActionsSkins(square, type);
            gbcCP.gridx += 1;
        }

        gbc.gridy = 1;
        mainPanel.add(charactersPanel, gbc);
        
        controlsLabel = new JLabel("Controls: WASD or Up/Down/Left/Right", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.018 * lastWidth)));
        controlsLabel.setBorder(BorderFactory.createEmptyBorder((int) (0.015 * lastHeight), 0, (int) (0.015 * lastHeight), 0));
        gbc.gridy = 2;
        mainPanel.add(controlsLabel, gbc);

        namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        namePanel.setBackground(Color.WHITE);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.018 * lastWidth)));
        
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension((int) (0.2 * lastWidth), (int) (0.018 * lastWidth)));
        nameField.setFont(new Font("Arial", Font.PLAIN, (int) (0.015 * lastWidth)));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        gbc.gridy = 3;
        mainPanel.add(namePanel, gbc);

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
        
        gbc.gridy = 4;
        mainPanel.add(buttonsPanel, gbc);

        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnBack.addActionListener(e -> {
            dispose();
            if (machineDifficulty == MachineDifficulty.NONE) {
            	new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        	} else {
        		new MachineDificultyWindow(getWidth(), getHeight()).setVisible(true);
        	}
        });

        btnContinue.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isBlank() || selectedCharacter == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a Skin.");
                return;
            }
            dispose();
            GameConfig gameConfig = GameConfig.getInstance();
            GameConfig.initSinglePlayer(name, selectedCharacter, machineDifficulty);
            new LevelSelectorWindow(getWidth(), getHeight(), gameConfig).setVisible(true);
        });
    }
    
    private void prepareActionsSkins(JPanel square, PlayerType type) {
    	square.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                selectedCharacter = type;
                for (JPanel skin : skins) {
                	skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth())));  
                }
                square.setBorder(BorderFactory.createLineBorder(Color.ORANGE, (int) (0.01 * getWidth())));
            }
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	        
    	        selectionLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.025 * newWidth)));
    	        selectionLabel.setBorder(BorderFactory.createEmptyBorder((int) (0.02 * newHeight), 0, (int) (0.015 * newHeight), 0));
    	        
    	        for (JPanel skin : skins) {
    	        	Color skinColor = skin.getBackground();
    	        	PlayerType type = PlayerType.getPlayerTypeFromColor(skinColor);
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	int size = (int) (type.getSizeMultiplier() * 0.08 * newWidth);
    	        	skin.setPreferredSize(new Dimension(size, size));
    	        	if (selectedCharacter == null) {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth())));
    	        	} else if (skinColor == selectedCharacter.getColor()){
        	        	skin.setBorder(BorderFactory.createLineBorder(Color.ORANGE, (int) (0.01 * getWidth())));
    	        	} else {
    	        		skin.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * getWidth())));
    	        	}
                }
    	        
    	        for (JLabel name: skinsNames) {
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	name.setFont(new Font("Arial", Font.PLAIN, (int) (0.02 * newWidth)));
    	        	name.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * newHeight), 0, (int) (0.01 * newHeight), 0));
                }
    	        
    	        for (JLabel skill: skinsSkills) {
    	        	//En caso de existir mas skins y salirse del JPanel, hacer mas pequeño el decimal de la linea de abajo
    	        	skill.setFont(new Font("Arial", Font.PLAIN, (int) (0.015 * newWidth)));
    	        	skill.setBorder(BorderFactory.createEmptyBorder((int) (0.01 * newHeight), 0, (int) (0.01 * newHeight), 0));
                }
    	        
    	        controlsLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.018 * newWidth)));
    	        controlsLabel.setBorder(BorderFactory.createEmptyBorder((int) (0.015 * newHeight), 0, (int) (0.015 * newHeight), 0));
    	        
    	        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.018 * newWidth)));
    	        nameField.setPreferredSize(new Dimension((int) (0.2 * newWidth), (int) (0.018 * newWidth)));
    	        nameField.setFont(new Font("Arial", Font.PLAIN, (int) (0.015 * newWidth)));
    	        
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
}
