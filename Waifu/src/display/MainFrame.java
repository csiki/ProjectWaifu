package display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import core.Settings;
import javax.swing.JPanel;
import java.awt.Insets;

//  @ Project		: ProjectWaifu
//  @ File Name		: MainFrame.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class MainFrame {
    
    private SkinPanel skinPanel;
    private CloudCommentPanel cloudCommentPanel;
    private MenuPanel menuPanel;
    private Settings settings;
    private SkinOptionsDialog skinOptionsDialog;
    private AIOptionsDialog aiOptionsDialog;
    private SettingsDialog settingsDialog;
    
    private JFrame frame = new JFrame();
    
    /**
	 * Create the application.
	 */
	public MainFrame(Settings settings,
			SkinPanel skinPanel, CloudCommentPanel cloudCommentPanel, MenuPanel menuPanel,
			SkinOptionsDialog skinOptionsDialog, AIOptionsDialog aiOptionsDialog, SettingsDialog settingsDialog) {
		this.settings = settings;
		this.skinPanel = skinPanel;
		this.cloudCommentPanel = cloudCommentPanel;
		this.menuPanel = menuPanel;
		this.skinOptionsDialog = skinOptionsDialog;
		this.aiOptionsDialog = aiOptionsDialog;
		this.settingsDialog = settingsDialog;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame.setBounds(settings.getPosX(), settings.getPosY(), (int) settings.getResolution().getWidth() + Settings.cloudWidth + 50, (int)settings.getResolution().getHeight() + 50);
		//frame.setBounds(200, 200, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color (0, 0, 0, 0));
		
		if (this.settings.isPlaceOnTop()) {
			this.frame.setAlwaysOnTop(true);
		}
		
		// gridbag
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};*/
		frame.getContentPane().setLayout(gridBagLayout);
		
		// skin panel gbc
		GridBagConstraints skinGBC = new GridBagConstraints();
		skinGBC.fill = GridBagConstraints.BOTH;
		skinGBC.gridheight = 2;
		skinGBC.gridx = 0;
		skinGBC.gridy = 0;
		frame.getContentPane().add(this.skinPanel, skinGBC);
		
		// menu panel gbc
		GridBagConstraints menuGBC = new GridBagConstraints();
		menuGBC.fill = GridBagConstraints.BOTH;
		menuGBC.gridx = 1;
		menuGBC.gridy = 0;
		frame.getContentPane().add(this.menuPanel, menuGBC);
		
		// cloud comment panel gbc
		GridBagConstraints cloudGBC = new GridBagConstraints();
		cloudGBC.fill = GridBagConstraints.BOTH;
		cloudGBC.gridx = 1;
		cloudGBC.gridy = 1;
		frame.getContentPane().add(this.cloudCommentPanel, cloudGBC);
		
		// drag and drop
		DragMouseListener dml = new DragMouseListener(this.frame);
		this.frame.getContentPane().addMouseListener(dml);
		this.frame.getContentPane().addMouseMotionListener(dml);
		
		skinPanel.repaint();
		
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
