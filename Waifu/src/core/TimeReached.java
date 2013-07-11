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
     * A full numeric representation of a year, 4 digits.
     */
    private int year;
    
    /**
     * Numeric representation of a month, without leading zeros.
     */
    private int month;
    
    /**
     * Day of the month without leading zeros.
     */
    private int day;
    
    /**
     * 24-hour format of an hour without leading zeros.
     */
    private int hour;
    
    /**
     * Minutes without leading zeros.
     */
    private int minute;
    
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
    	this.year = -1;
    	this.month = -1;
    	this.day = -1;
    	this.hour = -1;
    	this.minute = -1;
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
    	return this.year;
    }
    
    /**
     * Gets the given month.
     * @return month or -1 if it has not been specified.
     */
    public int getMonth() {
    	return this.month;
    }
    
    /**
     * Gets the given day.
     * @return day or -1 if it has not been specified.
     */
    public int getDay() {
    	return this.day;
    }
    
    /**
     * Gets the given hour.
     * @return hour (0-23) or -1 if it has not been specified.
     */
    public int getHour() {
    	return this.hour;
    }
    
    /**
     * Gets the given minute.
     * @return minute or -1 if it has not been specified.
     */
    public int getMinute() {
    	return this.minute;
    }
    
    /**
     * Sets year.
     * @param year > 0
     */
    public void setYear(int year) {
    	if (year > 0) {
    		this.year = year;
    	}
    }
    
    /**
     * Sets month.
     * @param month > 0 and <= 12
     */
    public void setMonth(int month) {
    	if (month > 0 && month <= 12) {
    		this.month = month;
    	}
    }
    
    /**
     * Sets day.
     * @param day > 0 and <= 31
     */
    public void setDay(int day) {
    	if (day > 0 && day <= 31) {
    		this.day = day;
    	}
    }
    
    /**
     * Sets hour.
     * @param hour >= 0 (mod 24 of it is stored)
     */
    public void setHour(int hour) {
    	if (hour >= 0) {
    		this.hour = hour % 24;
    	}
    }
    
    /**
     * Sets minute.
     * @param minute >= 0 (mod 60 of it is stored)
     */
    public void setMinute(int minute) {
    	if (minute >= 0) {
    		this.minute = minute % 60;
    	}
    }
    
    /**
     * Sets year, month, day, hour and minute as in time (time is serializable).
     * @param time
     */
    public void setTime(Time time) {
    	this.year = time.getYear();
    	this.month = time.getMonth();
    	this.day = time.getDay();
    	this.hour = time.getHour();
    	this.minute = time.getMinute();
    }
    
    /**
     * Sets the time to the current + min minutes.
     * @param min
     */
    public void nextMinutes(int min) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MINUTE, min);
    	
    	this.setYear(cal.get(Calendar.YEAR));
    	this.setMonth(cal.get(Calendar.MONTH));
    	this.setDay(cal.get(Calendar.DAY_OF_MONTH));
    	this.setHour(cal.get(Calendar.HOUR));
    	this.setMinute(cal.get(Calendar.MINUTE));
    }
    
    /**
     * TimeChecker calls it every minute.
     * Behavior should NOT call it!
     */
    public void update() {
    	
    	Calendar cal = Calendar.getInstance();
    	int currY = cal.get(Calendar.YEAR);
    	int currMo = cal.get(Calendar.MONTH);
    	int currD = cal.get(Calendar.DAY_OF_MONTH);
    	int currH = cal.get(Calendar.HOUR_OF_DAY);
    	int currMi = cal.get(Calendar.MINUTE);
    	
    	int y = (this.year == -1) ? currY : this.year;
    	int mo = (this.month == -1) ? currMo : this.month;
    	int d = (this.day == -1) ? currD : this.day;
    	int h = (this.hour == -1) ? currH : this.hour;
    	int mi = (this.minute == -1) ? currMi : this.minute;
    	
    	/*
    	 * Values: 	minute	1
    	 * 			hour	60
    	 * 			day		1440
    	 * 			month	44640
    	 * 			year	535680
    	 */
    	
    	int reached = (currY - y) * 535680 + (currMo - mo) * 44640 + (currD - d) * 1440 + (currH - h) * 60 + (currMi - mi);
    	
    	if (reached >= 0) {
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
