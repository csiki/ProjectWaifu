package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: UserActionFactory.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class UserActionFactory {
	
    private KeyReader keylogger;
    private TimeChecker timeChecker;
    private HighlightTracker highlightTracker;
    private SoundListener soundListener;
    
    public KeyTyped createKeyTyped(char keyExpected) {
    	return null;
    }
    
    public SoundChanged createSoundChanged(boolean soundStartExpected) {
    	return null;
    }
    
    public StringTyped createStringTyped(String stringExpected) {
    	return null;
    }
    
    public TextHighlighted createTextHighlighted() {
    	return null;
    }
    
    public TimeReached createTimeReached() {
    	return null;
    }
}
