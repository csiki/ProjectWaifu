package display;

import java.awt.Graphics;

import javax.swing.JPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudCommentPanel.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudCommentPanel extends JPanel {
	
	private static final long serialVersionUID = 4299025159444158005L;
	
	private ElementDisplay cloudCommentDisplay;
    
	public CloudCommentPanel(ElementDisplay cloudCommentDisplay) {
		this.cloudCommentDisplay = cloudCommentDisplay;
		this.setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.cloudCommentDisplay.paintComponent(g, 0, 0);
	}
}
