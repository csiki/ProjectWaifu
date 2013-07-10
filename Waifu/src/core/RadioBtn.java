package core;

import java.util.ArrayList;
import java.util.List;
import display.RadioBtnDialog;

//  @ Project		: ProjectWaifu
//  @ File Name		: RadioBtn.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Popup an input dialog including (multiple) radio buttons and stores the input.
 * @author csiki
 *
 */
public class RadioBtn extends CounterAction {
	
	/**
	 * List of options (texts next to radio buttons) to be showed.
	 */
    private List<String> options;
    
    /**
     * Index of the selected option (radio button).
     */
    private int selectedIndex;
    
    /**
     * Dialog box for showing the radio buttons and an Ok and a Cancel button.
     */
    private RadioBtnDialog radioBtnDialog;
    
    /**
     * Constructor of RadioBtn.
     * @param radioBtnDialog
     */
    public RadioBtn(RadioBtnDialog radioBtnDialog) {
    	this.radioBtnDialog = radioBtnDialog;
    	this.options = new ArrayList<String>();
    	this.selectedIndex = -1;
    }
    
    /**
     * Adds an extra radio button.
     * Call only before triggered!
     * @param option text is shown next to the added radio button.
     */
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    /**
     * Gets the text of the selected radio button.
     * Call only after triggered!
     * @return text that is shown next to the selected radio button.
     */
    public String getSelectedText() {
    	if (this.selectedIndex != -1) {
    		return this.options.get(selectedIndex);
    	}
    	
    	return null;
    }
    
    /**
     * Gets the index of the selected radio button.
     * Index of the first added radio button is 0.
     * Call only after triggered!
     * @return index of the selected radio button (from 0 to [number of radio buttons added]-1).
     */
    public int getSelectedIndex() {
    	return this.selectedIndex;
    }
    
    /**
     * Shows window including the radio buttons for the user, waits till input is given.
     */
    public void trigger() {
    	this.radioBtnDialog.addOptions(options);
    	this.radioBtnDialog.init();
    	this.selectedIndex = this.radioBtnDialog.getResponse();
    }
}
