package com.vehiclerecycle.bigbest.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author Ljj
 *
 */
public class DateUtil {

	/**
	 * 返回当前时间的"yyyy-MM-dd hh:mm:ss"格式的字符串
	 * 
	 * @return
	 */
	public static String nowDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDateStr = sdf.format(new Date());
		return nowDateStr;
	}

	/**
	 * 返回当前时间的给定格式的字符串
	 * 
	 * @param format
	 *            字符串格式
	 * @return
	 */
	public static String nowDateStr(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowDateStr = sdf.format(new Date());
		return nowDateStr;
	}

	/**
	 * 把指定的日期转化成指定格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            符串格式字
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowDateStr = sdf.format(date);
		return nowDateStr;
	}

	/**
	 * 把指定的日期转化成"yyyy-MM-dd hh:mm:ss"格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDateStr = sdf.format(date);
		return nowDateStr;
	}

	/**
	 * 返回指定字符串代表的标准时间
	 * 
	 * @param dateString
	 *            格式：yyyy-MM-dd 或者 yyyy-MM-dd hh-mm-ss
	 * @return
	 */
	public static Date strToDate(String dateString) {
		Date date = null;
		String[] strings = dateString.split(" ");
		if (strings.length == 1) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				ParsePosition parsePosition = new ParsePosition(0);
				date = sdf.parse(dateString, parsePosition);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				ParsePosition parsePosition = new ParsePosition(0);
				date = sdf.parse(dateString, parsePosition);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// String nowDateStr=sdf.format(date);
		return date;
	}

	/**
	 * 返回指定毫秒数代表的标准时间
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static Date longToDate(long number) {
		return new Date(number);
	}

	/**
	 * 返回指定毫秒数代表的时间的yyyy-MM-dd hh-mm-ss格式的字符串
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String longToStr(long number) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		str = sdf.format(new Date(number));
		return str;
	}

	/**
	 * 返回指定毫秒数代表的时间的指定格式的字符串
	 * 
	 * @param number
	 *            时间毫秒数
	 * @param format
	 *            字符串格式
	 * @return
	 */
	public static String longToStr(long number, String format) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		str = sdf.format(new Date(number));
		return str;
	}

	/**
	 * 返回指定毫秒数对应日期的年数
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String getYear(Object object) {
		if(object instanceof Long){
			Date date=new Date((long)object);
			String dateString=DateUtil.dateToStr(date);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[0];
		}
		else if (object instanceof Date) {
			String dateString=DateUtil.dateToStr((Date)object);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[0];
		}
		else if (object instanceof String) {		
			String dateString=DateUtil.dateToStr(DateUtil.strToDate((String)object));		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[0];
		}
		else {
			return null;
		}
	}

	/**
	 * 返回指定毫秒数对应日期的月数
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String getMonth(Object object) {
		if(object instanceof Long){
			Date date=new Date((long)object);
			String dateString=DateUtil.dateToStr(date);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[1];
		}
		else if (object instanceof Date) {
			String dateString=DateUtil.dateToStr((Date)object);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[1];
		}
		else if (object instanceof String) {		
			String dateString=DateUtil.dateToStr(DateUtil.strToDate((String)object));		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[1];
		}
		else {
			return null;
		}
	}

	/**
	 * 返回指定毫秒数对应日期的天数
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String getDay(Object object) {
		if(object instanceof Long){
			Date date=new Date((long)object);
			String dateString=DateUtil.dateToStr(date);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[2];
		}
		else if (object instanceof Date) {
			String dateString=DateUtil.dateToStr((Date)object);		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[2];
		}
		else if (object instanceof String) {		
			String dateString=DateUtil.dateToStr(DateUtil.strToDate((String)object));		
			return dateString.replace(" ", "-").replace(":", "-").split("-")[2];
		}
		else {
			return null;
		}
	}

	/**
	 * 返回指定毫秒数对应日期的小时数
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String getHour(Object object) {
		if (object instanceof Long) {
			Date date = new Date((long) object);
			String dateString = DateUtil.dateToStr(date);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[3];
		} else if (object instanceof Date) {
			String dateString = DateUtil.dateToStr((Date) object);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[3];
		} else if (object instanceof String) {
			String dateString = DateUtil.dateToStr(DateUtil.strToDate((String) object));
			return dateString.replace(" ", "-").replace(":", "-").split("-")[3];
		} else {
			return null;
		}
	}

	/**
	 * 返回指定毫秒数对应日期的分钟数
	 * 
	 * @param number
	 *            毫秒数
	 * @return
	 */
	public static String getMin(Object object) {
		if (object instanceof Long) {
			Date date = new Date((long) object);
			String dateString = DateUtil.dateToStr(date);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[4];
		} else if (object instanceof Date) {
			String dateString = DateUtil.dateToStr((Date) object);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[4];
		} else if (object instanceof String) {
			String dateString = DateUtil.dateToStr(DateUtil.strToDate((String) object));
			return dateString.replace(" ", "-").replace(":", "-").split("-")[4];
		} else {
			return null;
		}
	}

	/**
	 * 返回指定毫秒数对应日期的秒数
	 * 
	 * @param object
	 *            毫秒数、日期或者代表日期的字符串
	 * @return
	 */
	public static String getSeconds(Object object) {
		if (object instanceof Long) {
			Date date = new Date((long) object);
			String dateString = DateUtil.dateToStr(date);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[5];
		} else if (object instanceof Date) {
			String dateString = DateUtil.dateToStr((Date) object);
			return dateString.replace(" ", "-").replace(":", "-").split("-")[5];
		} else if (object instanceof String) {
			String dateString = DateUtil.dateToStr(DateUtil.strToDate((String) object));
			return dateString.replace(" ", "-").replace(":", "-").split("-")[5];
		} else {
			return null;
		}
	}

	/**
	 * 返回指定日期表示的星期几
	 * @param object
	 * 			毫秒数、日期或者代表日期的字符串
	 * @return
	 */
	public static String getWeek(Object object){
		SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
		Date date=null;
		if (object instanceof Long) {
			date = new Date((long) object);
		} else if (object instanceof Date) {
			date =(Date) object;
		} else if (object instanceof String) {
			date=DateUtil.strToDate((String)object);
		} 
		return sdf.format(date);
	}
	
}
