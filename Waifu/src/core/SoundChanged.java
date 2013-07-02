package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: SoundChanged.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class SoundChanged extends UserAction {
	
    private boolean soundStartExpected;
    private boolean soundOn;
    private SoundListener soundListener;
    
    public boolean getState() {
    	return false;
    }
    
    public boolean isSoundOn() {
    	return false;
    }
    
    public void update() {
    
    }
    
    public void activate(Behavior behavior) {
    
    }
    
    public void deactivate() {
    
    }
}
