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
    
    }
    
    public SoundChanged createSoundChanged(boolean soundStartExpected) {
    
    }
    
    public StringTyped createStringTyped(String stringExpected) {
    
    }
    
    public TextHighlighted createTextHighlighted() {
    
    }
    
    public TimeReached createTimeReached() {
    
    }
}
