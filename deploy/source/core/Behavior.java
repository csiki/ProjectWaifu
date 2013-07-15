package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: Behavior.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Base class for concrete behaviors.
 * @author csiki
 *
 */
public abstract class Behavior {
	
	/**
	 * Name of the behavior.
	 * Later will have meaning by having the possibility to turn on/off exact behaviors.
	 */
    private String name;
    
    /**
     * The one who let behaviors prevail.
     */
    private AI ai;
    
    /**
     * Constructor of a behavior.
     * Sets behaviors name.
     * That's why when Behavior is extended by a subclass, in the subclass constructor super("behavior_name") must be called.
     * @param name name of the behavior
     */
    public Behavior(String name) {
    	this.name = name;
    	this.ai = null;
    }
    
    /**
     * Contains the "condition" for the "consequent" to run.
     * The place where user actions should be created and activated.
     * @param UAF UserActionFactory instance is passed, so user actions can be created here.
     */
    public abstract void condition(UserActionFactory UAF);
    
    /**
     * This method is called, when a user action is created, activated with the actual behavior passed and the user action gone off.
     * @param userAction the user action that has gone off, and now notifies the actual behavior.
     */
    public abstract void actionPerformed(UserAction userAction);
    
    /**
     * The "consequent" sequence of program, that would run if the "condition" part is fulfilled, and the AI let it to do so.
     * @param CAF CounterActionFactory instance is passed, so counter actions can be created here.
     */
    public abstract void consequent(CounterActionFactory CAF);
    
    /**
     * This method should be called if (all) condition is fulfilled, and the behavior would like to signal that the consequent should run.
     */
    final protected void conditionFulfilled() {
    	if (this.ai != null) {
    		this.ai.conditionFulfilled(this);
    	}
    }
    
    /**
     * Gets the name of the behavior.
     * @return name of the behavior.
     */
    final public String getName() {
    	return this.name;
    }
    
    /**
     * Provide an instance of the AI to the behavior, to be able to call it.
     * Should NOT be called by the behavior itself.
     * @param ai
     */
    final public void provideAI(AI ai) {
    	this.ai = ai;
    }
}
