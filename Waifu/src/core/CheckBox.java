package core;

import java.util.ArrayList;
import java.util.List;
import display.CheckBoxDialog;

//  @ Project		: ProjectWaifu
//  @ File Name		: CheckBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CheckBox extends CounterAction {
	
    private List<String> options;
    private List<Integer> selectedIndexes;
    private CheckBoxDialog checkBoxDialog;
    
    public CheckBox(CheckBoxDialog checkBoxDialog) {
    	this.checkBoxDialog = checkBoxDialog;
    	this.options = new ArrayList<String>();
    	this.selectedIndexes = new ArrayList<Integer>();
    }
    
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    public int getNumOfSelectedIndexes() {
    	return this.selectedIndexes.size();
    }
    
    public int getSelectedIndex(int index) {
    	
    	if (index >= 0 && index < this.selectedIndexes.size()) {
    		return this.selectedIndexes.get(index);
    	}
    	
    	return -1;
    }
    
    public String getSelectedText(int index) {
    	if (index >= 0 && index < this.selectedIndexes.size()) {
    		return this.options.get(this.selectedIndexes.get(index));
    	}
    	
    	return null;
    }
    
    public void trigger() {
    	this.checkBoxDialog.addOptions(options);
    	this.checkBoxDialog.init();
    	this.selectedIndexes = this.checkBoxDialog.getResponse();
    }
}
