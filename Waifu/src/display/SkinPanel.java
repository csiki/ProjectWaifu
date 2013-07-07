package display;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinPanel.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinPanel extends JPanel {
	
	private static final long serialVersionUID = 1636142671454025185L;
	
	private Settings settings;
	private ElementDisplay skinDisplay;
    
	public SkinPanel(Settings settings, ElementDisplay skinDisplay) {
		this.settings = settings;
		this.skinDisplay = skinDisplay;
		
		this.setPreferredSize(new Dimension(this.settings.getCurrentSizing().waifuWidth,
				this.settings.getCurrentSizing().waifuHeight));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.setOpaque(false);
		skinDisplay.paintComponent(g, 0, 0);
	}
}
