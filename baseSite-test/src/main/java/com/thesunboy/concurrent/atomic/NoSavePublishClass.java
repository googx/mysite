/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.concurrent.atomic;

import com.thesunboy.commons.config.MyLogFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

/**
 * <b>功能说明:</b>
 * <p>
 * 测试不安全的发布, 并发书p284
 * 
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
 * @CreateDate 2017-8-31 - 下午7:47:38
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class NoSavePublishClass
{
	private final static MyLogger logger = MyLogFactory.getLogger(NoSavePublishClass.class, LogImplEnum.ConsoleLogImpl);

	private TestBean bean;

	public static void main(String[] args)
	{
		NoSavePublishClass sdfClass = new NoSavePublishClass();
		sdfClass.aThread();
		sdfClass.bThread();
	}

	public void aThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				logger.info("run()==>a 线程 执行");
				bean = new TestBean("王一", 12, "男");
				logger.info("run()==>a 线程 end");
			}
		}).start();
	}

	public void bThread()
	{
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				logger.info("run()==>b 线程 执行");
				if(bean != null)
				{
					logger.info("run()==>" + bean.getAge());
					logger.info("run()==>" + bean.getName());
					logger.info("run()==>" + bean.getSexString());
					logger.info("run()==>bug存在");
				}
				else
				{
					logger.info("run()==>bug不存在,");
				}
				logger.info("run()==>b 线程 end");
			}
		}).start();
	}

	public class TestBean
	{
		/**
		 * @param name
		 * @param age
		 * @param sexString
		 */
		public TestBean(String name, int age, String sexString)
		{
			super();
			logger.info("TestBean()==>开始执行构造函数");
			this.name = name;
			this.age = age;
			this.sexString = sexString;
			logger.info("TestBean()==>执行构造函数--- 完毕");
		}

		String name;
		int age;
		String sexString;

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name)
		{
			this.name = name;
		}

		/**
		 * @return the age
		 */
		public int getAge()
		{
			return age;
		}

		/**
		 * @param age
		 *            the age to set
		 */
		public void setAge(int age)
		{
			this.age = age;
		}

		/**
		 * @return the sexString
		 */
		public String getSexString()
		{
			return sexString;
		}

		/**
		 * @param sexString
		 *            the sexString to set
		 */
		public void setSexString(String sexString)
		{
			this.sexString = sexString;
		}
	}

}
