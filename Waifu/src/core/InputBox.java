package core;

import display.InputBoxPane;

//  @ Project		: ProjectWaifu
//  @ File Name		: InputBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class InputBox extends CounterAction {
	
    private String input;
    private InputBoxPane inputBoxPane;
    
    public InputBox(InputBoxPane inputBoxPane) {
    	this.inputBoxPane = inputBoxPane;
    }
    
    public String getInput() {
    	return this.input;
    }
    
    public void trigger() {
    	this.input = this.inputBoxPane.waitForResponse();
    }
}
