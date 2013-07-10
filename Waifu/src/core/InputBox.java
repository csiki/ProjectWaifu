package core;

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
     * Constructor of InputBox.
     * @param inputBoxDialog
     */
    public InputBox(InputBoxDialog inputBoxDialog) {
    	this.inputBoxDialog = inputBoxDialog;
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
    	this.inputBoxDialog.init();
    	this.input = this.inputBoxDialog.getInput();
    }
}
