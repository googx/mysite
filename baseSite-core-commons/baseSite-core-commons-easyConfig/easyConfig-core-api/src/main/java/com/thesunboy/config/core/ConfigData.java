/**
 * 
 */
package com.thesunboy.config.core;

import java.io.Serializable;

/**
 * 配置数据的接口,要使用自定义的配置信息,需要使用此接口进行传递数据,也就是说,此接口对象将会成为此模块的核心对象,因为此接口就是配置对象. </br>
 * <p>
 * 所有实现了该接口的自定义配置对象还需要实现ConfigDataParser自定义配置对象解析器,以便于达到将自定义的配置对象解析出并给调用者
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2016-6-22 - 上午11:38:58
 * @ModifiedDate 2016-6-22 - 上午11:38:58
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public interface ConfigData extends Serializable
{
	/**
	 * 返回配置值,实现类可以自由对返回值进行格式的设定,并通过设置配置数据解析器的方式,进行返回配置项数据,否则将无法识别
	 * 
	 * @return
	 * @Author hx940929
	 * @Date 2016-7-1 - 下午8:01:23
	 */
	public Object getValue();

}
