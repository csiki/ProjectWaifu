package core;


//  @ Project		: ProjectWaifu
//  @ File Name		: KeyTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyTyped extends UserAction {
	
    private int key;
    private int keyExpected;
    private boolean keyTyped;
    private KeyReader keyReader;
    
    public KeyTyped(char keyExpected, KeyReader keyReader) {
    	this.keyReader = keyReader;
    	this.keyTyped = false;
    	this.key = 0;
    	
    	// find key expected code
    	for (int i = 65; i <= 90; ++i) {
    		if (Character.toChars(i)[0] == keyExpected) {
    			this.keyExpected = i;
    			break;
    		}
    	}
    }
    
    public KeyTyped(int keyExpected, KeyReader keyReader) {
    	this.keyExpected = keyExpected;
    	this.keyReader = keyReader;
    	this.key = 0;
    	this.keyTyped = false;
    }
    
    public int getState() {
    	return this.key;
    }
    
    public boolean isKeyTyped() {
    	return this.keyTyped;
    }
    
    public void update() {
    	this.key = this.keyReader.getKeyTyped();
    	
    	if (this.key == this.keyExpected) {
    		this.deactivate();
    		this.behavior.notify(this);
    	}
    }
    
    public void activate(Behavior behavior) {
    	this.deactivate();
    	this.behavior = behavior;
    	this.keyReader.subscribe(this);
    }
    
    public void deactivate() {
    	this.keyReader.unsubscribe(this);
    }
}
