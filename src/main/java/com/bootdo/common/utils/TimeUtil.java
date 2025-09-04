package com.bootdo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	public static String getTimeByMinute(int minute){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);	
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmm");
		return new SimpleDateFormat("yyyyMMddHHmm").format(calendar.getTime());
	}
}
