/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.site.core;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
 * @CreateDate 2017-6-23 - 下午7:56:33 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Sss
{
	private final static MyLogger logger = MyLogFactory.getLogger(Sss.class, LogImplEnum.ConsoleLogImpl);
	private final Object lock = new Object();
	private final static int num = 10;
	final static CyclicBarrier e = new CyclicBarrier(num);
	
	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @Author hx940929
	 * @Date 2017-6-23 - 下午7:56:34
	 */
	public static void main(String[] args) throws InterruptedException
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					e.await();
					logger.info("run()==>Describe_Info");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				catch (BrokenBarrierException e)
				{
					e.printStackTrace();
				}
			}
		}).start();

		Thread.sleep(2000);
		logger.info("main()==>Describe_Info");

	}
}
