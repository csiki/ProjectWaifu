package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: TimeChecker.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class TimeChecker extends Sensor implements Runnable {
	
    public TimeChecker(String name) {
		super(name);
	}
    
    @Override
	public void run() {
		
    	while (this.turnedOn) {
    		
    		// notify all subs every 5 minutes
    		this.notifyAllSubs();
    		
    		try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
	}

	public void on() {
		this.turnedOn = true;
    }
    
    public void off() {
    	this.turnedOn = false;
    }
}
