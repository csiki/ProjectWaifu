package core;

import display.CloudCommentDisplay;
import display.CloudCommentPanel;
import display.InputBoxDialog;

//  @ Project		: ProjectWaifu
//  @ File Name		: InputBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Popup an input dialog for text inputs and stores the input.
 * @author csiki
 *
 */
public class InputBox extends CounterAction {
	
	/**
	 * Input given by user.
	 */
    private String input;
    
    /**
     * Dialog box for showing input box and an Ok and a Cancel buttons.
     */
    private InputBoxDialog inputBoxDialog;
    
    /**
     * For displaying lower cloud.
     */
    private CloudCommentDisplay cloudCommentDisplay;
    
    /**
     * For displaying lower cloud.
     */
    private CloudCommentPanel cloudCommentPanel;
    
    /**
     * Constructor of InputBox.
     * @param inputBoxDialog
     */
    public InputBox(InputBoxDialog inputBoxDialog, CloudCommentDisplay cloudCommentDisplay, CloudCommentPanel cloudCommentPanel) {
    	this.inputBoxDialog = inputBoxDialog;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.cloudCommentPanel = cloudCommentPanel;
    	this.input = null;
    }
    
    /**
     * Gets the input the user has given.
     * Call only after triggered!
     * @return
     */
    public String getInput() {
    	return this.input;
    }
    
    /**
     * Shows the input box window for the user, waits till input is given.
     */
    public void trigger() {
    	// popup lower cloud
    	this.cloudCommentDisplay.popupComment("");
    	this.cloudCommentPanel.repaint();
    	
    	// popup input
    	this.inputBoxDialog.init();
    	this.input = this.inputBoxDialog.getInput();
    	
    	// hide lower cloud
    	this.cloudCommentDisplay.hideComment();
    	this.cloudCommentPanel.setOpaque(false);
    	this.cloudCommentPanel.repaint();
    }
}
