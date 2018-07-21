/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.Proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.utils.ReflectUtils;

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
 * @CreateDate 2017-8-28 - 上午9:32:39 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class DynamicProxy implements InvocationHandler
{
	private final static MyLogger logger = MyLogFactory.getLogger(DynamicProxy.class, LogImplEnum.ConsoleLogImpl);

	ProxyInterface interface1;

	ProxyInterface2 interface2;

	// @Autowired
	// @Qualifier(value = "ProxyObject2")
	ProxyInterface2 interface3332;

	public static void main(String[] args) throws IOException
	{
		logger.info("main()==>" + System.getProperty("maven.multiModuleProjectDirectory"));
		logger.info("main()==>" + System.setProperty("hx940929", "test"));
		logger.info("main()==>" + System.getProperty("hx940929"));

		SecurityManager custoManager = new SecurityManager();
		Object securityContext = custoManager.getSecurityContext();

		SecurityManager securityManage = System.getSecurityManager();
		new DynamicProxy().proxy();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void proxy()
	{
		List<Class> classes = ReflectUtils.getClasssFromPackage("com.thesunboy.test");
		List<Class> proxyClasses = new LinkedList<>();// need to be proxy
		for (Class item : classes)
		{
			if(item.isAnnotationPresent(Proxy.class))
			{
				proxyClasses.add(item);
			}
		}
		Class[] array_proxyClasses = proxyClasses.toArray(new Class[] { null });
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		Map<Class, Object> proxyMap = new HashMap<Class, Object>(array_proxyClasses.length);
		for (Class item : array_proxyClasses)
		{
			Class[] proxyInterface = item.getInterfaces();
			Object proxyObject = java.lang.reflect.Proxy.newProxyInstance(currentClassLoader, proxyInterface, this);
			for (Class class1 : proxyInterface)
			{
				if(proxyMap.containsKey(class1))// 有两个不同的对象实例实现了相同的接口,这个时候map的key应该同时包含注解的相关信息,应该以包装对象解决
				{

				}
				proxyMap.put(class1, proxyObject);
			}

		}

		// java.lang.reflect.Proxy.
		logger.info("main()==>end");

	}

	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 * @Author hx940929
	 * @Date 2017-8-28 - 下午12:23:20
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		// TODO hx940929 by 下午12:23:20
		logger.warn("InvocationHandler=>invoke()=>暂未实现");

		return null;
	}
}
