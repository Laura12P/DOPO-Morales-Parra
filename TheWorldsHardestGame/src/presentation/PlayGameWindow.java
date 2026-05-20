package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

public class PlayGameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel buttonsRow;
	private JPanel bottomPanel;
    private JButton btnNewGame;
    private JButton btnLoadGame;
    private JButton btnBack;

    public PlayGameWindow(int lastWidth, int lastHeight) {
    	prepareElements(lastWidth, lastHeight);
    	prepareActions();
    	prepareResponsiveGUI();
    }
    
    private void prepareElements(int lastWidth, int lastHeight) {
    	setTitle("New Game or Load Game");
    	setMinimumSize(new Dimension(720, 480));
        setSize(lastWidth, lastHeight);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        
        buttonsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttonsRow.setBackground(Color.WHITE);

        btnNewGame = new JButton("New Game");
        btnNewGame.setPreferredSize(new Dimension((int) (0.28 * lastWidth), (int) (0.17 * lastHeight)));
        btnNewGame.setBackground(new Color(70, 130, 200));
        btnNewGame.setForeground(Color.WHITE);
        btnNewGame.setFocusPainted(false);
        int btnNGWidth = btnNewGame.getWidth();
        btnNewGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnNGWidth)));
        btnNewGame.setBorderPainted(false);

        btnLoadGame = new JButton("Load Game");
        btnLoadGame.setPreferredSize(new Dimension((int) (0.29 * lastWidth), (int) (0.17 * lastHeight)));
        btnLoadGame.setBackground(new Color(220, 80, 80));
        btnLoadGame.setForeground(Color.WHITE);
        btnLoadGame.setFocusPainted(false);
        int btnLGWidth = btnLoadGame.getWidth();
        btnLoadGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnLGWidth)));
        btnLoadGame.setBorderPainted(false);
        
        buttonsRow.add(btnNewGame);
        buttonsRow.add(btnLoadGame);
        
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
        btnNewGame.addActionListener(e -> {
        	dispose();
        	new GameModeWindow(getWidth(), getHeight()).setVisible(true);
        });

        btnLoadGame.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
        });
        
        btnBack.addActionListener(e -> {
            dispose();
        	new MainWindow(getWidth() , getHeight()).setVisible(true);
        });
    }
    
    private void prepareResponsiveGUI() {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
    	        int newHeight = getHeight();
    	        int newWidth = getWidth();
    	
    	        btnNewGame.setPreferredSize(new Dimension((int) (0.28 * newWidth), (int) (0.17 * newHeight)));
    	        int btnNGWidth = btnNewGame.getWidth();
    	        btnNewGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnNGWidth)));
    	        
    	        btnLoadGame.setPreferredSize(new Dimension((int) (0.29 * newWidth), (int) (0.17 * newHeight)));
    	        int btnLGWidth = btnLoadGame.getWidth();
    	        btnLoadGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.14 * btnLGWidth)));
    	        
    	        btnBack.setPreferredSize(new Dimension((int) (0.15 * newWidth), (int) (0.16 * newHeight)));
    	        int btnBWidth = btnBack.getWidth();
    	        btnBack.setFont(new Font("Arial", Font.PLAIN, (int) (0.22 * btnBWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }
}