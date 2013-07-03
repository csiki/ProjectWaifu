package core;

import java.util.Calendar;
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
    
    public TimeReached(TimeChecker timeChecker) {
    	this.timeChecker = timeChecker;
    	this.year = -1;
    	this.month = -1;
    	this.day = -1;
    	this.hour = -1;
    	this.minute = -1;
    	this.timeReached = false;
    }
    
    public boolean getState() {
    	return this.timeReached;
    }
    
    public int getYear() {
    	return this.year;
    }
    
    public int getMonth() {
    	return this.month;
    }
    
    public int getDay() {
    	return this.day;
    }
    
    public int getHour() {
    	return this.hour;
    }
    
    public int getMinute() {
    	return this.minute;
    }
    
    public void setYear(int year) {
    	if (year > 0) {
    		this.year = year;
    	}
    }
    
    public void setMonth(int month) {
    	if (month > 0 && month <= 12) {
    		this.month = month;
    	}
    }
    
    public void setDay(int day) {
    	if (day > 0 && day <= 31) {
    		this.day = day;
    	}
    }
    
    public void setHour(int hour) {
    	if (hour >= 0 && hour <= 23) {
    		this.hour = hour;
    	}
    }
    
    public void setMinute(int minute) {
    	if (minute >= 0 && minute < 60) {
    		this.minute = minute;
    	}
    }
    
    public void update() {
    	Calendar cal = Calendar.getInstance();
    	if ( 
    			(year >= cal.get(Calendar.YEAR) || year == -1)
    			&& (month >= cal.get(Calendar.MONTH) || month == -1)
    			&& (day >= cal.get(Calendar.DAY_OF_MONTH) || day == -1)
    			&& (hour >= cal.get(Calendar.HOUR_OF_DAY) || hour == -1)
    			&& (minute >= cal.get(Calendar.MINUTE) || minute == -1))
    	{
    		// time reached
    		this.deactivate();
    		this.timeReached = true;
    		this.behavior.notify(this);
    	}
    }
    
    public void activate(Behavior behavior) {
    	// don't clear year, month, day etc.
    	this.deactivate();
    	this.behavior = behavior;
    	this.timeReached = false;
    	this.timeChecker.subscribe(this);
    }
    
    public void deactivate() {
    	this.timeChecker.unsubscribe(this);
    }
}
