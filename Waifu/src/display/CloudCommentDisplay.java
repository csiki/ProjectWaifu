package display;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Settings;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudCommentDisplay.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudCommentDisplay implements ElementDisplay {
	
	private boolean popupped;
    private String comment;
    private BufferedImage cloudImg;
    private Settings settings;
    
    public CloudCommentDisplay(Settings settings, BufferedImage cloudImg) {
    	this.settings = settings;
    	this.popupped = false;
    	this.comment = null;
    	this.cloudImg = cloudImg;
    }
    
    public void popupComment(String comment) {
    	this.comment = comment;
    	this.popupped = true;
    }
    
    public void hideComment() {
    	this.popupped = false;
    }

	@Override
	public void paintComponent(Graphics g, int x, int y) {
		
		Graphics2D g2 = (Graphics2D) g;
		if (this.popupped) {
			// draw image
			g2.drawImage(cloudImg, x, y, x + this.settings.getCurrentSizing().cloudWidth, y + this.settings.getCurrentSizing().cloudHeight, 0, 0, cloudImg.getWidth(), cloudImg.getHeight(), null);
			
			// draw string
			g2.setFont(new Font("Arial", Font.BOLD, 20));
			g2.drawString(comment, x + 90, y + 130);
		}
		else {
			// TODO transparent draw
		}
	}
}
