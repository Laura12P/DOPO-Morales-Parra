package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameModeWindow extends JFrame {
	
	private JPanel mainPanel;
	private JLabel title;
	private JPanel buttonPanel;
	private JButton btnOnePlayer;
	private JButton btnPvP;
	private JButton btnPvM;
	private JPanel bottomPanel;
	private JButton btnBack;
	
    public GameModeWindow(int lastWidth, int lastHeight) {
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	setTitle("Game Modes");
    	setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        title = new JLabel("Game Mode", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, (int) (0.05 * lastWidth)));
        title.setBorder(BorderFactory.createEmptyBorder(
        		(int) (0.1 * lastHeight),
        		(int) (0.12 * lastWidth),
        		(int) (0.1 * lastHeight),
        		(int) (0.12 * lastWidth)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(title, gbc);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnOnePlayer = new JButton("One Player");
        btnOnePlayer.setPreferredSize(new Dimension((int) (0.18 * lastWidth), (int) (0.138 * lastHeight)));
        btnOnePlayer.setBackground(new Color(70, 130, 200));
        btnOnePlayer.setForeground(Color.WHITE);
        btnOnePlayer.setFocusPainted(false);
        int btnOPWidth = btnOnePlayer.getWidth();
        btnOnePlayer.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnOPWidth)));
        btnOnePlayer.setBorderPainted(false);

        btnPvP = new JButton("Player vs Player");
        btnPvP.setPreferredSize(new Dimension((int) (0.25 * lastWidth), (int) (0.138 * lastHeight)));
        btnPvP.setBackground(new Color(220, 80, 80));
        btnPvP.setForeground(Color.WHITE);
        btnPvP.setFocusPainted(false);
        int btnPVPWidth = btnPvP.getWidth();
        btnPvP.setFont(new Font("Arial", Font.PLAIN, (int) (0.11 * btnPVPWidth)));
        btnPvP.setBorderPainted(false);

        btnPvM = new JButton("Player vs Machine");
        btnPvM.setPreferredSize(new Dimension((int) (0.28 * lastWidth), (int) (0.138 * lastHeight)));
        btnPvM.setBackground(new Color(230, 200, 50));
        btnPvM.setForeground(Color.WHITE);
        btnPvM.setFocusPainted(false);
        int btnPVMWidth = btnPvM.getWidth();
        btnPvM.setFont(new Font("Arial", Font.PLAIN, (int) (0.1 * btnPVMWidth)));
        btnPvM.setBorderPainted(false);
        
        buttonPanel.add(btnOnePlayer);
        buttonPanel.add(btnPvP);
        buttonPanel.add(btnPvM);
        
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(buttonPanel,gbc);
        
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        bottomPanel.setBackground(Color.WHITE);

        btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension((int) (0.14 * lastWidth), (int) (0.14 * lastHeight)));
        btnBack.setFocusPainted(false);
        int btnBWidth = btnBack.getWidth();
        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.20 * btnBWidth)));

        bottomPanel.add(btnBack);
        
        gbc.gridy = 2;
        gbc.weighty = 0;
        mainPanel.add(bottomPanel, gbc);

        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnOnePlayer.addActionListener(e -> {
        	dispose();
        	Dimension sizeJFrame = this.getSize();
        	new CustomizationOnePlayerWindow(sizeJFrame.width, sizeJFrame.height, false).setVisible(true);
        });

        btnPvP.addActionListener(e -> {
            dispose();
            new CustomizationTwoPlayersWindow().setVisible(true);
        });

        btnPvM.addActionListener(e -> {
        	dispose();
        	Dimension sizeJFrame = this.getSize();
        	new CustomizationOnePlayerWindow(sizeJFrame.width, sizeJFrame.height, true).setVisible(true);
        });
        
        btnBack.addActionListener(e -> {
            dispose();
            new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	        
    	        title.setFont(new Font("Arial", Font.PLAIN, (int)(0.05 * newWidth)));
    	        title.setBorder(BorderFactory.createEmptyBorder(
    	        		(int) (0.09 * newHeight),
    	        		(int) (0.12 * newWidth),
    	        		(int) (0.09 * newHeight),
    	        		(int) (0.12 * newWidth)));
    	
    	        btnOnePlayer.setPreferredSize(new Dimension((int) (0.18 * newWidth), (int) (0.138 * newHeight)));
    	        int btnOPWidth = btnOnePlayer.getWidth();
    	        btnOnePlayer.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnOPWidth)));
    	        
    	        btnPvP.setPreferredSize(new Dimension((int) (0.25 * newWidth), (int) (0.138 * newHeight)));
    	        int btnPVPWidth = btnPvP.getWidth();
    	        btnPvP.setFont(new Font("Arial", Font.PLAIN, (int) (0.11 * btnPVPWidth)));
    	        
    	        btnPvM.setPreferredSize(new Dimension((int) (0.28 * newWidth), (int) (0.138 * newHeight)));
    	        int btnPVMWidth = btnPvM.getWidth();
    	        btnPvM.setFont(new Font("Arial", Font.PLAIN, (int) (0.1 * btnPVMWidth)));
    	        
    	        btnBack.setPreferredSize(new Dimension((int) (0.14 * newWidth), (int) (0.14 * newHeight)));
    	        int btnBWidth = btnBack.getWidth();
    	        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.20 * btnBWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }
}
