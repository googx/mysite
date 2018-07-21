/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import jline.Terminal;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b>
 * <p>
 * 从控制台 内容操作类-阻塞
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 一些说明写这里
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * 一些说明写这里
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2017-7-12 - 下午8:26:20
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class ConsoleUtils
{
	private final MyLogger logger = MyLogFactory.getLogger(ConsoleUtils.class, LogImplEnum.ConsoleLogImpl);
	{
		System.setProperty("jline.WindowsTerminal.directConsole", "false");
	}
	
	Terminal terminal = Terminal.getTerminal();

	// TODO 待实现 参照http://blog.csdn.net/joe_007/article/details/8529480
	// ConsoleReader reader = new ConsoleReader();
	// String asdfString = reader.readLine(">");

	// 直接可以使用jline来跨平台读取和输出系统控制台
	public static String readLine(InputStream dataInputStream, String hint) throws IOException
	{
		// boolean nowretu = StringUtils.isNotBlank(endFlag) ? false : true;//
		// 没有结束标示符是否读取到内容立即返回
		// InputStream dataInputStream = System.in;
		BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));
		StringBuilder builder = new StringBuilder();
		String tem;
		while ((tem = reader.readLine()) != null)
		{
			builder.append(tem);// TODO 明天想想多个线程在竞争system.in的时候怎么办,是否能竞争到 .
								// read内置锁是针对read对象的,并不是针对System.in的
								// reader是线程安全的.但是System.in不是吧..
		}
		return builder.toString();
	}

	public static String readLine(InputStream dataInputStream, String endFlag, String hint, int timeout, TimeUnit timeUnit)
	{

		return null;
	}

	public static synchronized String readLine(String endFlag) throws IOException
	{
		return readLine(System.in, endFlag);
	}

	// public static byte[] read(InputStream in,int off,int len)
	// {
	// int avb = ConsoleUtils.StreamStatus_notInit;
	// try
	// {
	// avb = in != null ? in.available() : StreamStatus_notAvailable;
	// }
	// catch (IOException e)
	// {
	// avb = StreamStatus_notAvailable;
	// return null;
	// }
	//
	// }
}
