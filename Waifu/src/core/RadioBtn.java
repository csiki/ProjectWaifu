package core;

import java.util.ArrayList;
import java.util.List;
import display.RadioBtnDialog;

//  @ Project		: ProjectWaifu
//  @ File Name		: RadioBtn.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class RadioBtn extends CounterAction {
	
    private List<String> options;
    private int selectedIndex;
    private RadioBtnDialog radioBtnDialog;
    
    public RadioBtn(RadioBtnDialog radioBtnDialog) {
    	this.radioBtnDialog = radioBtnDialog;
    	this.options = new ArrayList<String>();
    	this.selectedIndex = -1;
    }
    
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    public String getSelectedText() {
    	if (this.selectedIndex != -1) {
    		return this.options.get(selectedIndex);
    	}
    	
    	return null;
    }
    
    public int getSelectedIndex() {
    	return this.selectedIndex;
    }
    
    public void trigger() {
    	this.radioBtnDialog.addOptions(options);
    	this.radioBtnDialog.init();
    	this.selectedIndex = this.radioBtnDialog.getResponse();
    }
}
