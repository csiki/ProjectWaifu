package core;

import java.util.ArrayList;
import java.util.List;

import display.CheckBoxPane;

//  @ Project		: ProjectWaifu
//  @ File Name		: CheckBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CheckBox extends CounterAction {
	
    private List<String> options;
    private List<Integer> chosenIndexes;
    private CheckBoxPane checkBoxPane;
    
    public CheckBox(CheckBoxPane checkBoxPane) {
    	this.checkBoxPane = checkBoxPane;
    	this.options = new ArrayList<String>();
    	this.chosenIndexes = new ArrayList<Integer>();
    }
    
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    public int getNumOfChosenIndexes() {
    	return this.chosenIndexes.size();
    }
    
    public int getChosenIndex(int index) {
    	
    	if (index >= 0 && index < this.chosenIndexes.size()) {
    		return this.chosenIndexes.get(index);
    	}
    	
    	return 0;
    }
    
    public String getChosenString(int index) {
    	if (index >= 0 && index < this.chosenIndexes.size()) {
    		return this.options.get(this.chosenIndexes.get(index));
    	}
    	
    	return "";
    }
    
    public void trigger() {
    	this.checkBoxPane.addOptions(options);
    	this.chosenIndexes = this.checkBoxPane.waitForResponse();
    }
}
