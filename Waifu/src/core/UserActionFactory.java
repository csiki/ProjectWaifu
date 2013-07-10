package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: UserActionFactory.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class UserActionFactory {
	
	/**
	 * KeyReader instance for creating KeyTyped and StringTyped instances.
	 */
    private KeyReader keyreader;
    
    /**
     * TimeChecker instance for creating TimeReached instances.
     */
    private TimeChecker timeChecker;
    
    /**
     * HighlightTracker instance for creating TextHighlighted instances.
     */
    private HighlightTracker highlightTracker;
    
    /**
     * Constructor storing the given KeyReader, TimeChecker and HighlightTracker instances.
     * @param keyreader KeyReader instance
     * @param timeChecker TimeChecker instance
     * @param highlightTracker HighlightTracker instance
     */
    public UserActionFactory(KeyReader keyreader, TimeChecker timeChecker, HighlightTracker highlightTracker) {
    	this.keyreader = keyreader;
    	this.timeChecker = timeChecker;
    	this.highlightTracker = highlightTracker;
    }
    
    /**
     * Creates an instance of KeyTyped (waiting for character from 'a' to 'z').
     * @param keyExpected the expected character KeyTyped will be waiting for after activated.
     * @return KeyTyped instance
     */
    public KeyTyped createKeyTyped(char keyExpected) {
    	return new KeyTyped(keyExpected, this.keyreader);
    }
    
    /**
     * Creates an instance of KeyTyped (waiting for 'any' key hit on keyboard).
     * @param keyExpectedUnicode unicode of the key to expected hit. Use KeyEvent static attributes for determining unicode of keys.
     * @return KeyTyped instance.
     */
    public KeyTyped createKeyTyped(int keyExpectedUnicode) {
    	return new KeyTyped(keyExpectedUnicode, this.keyreader);
    }
    
    /**
     * Creates an instance of StringTyped.
     * @param stringExpected the list of characters that will be waited for to be typed (in the given sequence).
     * @return StringTyped instance
     */
    public StringTyped createStringTyped(String stringExpected) {
    	return new StringTyped(stringExpected, this.keyreader);
    }
    
    /**
     * Creates an instance of TextHighlighted.
     * @return TextHighlighted instance.
     */
    public TextHighlighted createTextHighlighted() {
    	return new TextHighlighted(this.highlightTracker);
    }
    
    /**
     * Creates an instance of TimeReached (all its properties, like year, month, day etc. can be set after created).
     * @return TimeReached instance
     */
    public TimeReached createTimeReached() {
    	return new TimeReached(timeChecker);
    }
}
