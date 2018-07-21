package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-04-12 下午3:12
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Sss
{
private  static MyLogger logger= MyLog.getLogger(Sss.class, MyLogger.LogImplEnum.ConsoleLogImpl);
	public static void main(String[] args)
	{
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long curtime=System.currentTimeMillis();
		logger.info("main()"+curtime+"==>"+dateTimeFormat.format(new Date(curtime)));
		logger.info("main()"+curtime+"==>"+dateTimeFormat.format(new Date(2208988800L)));
		logger.info("main()"+(curtime/1000L +2208988800L)+"==>"+dateTimeFormat.format(new Date(curtime +2208988800L)));



	}
}
