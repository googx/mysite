package com.thesunboy.commons.config;

import com.thesunboy.utils.mylog.client.SimpleLogFactoryImpl;
import com.thesunboy.utils.mylog.core.ILogConfig;
import com.thesunboy.utils.mylog.core.ILogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

public class MyLogFactory
{
	/**
	 * 后期此日志对象模块应该交由spring的自定义生命周期管理组件管理,前期就先这样用了
	 */
	private static final SimpleLogFactoryImpl logFactory;

	static
	{
		ILogConfig config = new MyLoggerConfig();// 创建日志的config的配置文件
		logFactory = new SimpleLogFactoryImpl(config);
	}

	@SuppressWarnings("rawtypes")
	public static MyLogger getLogger(Class clazz, LogImplEnum impl)
	{
		return logFactory.buildLoger(clazz, impl);
	}

	public static ILogFactory getMyLogFactory()
	{
		return logFactory;
	}
}
