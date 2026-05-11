package presentation;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class PauseWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JButton btnContinue;
    private JButton btnBackMenu;
    private JButton btnSave;
    private JButton btnLoad;
    
    public PauseWindow(JFrame parent) {
        super(parent, "Pause", true);
        prepareElements(parent);
        prepareActions();
    }
    
    public void prepareElements(JFrame parent) {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
        setSize(300, 400);
        setLocationRelativeTo(parent);
        
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Color caja:"), gbc);
        gbc.gridx = 1;
        buttonColorCaja = new JButton("Elegir");
        mainPanel.add(buttonColorCaja, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Color caja destino:"), gbc);
        gbc.gridx = 1;
        buttonColorCajaDestino = new JButton("Elegir");
        mainPanel.add(buttonColorCajaDestino, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Color destino:"), gbc);
        gbc.gridx = 1;
        buttonColorDestino = new JButton("Elegir");
        mainPanel.add(buttonColorDestino, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        buttonSalir = new JButton("Salir");
        mainPanel.add(buttonSalir, gbc);
        gbc.gridx = 1;
        buttonAceptar = new JButton("Aceptar");
        mainPanel.add(buttonAceptar, gbc);
        
        add(mainPanel);
    }
    
    public void prepareActions() {
        buttonColorCaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { elegirColorCaja(); }
        });
        buttonColorCajaDestino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { elegirColorCajaDestino(); }
        });
        buttonColorDestino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { elegirColorDestino(); }
        });
        buttonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { aceptar(); }
        });
        buttonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
    }
    
    public void elegirColorCaja() {
        Color color = JColorChooser.showDialog(this, "Color caja", colorCaja);
        if (color != null) colorCaja = color;
    }
    
    public void elegirColorCajaDestino() {
        Color color = JColorChooser.showDialog(this, "Color caja destino", colorCajaDestino);
        if (color != null) colorCajaDestino = color;
    }
    
    public void elegirColorDestino() {
        Color color = JColorChooser.showDialog(this, "Color destino", colorDestino);
        if (color != null) colorDestino = color;
    }
    
    public void aceptar() {
        dispose();
    }
    
    public Color getColorCaja() { return colorCaja; }
    public Color getColorCajaDestino() { return colorCajaDestino; }
    public Color getColorDestino() { return colorDestino; }
}