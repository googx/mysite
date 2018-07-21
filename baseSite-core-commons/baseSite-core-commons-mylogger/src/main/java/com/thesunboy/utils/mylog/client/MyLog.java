package com.thesunboy.utils.mylog.client;

import com.thesunboy.utils.mylog.core.ILogConfig;
import com.thesunboy.utils.mylog.core.ILogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 建议的Logger客户端实现
 * 
 * @author hx940929
 * @CreateDate 2016-3-13 - 下午4:17:16
 * @ModifiedDate 2016-3-13 - 下午4:17:16
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public class MyLog
{

	/**
	 * 应用程序的全局编码格式s
	 */
	public static final String Encoding = "UTF-8";

	private static final SimpleLogFactoryImpl logFactory;

	static
	{
		ILogConfig config = new DefaultLogConfig(null);// 创建日志的config的配置文件
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
