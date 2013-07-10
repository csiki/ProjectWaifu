package display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: MainFrame.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class MainFrame {
    
    private SkinPanel skinPanel;
    private CloudCommentPanel lowerCloudPanel;
    private CloudCommentPanel upperCloudPanel;
    private MenuPanel menuPanel;
    private Settings settings;
    
    private JFrame frame = new JFrame();
    
    /**
	 * Create the application.
	 */
    public MainFrame(Settings settings) {
		this.settings = settings;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame.setBounds(settings.getPosX(), settings.getPosY(),
				this.settings.getCurrentSizing().frameWidth, settings.getCurrentSizing().frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color (0, 0, 0, 0));
		
		if (this.settings.isPlaceOnTop()) {
			this.frame.setAlwaysOnTop(true);
		}
		
		// gridbag
		GridBagLayout gridBagLayout = new GridBagLayout();
		frame.getContentPane().setLayout(gridBagLayout);
		
		// upper cloud panel gpc
		GridBagConstraints upperCloudGBC = new GridBagConstraints();
		upperCloudGBC.fill = GridBagConstraints.BOTH;
		upperCloudGBC.gridwidth = 2;
		upperCloudGBC.gridx = 0;
		upperCloudGBC.gridy = 0;
		upperCloudGBC.insets = new Insets(0,130,0,0);
		frame.getContentPane().add(this.upperCloudPanel, upperCloudGBC);
		
		// skin panel gbc
		GridBagConstraints skinGBC = new GridBagConstraints();
		skinGBC.fill = GridBagConstraints.BOTH;
		skinGBC.gridheight = 2;
		skinGBC.gridx = 0;
		skinGBC.gridy = 1;
		frame.getContentPane().add(this.skinPanel, skinGBC);
		
		// menu panel gbc
		GridBagConstraints menuGBC = new GridBagConstraints();
		menuGBC.fill = GridBagConstraints.BOTH;
		menuGBC.gridx = 1;
		menuGBC.gridy = 1;
		frame.getContentPane().add(this.menuPanel, menuGBC);
		
		// lower cloud panel gbc
		GridBagConstraints lowerCloudGBC = new GridBagConstraints();
		lowerCloudGBC.fill = GridBagConstraints.BOTH;
		lowerCloudGBC.gridx = 1;
		lowerCloudGBC.gridy = 2;
		frame.getContentPane().add(this.lowerCloudPanel, lowerCloudGBC);
		
		// drag and drop
		DragMouseListener dml = new DragMouseListener(this.frame, this.settings);
		this.frame.getContentPane().addMouseListener(dml);
		this.frame.getContentPane().addMouseMotionListener(dml);
		
		skinPanel.repaint();
		
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void providePanels(SkinPanel skinPanel, CloudCommentPanel lowerCloudPanel, CloudCommentPanel upperCloudPanel, MenuPanel menuPanel) {
		this.skinPanel = skinPanel;
		this.lowerCloudPanel = lowerCloudPanel;
		this.upperCloudPanel = upperCloudPanel;
		this.menuPanel = menuPanel;
	}
}
