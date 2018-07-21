/**
 * 
 */
package com.thesunboy.commons.config;

import com.thesunboy.utils.mylog.core.ILogConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Mylogger的配置
 * 
 * @author hx940929
 * @CreateDate 2016-5-20 - 下午8:32:34
 * @ModifiedDate 2016-5-20 - 下午8:32:34
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class MyLoggerConfig implements ILogConfig
{

	/**
	 * @param enableItem
	 * @return
	 * @Date 2016-5-20 - 下午8:32:49
	 */
	@Override
	public LogEnableItem setLogConfig(LogEnableItem enableItem)
	{
		return enableItem;
	}

	/**
	 * @return
	 * @Date 2016-5-20 - 下午8:32:49
	 */
	@Override
	public Map<String, Object> getDetailParamsConfig()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ILocalEnviConsoleImpl", MyLogConsoleHandle.class);
		return map;
	}
}
