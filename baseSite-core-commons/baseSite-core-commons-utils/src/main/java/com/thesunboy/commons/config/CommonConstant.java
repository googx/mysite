/**
 * 
 */
package com.thesunboy.commons.config;

import com.thesunboy.utils.mylog.client.SimpleLogFactoryImpl;
import com.thesunboy.utils.mylog.core.ILogConfig;
import com.thesunboy.utils.mylog.core.ILogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;
// import com.thesunboy.site.core.commons.Utils.
// SpringApplicationContext;

/**
 * 
 * @author hx940929
 * @CreateDate 2016-3-13 - 下午4:17:16
 * @ModifiedDate 2016-3-13 - 下午4:17:16
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
@Deprecated
public class CommonConstant
{

	/**
	 * 网站 域名
	 */
	public static final String SiteDomain;

	public static final String ResourceDomain;

	/**
	 * 这个app是用的二级域名
	 */
	public static final String AppDomain = "expenseInfoApp";
	/**
	 * http连接服务器的通讯端口
	 */
	public static final String AppDomainHTTP_Port = "80";


	/**
	 * 后期此日志对象模块应该交由spring的自定义生命周期管理组件管理,前期就先这样用了
	 */
	private static final SimpleLogFactoryImpl logFactory;
	// private static ApplicationContext applicationContext_IOC;
	// private static final IWeChatAccountInfo weixinConfig;

	static
	{
		boolean debug = false;
		if(debug)
		{
			boolean onlineDebug = false;
			if(onlineDebug)
			{
				SiteDomain = "http://dvpwww.thesunboy.com";// 在线调试路径,走
				ResourceDomain = "http://dvpresources.thesunboy.com";
			}
			else
			{
				SiteDomain = "http://127.0.0.1:8080";// 本机路径
				ResourceDomain = "http://127.0.0.1:8080";
			}
		}
		else
		{
			// 正式发布路径
			SiteDomain = "http://www.thesunboy.com";
			ResourceDomain = "http://rescdn.thesunboy.com";// 非动态资源文件走cdn
		}

		ILogConfig config = new MyLoggerConfig();// 创建日志的config的配置文件
		logFactory = new SimpleLogFactoryImpl(config);

		// applicationContext_IOC =
		// SpringApplicationContext.getApplicationContext();
		// logger.info("Spring上下文对象: " + applicationContext_IOC);
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

	// public static ApplicationContext getContext()
	// {
	// return applicationContext_IOC;
	// }

}
