package core;

import display.SkinDisplay;
import display.SkinPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinSwitch.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinSwitch extends CounterAction {
	
    private int skin;
    private SkinDisplay skinDisplay;
    private SkinContainer skinContainer;
    private SkinPanel skinPanel;
    
    public SkinSwitch(int skinIndex, SkinDisplay skinDisplay, SkinContainer skinContainer, SkinPanel skinPanel) {
    	this.skinDisplay = skinDisplay;
    	this.skinContainer = skinContainer;
    	this.skinPanel = skinPanel;
    	
    	this.setSkin(skinIndex);
    }
    
    public void setSkin(int skinIndex) {
    	
    	if (skinContainer.getSkin(skinIndex) != null) {
    		this.skin = skinIndex;
    	}
    	else {
    		// default skin
    		this.skin = 0;
    	}
    }
    
    public void trigger() {
    	this.skinDisplay.loadSkin(skinContainer.loadSkin(skin));
    	this.skinPanel.repaint();
    }
}
