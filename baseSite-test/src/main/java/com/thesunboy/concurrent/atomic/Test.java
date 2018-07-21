/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.concurrent.atomic;

import java.io.IOException;

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
 * @CreateDate 2017-8-11 - 下午3:19:36 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class Test
{
	private final static MyLogger logger = MyLogFactory.getLogger(Test.class, LogImplEnum.ConsoleLogImpl);

	volatile int i;

	public void test()
	{
		i++;
	}
	public static void main(String[] args) throws IOException
	{
		// Random random = new Random();
		// TLinkedQueue_atomicReference<String> queue = new
		// TLinkedQueue_atomicReference<>();
		// queue.put(random.nextFloat() + "");
		// queue.put(random.nextFloat() + "");
		// queue.put(random.nextFloat() + "");
		// queue.put(random.nextFloat() + "");
		// queue.put(random.nextFloat() + "");

	}
}
