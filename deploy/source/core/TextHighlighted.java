package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: TextHighlighted.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Listen for any text highlighted and stores it immediately.
 * Can be created by behaviors using UserActionFactory.
 * @author csiki
 *
 */
public class TextHighlighted extends UserAction {
	
	/**
	 * The highlighted text.
	 */
    private String highlightedString;
    
    /**
     * True if text has been highlighted when activated, false otherwise.
     */
    private volatile boolean textHighlighted;
    
    /**
     * HighlightTracker instance.
     */
    private HighlightTracker highlightTracker;
    
    /**
     * Constructor of TextHighlighted.
     * @param highlightTracker
     */
    public TextHighlighted(HighlightTracker highlightTracker) {
    	this.highlightedString = null;
    	this.highlightTracker = highlightTracker;
    	this.textHighlighted = false;
    }
    
    /**
     * Gets the highlighted text (if any has been highlighted since activated).
     * @return highlighted text or null if nothing has been highlighted since activated.
     */
    public String getHighlightedText() {
    	return this.highlightedString;
    }
    
    /**
     * Gets if a text is highlighted since activated.
     * @return true if text is highlighted since activated, false otherwise.
     */
    public boolean isTextHighlighted() {
    	return this.textHighlighted;
    }
    
    /**
     * HighlightTracker calls it if new key is hit.
     * Behavior should NOT call it!
     */
    public void update() {
    	this.deactivate();
    	this.highlightedString = this.highlightTracker.getHighlightedText();
    	this.textHighlighted = true;
    	
    	if (this.behavior != null) {
    		this.behavior.actionPerformed(this);
    	}
    }
    
    /**
     * Activates TextHighlighted, so it listens for text highlighted.
     * Should be activated after created by behavior!
     */
    public void activate(Behavior behavior) {
    	this.deactivate();
    	this.textHighlighted = false;
    	this.highlightedString = null;
    	this.behavior = behavior;
    	this.highlightTracker.subscribe(this);
    }
    
    /**
     * Deactivates TextHighlighted, so it won't listen for text highlighted.
     * Can be called by behavior but automatically called by itself if the expected key is hit (after had been activated ofc.).
     */
    public void deactivate() {
    	this.highlightTracker.unsubscribe(this);
    }
}
