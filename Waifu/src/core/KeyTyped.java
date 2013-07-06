package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: KeyTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyTyped extends UserAction {
	
    private char key;
    private char keyExpected;
    private boolean keyTyped;
    private KeyReader keyReader;
    
    public KeyTyped(char keyExpected, KeyReader keyReader) {
    	this.keyReader = keyReader;
    	this.keyTyped = false;
    	this.keyExpected = Character.toUpperCase(keyExpected);
    	this.key = 0;
    }
    
    public char getState() {
    	return this.key;
    }
    
    public boolean isKeyTyped() {
    	return this.keyTyped;
    }
    
    public void update() {
    	this.key = Character.toChars(this.keyReader.getKeyTyped())[0];
    	
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
