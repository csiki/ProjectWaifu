package core;

//@ Project			: ProjectWaifu
//@ File Name		: Sizing.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved


public class Sizing {
	
	final public int waifuWidth;
	final public int waifuHeight;
	final public int cloudWidth;
	final public int cloudHeight;
	final public int cloudFontSize;
	final public int menuElementWidth;
	final public int menuElementHeight;
	final public int menuElementFontSize;
	final public int menuPanelWidth;
	final public int menuPanelHeight;
	final public int frameWidth;
	final public int frameHeight;
	final public int offsetInputDialogBoxX;
	final public int offsetInputDialogBoxY;
    
    Sizing() {
    	this.waifuWidth = 300;
    	this.waifuHeight = 400;
    	this.cloudWidth = 300;
    	this.cloudHeight = 250;
    	this.cloudFontSize = 18;
        this.menuElementWidth = 130;
        this.menuElementHeight = 38;
        this.menuElementFontSize = 20;
        this.menuPanelWidth = 300;
        this.menuPanelHeight = 200;
        this.offsetInputDialogBoxX = 370;
        this.offsetInputDialogBoxY = 590;
        
        this.frameWidth = this.waifuWidth + this.cloudWidth + 20;
        this.frameHeight = 3 * this.cloudHeight;
    }
    
    Sizing(int charWidth, int charHeight, int cloudWidth, int cloudHeight, int cloudFontSize,
    		int menuElementWidth, int menuElementHeight, int menuElementFontSize,
    		int menuPanelWidth, int menuPanelHeight,
    		int frameWidth, int frameHeight,
    		int offsetInputDialogBoxX, int offsetInputDialogBoxY)
    {
    	this.waifuWidth = charWidth;
    	this.waifuHeight = charHeight;
    	this.cloudWidth = cloudWidth;
    	this.cloudHeight = cloudHeight;
    	this.cloudFontSize = cloudFontSize;
    	this.menuElementWidth = menuElementWidth;
    	this.menuElementHeight = menuElementHeight;
    	this.menuElementFontSize = menuElementFontSize;
    	this.menuPanelWidth = menuPanelWidth;
    	this.menuPanelHeight = menuPanelHeight;
    	this.frameWidth = frameWidth;
    	this.frameHeight = frameHeight;
    	this.offsetInputDialogBoxX = offsetInputDialogBoxX;
    	this.offsetInputDialogBoxY = offsetInputDialogBoxY;
    }
}
