package core;

import java.util.ArrayList;
import java.util.List;

import display.RadioBtnPane;

//  @ Project		: ProjectWaifu
//  @ File Name		: RadioBtn.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class RadioBtn extends CounterAction {
	
    private List<String> options;
    private int chosenIndex;
    private RadioBtnPane radioBtnPane;
    
    RadioBtn(RadioBtnPane radioBtnPane) {
    	this.radioBtnPane = radioBtnPane;
    	this.options = new ArrayList<String>();
    	this.chosenIndex = -1;
    }
    
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    public String getChosenText() {
    	if (this.chosenIndex != -1) {
    		return this.options.get(chosenIndex);
    	}
    	
    	return null;
    }
    
    public int getChosenIndex() {
    	return this.chosenIndex;
    }
    
    public void trigger() {
    	this.radioBtnPane.addOptions(options);
    	this.chosenIndex = this.radioBtnPane.waitForResponse();
    }
}
