package core;

public class Sizing {
	
	public int waifuWidth;
	public int waifuHeight;
	public int cloudWidth;
    public int cloudHeight;
    public int menuElementWidth;
    public int menuElementHeight;
    public int menuElementFontSize;
    public int menuPanelWidth;
    public int menuPanelHeight;
    
    Sizing() {
    	waifuWidth = 300;
    	waifuHeight = 400;
    	cloudWidth = 300;
        cloudHeight = 250;
        menuElementWidth = 100;
        menuElementHeight = 60;
        menuElementFontSize = 24;
        this.menuPanelWidth = 300;
        this.menuPanelHeight = 200;
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
