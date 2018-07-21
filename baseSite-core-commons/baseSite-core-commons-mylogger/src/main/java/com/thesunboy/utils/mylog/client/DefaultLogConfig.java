package com.thesunboy.utils.mylog.client;

import java.util.HashMap;
import java.util.Map;

import com.thesunboy.utils.mylog.core.ILogConfig;
import com.thesunboy.utils.mylog.core.LogConfiger;

/**
 * 日志模块的常量配置，由客户端实现logconfig接口
 * 
 * @author hx940929
 * @CreateDate 2016年2月11日 - 下午3:55:36
 * @ModifiedDate 2016年2月11日 - 下午3:55:36
 * @Encoding UTF-8
 * @Version 1.0
 * @Site www.thesunboy.com
 * @QQ 836845967
 */
public class DefaultLogConfig implements ILogConfig
{

	private Map<String, Object> resourcesParams = null;

	public DefaultLogConfig(Map<String, Object> resources)
	{
		this.resourcesParams = resources;
	}

	// private static final int logTag_test = ""; // string.logTag_test;
	// private static final int logTag_App = ""; // string.logTag_App;
	// private static final int logTag_Debug = ""; // string.logTag_Debug;
	// private static final int logTag_LogInfo = ""; // string.logTag_LogInfo;
	// private static final int logTag_LogWarn = ""; // string.logTag_LogWarn;
	// private static final int logTag_LogError = ""; // string.logTag_LogError;

	/**
	 * 配置日志打印的总开关,注意，此方法总是不应该被外部调用，该方法将总是由模块内部调用，简而言之，该方法不可调用<br/>
	 * 配置来源可能是多个,优先级:
	 * 开发时的debug(优先级最高,一般为map常量,发布时应该删除)>intnet(应用管理员配置)>本地数据库(用户本地配置
	 * )>安卓本地resouce文件(默认配置)<br/>
	 * 虽然配置来源具有优先级,但是配置的基础是必须以原有数据的基础进行更新操作,所以必须先取出原有的数据才能进行更新<br/>
	 */
	@Deprecated
	@Override
	public LogEnableItem setLogConfig(LogEnableItem enableItem)
	{
		if(resourcesParams != null && resourcesParams.size() > 0)
		{
			if(resourcesParams.containsKey("Debug_MyLog"))// 远程主机配置
			{

			}

			if(resourcesParams.containsKey("RemoteSite_MyLog"))// 远程主机配置
			{
				// 先取出原有数据更新,在保存,参考mvc实现中的保存dao的方式
			}

			if(resourcesParams.containsKey("LocalSqlite_MyLog"))// 本地数据库
			{

			}

			if(resourcesParams.containsKey("AndroidResouce_MyLog"))
			{
				// Object androidResouce =
				// resourcesParams.get("AndroidResouce_MyLog");
				// if(androidResouce != null && androidResouce instanceof
				// Resources) { return LogConfigFromResource(
				// (Resources) androidResouce, enableItem); }
			}
		}// 如果传入的为null.则使用本地properties的默认配置,如果还是为空.则使用该枚举中定义的默认配置
		else
		{
			// 这里理应使用properties配置,但是此处为直接写
			// true为默认,和对应的tag不填 都是默认的
			// enableItem.Debug.setValue(true);
			// enableItem.Debug.setTag("Debug");
			//
			// enableItem.LogInfo.setTag("Info");
			// enableItem.LogInfo.setValue(false);
			// enableItem.Debug.setValue(false);
			// enableItem.LogWarn.setValue(false);
			//
			// enableItem.LogWarn.setTag("Warn");
			// enableItem.LogWarn.setValue(true);
			//
			// enableItem.LogError.setTag("Error");
			// enableItem.LogError.setValue(true);
		}
		return enableItem;
	}

	/**
	 * 当配置日志的数据源来自于安卓本地的Resouce配置文件时
	 * 
	 * @param resource
	 * @param enableItem
	 * @return
	 * @author hx940929
	 * @Date 2016年2月20日 - 下午9:25:02
	 */
	@SuppressWarnings("static-access")
	// private LogEnableItem LogConfigFromResource(Resources resource,
	// LogEnableItem enableItem)
	// {
	// enableItem.All_Log_Enable.setValue(resource.getBoolean(bool.All_Log_Enable));
	// enableItem.All_Log_File_Enable.setValue(resource.getBoolean(bool.All_Log_File_Enable));
	// enableItem.All_Log_Http_Enable.setValue(resource.getBoolean(bool.All_Log_Http_Enable));
	// enableItem.All_Log_localDataBase_Enable.setValue(resource
	// .getBoolean(bool.All_Log_localDataBase_Enable));
	// enableItem.All_Log_PrintConsole_Enable.setValue(resource
	// .getBoolean(bool.All_Log_PrintConsole_Enable));
	// enableItem.Debug.setValue(resource.getBoolean(bool.Debug));
	// enableItem.Debug.setTag(resource.getString(logTag_Debug));
	//
	// enableItem.LogError.setValue(resource.getBoolean(bool.LogError));
	// enableItem.LogError.setTag(resource.getString(logTag_LogError));
	//
	// enableItem.LogInfo.setValue(resource.getBoolean(bool.LogInfo));
	// enableItem.LogInfo.setTag(resource.getString(logTag_LogInfo));
	//
	// enableItem.LogWarn.setValue(resource.getBoolean(bool.LogWarn));
	// enableItem.LogWarn.setTag(resource.getString(logTag_LogWarn));
	// return enableItem;
	// }
	/**
	 * 附加配置
	 */
	@Override
	public Map<String, Object> getDetailParamsConfig()
	{
		if(resourcesParams == null)
		{
			resourcesParams = new HashMap<String, Object>(1);
		}

		resourcesParams.put(LogConfiger.localEnviConsoleTag,
				DefaultHandle.class);
		// return null;
		return resourcesParams;
	}
}
