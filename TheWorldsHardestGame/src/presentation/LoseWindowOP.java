package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoseWindowOP extends JDialog {
	private static final long serialVersionUID = 1L;
	private GameWindow parent;
	private JPanel mainPanel;
	private JLabel winLabel;
	private JLabel informationLabel;
	private JLabel questionLabel;
	private JButton btnContinue;
    private JButton btnBackMenu;
    private JButton btnSave;
    private JButton btnLoad;
    
    public LoseWindowOP(GameWindow parent) {
        super(parent, "Win", true);
        this.parent = parent;
        prepareElements();
        prepareActions();
    }
    
    public void prepareElements() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(parent);
        
        mainPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        
        winLabel = new JLabel("¡You Lost!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        winLabel.setBorder(BorderFactory.createEmptyBorder(
        		20,
        		0,
        		20,
        		0));
        mainPanel.add(btnContinue, gbc);
        
        informationLabel = new JLabel("Current Total Time: " + parent.getTotalTimePO() + "\nTime in this Level: " + parent.getLevelTimePO(), SwingConstants.CENTER);
        informationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        informationLabel.setBorder(BorderFactory.createEmptyBorder(
        		20,
        		0,
        		20,
        		0));
        gbc.gridy = 1;
        mainPanel.add(btnContinue, gbc);
        
        questionLabel = new JLabel("¿Do You Want To Continue?", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(
        		20,
        		0,
        		20,
        		0));
        gbc.gridy = 2;
        mainPanel.add(btnContinue, gbc);
        
        btnContinue = new JButton("Continue");
        btnContinue.setPreferredSize(new Dimension(40, 14));
        btnContinue.setBackground(new Color(220, 80, 80));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 3;
        mainPanel.add(btnContinue, gbc);
        
        btnBackMenu = new JButton("Back To Menu");
        btnBackMenu.setPreferredSize(new Dimension(40, 14));
        btnBackMenu.setBackground(new Color(220, 80, 80));
        btnBackMenu.setForeground(Color.WHITE);
        btnBackMenu.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 4;
        mainPanel.add(btnBackMenu, gbc);
        
        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(40, 14));
        btnSave.setBackground(new Color(220, 80, 80));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 5;
        mainPanel.add(btnSave, gbc);
        
        btnLoad = new JButton("Continue");
        btnLoad.setPreferredSize(new Dimension(40, 14));
        btnLoad.setBackground(new Color(220, 80, 80));
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 6;
        mainPanel.add(btnLoad, gbc);
        
        add(mainPanel);
    }
    
    public void prepareActions() {
    	btnContinue.addActionListener(e -> {
    		parent.nextLevel();
    		dispose();
        });

        btnBackMenu.addActionListener(e -> {
        	parent.dispose();
        	dispose();
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        	int width = (int) (screenSize.getWidth ()/2);
            int height = (int) (screenSize.getHeight ()/2);
        	new MainWindow(width, height).setVisible(true);
        });
        
        btnSave.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
        });

        btnLoad.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Archivo cargado: " + file.getName());
            }
        });
    }
}