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
    		
    		// wait 5 minutes
    		for (int i = 0; i < 300; ++i) {
    			
    			if (!this.turnedOn) {
    				break;
    			}
    			
    			try {
    				Thread.sleep(1000);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}

	@Override
	public void turnOff() {
		this.turnedOn = false;
	}
}
