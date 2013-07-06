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
		DragMouseListener dml = new DragMouseListener(this.frame, this.settings);
		this.frame.getContentPane().addMouseListener(dml);
		this.frame.getContentPane().addMouseMotionListener(dml);
		
		skinPanel.repaint();
		
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void providePanels(SkinPanel skinPanel, CloudCommentPanel cloudCommentPanel, MenuPanel menuPanel) {
		this.skinPanel = skinPanel;
		this.cloudCommentPanel = cloudCommentPanel;
		this.menuPanel = menuPanel;
	}
}
