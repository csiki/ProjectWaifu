package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudCommentDisplay.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudCommentDisplay implements ElementDisplay {
	
	private boolean popupped;
    private String comment;
    private BufferedImage cloudImg;
    
    public CloudCommentDisplay(BufferedImage cloudImg) {
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
			g2.drawImage(cloudImg, x, y, x + cloudImg.getWidth(), y + cloudImg.getHeight(), 0, 0, cloudImg.getWidth(), cloudImg.getHeight(), null);
			
			// draw text
			g2.drawString(comment, x + 10, y + 10);
		}
		else {
			// TODO transparent draw
		}
	}
}
