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
	
	private ElementDisplay skinDisplay;
    
	public SkinPanel(ElementDisplay skinDisplay) {
		this.skinDisplay = skinDisplay;
		this.setPreferredSize(new Dimension(Settings.waifuWidth, Settings.waifuHeight));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		skinDisplay.paintComponent(g, 0, 0);
	}
}
