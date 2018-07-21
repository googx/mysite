package com.thesunboy.utils.mylog.core;

/**
 * 本地客户端实现的接口,用于本地环境的输出控制台,如果用户实现了该接口请直接在该方法内使用本地环境已经实现的日志输出,比如安卓的util.log ,
 * 框架中的log4j<br/>
 * * 客户端环境实现该接口,可以在这里在接上其他的日志框架扩展类,该tag可能是传入这个消息的class名</br> 回头要改进下多实例的问题:
 * 如果一个项目存在多实例,比如像log4j那样传入class名,这种问题该如何解决,是同一个实例,还是不同实例,
 * 如果是多个实例,那应该保持配置对象的一致性,即使用同一个配置对象来构建.
 * 
 * @author hx940929
 * @CreateDate 2016年2月20日 - 下午10:02:47
 * @ModifiedDate 2016年2月20日 - 下午10:02:47
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite www.thesunboy.com
 * @QQ 836845967
 */
public interface ILocalEnviConsole
{
	void print(ELogLevel level, String clazz, String tag, Object content,
			Object... msgDetail);

}
