package com.thesunboy.utils.mylog.core;

import java.util.Map;

import com.thesunboy.utils.mylog.core.ILogConfig.LogEnableItem;

/**
 * 内部config对象,用于日志模块配置实用.内部都是取这个对象中的数据,IlogConfig接口是给用户实现的,用于配置一些参数.一般情况下,
 * 此LogConfiger对象在创建之后不建议销毁,应该由创建者维护,以便重复使用
 * 把配置和.实现分离开.一些具体实现的对象也,放在这里.比如IlocalEnviConsole对象 这里用来解析这些东西
 * 
 * @author hx940929
 * @CreateDate 2016-3-17 - 下午4:33:19
 * @ModifiedDate 2016-3-17 - 下午4:33:19
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public class LogConfiger
{
	private final ILogConfig mConfig;
	private final ILocalEnviConsole console;
	private final LogEnableItem mEnable;
	private final Map<String, Object> paramsConfig;

	public static final String localEnviConsoleTag = "ILocalEnviConsoleImpl";

	public LogConfiger(ILogConfig config)
	{
		if(config != null)
		{
			this.mConfig = config;
			this.mEnable = mConfig
					.setLogConfig(ILogConfig.LogEnableItem.All_Log_Enable
							.setValue(true));
			this.paramsConfig = mConfig.getDetailParamsConfig();
			this.console = this.createConsole();
		}
		else
		{
			this.mConfig = null;
			this.mEnable = null;
			this.paramsConfig = null;
			this.console = null;
			throw new NullPointerException("ILogConfig Is not be allow null");
		}

	}

	/**
	 * 从配置文件中读取接口的实现类的名字,或者是class对象并 以反射的方式创建ILocalEnviConsole 接口的实例对象
	 * 
	 * @return
	 * @author hx940929
	 * @Date 2016年2月20日 - 下午10:23:28
	 */
	@SuppressWarnings("unchecked")
	private ILocalEnviConsole createConsole()
	{
		if(paramsConfig != null && paramsConfig.size() > 0
				&& paramsConfig.containsKey(localEnviConsoleTag))
		{// 如果配置了该实例,却找不到具体的实现类,则抛异常,如果没有配置实现类,则打印警告信息
			Object t = paramsConfig.get(localEnviConsoleTag);
			try
			{
				if(t != null && t instanceof Class)
				{
					return (ILocalEnviConsole) ((Class<ILocalEnviConsole>) t)
							.newInstance();
				}
				else if(t != null && t instanceof String)
				{
					return (ILocalEnviConsole) Class.forName((String) t)
							.newInstance();
				}
				else
				{
					throw new IllegalArgumentException(
							"获取ILocalEnviConsole接口的实现类名错误,应该传入该接口的实现类名,创建实例类失败");
				}
			}
			catch (InstantiationException e)
			{
				throw new RuntimeException(
						"实现ILocalEnviConsole类失败,InstantiationException", e);
			}
			catch (IllegalAccessException e)
			{
				throw new RuntimeException(
						"ILocalEnviConsole接口实现未有公开public的构造方法,即构造方法为private,访问级别不够",
						e);
			}
			catch (ClassNotFoundException e)
			{
				throw new RuntimeException(
						"找不到该实现ILocalEnviConsole类,请确认配置文件所配置的实现类名是否正确", e);
			}
		}
		else
		{
			System.err
					.println("警告: MyLog.jar==>ConsoleLogImpl.class==>private ILocalEnviConsole getConsole()::=>  未配置ILocalEnviConsole接口的实现名 key值为:ILocalEnviConsoleImpl,请在AControlerLog的paramsConfig参数中配置");
		}
		return null;
	}

	public Map<String, Object> getParamsConfig()
	{
		return paramsConfig;// 这里应该不能直接返回这个实例引用,应该复制一个一样数据的对象.不可以使用原来的引用,不能让外面修改此对象
	}

	public ILocalEnviConsole getLocalEnviConsole()
	{
		return this.console;
	}

	public LogEnableItem getEnable()
	{
		return mEnable;
	}
}
