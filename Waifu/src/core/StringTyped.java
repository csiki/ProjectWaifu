package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: StringTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Listen for a certain sequence of characters (like multiple KeyTypeds after each other).
 * Can be created by behaviors using UserActionFactory.
 * @author csiki
 *
 */
public class StringTyped extends UserAction {
	
	/**
	 * Number of keys typed from the expected sequence.
	 */
    private int state;
    
    /**
     * The expected sequence of characters to be typed.
     */
    private String stringExpected;
    
    /**
     * Instance of KeyReader.
     */
    private KeyReader keyReader;
    
    /**
     * True if the sequence is typed, false otherwise.
     */
    private volatile boolean stringTyped;
    
    /**
     * StringTyped constructor.
     * @param stringExpected sequence of character expected to be typed.
     * @param keyReader KeyReader instance.
     */
    public StringTyped(String stringExpected, KeyReader keyReader) {
    	this.stringExpected = stringExpected.toLowerCase();
    	this.keyReader = keyReader;
    	this.state = 0;
    	this.stringTyped = false;
    }
    
    /**
     * Gets if the sequence of characters (string) is already typed.
     * @return true if string is typed (after StringTyped had activated), false otherwise.
     */
    public boolean isStringTyped() {
    	return this.stringTyped;
    }
    
    /**
     * KeyReader calls it if new key is hit.
     * Behavior should NOT call it!
     */
    public void update() {
    	char keyRead = Character.toLowerCase(Character.toChars(this.keyReader.getKeyTyped())[0]);

    	if (keyRead == this.stringExpected.charAt(state)) {
    		++this.state;
    		if (this.state == this.stringExpected.length()) {
    			this.deactivate();
    			
    			if (this.behavior != null) {
    				this.behavior.notify(this);
    			}
    			this.stringTyped = true;
    		}
    	}
    	else {
    		this.state = 0;
    	}
    }
    
    /**
     * Activates StringTyped, so it listens for key hits.
     * Should be activated after created by behavior!
     */
    public void activate(Behavior behavior) {
    	this.deactivate();
    	this.behavior = behavior;
    	this.state = 0;
    	this.stringTyped = false;
    	this.keyReader.subscribe(this);
    }
    
    /**
     * Deactivates StringTyped, so it won't listen for key hits.
     * Can be called by behavior but automatically called by itself if the expected key is hit (after had been activated ofc.).
     */
    public void deactivate() {
    	this.keyReader.unsubscribe(this);
    }
}
