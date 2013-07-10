package core;


//  @ Project		: ProjectWaifu
//  @ File Name		: KeyTyped.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Listen for a certain key and if it's typed calls Behavior.update().
 * Can be created by behaviors using UserActionFactory.
 * Can listen to unicode of a key ('any' key on keyboard) or for a character (from 'a' to 'z').
 * @author csiki
 *
 */
public class KeyTyped extends UserAction {
	
	/**
	 * Unicode of the last typed key.
	 */
    private int key;
    
    /**
     * Unicode of the expected key.
     */
    private int keyExpected;
    
    /**
     * True if the expected key is already typed.
     */
    private volatile boolean keyTyped;
    
    /**
     * KeyReader instance to subscribe to it.
     */
    private KeyReader keyReader;
    
    /**
     * Constructor of KeyTyped (where char passed).
     * @param keyExpected expected character to be typed
     * @param keyReader to store KeyReader instance
     */
    public KeyTyped(char keyExpected, KeyReader keyReader) {
    	this.keyReader = keyReader;
    	this.keyTyped = false;
    	this.key = 0;
    	this.keyExpected = -1;
    	
    	// find key expected code
    	keyExpected = Character.toUpperCase(keyExpected);
    	for (int i = 65; i <= 90; ++i) {
    		if (Character.toChars(i)[0] == keyExpected) {
    			this.keyExpected = i;
    			break;
    		}
    	}
    }
    
    /**
     * Constructor of KeyTyped (where unicode passed).
     * @param keyExpected expected unicode of the key to be typed
     * @param keyReader to store KeyReader instance
     */
    public KeyTyped(int keyExpected, KeyReader keyReader) {
    	this.keyExpected = keyExpected;
    	this.keyReader = keyReader;
    	this.key = 0;
    	this.keyTyped = false;
    }
    
    /**
     * Gets the unicode of the last key typed, if KeyTyped is activated, and has not been deactivated.
     * If deactivated it returns the last key typed when activated.
     * @return unicode of the last key typed
     */
    public int keyTypedLast() {
    	return this.key;
    }
    
    /**
     * Gets if the expected key is typed.
     * @return true if the expected key has been already typed, false otherwise.
     */
    public boolean isKeyTyped() {
    	return this.keyTyped;
    }
    
    /**
     * KeyReader calls it if new key is hit.
     * Behavior should NOT call it!
     */
    public void update() {
    	this.key = this.keyReader.getKeyTyped();
    	
    	if (this.key == this.keyExpected) {
    		this.deactivate();
    		
    		if (this.behavior != null) {
    			this.behavior.notify(this);
    		}
    		this.keyTyped = true;
    	}
    }
    
    /**
     * Activates KeyTyped, so it listens for key hits.
     * Should be activated after created by behavior!
     */
    public void activate(Behavior behavior) {
    	if (this.keyExpected != -1) {
    		this.deactivate();
    		this.behavior = behavior;
    		this.keyReader.subscribe(this);
    	}
    }
    
    /**
     * Deactivates KeyTyped, so it won't listen for key hits.
     * Can be called by behavior but automatically called by itself if the expected key is hit (after activated ofc.).
     */
    public void deactivate() {
    	this.keyReader.unsubscribe(this);
    }
}
