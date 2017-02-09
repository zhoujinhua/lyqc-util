package com.liyun.car.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author zhoufei
 *
 */

public class DateUtil {

	private static final SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SDF1=new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat SDF2=new SimpleDateFormat("yyyyMMdd HHmmss");
	private static final SimpleDateFormat SDF2_=new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat SDF3=new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat SDF4=new SimpleDateFormat("yyMMdd");
	private static final SimpleDateFormat SDF5=new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getgetDateFormat5(Date d){
		return SDF5.format(d);
	}
	public static String getSYSDateString(){
		return getDateFormat(new Date());
	}
	public static String getDateFormatAll(Date d){
		return SDF2.format(d);
	}
	public static String getDateFormatAll_(Date d){
		return SDF2_.format(d);
	}
	public static String getDateFormat(Date d){
		return SDF.format(d);
	}
	public static String getDateFormate2(Date d){
		return getDateFormat(d).substring(0, getDateFormat(d).length()-3);
	}
	public static String getDateFormat(long d){
		return SDF.format(d);
	}
	public static String formatDay(Date d){
		return SDF1.format(d);
	}
	public static String formatTime(Date d){
		return SDF.format(d);
	}
	public static String getDateFormatE(Date d){
		return SDF3.format(d);
	}
	/**
	 * Date --> yyyy-MM-dd 00:00:00
	 * @param d
	 * @return
	 */
	public static String formatDayStart(Date d){
		return SDF1.format(d)+" 00:00:00";
	}
	/**
	 * Date --> yyyy-MM-dd 23:59:59
	 * @param d
	 * @return
	 */
	public static String formatDayEnd(Date d){
		return SDF1.format(d)+" 23:59:59";
	}
	/**
	 * Date --> yyyy-MM-dd 00:00:00 -->Date
	 * @param d
	 * @return
	 */
	public static Date getDateStart(Date d){
		String dateString =SDF1.format(d);
		try {
			return SDF.parse(dateString+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d; 
	}
	/**
	 * Date --> yyyy-MM-dd 23:59:59-->Date
	 * @param d
	 * @return
	 */
	public static Date getDateEnd(Date d){
		String dateString =SDF1.format(d);
		try {
			return SDF.parse(dateString+" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d; 
	}
	/**
	 * yyyy-MM-dd  --> Date
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString){
		try {
			return SDF1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static Date parseDate1(String dateString){
		try{
			return SDF3.parse(dateString);
		} catch (Exception e){
			e.printStackTrace();
		}
		return new Date();
	}
	/**
	 * yyyy-MM-dd HH:mm:ss  --> Date
	 * @param date
	 * @return
	 */
	public static Date parsesDate(String dateString){
		try {
			return SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static Date formatToDate(String dateString,String format){
		try {
			SimpleDateFormat SDF = new SimpleDateFormat(format);
			return SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static Date parsesDate1(String dateString){
		try {
			if("".equals(dateString)){
				return new Date();
			}
			return SDF1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	
	
	public static Date currentDate(Date date){
		String dateString=SDF1.format(date);
		try {
			return SDF1.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static int getMonthDay(Date date){
		Calendar cal=Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public static void main(String[] args){
		System.out.println(DateUtil.getDateFormatAll_(new Date()));
		System.out.println(DateUtil.getMonthDay(new Date())+"");
	}
	public static String getgetDateFormat4(Date date) {
		return SDF4.format(date);
	}
}
