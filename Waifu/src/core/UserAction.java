package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: UserAction.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public abstract class UserAction {
	
    protected Behavior behavior;
    
    public UserAction() {
    	this.behavior = null;
    }
    
    public abstract void update();
    public abstract void activate(Behavior behavior);
    public abstract void deactivate();
    
}
