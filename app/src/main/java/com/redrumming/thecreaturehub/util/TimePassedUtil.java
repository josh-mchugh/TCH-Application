package com.redrumming.thecreaturehub.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.text.format.DateUtils;

public class TimePassedUtil {
	
	private String date;
	
	public TimePassedUtil(){
		
	}
	
	public TimePassedUtil(String date){
	
		this.date = date;
	}
	
	public String getTimeDifference(){
	
		String timeDiference = getTimeDifference(date);
		
		return timeDiference;
	}
	
	public String getTimeDifference(String date){
	
		//create videoDate and currentDate
		Calendar videoDate = createVideoDate(date);
		Calendar currentDate = GregorianCalendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
		
		//get long of both current time and video time
		long videoDateLong = videoDate.getTimeInMillis();
		long currentDateLong = currentDate.getTimeInMillis();
		
		//calculate time passed
		long timePassed = calculateTimePassed(videoDateLong, currentDateLong);
		
		//get correct response
		String displayTime = generateDisplayTime(videoDateLong, currentDateLong, timePassed);
		
		//return string
		return displayTime;
	}
	
	
	public Calendar createVideoDate(String date){
	
		int[] parsedDate = parseDateString(date);
		
		Calendar videoDate = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
		videoDate.set(parsedDate[0], parsedDate[1] -1, parsedDate[2], parsedDate[3], parsedDate[4], parsedDate[5]); //month needs to be subtracted by one
		
		return videoDate;
	}
	
	
	private int[] parseDateString(String date){
	
		//0 - year, 1 - month, 2 - day, 3 - hour, 4 - min, 5 - second, 6 - millisond.
		int[] parsedDate = new int[7];
		String[] dateComponents = new String[7];
		
		try{
		
			String[] dateAndTimeSplit = date.split("T");
			
			String[] dateArray = dateAndTimeSplit[0].split("-");
			dateComponents[0] = dateArray[0];//year 
			dateComponents[1] = dateArray[1];//month
			dateComponents[2] = dateArray[2];//day
			
			String[] timeArray = dateAndTimeSplit[1].split(":");
			dateComponents[3] = timeArray[0];//hour
			dateComponents[4] = timeArray[1];//minutes
			
			String[] secondsArray = timeArray[2].split("\\.");
			dateComponents[5] = secondsArray[0];//seconds
			dateComponents[6] = secondsArray[1].replaceAll("[a-zA-Z]+", "");//milliseconds
			
			for(int i = 0; i < dateComponents.length; i++){
				parsedDate[i] = Integer.parseInt(dateComponents[i]);
			}
			
		}catch(Exception e){
			
		}
		
		return parsedDate;
	}
	
	
	private long calculateTimePassed(long videoTime, long currentTime){
	
		long timePassed = (currentTime - videoTime);		
		
		return timePassed;
	}
	
	private String generateDisplayTime(long videoTime, long currentTime, long timePassed){
			
		long withinHour = currentTime - TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
		long withinDay = currentTime - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		long withinWeek = currentTime - TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS);
		long withinMonth = currentTime - TimeUnit.MILLISECONDS.convert(30, TimeUnit.DAYS);
		long withinYear = currentTime - TimeUnit.MILLISECONDS.convert(365, TimeUnit.DAYS);
		
		
		if(videoTime > withinHour){
			
			Long minsPassed = TimeUnit.MINUTES.convert(timePassed, TimeUnit.MILLISECONDS);
			
			//if minsPassed = 1 don't use plural version
			return minsPassed.equals(1) ? minsPassed + " minute ago" : minsPassed + " minutes ago";
		
		}else if(videoTime > withinDay){
			
			Long hoursPassed = TimeUnit.HOURS.convert(timePassed, TimeUnit.MILLISECONDS);
			
			return hoursPassed.equals(1L) ? hoursPassed + " hour ago" : hoursPassed + " hours ago";
		
		}else if(videoTime > withinWeek){
			
			Long daysPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS);
			
			return daysPassed.equals(1L) ? daysPassed + " day ago" : daysPassed + " days ago";
		
		}else if(videoTime > withinMonth){
			
			Long weeksPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / 7;
			
			return weeksPassed.equals(1L) ? weeksPassed + " week ago" : weeksPassed + " weeks ago";
		
		}else if(videoTime > withinYear){
			
			Long monthsPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / 30;
			
			return monthsPassed.equals(1L) ? monthsPassed + " month ago" : monthsPassed + " months ago";
		
		}else if(videoTime < withinYear){
		
			Long yearsPassed = TimeUnit.DAYS.convert(timePassed, TimeUnit.MILLISECONDS) / 365;
			
			return yearsPassed.equals(1L) ? yearsPassed + " year ago" : yearsPassed + " years ago";
		
		}else{
			
			return DateUtils.getRelativeTimeSpanString(videoTime).toString();
		}
	}
}
