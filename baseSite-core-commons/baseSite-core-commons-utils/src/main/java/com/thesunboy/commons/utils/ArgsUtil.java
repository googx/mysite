package com.thesunboy.commons.utils;

import java.util.Collection;

/**
 * 该类用于在方法入参时,进行参数的非法校验,来自于apache的http包</br>
 * 以后要重构此类,抛出的异常都必须是自定义的异常,以便于进行向上级调用着给予一定的反馈信息
 * ,用于诊断判断的类型.如果是直接返回给客户端的,可以在加上一些json返回值等.以后参数的检查用继承的方式在封装一个util
 * 
 * @author hx940929
 * @CreateDate 2016-2-24 - 下午10:05:50
 * @ModifiedDate 2016-2-24 - 下午10:05:50
 * @Encoding UTF-8
 * @Version 1.0
 * @Site www.thesunboy.com
 * @QQ 836845967
 */
public class ArgsUtil
{

	public static void check(final boolean expression, final String message)
	{
		if(!expression) { throw new IllegalArgumentException(message); }
	}

	public static void check(final boolean expression, final String message, final Object... args)
	{
		if(!expression) { throw new IllegalArgumentException(String.format(message, args)); }
	}

	public static void check(final boolean expression, final String message, final Object arg)
	{
		if(!expression) { throw new IllegalArgumentException(String.format(message, arg)); }
	}

	public static <T> T notNull(final T argument, final String name)
	{
		if(argument == null) { throw new IllegalArgumentException(name + " may not be null"); }
		return argument;
	}

	public static <T extends CharSequence> T notEmpty(final T argument, final String name)
	{
		if(argument == null) { throw new IllegalArgumentException(name + " may not be null"); }
		if(TextUtil.isEmpty(argument)) { throw new IllegalArgumentException(name + " may not be empty"); }
		return argument;
	}

	public static <T extends CharSequence> T notBlank(final T argument, final String name)
	{
		if(argument == null) { throw new IllegalArgumentException(name + " may not be null"); }
		if(TextUtil.isBlank(argument)) { throw new IllegalArgumentException(name + " may not be blank"); }
		return argument;
	}

	public static <T extends CharSequence> T containsNoBlanks(final T argument, final String name)
	{
		if(argument == null) { throw new IllegalArgumentException(name + " may not be null"); }
		if(TextUtil.containsBlanks(argument)) { throw new IllegalArgumentException(name + " may not contain blanks"); }
		return argument;
	}

	public static <E, T extends Collection<E>> T notEmptyCollection(final T argument, final String name)
	{
		if(argument == null) { throw new IllegalArgumentException(name + " may not be null"); }
		if(argument.isEmpty()) { throw new IllegalArgumentException(name + " may not be empty"); }
		return argument;
	}

	public static int positive(final int n, final String name)
	{
		if(n <= 0) { throw new IllegalArgumentException(name + " may not be negative or zero"); }
		return n;
	}

	public static long positive(final long n, final String name)
	{
		if(n <= 0) { throw new IllegalArgumentException(name + " may not be negative or zero"); }
		return n;
	}

	public static int notNegative(final int n, final String name)
	{
		if(n < 0) { throw new IllegalArgumentException(name + " may not be negative"); }
		return n;
	}

	public static long notNegative(final long n, final String name)
	{
		if(n < 0) { throw new IllegalArgumentException(name + " may not be negative"); }
		return n;
	}

}
