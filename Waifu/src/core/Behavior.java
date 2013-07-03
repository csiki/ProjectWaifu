package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: Behavior.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public abstract class Behavior {
	
    private String name;
    private AI ai;
    
    Behavior(String name) {
    	this.name = name;
    	this.ai = null;
    }
    
    public abstract void notify(UserAction userAction);
    public abstract void condition(UserActionFactory UAF);
    public abstract void consequent(CounterActionFactory CAF);
    
    final protected void conditionFulfilled() {
    	if (this.ai != null) {
    		this.ai.conditionFulfilled(this);
    	}
    }
    
    final public String getName() {
    	return this.name;
    }
    
    final public void provideAI(AI ai) {
    	this.ai = ai;
    }
}
