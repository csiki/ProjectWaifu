package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: MenuPanel.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -951173760082267556L;
	
	private BufferedImage settingsIcon;
    private BufferedImage exitIcon;
    
    public MenuPanel(BufferedImage settingsIcon, BufferedImage exitIcon) {
    	this.settingsIcon = settingsIcon;
    	this.exitIcon = exitIcon;
    }
    
    @Override
	public void paintComponent(Graphics g) {
		// TODO
	}
}
