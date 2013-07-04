package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinContainer.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinContainer implements Serializable {
	
	private static final long serialVersionUID = -1870357066579605091L;
	
	private List<String> skins;
	transient private Map<Integer, BufferedImage> loadedSkins;
    
    public SkinContainer() {
    	this.skins = new ArrayList<String>();
    	this.loadedSkins = new HashMap<Integer, BufferedImage>();
    	
    	// fill with null elements
    	for (int i = 0; i < Settings.maxNumOfSkins; ++i) {
    		this.skins.add(null);
    	}
    }
    
    public BufferedImage loadSkin(int index) {
    	
    	String path = this.skins.get(index);
    	if (index >= 0 && index < this.skins.size() && path != null) {
    		
    		if (this.loadedSkins.containsKey(index)) {
    			
    			return this.loadedSkins.get(index);

    		}
    		
    		File skinImgFile = new File(path);
    		
    		if (skinImgFile.canRead()) {
    			try {
    				BufferedImage bi = ImageIO.read(skinImgFile);
    				this.loadedSkins.put(index, bi);
					return bi;
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	return null;
    }
    
    public String getSkin(int index) {
    	return this.skins.get(index);
    }
    
    public void addSkin(int index, String imgPath) {
    	this.skins.set(index, imgPath);
    }
    
    public void rmSkin(int index) {
    	this.skins.remove(index);
    }
    
    public int getSize() {
    	return this.skins.size();
    }
}
