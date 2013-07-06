package core;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//  @ Project		: ProjectWaifu
//  @ File Name		: Settings.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class Settings implements Serializable {
	
	private static final long serialVersionUID = 2422256491766180618L;
	
	private boolean runOnStartUp;
    private boolean placeOnTop;
    //private Dimension resolution;
    private int posX;
    private int posY;
    private String behaviorsPath;
    private String currentSizingName;
    
    public static final Map<String, Sizing> sizes = new HashMap<String, Sizing>();
    
    // add further sizes here
    static {
    	sizes.put("300x400", new Sizing()); // default
    }
    
    // static vars
    // TODO kicserélni Sizingra!!!
    public final static int maxNumOfSkins = 100;
    public final static int waifuWidth = 300;
    public final static int waifuHeight = 400;
    public final static int cloudWidth = 300;
    public final static int cloudHeight = 250;
    public final static int menuElementWidth = 100;
    public final static int menuElementHeight = 60;
    public final static int menuElementFontSize = 24;
    
    public Settings() {
    	
    	// default settings
    	this.runOnStartUp = true;
    	this.placeOnTop = true;
    	this.behaviorsPath = "behaviors" + java.io.File.separator;
    	this.currentSizingName = "300x400";
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	this.posX = (int) screenSize.getWidth() - this.getCurrentSizing().frameWidth;
    	this.posY = (int) screenSize.getHeight() - this.getCurrentSizing().frameHeight;
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
    
    public void setBehaviorsPath(String set) {
    	this.behaviorsPath = set;
    }
    
    public Sizing getCurrentSizing() {
    	return sizes.get(this.currentSizingName);
    }
    
    public String getCurrentSizingName() {
    	return this.currentSizingName;
    }
    
    public void setCurrentSizing(String name) {
    	if (sizes.containsKey(name)) {
    		this.currentSizingName = name;
    	}
    }
}
