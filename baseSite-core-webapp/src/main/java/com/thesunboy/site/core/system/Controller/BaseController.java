/**
 * 
 */
package com.thesunboy.site.core.system.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.constant.BaseSiteProject;
import com.thesunboy.commons.utils.ArgsUtil;
import com.thesunboy.site.core.utils.RequestUtils;
import com.thesunboy.site.core.utils.ServletUtil;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * 所有其他系统组件级的controller皆会继承 其他Controller继承自该基础Controller得到些通用功能
 * 
 * @author hx940929
 * @CreateDate 2016-3-24 - 下午3:10:45
 * @ModifiedDate 2016-3-24 - 下午3:10:45
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public abstract class BaseController
{
	private final MyLogger logger = MyLogFactory.getLogger(BaseController.class, LogImplEnum.ConsoleLogImpl);
	public final String Encoding = BaseSiteProject.Encoding;

	// 1.获取ip,mac
	public String getCurrentIpv4(HttpServletRequest request)
	{
		ArgsUtil.notNull(request, "HttpServletRequest");
		return RequestUtils.getRequestIPv4Addr(request);
	}


	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 根据配置,是否自动根据某些请求头.做web端和移动端,或者返回什么样的数据格式做处理.
	 * 是直接返回模型视图对象,还是返回处理结果的一个json数据,或者之前还是之后还需要做什么,增加一个事件回调接口
	 * </p>
	 * 注意事项: <b>
	 * 
	 * </b></br>
	 * 
	 * @param response
	 * @param httpRequest
	 * @param httpResponse
	 * @Author hx940929
	 * @Date 2016-8-13 - 上午12:39:54
	 */
	protected Object Response(ModelAndView response, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
	{
		// TODO 测试springMVC的视图解析器.能否返回一个自定义的对象.然后再专门的解析器中做解析处理
		// HandlerMethodReturnValueHandler使用这个接口进行自定义实现返回参数的类型解析
		return null;
	}

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * 注意事项: <b>
	 * 
	 * </b></br>
	 * 
	 * @param text
	 * @Author hx940929
	 * @Date 2016-8-21 - 下午10:54:29
	 */
	protected void printPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object msg)
	{
		ServletUtil.printPage(httpRequest, httpResponse, msg);
	}
}
