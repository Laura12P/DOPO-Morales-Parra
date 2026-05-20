package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import presentation.enums.MachineDifficulty;

public class MachineDificultyWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JLabel title;
	private JPanel buttonPanel;
	private JButton btnRandom;
	private JButton btnExpert;
	private JPanel bottomPanel;
	private JButton btnBack;
	
    public MachineDificultyWindow(int lastWidth, int lastHeight) {
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	setTitle("Machine Dificulty");
    	setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        title = new JLabel("Machine Dificulty", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, (int) (0.04 * lastWidth)));
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
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnRandom = new JButton("Random");
        btnRandom.setPreferredSize(new Dimension((int) (0.18 * lastWidth), (int) (0.138 * lastHeight)));
        btnRandom.setBackground(new Color(70, 130, 200));
        btnRandom.setForeground(Color.WHITE);
        btnRandom.setFocusPainted(false);
        int btnOPWidth = btnRandom.getWidth();
        btnRandom.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnOPWidth)));
        btnRandom.setBorderPainted(false);

        btnExpert = new JButton("Expert");
        btnExpert.setPreferredSize(new Dimension((int) (0.18 * lastWidth), (int) (0.138 * lastHeight)));
        btnExpert.setBackground(new Color(220, 80, 80));
        btnExpert.setForeground(Color.WHITE);
        btnExpert.setFocusPainted(false);
        int btnExpertWidth = btnExpert.getWidth();
        btnExpert.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnExpertWidth)));
        btnExpert.setBorderPainted(false);
        
        buttonPanel.add(btnRandom);
        buttonPanel.add(btnExpert);
        
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
    	btnRandom.addActionListener(e -> {
        	dispose();
        	new CustomizationOnePlayerWindow(getWidth(), getHeight(), MachineDifficulty.RANDOM).setVisible(true);
        });

        btnExpert.addActionListener(e -> {
        	dispose();
        	new CustomizationOnePlayerWindow(getWidth(), getHeight(), MachineDifficulty.EXPERT).setVisible(true);
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
    	        
    	        title.setFont(new Font("Arial", Font.PLAIN, (int)(0.04 * newWidth)));
    	        title.setBorder(BorderFactory.createEmptyBorder(
    	        		(int) (0.09 * newHeight),
    	        		(int) (0.12 * newWidth),
    	        		(int) (0.09 * newHeight),
    	        		(int) (0.12 * newWidth)));
    	
    	        btnRandom.setPreferredSize(new Dimension((int) (0.18 * newWidth), (int) (0.138 * newHeight)));
    	        int btnOPWidth = btnRandom.getWidth();
    	        btnRandom.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnOPWidth)));
    	        
    	        btnExpert.setPreferredSize(new Dimension((int) (0.18 * newWidth), (int) (0.138 * newHeight)));
    	        int btnExpertWidth = btnExpert.getWidth();
    	        btnExpert.setFont(new Font("Arial", Font.PLAIN, (int) (0.15 * btnExpertWidth)));
    	        
    	        btnBack.setPreferredSize(new Dimension((int) (0.14 * newWidth), (int) (0.14 * newHeight)));
    	        int btnBWidth = btnBack.getWidth();
    	        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.20 * btnBWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }
}