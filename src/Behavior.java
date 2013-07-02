//  @ Project		: ProjectWaifu
//  @ File Name		: Behavior.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public abstract class Behavior {
    private String name;
    private AI ai;
    public abstract void notify(UserAction userAction);
    public abstract void condition(UserActionFactory UAF);
    public abstract void consequent(CounterActionFactory CAF);
    public String getName() {
    
    }
}
