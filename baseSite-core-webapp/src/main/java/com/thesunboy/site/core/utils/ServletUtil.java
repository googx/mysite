/**
 * 
 */
package com.thesunboy.site.core.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.constant.Constant_HttpHead;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 以后使用ThreadLocal改写,因为每一次的request都是新线程
 * 
 * @author hx940929
 * @CreateDate 2016-3-30 - 下午1:02:12
 * @ModifiedDate 2016-3-30 - 下午1:02:12
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public class ServletUtil
{
	private static final MyLogger logger = MyLogFactory.getLogger(
			ServletUtil.class, LogImplEnum.ConsoleLogImpl);

	private static final String mAccessTokenKey = "";
	private static final String mAccessReferer = "";
	/**
	 * 打印错误信息的msg
	 * 
	 * @param request
	 * @param response
	 * @param jsonString
	 * @Author hx940929
	 * @Date 2016-4-30 - 下午4:57:47
	 */
	public static void printErrorMsg(HttpServletRequest request,
			HttpServletResponse response, String errorcode, String msg)
	{
		printPage(request, response, "{\"errmsg\":\"" + msg
				+ "\",\"errcode\":\"" + errorcode + "\"}");
	}

	public static void printPage(HttpServletRequest httpRequest,
 HttpServletResponse httpResponse, Object msg)
	{
		PrintWriter out = null;
		try
		{
			setNoClientCache(httpResponse);
			setContentType(httpResponse, Constant_HttpHead.ConType_TEXT_TYPE);
			out = httpResponse.getWriter();
			out.print(msg);
			out.flush();
		}
		catch (IOException e)
		{
			logger.error("", e, e.getClass());
		}
		finally
		{
			if(null != out)
			{
				out.close();
			}
		}
	}

	/**
	 * 响应头===设置游览器端禁止使用缓存
	 * 
	 * @param response
	 * @Author hx940929
	 * @Date 2016-3-30 - 下午1:06:18
	 */
	public static void setNoClientCache(HttpServletResponse response)
	{
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * 响应头===设置返回数据类型
	 * 
	 * @param response
	 * @Author hx940929
	 * @Date 2016-3-30 - 下午1:09:11
	 */
	public static void setContentType(HttpServletResponse response, String type)
	{
		response.setHeader("Content-type", type);
	}

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 获取请求随机token,从cookie中取出,或者head,或者request域中,禁止从请求参数中读取(防止csrf)
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param httpRequest
	 * @return
	 * @Author hx940929
	 * @Date 2017-3-13 - 下午10:07:47
	 */
	public static String getReqRandomAccessToken(HttpServletRequest httpRequest)
	{
		Object token = httpRequest.getAttribute(ServletUtil.mAccessTokenKey);
		if(token != null) { return (String) token; }
		return null;
	}

	public static String getReferer(HttpServletRequest httpRequest)
	{
		Object referer = httpRequest.getAttribute(ServletUtil.mAccessReferer);
		if(referer != null) { return (String) referer; }
		return null;
	}
}
