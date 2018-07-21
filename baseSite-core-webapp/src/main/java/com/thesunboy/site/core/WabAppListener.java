package com.thesunboy.site.core;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.*;

public class WabAppListener extends GenericFilterBean implements ServletContextListener
{
	private final MyLogger logger = MyLogFactory.getLogger(WabAppListener.class, LogImplEnum.ConsoleLogImpl);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		// TODO hx940929 by 下午3:26:15
		logger.warn("Filter=>doFilter()=>该方法暂未实现");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		ServletContext context = sce.getServletContext();
		Map<String, ? extends FilterRegistration> filets = context.getFilterRegistrations();
		for (String filterItem : filets.keySet())
		{
			FilterRegistration filter = filets.get(filterItem);
			logger.info("contextInitialized()==>加载filter,name[" + filterItem + "], filter: " + filter.toString());
		}

		logger.info("contextInitialized()==>Context已加载完成");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		// TODO hx940929 by 下午3:26:15
		logger.warn("ServletContextListener=>contextDestroyed()=>该方法暂未实现");

	}
}
