package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinDisplay.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinDisplay implements ElementDisplay {
	
    private BufferedImage loadedSkin;
    private BufferedImage defaultSkin;
    private Settings settings;
    
    public SkinDisplay(Settings settings, BufferedImage defaultSkin) {
    	this.settings = settings;
    	this.defaultSkin = defaultSkin;
    }
    
    public void loadSkin(BufferedImage skinImg) {
    	loadedSkin = skinImg;
    }

	@Override
	public void paintComponent(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (loadedSkin != null) {
			// load loaded
			g2.drawImage(loadedSkin, x, y, x + settings.getCurrentSizing().waifuWidth, y + settings.getCurrentSizing().waifuHeight, 0, 0, loadedSkin.getWidth(), loadedSkin.getHeight(), null);
		}
		else {
			// load default
			g2.drawImage(defaultSkin, x, y, x + settings.getCurrentSizing().waifuWidth, y + settings.getCurrentSizing().waifuHeight, 0, 0, defaultSkin.getWidth(), defaultSkin.getHeight(), null);
		}
	}
}
