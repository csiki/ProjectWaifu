package core;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;

//  @ Project		: ProjectWaifu
//  @ File Name		: Settings.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class Settings implements Serializable {
	
	private static final long serialVersionUID = 2422256491766180618L;
	
	private boolean runOnStartUp;
    private boolean placeOnTop;
    private Dimension resolution;
    private int posX;
    private int posY;
    private String behaviorsPath;
    
    // static vars
    public final static int cloudWidth = 300;
    public final static int maxNumOfSkins = 100;
    public final static int menuElementWidth = 100;
    public final static int menuElementHeight = 60;
    public final static int menuElementFontSize = 24;
    
    public Settings() {
    	// default settings
    	this.runOnStartUp = true;
    	this.placeOnTop = true;
    	this.resolution = new Dimension(300, 400);
    	this.behaviorsPath = "behaviors" + java.io.File.separator;
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	this.posX = (int) screenSize.getWidth() - 350 - Settings.cloudWidth;
    	this.posY = (int) screenSize.getHeight() - 450;
    }
    
    public boolean isRunOnStartUp() {
    	return this.runOnStartUp;
    }
    
    public boolean isPlaceOnTop() {
    	return this.placeOnTop;
    }
    
    public void setRunOnStartUp(boolean runOnStartUp) {
    	this.runOnStartUp = runOnStartUp;
    }
    
    public void setPlaceOnTop(boolean placeOnTop) {
    	this.placeOnTop = placeOnTop;
    }
    
    public Dimension getResolution() {
    	return this.resolution;
    }
    
    public int getPosX() {
    	return this.posX;
    }
    
    public int getPosY() {
    	return this.posY;
    }
    
    public String getBehaviorsPath() {
    	return this.behaviorsPath;
    }
    
    public void setPosX(int set) {
    	this.posX = set;
    }
    
    public void setPosY(int set) {
    	this.posY = set;
    }
    
    public void setResolution(int width, int height) {
    	this.resolution = new Dimension(width, height);
    }
    
    public void setBehaviorsPath(String set) {
    	this.behaviorsPath = set;
    }
}
