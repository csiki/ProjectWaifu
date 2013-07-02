package core;

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
    private RadioBtnPane radioBtnFrame;
    
    public void addOption(String option) {
    
    }
    
    public String getChosenText() {
    	return null;
    }
    
    public int getChosenIndex() {
    	return 0;
    }
    
    public void trigger() {
    
    }
}
