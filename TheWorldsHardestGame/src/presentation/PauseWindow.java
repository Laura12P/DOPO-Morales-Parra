package presentation;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PauseWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public static final int RESUME = 0;
	public static final int MENU = 1;
	public static final int SAVE = 2;
	public static final int LOAD = 3;
	
	private int answer = -1;
	
	private GameWindow parent;
	private JPanel mainPanel;
	private JButton btnResume;
    private JButton btnBackMenu;
    private JButton btnSave;
    private JButton btnLoad;
    
    public PauseWindow(GameWindow parent) {
        super(parent, "Pause", true);
        this.parent = parent;
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(parent);
        
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        
        btnResume = new JButton("Resume");
        btnResume.setPreferredSize(new Dimension(200, 50));
        btnResume.setBackground(new Color(230, 200, 50));
        btnResume.setForeground(Color.BLACK);
        btnResume.setFont(new Font("Arial", Font.PLAIN, 19));
        btnResume.setFocusPainted(false);
        btnResume.setBorderPainted(false);
        mainPanel.add(btnResume, gbc);
        
        btnBackMenu = new JButton("Back To Menu");
        btnBackMenu.setPreferredSize(new Dimension(200, 50));
        btnBackMenu.setBackground(new Color(220, 80, 80));
        btnBackMenu.setForeground(Color.BLACK);
        btnBackMenu.setFont(new Font("Arial", Font.PLAIN, 19));
        btnBackMenu.setFocusPainted(false);
        btnBackMenu.setBorderPainted(false);
        gbc.gridy = 1;
        mainPanel.add(btnBackMenu, gbc);
        
        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(200, 50));
        btnSave.setBackground(new Color(153, 255, 255));
        btnSave.setForeground(Color.BLACK);
        btnSave.setFont(new Font("Arial", Font.PLAIN, 19));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        gbc.gridy = 2;
        mainPanel.add(btnSave, gbc);
        
        btnLoad = new JButton("Load");
        btnLoad.setPreferredSize(new Dimension(200, 50));
        btnLoad.setBackground(new Color(153, 255, 255));
        btnLoad.setForeground(Color.BLACK);
        btnLoad.setFont(new Font("Arial", Font.PLAIN, 19));
        btnLoad.setFocusPainted(false);
        btnLoad.setBorderPainted(false);
        gbc.gridy = 3;
        mainPanel.add(btnLoad, gbc);
        
        add(mainPanel);
    }
    
    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resume();
            }
        });
    	
    	btnResume.addActionListener(e -> {
    		resume();
        });

        btnBackMenu.addActionListener(e -> {
        	answer = 1;
        	dispose();
        });
        
        btnSave.addActionListener(e -> {
        	answer = 2;
        	dispose();
        });

        btnLoad.addActionListener(e -> {
        	answer = 3;
        	dispose();
        });
    }
    
    private void resume() {
    	answer = 0;
    	dispose();
    }
    
    public int showMessageDialog() {
    	this.setVisible(true);
    	return answer;
    }
}