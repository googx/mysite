package com.thesunboy.utils.mylog.core;

import java.util.Map;

/**
 * log模块的配置基础接口,该接口一般用于模块的初始化时,后续还将扩充接口,以便在程序运行时也能对配置的更新,而不用重新启动应用,
 * 比如来自网络或者用户的操作的更新
 * 
 * @author hx940929
 * @CreateDate 2016年2月20日 - 下午8:20:58
 * @ModifiedDate 2016年2月20日 - 下午8:20:58
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public interface ILogConfig
{

	enum LogEnableItem
	{
		/**
		 * 所有日志的总开关
		 */
		All_Log_Enable
		{
			private boolean value = true;
			private String t = null;

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * 打印到环境控制台
		 */
		All_Log_PrintConsole_Enable
		{
			private String t = null;

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			private boolean value = true;

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * 插入到本地数据库中（实现类实现）
		 */
		All_Log_localDataBase_Enable
		{
			private boolean value = true;
			private String t = null;

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * 上传至服务器中（实现类实现）
		 */
		All_Log_Http_Enable
		{
			private boolean value = true;
			private String t = null;

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * 以io流的方式记录到本地文件中（实现类实现）
		 */
		All_Log_File_Enable
		{
			private boolean value = true;
			private String t = null;

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * debug模式，tag标签
		 */
		Debug
		{
			private boolean value = true;
			private String t = "Debug";

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * tag 标签
		 */
		LogInfo
		{
			private boolean value = true;
			private String t = "info";

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * tag 标签
		 */
		LogWarn
		{
			private boolean value = true;
			private String t = "Warn";

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		},
		/**
		 * tag 标签
		 */
		LogError
		{
			private boolean value = true;

			private String t = "Error";

			@Override
			public void setTag(String tag)
			{
				t = tag;
			}

			@Override
			public String getTag()
			{
				return t;
			}

			@Override
			public boolean getValue()
			{
				return value;
			}

			@Override
			public LogEnableItem setValue(boolean value)
			{
				this.value = value;
				return this;
			}
		};
		public abstract boolean getValue();

		public abstract void setTag(String tag);

		public abstract String getTag();

		public abstract LogEnableItem setValue(boolean value);
	}

	/**
	 * 用户必须实现该接口以达到配置logger日志模块，配置方法如：enableItem.All_Log_Enable.setValue(true);
	 * 
	 * @param enableItem
	 * @return
	 * @author hx940929
	 * @Date 2016年1月31日 - 下午11:18:17
	 */
	LogEnableItem setLogConfig(LogEnableItem enableItem);

	/**
	 * 用于日志模块更详细的配置,不影响基础功能
	 * 
	 * @return
	 * @author hx940929
	 * @Date 2016年2月21日 - 上午12:03:33
	 */
	Map<String, Object> getDetailParamsConfig();
}
