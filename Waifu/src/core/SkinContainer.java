package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinContainer.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SkinContainer implements Serializable {
	
	private static final long serialVersionUID = -1870357066579605091L;
	
	private List<String> skins;
    
    public SkinContainer() {
    	this.skins = new ArrayList<String>();
    }
    
    public BufferedImage loadSkin(int index) {
    	
    	if (index >= 0 && index < this.skins.size()) {
    		
    		String path = this.skins.get(index);
    		File skinImgFile = new File(path);
    		
    		if (skinImgFile.canRead()) {
    			try {
					return ImageIO.read(skinImgFile);
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
}
