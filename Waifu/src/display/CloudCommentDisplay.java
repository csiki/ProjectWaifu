package display;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
			
			// set string font
			g2.setFont(new Font("Arial", Font.BOLD, this.settings.getCurrentSizing().cloudFontSize));
			
			// calculate length of comment in px
			FontMetrics fm = g2.getFontMetrics();
			int textLength = fm.stringWidth(comment);
			
			// calculate space for text (80% in length and height of cloud)
			int cW = (int) (this.settings.getCurrentSizing().cloudWidth * 0.8);
			int cH = (int) (this.settings.getCurrentSizing().cloudHeight * 0.8);
			
			// calculate number of rows (minimum)
			int numOfRowsAtLeast = (int) Math.ceil((double) textLength / (double) cW);
			
			// string wrapping
			int divider = textLength / numOfRowsAtLeast; // number of characters in every rows (but the last) at least
			
			int prevSpacePos = 0;
			
			// split comment by spaces
			String[] words = comment.split(" ");
			String tmp = words[0];
			List<String> rows = new ArrayList<String>();
			for (int w = 1; w < words.length; ++w) {
				if (fm.stringWidth(tmp + words[w]) > cW) {
					// then it's too long so tmp makes a row already
					rows.add(tmp);
					tmp = words[w]; // reset
				}
				else {
					tmp += " " + words[w]; // append
				}
			}
			rows.add(tmp); // last row
			
			// center vertically
			int textHeight = rows.size() * fm.getHeight();
			int yPos = (int) (y + (cH - textHeight) / 2 + cH * 0.2);
			
			// draw string at last
			int xPos;
			for (String row : rows) {
				xPos = (int) ((cW - fm.stringWidth(row)) / 2 + cW * 0.1);
				
				g2.drawString(row, xPos, yPos);
				
				yPos += fm.getHeight();
			}
		}
	}
}
