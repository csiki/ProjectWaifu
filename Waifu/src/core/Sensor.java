package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//  @ Project		: ProjectWaifu
//  @ File Name		: Sensor.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public abstract class Sensor {
	
	protected volatile boolean turnedOn;
    private String name;
    private List<UserAction> subs;
    
    public Sensor(String name) {
    	this.name = name;
    	this.subs = Collections.synchronizedList(new ArrayList<UserAction>());
    	this.turnedOn = true;
    }
    
    public abstract void turnOff();
    
    final protected void notifyAllSubs() {
    	
    	for (UserAction sub : subs) {
    		sub.update();
    	}
    }
    
    final public void subscribe(UserAction subber) {
    	this.subs.add(subber);
    }
    
    final public void unsubscribe(UserAction unsubber) {
    	this.subs.remove(unsubber);
    }
    
    final public String getName() {
    	return this.name;
    }
}
