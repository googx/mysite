/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

import java.io.InputStream;
import java.net.URL;

/**
 * <b>功能说明:</b>
 * <p>
 * 一些说明写这里
 * </p>
 * </br> <b>设计思想、目的:</b>
 * <p>
 * 一些说明写这里
 * </p>
 * </br><b>设计缺陷: </b>
 * <p>
 * 一些说明写这里
 * </p>
 * 
 * @author hx940929
 * @CreateDate 2018-1-14 - 下午10:45:41
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class SyncMain
{
	private final MyLogger logger = MyLogFactory.getLogger(SyncMain.class, LogImplEnum.ConsoleLogImpl);

	public static void main(String[] args)
	{
		URL url = SyncMain.class.getClassLoader().getResource("/");
		String uString = System.getProperty("user.dir");
		URL resource = SyncMain.class.getResource("/config/runArgs.properties");
		InputStream inputStream = SyncMain.class.getClassLoader().getResourceAsStream("/config/runArgs.properties");

	}
}
