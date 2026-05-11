package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomizationOnePlayerWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
	private JPanel colorsRow;
	private JPanel namePanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JPanel buttonsPanel;
	private JButton btnBack;
	private JButton btnContinue;
	private Color selectedColor;
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

        colorsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.075 * lastWidth), 0));
        colorsRow.setBackground(Color.WHITE);
        colorsRow.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
        for (Color color : colors) {
            JPanel colorBox = new JPanel();
            colorBox.setBackground(color);
            colorBox.setPreferredSize(new Dimension((int) (0.13 * lastWidth), (int) (0.13 * lastWidth)));
            colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.005 * lastWidth)));
            colorBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
            prepareActionsColorBox(colorBox, color);
            colorsRow.add(colorBox);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        
        mainPanel.add(colorsRow, gbc);

        namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        namePanel.setBackground(Color.WHITE);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.04 * lastWidth)));
        
        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension((int) (0.1 * lastWidth), (int) (0.04 * lastWidth)));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weighty = 1;
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
        
        gbc.gridy = 2;
        gbc.weighty = 0;
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
            if (name.isBlank() || selectedColor == null) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and select a color.");
                return;
            }
            dispose();
            GameConfig gameConfig = GameConfig.getInstance();
            GameConfig.initSinglePlayer(name, selectedColor, machineDifficulty);
            new LevelSelectorWindow(getWidth(), getHeight(), gameConfig).setVisible(true);
        });
    }
    
    private void prepareActionsColorBox(JPanel colorBox, Color color) {
    	colorBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
    	        int newWidth = getWidth();
                selectedColor = color;
                for (Component comp : colorsRow.getComponents()) {
                	if (comp instanceof JPanel) {
    	                JPanel colorBox = (JPanel) comp;
    	                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.01 * newWidth)));
    	            }
    	        }
                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.02 * newWidth)));
            }
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	        
    	        for (Component comp : colorsRow.getComponents()) {
    	            if (comp instanceof JPanel) {
    	                JPanel colorBox = (JPanel) comp;
    	                colorBox.setPreferredSize(new Dimension((int) (0.13 * newWidth), (int) (0.13 * newWidth)));
    	                colorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, (int) (0.01 * newWidth)));
    	            }
    	        }
    	        
    	        nameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (0.04 * newWidth)));
    	        nameField.setPreferredSize(new Dimension((int) (0.1 * newWidth), (int) (0.04 * newWidth)));
    	        
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
