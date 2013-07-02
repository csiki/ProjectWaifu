package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: Sensor.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public abstract class Sensor {
	
    private String name;
    private UserAction subs;
    
    public abstract void on();
    
    public abstract void off();
    
    protected void notifyAllSubbs() {
    	
    }
    
    public void subscribe(UserAction subber) {
    
    }
    
    public void unsubscribe(UserAction unsubber) {
    
    }
    
    public String getName() {
    	return null;
    }
}
