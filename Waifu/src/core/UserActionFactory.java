package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: UserActionFactory.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class UserActionFactory {
	
    private KeyReader keyreader;
    private TimeChecker timeChecker;
    private HighlightTracker highlightTracker;
    //private SoundListener soundListener; // removed
    
    public KeyTyped createKeyTyped(char keyExpected) {
    	return new KeyTyped(keyExpected, this.keyreader);
    }
    
    /*public SoundChanged createSoundChanged(boolean soundStartExpected) {
    	return null;
    }*/
    
    public StringTyped createStringTyped(String stringExpected) {
    	return new StringTyped(stringExpected, this.keyreader);
    }
    
    public TextHighlighted createTextHighlighted() {
    	return new TextHighlighted(this.highlightTracker);
    }
    
    public TimeReached createTimeReached() {
    	return new TimeReached(timeChecker);
    }
}
