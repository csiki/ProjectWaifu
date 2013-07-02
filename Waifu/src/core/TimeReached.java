package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: TimeReached.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class TimeReached extends UserAction {
	
    private boolean timeReached;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private TimeChecker timeChecker;
    
    public boolean getState() {
    	return false;
    }
    
    public int getYear() {
    	return 0;
    }
    
    public int getMonth() {
    	return 0;
    }
    
    public int getDay() {
    	return 0;
    }
    
    public int getHour() {
    	return 0;
    }
    
    public int getMinute() {
    	return 0;
    }
    
    public void setYear(int year) {
    	
    }
    
    public void setMonth(int month) {
    
    }
    
    public void setDay(int day) {
    
    }
    
    public void setHour(int hour) {
    
    }
    
    public void setMinute(int minute) {
    
    }
    
    public void update() {
    
    }
    
    public void activate(Behavior behavior) {
    
    }
    
    public void deactivate() {
    
    }
}
