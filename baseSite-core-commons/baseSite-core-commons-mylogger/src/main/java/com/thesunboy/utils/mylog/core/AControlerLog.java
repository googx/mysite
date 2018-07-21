package com.thesunboy.utils.mylog.core;

import java.util.Map;

import com.thesunboy.utils.mylog.core.ILogConfig.LogEnableItem;

/**
 * 第一个版本先只是在控制台上打印就好了。后期在考虑持久化保存的问题,
 * 本来想把各个不同类型的日志打印用接口实现类的方式拆分出来。不过考虑到手机app性能上的问题。就不创建更多的对象了。
 * 尽量让这个对象做到全局唯一。只需要一个对象。不过会有线程的安全问题。明天考虑用map缓存的方式
 * 先把各个不同的线程分离开来。用线程名来区分各个日志的输出。如果要分线程的话。就不能做到全局唯一对象了。
 * 如果是这样的话。还是要继续开发原来写的那个日志管理了
 * 不过是要把这个和那个整合在一起。要注意解耦。不管是app还是web或者javase都能做到只需要配置，不需要修改就能使用
 * 要把内容和格式分离开。能让用户自定义输出格式。
 * 
 * @author hx940929
 * @CreateDate 2016年1月22日 - 下午9:23:10
 * @ModifiedDate 2016年1月22日 - 下午9:23:10
 * @Encoding UTF-8
 * @Version 1.0
 * @QQ 836845967
 */
public abstract class AControlerLog implements MyLogger, ILogLifecycle
{
	protected LogEnableItem cfg_enable;
	private boolean All_Log_Enable = true;
	private boolean All_Log_PrintConsole_Enable = true;

	private boolean All_Log_localDataBase_Enable = true;
	private boolean All_Log_Http_Enable = true;
	private boolean All_Log_File_Enable = true;

	private boolean Debug = true;
	private boolean LogInfo = true;
	private boolean LogWarn = true;
	private boolean LogError = true;
	private String tag_info = null;
	private String tag_warn = null;
	private String tag_error = null;
	private String tag_debug = null;
	protected Map<String, Object> paramsConfig = null;

	protected AControlerLog(LogConfiger config)
	{
		loadParamsConfig(config);
		loadEnableConfig();// 从配置文件中读取变量配置
		lazyInit();// 由子类实现,在所有的参数都加载好后,子类在进行初始化修改
	}

	private void loadParamsConfig(LogConfiger config)
	{
		if(config != null)
		{
			paramsConfig = config.getParamsConfig();
			cfg_enable = config.getEnable();
		}
		else
		{
			throw new NullPointerException("LogConfig参数为空,必须指定该参数");
		}
	}

	/**
	 * 初始化日志开关状态
	 * 
	 * @Date 2016年1月23日 - 下午5:03:51
	 */
	@SuppressWarnings("static-access")
	private void loadEnableConfig()
	{
		if(cfg_enable == null) { throw new IllegalArgumentException(
				"请在子类中实现logconfig对象的设置"); }

		All_Log_Enable = cfg_enable.All_Log_Enable.getValue();
		All_Log_File_Enable = cfg_enable.All_Log_File_Enable.getValue();
		All_Log_Http_Enable = cfg_enable.All_Log_Http_Enable.getValue();
		All_Log_localDataBase_Enable = cfg_enable.All_Log_localDataBase_Enable
				.getValue();
		All_Log_PrintConsole_Enable = cfg_enable.All_Log_PrintConsole_Enable
				.getValue();

		LogInfo = cfg_enable.LogInfo.getValue();
		tag_info = cfg_enable.LogInfo.getTag();

		LogWarn = cfg_enable.LogWarn.getValue();
		tag_warn = cfg_enable.LogWarn.getTag();

		LogError = cfg_enable.LogError.getValue();
		tag_error = cfg_enable.LogError.getTag();

		Debug = cfg_enable.Debug.getValue();
		tag_debug = cfg_enable.Debug.getTag();

	}

	@Override
	public final void info(Object text)
	{
		if(All_Log_Enable && LogInfo)
		{
			i(tag_info, text);
		}
	}

	protected abstract void i(String tag, Object text);

	protected abstract void e(String tag, Object text, Exception exception,
			Class<? extends Exception> classT);

	protected abstract void w(String tag, Object text);

	protected abstract void d(String tag, Object text);

	@Override
	protected final void finalize() throws Throwable
	{
		destroy();
		super.finalize();
	}

	@Override
	public final void warn(Object text)
	{
		if(All_Log_Enable && LogWarn)
		{
			w(tag_warn, text);
		}
	}

	@Override
	public final void debug(Object text)
	{
		if(All_Log_Enable && Debug)
		{
			d(tag_debug, text);
		}
	}

	/**
	 * @param text
	 * @param exception
	 * @param classT
	 * @Date 2016-3-17 - 下午3:34:24
	 */
	@Override
	public final void error(Object text, Exception exception,
			Class<? extends Exception> classT)
	{

		if(All_Log_Enable && LogError)
		{
			e(tag_error, text, exception, classT);
		}

	}

	/**
	 * @return the all_Log_PrintConsole_Enable
	 */
	public boolean isAll_Log_PrintConsole_Enable()
	{
		return All_Log_PrintConsole_Enable;
	}

	/**
	 * @return the all_Log_localDataBase_Enable
	 */
	public boolean isAll_Log_localDataBase_Enable()
	{
		return All_Log_localDataBase_Enable;
	}

	/**
	 * @return the all_Log_Http_Enable
	 */
	public boolean isAll_Log_Http_Enable()
	{
		return All_Log_Http_Enable;
	}

	/**
	 * @return the all_Log_File_Enable
	 */
	public boolean isAll_Log_File_Enable()
	{
		return All_Log_File_Enable;
	}

	/**
	 * @return
	 * @Date 2016-3-18 - 下午9:29:52
	 */
	@Override
	public boolean isDebug()
	{
		return All_Log_Enable && Debug;
	}
}
