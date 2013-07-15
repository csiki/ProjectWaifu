package core;

import java.util.List;

//  @ Project		: ProjectWaifu
//  @ File Name		: AI.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class AI {
	
    //private List<Sensor> sensors; // later may be used
    private UserActionFactory userActionFactory;
    private CounterActionFactory counterActionFactory;
    private BehaviorContainer behContainer;
    private volatile boolean busy;
    
    public AI(
    		List<Sensor> sensors,
    		BehaviorContainer behContainer,
    		UserActionFactory userActionFactory,
    		CounterActionFactory counterActionFactory
    		) {
    	//this.sensors = sensors; // later may be used
    	this.behContainer = behContainer;
    	this.userActionFactory = userActionFactory;
    	this.counterActionFactory = counterActionFactory;
    	this.busy = false;
    }
    
    public void conditionFulfilled(Behavior beh) {
    	if (!this.busy && behContainer.isBehaviorActivated(beh)) {
    		this.busy = true;
    		beh.consequent(counterActionFactory);
    		this.busy = false;
    	}
    	else {
    		//beh.condition(userActionFactory);
    	}
    }
    
    public void newBehaviorsLoaded() {
    	
    	// call condition to all activated behaviors
    	for (int i = 0; i < this.behContainer.getNumOfBehaviors(); ++i) {
    		if (this.behContainer.isBehaviorActivated(i)) {
    			this.behContainer.getBehavior(i).condition(userActionFactory);
    		}
    	}
    }
}
