package com.thesunboy.site.core;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.filter.DelegatingFilterProxy;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class WebAppInitializer implements ServletContainerInitializer
{
	private final MyLogger logger = MyLogFactory.getLogger(WebAppInitializer.class, LogImplEnum.ConsoleLogImpl);

	private final Set<Class<? extends ServletContextListener>> webAppListeners = new HashSet<Class<? extends ServletContextListener>>();
	private final Set<javax.servlet.Filter> webAppFilters = new HashSet<javax.servlet.Filter>();
	private final boolean isAsync_supported = true;
	private ServletContext mServletContext;

	private void InitListeners()
	{
		mServletContext.setInitParameter("contextConfigLocation", "classpath*:/config/spring/applicationContext.xml");
		Set<SessionTrackingMode> sessionTrackingModes = new HashSet<>();
		// 追踪session模式,作用应该是服务器从客户端读取sessionid的有效途径
		sessionTrackingModes.add(SessionTrackingMode.COOKIE);// cookie中的JSESSIONID字段吧
		sessionTrackingModes.add(SessionTrackingMode.URL);// url参数中的JSESSIONID
		// sessionTrackingModes.add(SessionTrackingMode.SSL);
		mServletContext.setSessionTrackingModes(sessionTrackingModes);
		mServletContext.addListener(org.springframework.web.util.IntrospectorCleanupListener.class);
		mServletContext.addListener(org.springframework.web.context.ContextLoaderListener.class);
		mServletContext.addListener(org.springframework.web.util.Log4jConfigListener.class);
		DelegatingFilterProxy springFilterProxy = new DelegatingFilterProxy();
		// springSecurityFilterChain,//url-pattern: /*
		Dynamic filterDynamic = mServletContext.addFilter("springFilterProxy-security", springFilterProxy);
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
		filterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

	}

	private void putFilter(Filter filter, String... urlPatterns)
	{
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
		Dynamic filterDynamic = mServletContext.addFilter("", filter);
		filterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, urlPatterns);
		filterDynamic.setAsyncSupported(this.isAsync_supported);
		this.webAppFilters.add(filter);
	}

	private void putListener(Class<? extends ServletContextListener> listener)
	{
		mServletContext.addListener(listener);
		this.webAppListeners.add(listener);
	}

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext context) throws ServletException
	{
		this.mServletContext = context;
		this.InitListeners();
	}

}
