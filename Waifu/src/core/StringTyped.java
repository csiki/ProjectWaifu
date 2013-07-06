package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: StringTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class StringTyped extends UserAction {
	
    private int state;
    private String stringExpected;
    private KeyReader keyReader;
    private boolean stringTyped;
    
    public StringTyped(String stringExpected, KeyReader keyReader) {
    	this.stringExpected = stringExpected.toLowerCase();
    	this.keyReader = keyReader;
    	this.state = 0;
    	this.stringTyped = false;
    }
    
    public boolean isStringTyped() {
    	return this.stringTyped;
    }
    
    public void update() {
    	char keyRead = Character.toLowerCase(Character.toChars(this.keyReader.getKeyTyped())[0]);

    	if (keyRead == this.stringExpected.charAt(state)) {
    		++this.state;
    		if (this.state == this.stringExpected.length()) {
    			this.deactivate();
    			this.behavior.notify(this);
    		}
    	}
    	else {
    		this.state = 0;
    	}
    }
    
    public void activate(Behavior behavior) {
    	this.deactivate();
    	this.behavior = behavior;
    	this.state = 0;
    	this.stringTyped = false;
    	this.keyReader.subscribe(this);
    }
    
    public void deactivate() {
    	this.keyReader.unsubscribe(this);
    }
}
