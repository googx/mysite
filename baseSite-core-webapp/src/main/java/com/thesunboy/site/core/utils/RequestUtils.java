/**
 * 
 */
package com.thesunboy.site.core.utils;

import javax.servlet.http.HttpServletRequest;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.constant.Constant_RequestKey;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 关于request请求对象的相关工具类
 * 
 * @author hx940929
 * @CreateDate 2016-5-9 - 下午6:32:00
 * @ModifiedDate 2016-5-9 - 下午6:32:00
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class RequestUtils
{
	private final MyLogger logger = MyLogFactory.getLogger(RequestUtils.class, LogImplEnum.ConsoleLogImpl);

	/**
	 * 从request对象中获取请求的ipv4地址
	 * 
	 * @param request
	 * @return
	 * @Author hx940929
	 * @Date 2016-5-9 - 下午6:35:04
	 */
	public static String getRequestIPv4Addr(HttpServletRequest request)
	{

		Object object = request.getAttribute(Constant_RequestKey.RequestRealIpv4Key);
		if(object != null && object instanceof String) { return (String) object; }
		return getRequestIPv4AddrTemp(request);
	}

	public static String getRequestGatWay(HttpServletRequest request)
	{
		return null;

	}

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 获取用户请求头中的UserAgnet字段
	 * </p>
	 * <b> 注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param request
	 * @return
	 * @Author hx940929
	 * @Date 2017-3-16 - 上午10:59:26
	 */
	public static String getRequestUserAgent(HttpServletRequest request)
	{
		// 获取途径:1,head,2.cookie,3,attribute, 4sessionAttribute
		request.getAttribute("");
		String userAgent = null;
		request.getHeader("x-UserAgent");
		return userAgent;
	}

	private static String getRequestIPv4AddrTemp(HttpServletRequest request)
	{
		String realIp = request.getHeader("x-real-ip");
		// String proxyServer = request.getHeader("X-Forwarded-For");

		realIp = realIp == null || realIp.isEmpty() ? request.getRemoteAddr() : realIp;
		request.getSession().setAttribute(Constant_RequestKey.RequestRealIpv4Key, realIp);
		// request.setAttribute("proxyServerIp", proxyServer);
		return realIp;
	}

	public static String getRequestUrl(HttpServletRequest request)
	{
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	/**
	 * 从request对象中获取请求的mac地址,如果有,能获取到,没有则返回null
	 * 
	 * @param request
	 * @return
	 * @Author hx940929
	 * @Date 2016-5-9 - 下午6:35:04
	 */
	public static String getRequestMacAddr(HttpServletRequest request)
	{
		Object object = request.getAttribute(Constant_RequestKey.RequestRealMacKey);
		if(object != null && object instanceof String) { return (String) object; }
		return null;
	}
}
