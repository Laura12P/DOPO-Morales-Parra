package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel title;
	private JPanel buttonPanel;
    private JButton btnPlayGame;
    private JButton btnLeaderBoard;
    
    public MainWindow(int width, int height) {
        prepareElements(width, height);
        prepareActions();
        prepareResponsiveGUI();
    }
    
    private void prepareElements(int width, int height) {
    	setTitle("The World Hardest Game");
    	setMinimumSize(new Dimension(720, 480));
        setSize(width, height);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        title = new JLabel("The World Hardest Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, (int) (0.05 * width)));
        title.setBorder(BorderFactory.createEmptyBorder(
        		(int) (0.09 * height),
        		(int) (0.12 * width),
        		(int) (0.09 * height),
        		(int) (0.12 * width)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(title, gbc);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (0.08 * width), 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder((int) (0.06 * height), (int) (0.06 * width), (int) (0.06 * height), (int) (0.06 * width)));

        btnPlayGame = new JButton("Play Game");
        btnPlayGame.setPreferredSize(new Dimension((int) (0.21 * width), (int) (0.15 * height)));
        btnPlayGame.setBackground(new Color(70, 130, 200));
        btnPlayGame.setForeground(Color.WHITE);
        btnPlayGame.setFocusPainted(false);
        int btnPGWidth = btnPlayGame.getWidth();
        btnPlayGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.13 * btnPGWidth)));
        btnPlayGame.setBorderPainted(false);

        btnLeaderBoard = new JButton("Leader Board");
        btnLeaderBoard.setPreferredSize(new Dimension((int) (0.26 * width), (int) (0.15 * height)));
        btnLeaderBoard.setBackground(new Color(220, 80, 80));
        btnLeaderBoard.setForeground(Color.WHITE);
        btnLeaderBoard.setFocusPainted(false);
        int btnLBWidth = btnLeaderBoard.getWidth();
        btnLeaderBoard.setFont(new Font("Arial", Font.PLAIN, (int) (0.11 * btnLBWidth)));
        btnLeaderBoard.setBorderPainted(false);

        buttonPanel.add(btnPlayGame);
        buttonPanel.add(btnLeaderBoard);

        gbc.gridy = 1;
        gbc.weightx = 0.8;
        gbc.weighty = 0;
        
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel);
    }
    
    private void prepareActions() {
    	btnPlayGame.addActionListener(e -> {
        	dispose();
        	new PlayGameWindow(getWidth(), getHeight()).setVisible(true);
        });

        btnLeaderBoard.addActionListener(e -> {
        	dispose();
        	new LeaderBoardWindow().setVisible(true); 
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
    	        		(int) (0.12 * newHeight),
    	        		(int) (0.14 * newWidth),
    	        		(int) (0.12 * newHeight),
    	        		(int) (0.14 * newWidth) ));
    	        
    	        
    	        FlowLayout buttonLayout = (FlowLayout) buttonPanel.getLayout();
    	        buttonLayout.setHgap((int)(0.08 * getWidth()));
    	        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
    	        		(int) (0.06 * newHeight),
    	        		(int) (0.06 * newWidth),
    	        		(int) (0.06 * newHeight),
    	        		(int) (0.06 * newWidth) ));

    	
    	        btnPlayGame.setPreferredSize(new Dimension((int) (0.21 * newWidth), (int) (0.15 * newHeight)));
    	        int btnPGWidth = btnPlayGame.getWidth();
    	        btnPlayGame.setFont(new Font("Arial", Font.PLAIN, (int) (0.13 * btnPGWidth)));
    	        
    	        
    	        btnLeaderBoard.setPreferredSize(new Dimension((int) (0.26 * newWidth), (int) (0.15 * newHeight)));
    	        int btnLBWidth = btnLeaderBoard.getWidth();
    	        btnLeaderBoard.setFont(new Font("Arial", Font.PLAIN, (int) (0.11 * btnLBWidth)));
    	        
    	        revalidate();
    	        repaint();
    	    }
    	});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        	int width = (int) (screenSize.getWidth ()/2);
            int height = (int) (screenSize.getHeight ()/2);
            new MainWindow(width, height).setVisible(true);
        });
    }
}