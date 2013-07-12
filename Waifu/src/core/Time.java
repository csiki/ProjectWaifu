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
		return this.year + ". " + (this.month + 1) + ". " + this.day + ". " + this.hour + ":" + this.minute;
	}
}
