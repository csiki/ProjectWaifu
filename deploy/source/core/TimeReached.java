package core;

import java.util.Calendar;
//  @ Project		: ProjectWaifu
//  @ File Name		: TimeReached.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Checks if the given time is reached (when updated by TimeChecker).
 * Can be created by behaviors using UserActionFactory.
 * @author csiki
 *
 */
public class TimeReached extends UserAction {
	
	/**
	 * True if the given time is reached, false otherwise.
	 */
    private volatile boolean timeReached;
    
    /**
     * Time (including year, month, day, hour, minute).
     */
    private Time time;
    
    /**
     * TimeChecker instance.
     */
    private TimeChecker timeChecker;
    
    /**
     * Constructor of TimeReached.
     * @param timeChecker instance of TimeChecker
     */
    public TimeReached(TimeChecker timeChecker) {
    	this.timeChecker = timeChecker;
    	this.time = new Time();
    	this.timeReached = false;
    }
    
    /**
     * Gets if the given time is reached (after activated only).
     * @return True if has been activated and time is reached, false otherwise.
     */
    public boolean isTimeReached() {
    	return this.timeReached;
    }
    
    /**
     * Gets the given year.
     * @return four digit representation of the given year, -1 if it has not been specified.
     */
    public int getYear() {
    	return this.time.getYear();
    }
    
    /**
     * Gets the given month.
     * @return month or -1 if it has not been specified.
     */
    public int getMonth() {
    	return this.time.getMonth();
    }
    
    /**
     * Gets the given day.
     * @return day or -1 if it has not been specified.
     */
    public int getDay() {
    	return this.time.getDay();
    }
    
    /**
     * Gets the given hour.
     * @return hour (0-23) or -1 if it has not been specified.
     */
    public int getHour() {
    	return this.time.getHour();
    }
    
    /**
     * Gets the given minute.
     * @return minute or -1 if it has not been specified.
     */
    public int getMinute() {
    	return this.time.getMinute();
    }
    
    /**
     * Sets year.
     * @param year > 0
     */
    public void setYear(int year) {
    	if (year > 0) {
    		this.time.setYear(year);
    	}
    }
    
    /**
     * Sets month.
     * @param month > 0 and <= 12
     */
    public void setMonth(int month) {
    	if (month > 0 && month <= 12) {
    		this.time.setMonth(month);
    	}
    }
    
    /**
     * Sets day.
     * @param day > 0 and <= 31
     */
    public void setDay(int day) {
    	if (day > 0 && day <= 31) {
    		this.time.setDay(day);
    	}
    }
    
    /**
     * Sets hour.
     * @param hour >= 0 (mod 24 of it is stored)
     */
    public void setHour(int hour) {
    	if (hour >= 0) {
    		this.time.setHour(hour % 24);
    	}
    }
    
    /**
     * Sets minute.
     * @param minute >= 0 (mod 60 of it is stored)
     */
    public void setMinute(int minute) {
    	if (minute >= 0) {
    		this.time.setMinute(minute % 60);
    	}
    }
    
    /**
     * Sets year, month, day, hour and minute as in time (time is serializable).
     * @param time
     */
    public void setTime(Time time) {
    	this.time = time;
    }
    
    /**
     * Sets the time to the current + min minutes.
     * @param min
     */
    public void nextMinutes(int min) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MINUTE, min);
    	
    	this.setYear(cal.get(Calendar.YEAR));
    	this.setMonth(cal.get(Calendar.MONTH) + 1);
    	this.setDay(cal.get(Calendar.DAY_OF_MONTH));
    	this.setHour(cal.get(Calendar.HOUR_OF_DAY));
    	this.setMinute(cal.get(Calendar.MINUTE));
    }
    
    /**
     * TimeChecker calls it every minute.
     * Behavior should NOT call it!
     */
    public void update() {
    	if (this.time.isPassed()) {
    		// time reached
    		this.deactivate();
    		this.timeReached = true;
    		
    		if (this.behavior != null) {
    			this.behavior.actionPerformed(this);
    		}
    	}
    }
    
    /**
     * Activates TimeReached, so it listens for TimeChecker.
     * Should be activated after created by behavior!
     */
    public void activate(Behavior behavior) {
    	// don't clear year, month, day etc.
    	this.deactivate();
    	this.behavior = behavior;
    	this.timeReached = false;
    	this.timeChecker.subscribe(this);
    }
    
    /**
     * Deactivates TimeReached, so it won't listen for key hits.
     * Can be called by behavior but automatically called by itself if the expected key is hit (after had been activated ofc.).
     */
    public void deactivate() {
    	this.timeChecker.unsubscribe(this);
    }
}
