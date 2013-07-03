package display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudCommentDisplay.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudCommentDisplay extends ElementDisplay {
	
	private boolean popupped;
    private String comment;
    
    public CloudCommentDisplay() {
    	this.popupped = false;
    	this.comment = null;
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
			
			// load image
			File cloudImgFile = new File("img" + java.io.File.separator + "chatbubble.png");
			BufferedImage cloudImg = null;
			try {
				cloudImg = ImageIO.read(cloudImgFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// draw image
			g2.drawImage(cloudImg, x, y, x + cloudImg.getWidth(), y + cloudImg.getHeight(), 0, 0, cloudImg.getWidth(), cloudImg.getHeight(), null);
		}
		else {
			// TODO transparent draw
		}
	}
}
