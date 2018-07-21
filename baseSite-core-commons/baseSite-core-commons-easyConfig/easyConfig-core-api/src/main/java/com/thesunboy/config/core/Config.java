/**
 * 
 */
package com.thesunboy.config.core;

/**
 * 开始重新从头设计一个配置组件,结合装饰者模式
 * ,配置数据的来源可能来自不同的地方,因此装饰类,可能就是具体的数据来源修饰,比如本地配置文件,数据库,远程http服务器,缓存,这样的情况
 * 
 * @author hx940929
 * @CreateDate 2016-6-22 - 上午11:31:38
 * @ModifiedDate 2016-6-22 - 上午11:31:38
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者主页</a>
 * @QQ 836845967
 */
public abstract class Config
{

	/**
	 * 根据key从数据源中返回配置信息对象,这个数据源对象具体是取决于当前开发者的系统的.
	 * 比如我这个项目中使用的是DictItemService这个数据源实现, 该方法不应该在业务代码中被调用,该方法应该由具体的一个抽象子模块中被调用
	 * 
	 * @param key
	 * @return
	 * @Author hx940929
	 * @Date 2016-6-25 - 下午11:50:53
	 */
	public abstract ConfigData getConfig(Object key);
	
	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 值为空时返回默认值
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 * @Author hx940929
	 * @Date 2017-2-27 - 上午12:06:40
	 */
	public abstract ConfigData getConfig(Object key, Object defaultValue);

	/**
	 * 
	 * 简要功能说明:
	 * <p>
	 * 将配置对象设置进去,如果有这更新,如果没有则插入,操作结果返回boolean值
	 * </p>
	 * 注意事项: <b> 如果没有实现此方法,可能会导致配置数据不是实时最新的. </b></br>
	 * 
	 * @param key
	 * @param configData
	 * @return
	 * @Author hx940929
	 * @Date 2016-8-2 - 下午9:52:42
	 */
	public abstract boolean setConfig(Object key, ConfigData configData);

}
