package display;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudCommentPanel.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudCommentPanel extends JPanel {
	
	private static final long serialVersionUID = 4299025159444158005L;
	
	private ElementDisplay cloudCommentDisplay;
	private Settings settings;
    
	public CloudCommentPanel(Settings settings, ElementDisplay cloudCommentDisplay) {
		this.settings = settings;
		this.cloudCommentDisplay = cloudCommentDisplay;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(this.settings.getCurrentSizing().cloudWidth, this.settings.getCurrentSizing().cloudHeight));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.cloudCommentDisplay.paintComponent(g, 0, 0);
	}
}
