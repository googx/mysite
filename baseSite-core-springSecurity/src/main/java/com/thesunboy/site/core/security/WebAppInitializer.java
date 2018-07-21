package com.thesunboy.site.core.security;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements ServletContainerInitializer
{
	/*
	 * <filter> <filter-name>springSecurityFilterChain</filter-name>
	 * <filter-class
	 * >org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	 * <async-supported>true</async-supported> </filter> <filter-mapping>
	 * <filter-name>springSecurityFilterChain</filter-name>
	 * <url-pattern>/*</url-pattern> </filter-mapping>
	 */
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext context) throws ServletException
	{
		context.log("onStartup=>我是springSecurity,动态向Servlet注册Security组件");
		context.log("onStartup=>添加listen和相关配置");
		Dynamic filterDynamic = context.addFilter("springSecurity",
				org.springframework.web.filter.DelegatingFilterProxy.class);

		EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
		filterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
	}
}
