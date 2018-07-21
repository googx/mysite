/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.Proxy;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b><p>
 *一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-8-28 - 下午12:26:27 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
@Proxy(name = "ProxyObject")
public class ProxyObject implements ProxyInterface
{
	private final MyLogger logger = MyLogFactory.getLogger(ProxyObject.class, LogImplEnum.ConsoleLogImpl);

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param a
	 * @param b
	 * @Author hx940929
	 * @Date 2017-8-28 - 下午12:33:39
	 */
	@Override
	public void add(int a, int b)
	{
		// TODO hx940929 by 下午12:33:39
		logger.warn("ProxyInterface=>add()=>暂未实现");

	}
}
