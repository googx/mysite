package com.thesunboy.utils.mylog.core;

import java.util.Map;

import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

public interface ILogFactory
{
	@SuppressWarnings("rawtypes")
	public MyLogger buildLoger(Class clazz, LogImplEnum ImplType);

	public void setLogConfig(LogConfiger logConfig);

	public Map<String, MyLogger> getCacheLogger();
}
