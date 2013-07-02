package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: KeyTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyTyped extends UserAction {
	
    private int key;
    private char keyExpected;
    private boolean keyTyped;
    private KeyReader keyReader;
    
    public int getState() {
    	return 0;
    }
    
    public boolean isKeyTyped() {
    	return false;
    }
    
    public void update() {
    
    }
    
    public void activate(Behavior behavior) {
    
    }
    
    public void deactivate() {
    
    }
}
