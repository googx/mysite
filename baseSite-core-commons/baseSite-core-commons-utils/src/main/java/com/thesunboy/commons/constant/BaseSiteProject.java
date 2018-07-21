/**
 * 
 */
package com.thesunboy.commons.constant;

/**
 * <b>功能说明:</b>
 * <p>
 * 
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2017-4-18 - 下午3:55:41
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class BaseSiteProject
{
	public static String WebSitePath = "/BaseSite";// 具体名称是由项目部署时命名决定的,代码无法干预,因此项目启动时需要被更新
	/**
	 * 用于在系统操作向数据库插入数据时使用系统GUID,是以BaseSiteSystem字符用MD5生成的32位字符
	 */
	public static final String SystemGUID = "basesite-system-basesite-system-";

	/**
	 * 网站根域名
	 */
	public static final String RootDomain = "thesunboy.com";
	/**
	 * 应用程序的全局编码格式
	 */
	public static final String Encoding = "UTF-8";
}
