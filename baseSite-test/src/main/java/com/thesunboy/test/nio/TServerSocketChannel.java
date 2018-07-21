/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.utils.SimpleThreadFactory;

import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

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
 * @CreateDate 2017-9-13 - 上午11:38:00
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TServerSocketChannel
{
	private final static MyLogger logger = MyLogFactory.getLogger(TServerSocketChannel.class, LogImplEnum.ConsoleLogImpl);

	private final ExecutorService IOThreadPool = Executors.newFixedThreadPool(10 + 1, new SimpleThreadFactory());

	public void start() throws Exception
	{ 
		final Selector[] selectorList = new Selector[10];
		for (int i = 0; i < 10; i++)
		{
			Selector selector = Selector.open();
			selectorList[i] = selector;
			IOThreadPool.execute(new IOThread(selector));
		}
		// 选择一个线程池里的一个线程来做Accept线程,线程池里的其他线程做io线程
		IOThreadPool.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Random random = new Random();
					ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
					serverSocketChannel.bind(new InetSocketAddress(8888));
					while (Thread.currentThread().isInterrupted())
					{
						SocketChannel clientChannel = serverSocketChannel.accept();
						int selId = random.nextInt(10 - 1);
						clientChannel.register(selectorList[selId], SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		});

	}

	class IOThread implements Runnable
	{

		private final Selector selector;

		IOThread(Selector selector)
		{
			this.selector = selector;
		}

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
		 * @Date 2017-9-13 - 上午11:54:37
		 */
		@Override
		public void run()
		{
			try
			{
				logger.info("run()==>io工作线程开始工作"	);
				work(selector);
				logger.info("run()==>io工作线程结束工作");
			}
			catch (IOException e)
			{
				// TODO hx940929 by 下午1:30:21
				logger.error("请描述异常信息", e, e.getClass());
			}
		}

		private void work(Selector selector) throws IOException
		{
			while (!Thread.currentThread().isInterrupted())
			{
				if(selector.select() == 0) continue;
				Set<SelectionKey> acctiveSelection = selector.selectedKeys();
				Iterator<SelectionKey> selIterator = acctiveSelection.iterator();
				while (selIterator.hasNext())
				{
					SelectionKey key = selIterator.next();

					if(key.isAcceptable())
					{
						logger.info("TSelector()==>isAcceptable");
					}
					else if(key.isConnectable())
					{
						logger.info("TSelector()==>isConnectable");
					}
					else if(key.isReadable())
					{
						logger.info("TSelector()==>isReadable");

					}
					else if(key.isWritable())
					{
						logger.info("TSelector()==>isWritable");

					}
					// selIterator.remove();
					// acctiveSelection.remove(key);

					// 处理完这个通道响应的事件后,要从set(该set是由selector发布出来的,内部实现应该不是使用保护性发布,就是为了方便用作标记事件已处理的设计)中移除这个元素,否则会被视为未被处理的事件,在下一次的select中会重新进入,那样就会造成重复的使用了
				}

			}
		}
	}

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
	 * @Date 2017-9-13 - 上午11:38:00
	 * @param args
	 */
	public static void main(String[] args)
	{
		TServerSocketChannel serverSocketChannel = new TServerSocketChannel();
		try
		{
			serverSocketChannel.start();
		}
		catch (Exception e)
		{
			// TODO hx940929 by 下午1:32:12
			logger.error("请描述异常信息", e, e.getClass());
		}
	}
}
