package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import core.Settings;

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
    
    private JFrame frame = new JFrame();
    
    /**
	 * Create the application.
	 */
	public MainFrame(Settings settings, SkinPanel skinPanel, CloudCommentPanel cloudCommentPanel, MenuPanel menuPanel) {
		this.settings = settings;
		this.skinPanel = skinPanel;
		this.cloudCommentPanel = cloudCommentPanel;
		this.menuPanel = menuPanel;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(settings.getPosX(), settings.getPosY(), (int) settings.getResolution().getWidth(), (int)settings.getResolution().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		// skin panel gbc
		GridBagConstraints skinGBC = new GridBagConstraints();
		skinGBC.gridheight = 2;
		skinGBC.gridx = 0;
		skinGBC.gridy = 0;
		frame.getContentPane().add(this.skinPanel, skinGBC);
		
		// menu panel gbc
		GridBagConstraints menuGBC = new GridBagConstraints();
		menuGBC.gridx = 1;
		menuGBC.gridy = 0;
		frame.getContentPane().add(this.menuPanel, menuGBC);
		
		// cloud comment panel gbc
		GridBagConstraints cloudGBC = new GridBagConstraints();
		cloudGBC.gridx = 1;
		cloudGBC.gridy = 1;
		frame.getContentPane().add(this.cloudCommentPanel, cloudGBC);
	}
}
