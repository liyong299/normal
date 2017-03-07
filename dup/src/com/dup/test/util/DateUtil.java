/**
 * 项   目  名：SCEC
 * 包          名：cn.mopon.cec.core.util
 * 文   件  名：DateUtil.java
 * 版本信息：SCEC_Branches
 * 日          期：2015年8月19日-下午4:21:54
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author LRL
 * @version [SCEC_Branches, 2015年8月19日]
 * @备注：
 */
public class DateUtil {

	/**
	 * 日期格式：yyyy-MM
	 */
	private static String yearmonthPattern = "yyyy-MM";

	/**
	 * 日期格式：yyyy-MM-dd
	 */
	private static String defaultDatePattern = "yyyy-MM-dd";

	/**
	 * 时间格式：HH:mm:ss
	 */
	private static String defaultTimePattern = "HH:mm:ss";

	/**
	 * 日期格式：yyyyMMddHHmmss
	 */
	public static final String currentDateTimePattern = "yyyyMMddHHmmss";

	/**
	 * 日期格式：yyyyMMdd
	 */
	public static final String currentDatePattern = "yyyyMMdd";

	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

	public static final String dateTimePattenWithoutSecind = "yyyy-MM-dd HH:mm";

	/**
	 * 日期格式：yy/MM/dd
	 */
	public static String shortDatePatten = "yy/MM/dd";

	/**
	 * 日期格式：yy/MM/dd
	 */
	public static String shortDateTimePatten = "yy/MM/dd HH:mm:ss";

	/**
	 * 获得日期格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getDateTimePattern() {
		return dateTimePattern;
	}

	/**
	 * 日期格式：yyyy-MM-dd
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 得到当前年份
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getCurYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR));
	}

	/**
	 * 获得默认的 time pattern
	 */
	public static String getTimePattern() {
		return defaultTimePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 得到只有年月的时间
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static String getYearmonth(Date date) {
		return format(date, yearmonthPattern);
	}

	/**
	 * 得到有年月日的时间
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static String getYearmonthDay(Date date) {
		return format(date, defaultDatePattern);
	}

	/**
	 * 得到当前的日期和时间，如果是多个服务器，如果服务器时间不同步， 则需要使用这个方法取得同步的日期和时间
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static Date getDate() {
		Date today = new Date();
		return today;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static long getLongDate() {
		Date today = new Date();
		return today.getTime();
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @author LRL
	 * @param date
	 * @param pattern
	 * @return
	 * 
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";

		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}

		return (returnValue);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 * 
	 * @author LRL
	 * @param strDate
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static Date parse(String strDate) throws ParseException {
		if (null != strDate && strDate.length() > 13) {
			return parse(strDate, getDatePattern() + " " + getTimePattern());
		} else if (null != strDate) {
			return parse(strDate, getDatePattern());
		} else {
			return null;
		}

	}

	/**
	 * 使用参数Format将字符串转为Date
	 * 
	 * @author LRL
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @author LRL
	 * @param date
	 * @param n
	 * @return
	 * 
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 得到数月后的时间
	 * 
	 * @author LRL
	 * @param n
	 * @return
	 * 
	 */
	public static Date addMonth(int n) {
		return addMonth(getDate(), n);
	}

	/**
	 * 在日期上增加数个整天
	 * 
	 * @author LRL
	 * @param date
	 * @param n
	 * @return
	 * 
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个小时
	 * 
	 * @author LRL
	 * @param date
	 * @param n
	 * @return
	 * 
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数分钟
	 * 
	 * @author liangda
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMinute(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, n);
		return c.getTime();
	}

	/**
	 * 在日期上增加数秒
	 * 
	 * @author LRL
	 * @param date
	 * @param n
	 * @return
	 * 
	 */
	public static Date addSecond(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, n);
		return c.getTime();
	}

	/**
	 * 在日期上增加数天
	 * 
	 * @author LRL
	 * @param n
	 * @return
	 * 
	 */
	public static Date addDay(int n) {
		return addDay(getDate(), n);
	}

