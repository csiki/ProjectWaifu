package core;

import display.CloudCommentDisplay;
import display.CloudCommentPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudComment.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CloudComment extends CounterAction {
	
    private String comment;
    private CloudCommentDisplay cloudCommentDisplay;
    private CloudCommentPanel cloudCommentPanel;
    
    public CloudComment(String comment, CloudCommentDisplay cloudCommentDisplay, CloudCommentPanel cloudCommentPanel) {
    	this.comment = comment;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.cloudCommentPanel = cloudCommentPanel;
    }
    
    public void setComment(String comment) {
    	this.comment = comment;
    }
    
    public void trigger() {
    	this.cloudCommentDisplay.popupComment(comment);
    	this.cloudCommentPanel.repaint();
    }
    
    public void hide() {
    	this.cloudCommentDisplay.hideComment();
    	this.cloudCommentPanel.setOpaque(false);
    	this.cloudCommentPanel.repaint();
    }
}
