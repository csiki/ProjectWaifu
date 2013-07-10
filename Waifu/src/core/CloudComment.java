package core;

import display.CloudCommentDisplay;
import display.CloudCommentPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: CloudComment.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Popup a cloud with text in it, like the waifu is speaking.
 * @author csiki
 *
 */
public class CloudComment extends CounterAction {
	
	/**
	 * The text in the cloud to show.
	 */
    private String comment;
    
    /**
     * Instance of CloudCommentDisplay.
     */
    private CloudCommentDisplay cloudCommentDisplay;
    
    /**
     * Instance of CloudCommentPanel.
     */
    private CloudCommentPanel cloudCommentPanel;
    
    /**
     * Constructor of CloudComment.
     * @param comment
     * @param cloudCommentDisplay
     * @param cloudCommentPanel
     */
    public CloudComment(String comment, CloudCommentDisplay cloudCommentDisplay, CloudCommentPanel cloudCommentPanel) {
    	this.comment = comment;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.cloudCommentPanel = cloudCommentPanel;
    }
    
    /**
     * Changes the text in the cloud (to be shown after trigger).
     * The text only changes after a new call to trigger.
     * @param comment
     */
    public void setComment(String comment) {
    	this.comment = comment;
    }
    
    /**
     * Shows the cloud immediately with the comment in it.
     */
    public void trigger() {
    	this.cloudCommentDisplay.popupComment(comment);
    	this.cloudCommentPanel.repaint();
    }
    
    /**
     * Hides the cloud immediately.
     */
    public void hide() {
    	this.cloudCommentDisplay.hideComment();
    	this.cloudCommentPanel.setOpaque(false);
    	this.cloudCommentPanel.repaint();
    }
}
