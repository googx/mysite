/**
 * 
 */
package com.thesunboy.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * 日期时间的工具类,由于SimpleDateFormat并不是线程安全的,所以此工具类基于一个非线程安全对象也是非线程安全的,
 * 如果在多线程环境调用需要客户端代码自行保证线程安全
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
	private static final SimpleDateFormat dateTimeFormat;
	private static final SimpleDateFormat timeFormat;
	private static final SimpleDateFormat millTimeFormat;
	private static final SimpleDateFormat dateFormat;
	static
	{
		// 从系统缓存中读取格式化字符串设置
		// String formatstr = "yyyy-MM-dd HH:mm:ss";
		dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		millTimeFormat = new SimpleDateFormat("HH:mm:ss SSS");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
			throw new RuntimeException(e.getCause());
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
	 * 
	 * 简要功能说明:
	 * <p>
	 * 得到当前系统的日期yyyy-MM-dd
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2017-3-5 - 下午2:13:13
	 */
	public static String getCurrentDateStr()
	{
		return dateFormat.format(new Date());
	}

	public static String getDateStr(Date date)
	{
		return dateFormat.format(date);
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
		return dateTimeFormat.format(new Date(timestamp));
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

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 使用yyyy-MM-dd HH:mm:ss格式进行解析字符串
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param timeStr
	 * @return
	 * @throws ParseException
	 * @Author hx940929
	 * @Date 2017-3-13 - 下午1:35:04
	 */
	public static Date parseDateTime(String timeStr) throws ParseException
	{
		return DateUtils.parseDate(timeStr, dateTimeFormat);
	}

	public static Date parseDate(String timeStr, SimpleDateFormat formate) throws ParseException
	{
		return dateTimeFormat.parse(timeStr);
	}
}
