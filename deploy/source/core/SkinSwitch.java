package core;

import display.SkinDisplay;
import display.SkinPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinSwitch.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Change the skin of the waifu.
 * @author csiki
 *
 */
public class SkinSwitch extends CounterAction {
	
	/**
	 * The given skin to switch to.
	 */
    private int skin;
    
    /**
     * Instance of SkinDisplay.
     */
    private SkinDisplay skinDisplay;
    
    /**
     * Instance of SkinContainer.
     */
    private SkinContainer skinContainer;
    
    /**
     * Instance of SkinPanel.
     */
    private SkinPanel skinPanel;
    
    /**
     * Constructor of SkinSwitch.
     * @param skinIndex
     * @param skinDisplay
     * @param skinContainer
     * @param skinPanel
     */
    public SkinSwitch(int skinIndex, SkinDisplay skinDisplay, SkinContainer skinContainer, SkinPanel skinPanel) {
    	this.skinDisplay = skinDisplay;
    	this.skinContainer = skinContainer;
    	this.skinPanel = skinPanel;
    	
    	this.setSkin(skinIndex);
    }
    
    /**
     * Change the index of skin to switch to.
     * @param skinIndex index of skin to switch to.
     */
    public void setSkin(int skinIndex) {
    	
    	if (skinContainer.getSkin(skinIndex) != null) {
    		this.skin = skinIndex;
    	}
    	else {
    		// default skin
    		this.skin = 0;
    	}
    }
    
    /**
     * Change skin according to the given skin index.
     */
    public void trigger() {
    	this.skinDisplay.loadSkin(skinContainer.loadSkin(skin));
    	this.skinPanel.repaint();
    }
}
