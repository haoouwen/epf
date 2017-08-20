package com.rbt.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class DateUtil {
	 /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
    
    /** 年月日 yyyy-MM-dd */
    public static final String ymr = "yyyy-MM-dd";
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getFormatLong(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
		
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的六位数
	 * @return
	 */
	public static String getSix(){
		Random rad=new Random();
		return rad.nextInt(1000000)+"";
	}
	/**
	 * @author : LJQ
	 * @date : May 17, 2014 11:27:01 AM
	 * @Method Description :格式化时间
	 */
	public static Date parseDateTime(String str) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(str);
	}
	
	
	/**
	 * @author : LJQ
	 * @date : Jun 7, 2014 1:42:14 PM
	 * @Method Description :格式化日期
	 */
	public static String parseDateTime(Date date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * QJY
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateYMR(Date date)throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * @author : LJQ
	 * @date : May 17, 2014 11:27:01 AM
	 * @Method Description :格式化日期
	 */
	public static String formatDay(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * @author : LJQ
	 * @date : May 17, 2014 11:26:28 AM
	 * @Method Description :拆分时间按天数执行数据
	 */
	public static List<Date[]> splitTimeByDays(Date start, Date end, int days) {
		return splitTimeByHours(start, end, 24 * days);
	}

	/**
	 * @author : LJQ
	 * @date : May 17, 2014 11:27:24 AM
	 * @Method Description :拆分时间按小时执行数据
	 */
	public static List<Date[]> splitTimeByHours(Date start, Date end, int hours) {
		List<Date[]> dl = new ArrayList<Date[]>();
		while (start.compareTo(end) < 0) {
			Date _end = addHours(start, hours);
			if (_end.compareTo(end) > 0) {
				_end = end;
			}
			Date[] dates = new Date[] { (Date) start.clone(), (Date) _end.clone() };
			dl.add(dates);

			start = _end;
		}
		return dl;
	}

	/**
	 * @author : LJQ
	 * @date : Jun 7, 2014 1:43:08 PM
	 * @Method Description :加时间分数
	 */
	public static Date addMinutes(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, amount);
		return c.getTime();
	}

	/**
	 * @author : LJQ
	 * @date : Jun 7, 2014 1:43:23 PM
	 * @Method Description :加时间小时数
	 */
	public static Date addHours(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, amount);
		return c.getTime();
	}

	/**
	 * @author : LJQ
	 * @date : Jun 7, 2014 1:43:36 PM
	 * @Method Description :加时间天数
	 */
	public static Date addDays(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	/**
	 * @author : LJQ
	 * @date : Jun 7, 2014 1:43:43 PM
	 * @Method Description :加时间月数
	 */
	public static Date addMonths(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}

	/**
	 * 获取当前时期
	 */
	public static Date getTodayStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * @author : LJQ
	 * @throws ParseException 
	 * @date : Jun 7, 2014 1:43:56 PM
	 * @Method Description :获取当前的日期返回String类型数据
	 */
	public static String getCurrentTime() throws ParseException{
		Calendar cal = Calendar.getInstance();
		return parseDateTime(cal.getTime());
	} 
	/**
	 * @author : LJQ
	 * @throws ParseException 
	 * @date : Jun 7, 2014 1:43:56 PM
	 * @Method Description :获取当前的时间返回String类型数据
	 */
	public static String getCurrentDate() throws ParseException{
		Calendar cal = Calendar.getInstance();
		return formatDay(cal.getTime());
	} 
	/**
	 * @Method Description :时间比较 大于返回1 小于返回-1 异常返回0
	 * @author: HXK
	 * @date : Jan 22, 2015 9:04:57 PM
	 * @param 
	 * @return return_type
	 */
	public static int compare_date(String DATE1, String DATE2) {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	        Date dt1 = df.parse(DATE1);
	        Date dt2 = df.parse(DATE2);
	        if (dt1.getTime() > dt2.getTime()) {
	            return 1;//大于
	        } else if (dt1.getTime() < dt2.getTime()) {
	            return -1;//小于
	        } else {
	            return 0;
	        }
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	    return 0;
	}
	//当前时间 加上多少天
	public  static  String   nDaysAftertoday(int   n)   {  
         SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
         Calendar   rightNow   =   Calendar.getInstance();  
         rightNow.add(Calendar.DATE,+n);  
         return   df.format(rightNow.getTime());  
     }
	//当前时间 加上多少天
	public  static  String   nDaysAftertoday(Date date,int   n)   {  
         SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
         Calendar   rightNow   =   Calendar.getInstance();  
         rightNow.setTime(date);
         rightNow.add(Calendar.DATE,+n);  
         return   df.format(rightNow.getTime());  
     }
	/**
	  * 获取两个日期间的间隔天数
	  */
	 public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}
	 
	/**
	 * @author Administrator QJY
	 * @method 获取两个日期间隔的月数
	 * @param date1 <String>
	 * @param date2 <String>
	 * @return int
	 * @throws ParseException
	 */
    public static int getMonthSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = sdf.parse("2016-01-14 10:48:57");
		 System.out.println(getCurrentDate());
		return ;
	}
	
	
}
