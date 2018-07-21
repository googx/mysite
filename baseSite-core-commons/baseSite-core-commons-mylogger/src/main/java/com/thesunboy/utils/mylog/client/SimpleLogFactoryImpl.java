package com.thesunboy.utils.mylog.client;

import java.util.HashMap;
import java.util.Map;

import com.thesunboy.utils.mylog.core.ConsoleLogImpl;
import com.thesunboy.utils.mylog.core.ILogConfig;
import com.thesunboy.utils.mylog.core.ILogFactory;
import com.thesunboy.utils.mylog.core.LocalDBLogImpl;
import com.thesunboy.utils.mylog.core.LocalFileLogImpl;
import com.thesunboy.utils.mylog.core.LogConfiger;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 应用层。（由客户端实现。比如Android，se，ee）提供统一的接口用于将客户端环境的数据配置等转换成模块可以使用的
 * 3-7号更新,不能由客户端实现,应该在建立一个简单的静态工厂类,参数为class.内部把class的类名传进来,可以参考slf4j的实现.
 * 这样客户端就可以在任何地方不需要创建对象的情况下返回一个新的logger对象
 * 
 * @author hx940929
 * @CreateDate 2016年2月11日 - 下午5:19:30
 * @ModifiedDate 2016年2月11日 - 下午5:19:30
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public class SimpleLogFactoryImpl implements ILogFactory
{
	private LogConfiger mConfig = null;
	private static Map<String, MyLogger> mCaches = new HashMap<String, MyLogger>(
			50);

	public SimpleLogFactoryImpl(ILogConfig logConfig)
	{
		if(logConfig != null)
		{
			mConfig = new LogConfiger(logConfig);
		}
	}

	/**
	 * 以默认的
	 */
	public SimpleLogFactoryImpl()
	{
		mConfig = new LogConfiger(new DefaultLogConfig(null));
	}

	@Override
	public void setLogConfig(LogConfiger logConfig)
	{
		this.mConfig = logConfig;
	}

	public LogConfiger getLogConfig()
	{
		return this.mConfig;
	}

	/**
	 * 创建一个log实例,并放置到cache中,以便以后进行日志管理器对每一个日志对象的维护
	 * 
	 * @param clazz
	 * @param ImplType
	 *            要创建的日志对象的类型
	 * @return
	 * @Date 2016-3-18 - 下午12:17:26
	 */
	@Override
	public MyLogger buildLoger(Class clazz, LogImplEnum ImplType)
	{
		if(clazz != null && ImplType != null)
		{
			MyLogger logger = getLogerFromCache(clazz.getName()
					+ ImplType.getTypeCode());// 从缓存中取
			if(logger == null)
			{
				switch (ImplType)
					{
						case ConsoleLogImpl:
							logger = new ConsoleLogImpl(clazz, getLogConfig());
							break;

						case LocalDBLogImpl:
							logger = new LocalDBLogImpl(clazz, getLogConfig());
							break;

						case LocalFileLogImpl:
							logger = new LocalFileLogImpl(clazz, getLogConfig());
							break;

						case RemoteHttpLogImpl:
							logger = new LocalDBLogImpl(clazz, getLogConfig());
							break;

						default:
							// 使用控制台创建的.并打印一条警告消息
							break;
					}
				if(logger != null)
				{
					putCache(clazz.getName() + ImplType.getTypeCode(), logger);// 设置到缓存中
				}
			}
			return logger;
		}
		else
		{
			System.err.println("创建Logger对象失败.LogImplEnum 或者 Class对象不能为空");
			return null;
		}

	}

	/**
	 * 从缓存中取得 log实例,如果没有,在创建一个
	 * 
	 * @param key
	 * @return
	 * @Author hx940929
	 * @Date 2016-3-17 - 下午9:30:32
	 */
	private static MyLogger getLogerFromCache(String key)
	{
		if(key != null)
		{
			synchronized (mCaches)
			{
				mCaches.get(key);
			}
		}
		return null;
	}

	/**
	 * 将每一个class请求得到的log实例,放入缓存中,以后以观察者模式,对该缓存进行维护
	 * 
	 * @param key
	 * @param logger
	 * @Author hx940929
	 * @Date 2016-3-17 - 下午5:06:36
	 */
	private static void putCache(String key, MyLogger logger)
	{
		if(key != null && logger != null)
		{
			synchronized (mCaches)
			{
				mCaches.put(key, logger);
			}
		}
	}

	/**
	 * 返回全局所引用的logger集合
	 * 
	 * @return
	 * @Date 2016-3-24 - 下午8:10:48
	 */
	@Override
	public Map<String, MyLogger> getCacheLogger()
	{
		// Map<String, MyLogger> copy = new HashMap<String, MyLogger>();
		// copy.putAll(mCaches);
		return mCaches;
	}

}
