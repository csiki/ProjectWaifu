package core;

import java.util.ArrayList;
import java.util.List;

//  @ Project		: ProjectWaifu
//  @ File Name		: BehaviorContainer.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class BehaviorContainer {
	
	private volatile List<Boolean> behaviorActivated;
    private volatile List<Behavior> behaviors;
    
    public BehaviorContainer() {
    	this.behaviorActivated = new ArrayList<Boolean>();
    	this.behaviors = new ArrayList<Behavior>();
    }
    
    public Behavior getBehavior(int index) {
    	return this.behaviors.get(index);
    }
    
    public int getNumOfBehaviors() {
    	return this.behaviors.size();
    }
    
    public void addBehavior(Behavior beh) {
    	this.behaviors.add(beh);
    	this.behaviorActivated.add(true);
    }
    
    public void rmBehavior(int index) {
    	this.behaviors.remove(index);
    	this.behaviorActivated.remove(index);
    }
    
    public void clearBehaviors() {
    	this.behaviors.clear();
    }
    
    public void activateBehavior(int index) {
    	this.behaviorActivated.set(index, true);
    }
    
    public void deactivateBehavior(int index) {
    	this.behaviorActivated.set(index, false);
    }
    
    public boolean isBehaviorActivated(int index) {
    	return this.behaviorActivated.get(index);
    }
    
    public boolean isBehaviorActivated(Behavior beh) {
    	int index = this.behaviors.indexOf(beh);
    	return this.behaviorActivated.get(index);
    }
}
