package com.redrumming.thecreaturehub.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.text.format.DateUtils;

public class TimePassedUtil {

	private static final String MINUTE_PASSED = " minute ago";
	private static final String MINUTES_PASSED = " minutes ago";
	private static final String HOUR_PASSED = " hour ago";
	private static final String HOURS_PASSED = " hours ago";
	private static final String DAY_PASSED = " day ago";
	private static final String DAYS_PASSED = " days ago";
	private static final String WEEK_PASSED = " week ago";
	private static final String WEEKS_PASSED = " weeks ago";
	private static final String MONTH_PASSED = " month ago";
	private static final String MONTHS_PASSED = " months ago";
	private static final String YEAR_PASSED = " year ago";
	private static final String YEARS_PASSED = " years ago";

	private static final int WEEK_VALUE_IN_DAYS = 7;
	private static final int MONTH_VALUE_IN_DAYS = 30;
	private static final int YEAR_VALUE_IN_DAYS = 365;

	private static final Long SINGULAR_VALUE = 1l;

	public TimePassedUtil(){
		
	}
	
	public static String getTimeDifference(long date){
	
		//create videoDate and currentDate
		Calendar currentDate = GregorianCalendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
		
		//get long of both current time and video time
		long videoDateLong = date;
		long currentDateLong = currentDate.getTimeInMillis();
		
		//calculate time passed
		long timePassed = calculateTimePassed(videoDateLong, currentDateLong);
		
		//get correct response
		String displayTime = generateDisplayTime(videoDateLong, currentDateLong, timePassed);
		
		//return string
		return displayTime;
	}
	
	private static long calculateTimePassed(long videoTime, long currentTime){
	
		long timePassed = (currentTime - videoTime);		
		
		return timePassed;
	}
	
	private static String generateDisplayTime(long videoTime, long currentTime, long timePassed){
			
		long withinHour = currentTime - TimeUnit.MILLISECONDS.convert(SINGULAR_VALUE, TimeUnit.HOURS);
		long withinDay = currentTime - TimeUnit.MILLISECONDS.convert(SINGULAR_VALUE, TimeUnit.DAYS);
		long withinWeek = currentTime - TimeUnit.MILLISECONDS.convert(WEEK_VALUE_IN_DAYS, TimeUnit.DAYS);
		long withinMonth = currentTime - TimeUnit.MILLISECONDS.convert(MONTH_VALUE_IN_DAYS, TimeUnit.DAYS);
		long withinYear = currentTime - TimeUnit.MILLISECONDS.convert(YEAR_VALUE_IN_DAYS, TimeUnit.DAYS);
		
		
		if(videoTime > withinHour){
			
			Long minsPassed = TimeUnit.MINUTES.convert(timePassed, TimeUnit.MILLISECONDS);
			
			//if minsPassed = 1 don't use plural version
			return minsPassed.equals(SINGULAR_VALUE) ? minsPassed + MINUTE_PASSED : minsPassed + MINUTES_PASSED;
		
		}else if(videoTime > withinDay){
			
			Long hoursPassed = TimeUnit.HOURS.convert(timePassed, TimeUnit.MILLISECONDS);
			
			return hoursPassed.equals(SINGULAR_VALUE) ? hoursPassed + HOUR_PASSED : hoursPassed + HOURS_PASSED;
		
		}else if(videoTime > withinWeek){
			
			Long daysPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS);
			
			return daysPassed.equals(SINGULAR_VALUE) ? daysPassed + DAY_PASSED : daysPassed + DAYS_PASSED;
		
		}else if(videoTime > withinMonth){
			
			Long weeksPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / WEEK_VALUE_IN_DAYS;
			
			return weeksPassed.equals(SINGULAR_VALUE) ? weeksPassed + WEEK_PASSED : weeksPassed + WEEKS_PASSED;
		
		}else if(videoTime > withinYear){
			
			Long monthsPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / MONTH_VALUE_IN_DAYS;
			
			return monthsPassed.equals(SINGULAR_VALUE) ? monthsPassed + MONTH_PASSED : monthsPassed + MONTHS_PASSED;
		
		}else if(videoTime < withinYear){
		
			Long yearsPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / YEAR_VALUE_IN_DAYS;
			
			return yearsPassed.equals(SINGULAR_VALUE) ? yearsPassed + YEAR_PASSED : yearsPassed + YEARS_PASSED;
		
		}else{
			
			return DateUtils.getRelativeTimeSpanString(videoTime).toString();
		}
	}
}
