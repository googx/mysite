/**
 * 
 */
package com.thesunboy.utils.mylog.core;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-5-5 - 下午7:29:17
 * @ModifiedDate 2016-5-5 - 下午7:29:17
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class LocalFileLogImpl extends AControlerLog
{

	@SuppressWarnings("rawtypes")
	public LocalFileLogImpl(Class clazz, LogConfiger configer)
	{
		super(configer);

	}

	/**
	 * 
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	public void destroy()
	{

	}

	/**
	 * 
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	public void lazyInit()
	{

	}

	/**
	 * @param tag
	 * @param text
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	protected void i(String tag, Object text)
	{

	}

	/**
	 * @param tag
	 * @param text
	 * @param exception
	 * @param classT
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	protected void e(String tag, Object text, Exception exception,
			Class<? extends Exception> classT)
	{

	}

	/**
	 * @param tag
	 * @param text
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	protected void w(String tag, Object text)
	{

	}

	/**
	 * @param tag
	 * @param text
	 * @Date 2016-5-5 - 下午7:29:40
	 */
	@Override
	protected void d(String tag, Object text)
	{

	}

}
