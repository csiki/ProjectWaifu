package core;

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
    private CheckBoxPane checkBoxFrame;
    
    public void addOption(String option) {
    
    }
    
    public String getChosenText() {
    	return null;
    }
    
    public int getNumOfChosenIndexes() {
    	return 0;
    }
    
    public int getChosenIndex(int index) {
    	return 0;
    }
    
    public void trigger() {
    
    }
}
