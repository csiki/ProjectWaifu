package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.SkinContainer;
import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinDisplay.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinDisplay extends ElementDisplay {
	
    private BufferedImage loadedSkin;
    //private Dimension resolution; // get it from Settings
    private SkinContainer skinContainer;
    private Settings settings;
    
    public void loadSkin(BufferedImage skinImg) {
    	loadedSkin = skinImg;
    }

	@Override
	public void paintComponent(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
