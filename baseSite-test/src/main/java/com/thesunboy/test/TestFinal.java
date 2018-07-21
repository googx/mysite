/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * @CreateDate 2017-8-14 - 下午6:41:09 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TestFinal
{
	private final static MyLogger logger = MyLogFactory.getLogger(TestFinal.class, LogImplEnum.ConsoleLogImpl);
	Random random = new Random();
	public volatile Holder holder = new Holder(random.nextInt(2000));


	public void neHolder()
	{
		holder = new Holder(random.nextInt(2000));
	}

	private final ExecutorService service = Executors.newFixedThreadPool(50);
	
	/**
	 * 简要功能说明:
	 * <p>
	 * 
	 * </p>
	 * <b>注意事项: </b>
	 * 
	 * </br>
	 * 
	 * @Author hx940929
	 * @Date 2017-8-14 - 下午6:41:09
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		TestFinal final1 = new TestFinal();
		long start, end;
		logger.info("Test()==>start>" + (start = System.currentTimeMillis()));
		final1.Test();
		logger.info("Test()==>End>" + (end = System.currentTimeMillis()) + " 耗时: " + (end - start));

	}

	public void Test() throws InterruptedException
	{

		int count = 100000;
		final TestFinal final2 = this;
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
		final CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int i = 0; i < count; i++)
		{
			service.submit(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						cyclicBarrier.await();
					}
					catch (InterruptedException e)
					{
						// TODO hx940929 by 下午6:58:16
						logger.error("请描述异常信息", e, e.getClass());
					}
					catch (BrokenBarrierException e)
					{
						// TODO hx940929 by 下午6:58:16
						logger.error("请描述异常信息", e, e.getClass());
					}
					try
					{
						final2.holder.test();
						final2.neHolder();
						final2.holder.test();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		service.shutdown();
	}

	static class Holder
	{
		private final int n;

		public Holder(int n)
		{
			this.n = n;
		}

		public void test()
		{
			if(n != n) { throw new AssertionError("sad"); }
		}

	}
}
