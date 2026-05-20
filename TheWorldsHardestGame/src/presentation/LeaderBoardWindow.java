package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LeaderBoardWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnBack;

	public LeaderBoardWindow() {
        prepareElements();
        prepareActions();
        prepareResponsiveGUI();
    }
    
    private void prepareElements() {
    	setTitle("Leader Board");
        setMinimumSize(new Dimension(720,480));
        setSize(720, 480);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Barra superior
        JLabel topBar = new JLabel("The World Hardest Game");
        topBar.setBackground(new Color(220, 220, 220));
        topBar.setOpaque(true);
        topBar.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        mainPanel.add(topBar, BorderLayout.NORTH);

        // Tabla
        String[] columns = {"Position", "Name", "Total Time", "Level 1", "Level 2", "Level 3"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        // Fila de ejemplo vacía (se llenará con datos reales después)
        // model.addRow(new Object[]{1, "Karla", "3:55", "1:56", "1:03", "0:56"});

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Boton Back To Menu
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        btnBack = new JButton("Back To Menu");
        btnBack.setPreferredSize(new Dimension(180, 50));
        btnBack.setBackground(new Color(70, 130, 200));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBack.setBorderPainted(false);
        
        bottomPanel.add(btnBack);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnBack.addActionListener(e -> {
            dispose();
            Dimension sizeJFrame = this.getSize();
        	new MainWindow(sizeJFrame.width, sizeJFrame.height).setVisible(true);
        });
    }
    
    private void prepareResponsiveGUI() {
    	//Pendiente, estamos esperando a hacer la parte del Modelo que entrega estos datos para ahora si hacer responsivo y mejor este JFrame
    }
}