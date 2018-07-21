package com.thesunboy.utils.mylog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间的工具类
 * 
 * @author hx940929
 * @CreateDate 2016-3-17 - 下午2:28:21
 * @ModifiedDate 2016-3-17 - 下午2:28:21
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public class DateUtils
{
	private static SimpleDateFormat dateTimeFormat;
	private static SimpleDateFormat timeFormat;
	private static SimpleDateFormat millTimeFormat;
	static
	{
		// 从系统缓存中读取格式化字符串设置
		// String formatstr = "yyyy-MM-dd HH:mm:ss";
		dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		millTimeFormat = new SimpleDateFormat("HH:mm:ss SSS");
		dateTimeFormat.setLenient(false);// 设置为严格解析
	}

	/**
	 * 得到当前系统日期时间: yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-26 - 下午3:26:59
	 */
	public static String getCurrentDateTimeStr()
	{
		return dateTimeFormat.format(new Date(System.currentTimeMillis()));
	}

	public static Date getCurrentDateTime()
	{
		try
		{
			return dateTimeFormat.parse(getCurrentDateTimeStr());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static Date getDateTimeSecond(long timestamp)
	{
		return new Date(timestamp * 1000L);
	}

	/**
	 * 得到当前系统的时间 格式: HH:mm:ss
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-18 - 下午9:44:10
	 */
	public static String getCurrentTimeStr()
	{
		return timeFormat.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 将该long类型的日期戳,转换为易读的文本格式格式: HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @Author hx940929
	 * @Date 2016-4-13 - 下午7:40:37
	 */
	public static String getTimeStr(long timestamp)
	{
		return timeFormat.format(new Date(timestamp));
	}

	/**
	 * 将该Date类型的日期对象,转换为易读的文本格式格式: HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @Author hx940929
	 * @Date 2016-4-13 - 下午7:40:37
	 */
	public static String getTimeStr(Date date)
	{
		return timeFormat.format(date);
	}

	/**
	 * 将该long类型的基于<b>毫秒</b>的时间戳,转换为易读的文本格式格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @Author hx940929
	 * @Date 2016-4-13 - 下午7:40:37
	 */
	public static String getDateTimeMillStr(long timestamp)
	{
		return dateTimeFormat.format(new Date(timestamp));
	}

	/**
	 * 将该long类型的基于<b>秒</b>的时间戳,转换为易读的文本格式格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @Author hx940929
	 * @Date 2016-4-13 - 下午7:40:37
	 */
	public static String getDateTimeSecondStr(long timestamp)
	{
		return dateTimeFormat.format(new Date(timestamp * 1000L));
	}

	/**
	 * 将该Date类型的日期对象,转换为易读的文本格式格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 * @Author hx940929
	 * @Date 2016-4-13 - 下午7:40:37
	 */
	public static String getDateTimeStr(Date date)
	{
		return dateTimeFormat.format(date);
	}

	/**
	 * 得到当前系统的时间 毫秒级 格式: HH:mm:ss SSS
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-18 - 下午9:44:10
	 */
	public static String getCurrentMillTimeStr()
	{
		return millTimeFormat.format(new Date(System.currentTimeMillis()));
	}
}
