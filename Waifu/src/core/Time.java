package core;

import java.io.Serializable;
import java.util.Calendar;

//@ Project			: ProjectWaifu
//@ File Name		: Time.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved

/**
 * Be able to store year, month, day, hour and minute.
 * These attributes can be modified and read.
 * Serializable, so can be saved by Serializer.serialize()!
 * @author csiki
 *
 */
public class Time implements Serializable {

	private static final long serialVersionUID = 6319445833796974120L;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	public Time() {
		Calendar calendar = Calendar.getInstance();
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	@Override
	public String toString() {
		Integer min = this.minute;
		String minS = min.toString();
		
		if (min == 0) {
			minS += "0";
		}
		else if (min < 10) {
			minS = "0" + minS;
		}
		
		return this.year + ". " + (this.month + 1) + ". " + this.day + ". " + this.hour + ":" + minS;
	}
	
	public boolean isPassed() {
		
		Calendar cal = Calendar.getInstance();
    	int currY = cal.get(Calendar.YEAR);
    	int currMo = cal.get(Calendar.MONTH) + 1;
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
    	
    	return reached >= 0;
	}
}
