package core;

import display.InputBoxDialog;

//  @ Project		: ProjectWaifu
//  @ File Name		: InputBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class InputBox extends CounterAction {
	
    private String input;
    private InputBoxDialog inputBoxDialog;
    
    public InputBox(InputBoxDialog inputBoxDialog) {
    	this.inputBoxDialog = inputBoxDialog;
    	this.input = null;
    }
    
    public String getInput() {
    	return this.input;
    }
    
    public void trigger() {
    	this.inputBoxDialog.init();
    	this.input = this.inputBoxDialog.getInput();
    }
}
