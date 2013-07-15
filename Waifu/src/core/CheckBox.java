package core;

import java.util.ArrayList;
import java.util.List;

import display.CheckBoxDialog;
import display.CloudCommentDisplay;
import display.CloudCommentPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: CheckBox.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Popup an input dialog including (multiple) check boxes and stores the input.
 * @author csiki
 *
 */
public class CheckBox extends CounterAction {
	
	/**
	 * List of options (texts next to check boxes) to be shown.
	 */
    private List<String> options;
    
    /**
     * List of selected indexes of the options.
     */
    private List<Integer> selectedIndexes;
    
    /**
     * Dialog box for showing the check boxes and an Ok and a Cancel button.
     */
    private CheckBoxDialog checkBoxDialog;
    
    /**
     * For displaying lower cloud.
     */
    private CloudCommentDisplay cloudCommentDisplay;
    
    /**
     * For displaying lower cloud.
     */
    private CloudCommentPanel cloudCommentPanel;
    
    /**
     * Constructor of CheckBox.
     * @param checkBoxDialog
     */
    public CheckBox(CheckBoxDialog checkBoxDialog, CloudCommentDisplay cloudCommentDisplay, CloudCommentPanel cloudCommentPanel) {
    	this.checkBoxDialog = checkBoxDialog;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.cloudCommentPanel = cloudCommentPanel;
    	this.options = new ArrayList<String>();
    	this.selectedIndexes = new ArrayList<Integer>();
    }
    
    /**
     * Adds an extra check box.
     * Call only before triggered!
     * @param option text is showed next to the check box.
     */
    public void addOption(String option) {
    	if (option != null) {
    		this.options.add(option);
    	}
    }
    
    /**
     * Gets the number of selected check boxes.
     * Call only after triggered!
     * @return number of selected check boxes.
     */
    public int getNumOfSelectedIndexes() {
    	return this.selectedIndexes.size();
    }
    
    /**
     * Gets a selected index of options.
     * Index of the first added check box is 0.
     * Call only after triggered!
     * Because multiple check boxes can be chosen, there are multiple selected indexes in a list. This method helps getting the items of that list.
     * @param index the index of the selected indexes (from 0 to getNumOfSelectedIndexes()).
     * @return index of a selected option (from 0 to the number of added options).
     */
    public int getSelectedIndex(int index) {
    	
    	if (index >= 0 && index < this.selectedIndexes.size()) {
    		return this.selectedIndexes.get(index);
    	}
    	
    	return -1;
    }
    
    /**
     * Gets a selected options text.
     * Call only after triggered!
     * Same as int getSelectedIndex(int index), but it returns the exact option string, while the other gets the index of the added options (which is/are selected).
     * @param index the index of the selected indexes (from 0 to getNumOfSelectedIndexes()).
     * @return text of a selected option.
     */
    public String getSelectedText(int index) {
    	if (index >= 0 && index < this.selectedIndexes.size()) {
    		return this.options.get(this.selectedIndexes.get(index));
    	}
    	
    	return null;
    }
    
    /**
     * Gets if an index is selected.
     * @param index of the option
     * @return true if the given index was selected, false otherwise
     */
    public boolean isIndexSelected(int index) {
    	if (index >= 0 && index < this.options.size()) {
    		for (int i = 0; i < this.selectedIndexes.size(); ++i) {
    			if (index == this.selectedIndexes.get(i)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    /**
     * Gets if the given option is selected.
     * @param option string
     * @return true if the given option is selected, false otherwise
     */
    public boolean isOptionSelected(String option) {
    	for (int i = 0; i < this.selectedIndexes.size(); ++i) {
    		if (option.equals(this.options.get(this.selectedIndexes.get(i)))) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * Shows window including the check boxes for the user, waits till input is given.
     */
    public void trigger() {
    	// popup lower cloud
    	this.cloudCommentDisplay.popupComment("");
    	this.cloudCommentPanel.repaint();
    	
    	// popup input
    	this.checkBoxDialog.addOptions(options);
    	this.checkBoxDialog.init();
    	this.selectedIndexes = this.checkBoxDialog.getResponse();
    	
    	// hide lower cloud
    	this.cloudCommentDisplay.hideComment();
    	this.cloudCommentPanel.setOpaque(false);
    	this.cloudCommentPanel.repaint();
    }
}
