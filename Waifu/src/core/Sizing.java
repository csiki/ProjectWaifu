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
    public int frameWidth;
	public int frameHeight;
    
    Sizing() {
    	this.waifuWidth = 300;
    	this.waifuHeight = 400;
    	this.cloudWidth = 300;
    	this.cloudHeight = 250;
        this.menuElementWidth = 100;
        this.menuElementHeight = 60;
        this.menuElementFontSize = 24;
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
