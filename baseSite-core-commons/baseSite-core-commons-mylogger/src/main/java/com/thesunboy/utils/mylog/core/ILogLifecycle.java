package com.thesunboy.utils.mylog.core;

/**
 * 生命周期接口,使用这个接口的功能将使得实例具有对象的销毁,和初始化功能
 * 
 * @author hx940929
 * @CreateDate 2016年2月20日 - 下午8:33:59
 * @ModifiedDate 2016年2月20日 - 下午8:33:59
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public interface ILogLifecycle
{
	void destroy();

	/**
	 * 此方法一般在构造函数之后执行
	 * 
	 * @author hx940929
	 * @Date 2016年2月20日 - 下午8:41:21
	 */
	abstract void lazyInit();
}