	/**
	 * 比较两个日期
	 * 
	 * @author LRL
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 */
	public static boolean compare(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		// 如果date1在date2前面，表明date1比较小
		if (cal1.before(cal2)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取唯一编号
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getOnlyID() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMDDhhmmssSSS");
		double dblTmp;
		for (dblTmp = Math.random() * 100000D; dblTmp < 10000D; dblTmp = Math
				.random() * 100000D)
			;
		String strRnd = String.valueOf(dblTmp).substring(0, 4);
		String s = df.format(getDate()) + strRnd;
		return s;
	}

	/**
	 * 获取给定时间所在周的第一天(Sunday)的日期和最后一天(Saturday)的日期
	 * 
	 * @author LRL
	 * @param calendar
	 *            *
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 * @param calendar
	 * @return
	 * 
	 */
	public static Date[] getWeekStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];
		// 得到当天是这周的第几天
		Date firstDateOfWeek, lastDateOfWeek;

		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
		calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
		// 每周7天，加６，得到最后一天的日子
		firstDateOfWeek = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, 6);
		lastDateOfWeek = calendar.getTime();
		dates[0] = firstDateOfWeek;
		dates[1] = lastDateOfWeek;
		return dates;
	}

	/**
	 * 获取给定时间所在月的第一天的日期和最后一天的日期
	 * 
	 * @author LRL
	 * @param calendar
	 * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
	 * 
	 */
	public static Date[] getMonthStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];
		// 得到当天是这月的第几天
		Date firstDateOfMonth, lastDateOfMonth;
		// 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
		firstDateOfMonth = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
		lastDateOfMonth = calendar.getTime();
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	/**
	 * 获取系统时间 返回 Calendar
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static Calendar getCurrentDate() {
		Calendar now = Calendar.getInstance();
		return now;
	}

	/**
	 * 返回时间戳类型
	 * 
	 * @author LRL
	 * @param strDate
	 * @return
	 * 
	 */
	public static Calendar getCalendar(String strDate) {
		Calendar c1 = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date = df.parse(strDate);
			c1 = Calendar.getInstance();
			c1.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}

	/**
	 * 获取指定月有多少天
	 * 
	 * @author LRL
	 * @param strDate
	 * @return
	 * 
	 */
	public static int getMonthCount(String strDate) {
		int day = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = new GregorianCalendar();
			Date date = sdf.parse(strDate);
			calendar.setTime(date);
			day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception ex) {
		}
		return day;
	}

	/**
	 * 计算2个日期间的小时差值,返回整数
	 * 
	 * @author LRL
	 * @param d1
	 * @param d2
	 * @return
	 * 
	 */

	public static int getHours(String d1, String d2) {
		return (int) Math.abs(getDoubleHours(d1, d2));
	}

	/**
	 * 计算2个日期间的小时差值 返回float类型
	 * 
	 * @author LRL
	 * @param d1
	 * @param d2
	 * @return
	 * 
	 */

	public static double getDoubleHours(String d1, String d2) {
		double hour = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date now = df.parse(d1);
			java.util.Date date = df.parse(d2);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			hour = ((double) l / (60 * 60 * 1000) - day * 24);
			day = Math.abs(day);
			if (day != 0) {
				hour = Math.abs(hour);
				hour = hour + 24 * day;
			}
		} catch (Exception e) {
		}
		return (double) Math.abs(hour);
	}

	/**
	 * 计算2个日期间的小时差值,返回整数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * 
	 */

	public static int getHours(Date d1, Date d2) {
		return (int) Math.abs(getDoubleHours(d1, d2));
	}

	/**
	 * 计算2个日期间的分钟差值,返回整数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * 
	 */

	public static int getMMs(Date d1, Date d2) {
		return (int) Math.abs(getDoubleHours(d1, d2) * 60);
	}

	/**
	 * 计算两个时间的毫秒 milliseconds
	 * 
	 * @author LRL
	 * @param d1
	 * @param d2
	 * @return
	 * @time 上午11:14:53
	 */
	public static long getMilliseconds(Date d1, Date d2) {
		return (long) Math.abs(d1.getTime() - d2.getTime());
	}

	public static double getDoubleHours(Date d1, Date d2) {
		double hour = 0;
		try {
			long l = d1.getTime() - d2.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			hour = ((double) l / (60 * 60 * 1000) - day * 24);
			day = Math.abs(day);
			if (day != 0) {
				hour = Math.abs(hour);
				hour = hour + 24 * day;
			}
		} catch (Exception e) {
		}
		return (double) Math.abs(hour);
	}

	/**
	 * 获取当前时间，格式：yyyyMMddHHmmss
	 * 
	 * @author LRL
	 * @return
	 * 
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		return format(date, currentDateTimePattern);
	}

	/**
	 * 主要用于在HQL查询,时间参数格式化
	 * 
	 * @author LRL
	 * @param strBeginDate
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static Date getBeginDate(String strBeginDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(strBeginDate + " 00:00:00");
	}

	/**
	 * 主要用于在HQL查询,时间参数格式化
	 * 
	 * @author LRL
	 * @param strEndDate
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static Date getEndDate(String strEndDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(strEndDate + " 23:59:59");
	}

	/**
	 * 获取日期在一周的某天
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static int getDayOfWeek(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		return cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取日期在一月的某天
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static int getDayOfMonth(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		return cal.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得日期字符串，日期格式：yyyyMMddHHmmssSSS
	 * 
	 * @author LRL
	 * @param date
	 * @return
	 * 
	 */
	public static String getTimeString(Date date) {
		String timePattren = "yyyyMMddHHmmssSSS";
		return dateToString(date, timePattren);
	}

	/**
	 * 获得指定日期格式字符串
	 * 
	 * @author LRL
	 * @param date日期
	 * @param pattern
	 *            日期格式
	 * @return
	 * 
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat fo = new SimpleDateFormat(pattern);
		return fo.format(date);
	}

	/**
	 * 获取HH:mm:ss.sss时间格式的毫秒数
	 * 
	 * @author LRL
	 * @return 毫秒数
	 * 
	 */
	public static int getTimeByHHmmss(String hhmmss) {
		if (hhmmss == null || "".equals(hhmmss)) {
			return 0;
		}
		String[] arr = hhmmss.split(":");
		int num = 0;
		if (arr.length == 3) {
			num = (int) ((Double.valueOf(arr[0]) * 3600
					+ Double.valueOf(arr[1]) * 60 + Double.valueOf(arr[2])) * 1000);
			return num;
		} else {
			return 0;
		}
	}

	/**
	 * 严格的日期验证包括格式和逻辑上的合法性 例如：2004-02-29和2003-02-29 1.验证年月日的合法性 2.检验闰月和大小月
	 * 3.解决1、3、5、7、8、10、12月是31天的大月问题
	 * 
	 * @author 桂明 2011-12-08
	 * @param dateStr
	 * @return
	 */
	public static boolean check_yyyy_MM_dd_Valid(String dateStr) {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		return dateStr.matches(regex);
	}

	/**
	 * 严格的（日期+时间）验证包括格式和逻辑上的合法性 例如：2004-02-29 23:58:59 1.验证年月日的合法性 2.检验闰月和大小月
	 * 3.解决1、3、5、7、8、10、12月是31天的大月问题
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean check_yyyyMMdd_HHmmss_valid(String dateStr) {
		/* 日期与时间之间的空格可以用正则表示式来表示：【\\s】 */
		// String _regex =
		// "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]";
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(\\s([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9])";
		return dateStr.matches(regex);
	}

	/**
	 * 严格的验证时间格式和逻辑上的合法性 例如：23:58:59
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean check_HH_mm_ss_valid(String dateStr) {
		/* 日期与时间之间的空格可以用正则表示式来表示：【\\s】 */
		// String _regex =
		// "((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30))))) ([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]";
		String regex = "([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]";
		return dateStr.matches(regex);
	}

	/**
	 * 重新计算添加n天后日期的字符格式
	 * 
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String addDateStr(String dateStr, int n)
			throws ParseException {
		Date date = DateUtil.parse(dateStr, "yyyy-MM-dd");
		date = DateUtil.addDay(date, n);
		dateStr = DateUtil.format(date, "yyyy-MM-dd");
		return dateStr;
	}

	/**
	 * 
	 * 获取格式为yyyy-MM-dd HH:mm:ss的时间
	 * 
	 * @author Administrator
	 * @return
	 * 
	 */
	public static String getNowDate() {
		SimpleDateFormat ft = null;
		Date date = null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(new java.util.Date());
		date = cl.getTime();
		ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = ft.format(date);
		return dateTime;
	}

	/**
	 * 获取当前日期 i天后的日期 ，返回格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @author Administrator
	 * @param i
	 * @return
	 * 
	 */
	public static String dateAddday(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DAY_OF_MONTH);
		d = d + i;
		c.set(Calendar.DAY_OF_MONTH, d);
		String str = df.format(c.getTime());
		return str;
	}

	/**
	 * 返回dateStr追加N秒后的时间
	 * 
	 * @author No.12323
	 * @2012-3-27 下午03:57:03
	 * @param dateStr
	 * @param n
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static String addSecondStr(String dateStr, int n)
			throws ParseException {
		Date date = DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
		date = DateUtil.addSecond(date, n);
		dateStr = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		return dateStr;
	}

	/**
	 * 获取当前日期 i月后的日期 ，返回格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @author Administrator
	 * @param i
	 * @return
	 * 
	 */
	public static String dateAddMnoth(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.MONTH);
		d = d + i;
		c.set(Calendar.MONTH, d);
		String str = df.format(c.getTime());
		return str;
	}

	/**
	 * 比较 string =yyyy-MM-dd HH:mm:ss 的格式时间大小,str1>=str2时 ret true
	 * 
	 * @author Administrator
	 * @param str1
	 * @param str2
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static boolean compareStr(String str1, String str2)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a = df.parse(str1);
		Date b = df.parse(str2);
		return compare(a, b);
	}

	public static Integer SumStrtoInteger(String str) {
		Integer num = 0;
		String[] astr = str.split(":");
		if (Integer.valueOf(astr.length).toString().equals("3")) {
			num = Integer.valueOf(astr[0]) * 3600 + Integer.valueOf(astr[1])
					* 60 + Integer.valueOf(astr[2]);
		}
		return num;
	}
}
