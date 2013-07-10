package core;

//@ Project			: ProjectWaifu
//@ File Name		: Sizing.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved


public class Sizing {
	
	public int waifuWidth;
	public int waifuHeight;
	public int cloudWidth;
    public int cloudHeight;
    public int cloudFontSize;
    public int menuElementWidth;
    public int menuElementHeight;
    public int menuElementFontSize;
    public int menuPanelWidth;
    public int menuPanelHeight;
    public int frameWidth;
	public int frameHeight;
    
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
        
        this.frameWidth = this.waifuWidth + this.cloudWidth + 20;
        this.frameHeight = 2 * this.cloudHeight;
    }
    
    Sizing(int charWidth, int charHeight, int cloudWidth, int cloudHeight,
    		int menuElementWidth, int menuElementHeight, int menuElementFontSize)
    {
    	this.waifuWidth = charWidth;
    	this.waifuHeight = charHeight;
    	this.cloudWidth = cloudWidth;
    	this.cloudHeight = cloudHeight;
    	this.menuElementWidth = menuElementWidth;
    	this.menuElementHeight = menuElementHeight;
    	this.menuElementFontSize = menuElementFontSize;
    }
}
