package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: TextHighlighted.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class TextHighlighted extends UserAction {
	
    private String highlightedString;
    private boolean textHighlighted;
    private HighlightTracker highlightTracker;
    
    public TextHighlighted(HighlightTracker highlightTracker) {
    	this.highlightedString = null;
    	this.highlightTracker = highlightTracker;
    	this.textHighlighted = false;
    }
    
    public String getState() {
    	return this.highlightedString;
    }
    
    public boolean isTextHighlighted() {
    	return this.textHighlighted;
    }
    
    public void update() {
    	this.deactivate();
    	highlightedString = this.highlightTracker.getHighlightedText();
    	this.textHighlighted = true;
    }
    
    public void activate(Behavior behavior) {
    	this.deactivate();
    	this.textHighlighted = false;
    	this.highlightedString = null;
    	this.highlightTracker.subscribe(this);
    }
    
    public void deactivate() {
    	this.highlightTracker.unsubscribe(this);
    }
}
