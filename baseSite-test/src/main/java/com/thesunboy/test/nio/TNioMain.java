/**
 * 代码为通用实现,用作它途,并以此牟利的不合法行为,盖于本开发者(thesunboy.com)无关
 */
package com.thesunboy.test.nio;

import com.thesunboy.commons.config.MyLogFactory;
import com.thesunboy.commons.utils.ConsoleUtils;
import com.thesunboy.utils.mylog.core.MyLogger;
import com.thesunboy.utils.mylog.core.MyLogger.LogImplEnum;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <b>功能说明:</b><p>
 *jvm参数: -verbose:class 打印出项目引用jar的过程

 * </p></br> <b>设计思想、目的:</b><p> 
 *一些说明写这里
 * </p></br><b>设计缺陷: </b><p> 一些说明写这里
</p>
 * @author hx940929
 * @CreateDate 2017-9-9 - 下午9:27:11 
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TNioMain
{
	private final static MyLogger logger = MyLogFactory.getLogger(TNioMain.class, LogImplEnum.ConsoleLogImpl);

	public final static String sssss = new String("sss");
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
	 * @Date 2017-9-9 - 下午9:27:11
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) throws Exception
	{

		//noramlServerSocket();
		 TNioMain main = new TNioMain();
		 main.TSelector();
	}

	private static void noramlServerSocket() throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(2301);

		while (Thread.currentThread().interrupted() == false)
		{
			Socket ssss = serverSocket.accept();
			logger.info("main()==>Describe_Info" + sssss);
			String msg = ConsoleUtils.readLine(ssss.getInputStream(), "end");
			logger.info("main()==>" + msg);
		}

		// ss:id:32
		String ss = "sss";

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		String tem;
		while ((tem = reader.readLine()) != null && "end".equals(tem))
		{
			builder.append(tem);
		}
		tem = "sss";
		logger.info("main()==>" + ss);
		logger.info("main()==>" + tem);
		logger.info("TSelector()==>" + (ss == tem));
	}

	@SuppressWarnings("resource")
	private void TSelector()
	{
		try
		{
			Selector selector = Selector.open();
			// FileChannel fileChannel=new RandomAccessFile("",
			// "rw").getChannel();//文件通道不支持注册select,因为每有实现相关接口

			SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
			sChannel.configureBlocking(false);
			SelectionKey socket_selectionKey = sChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);// 如果哦需要使用多个事件常量,用"位或"操作符链接起来
			int interestOps = socket_selectionKey.interestOps();// 返回当前的通道的selector中所注册的事件类型
			logger.info("TSelector()==>当前的InterestOps值: " + interestOps);
			logger.info("TSelector()==>" + (interestOps & SelectionKey.OP_ACCEPT) + "是否订阅accept类型消息" + ((interestOps & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT));
			logger.info("TSelector()==>" + (interestOps & SelectionKey.OP_CONNECT) + "是否订阅连接类型消息" + ((interestOps & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT));
			logger.info("TSelector()==>" + (interestOps & SelectionKey.OP_READ) + "是否订阅数据包已准备完毕,可以读的事件类型" + ((interestOps & SelectionKey.OP_READ) == SelectionKey.OP_READ));
			logger.info("TSelector()==>" + (interestOps & SelectionKey.OP_WRITE) + "是否订阅数据通道可以写的事件类型" + ((interestOps & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE));
			socket_selectionKey.readyOps();//
			// socket_selectionKey.is
			// socket_selectionKey.a
			// socket_selectionKey.
			while (Thread.currentThread().isInterrupted() == false)
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
						SocketChannel channel = (SocketChannel) key.channel();
						SelectableChannel channel1 = key.channel();



						ByteBuffer buffer = ByteBuffer.allocate(100);
						// channel.read(buffer);
						int readCount;



						while ((readCount = channel.read(buffer)) != -1)
						{

							buffer.flip();
							// logger.info("main()==>the numbers of byteread: "
							// + readCount + ", position: [" + buffer.position()
							// + "]  ,  limit: [" + buffer.limit() + "]");
							byte[] dst = new byte[buffer.limit()];
							buffer.get(dst);
							String mesg = new String(dst);
							logger.info("TSelector()==>" + mesg);
							buffer.compact();

						}
						channel.close();
					}
					else if(key.isWritable())
					{
						logger.info("TSelector()==>isWritable");
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer sdfBuffer = ByteBuffer.allocate(100);
						sdfBuffer.putInt(234234);
						channel.write(sdfBuffer);
					}

					// selIterator.remove();
					// acctiveSelection.remove(key);

					// 处理完这个通道响应的事件后,要从set(该set是由selector发布出来的,内部实现应该不是使用保护性发布,就是为了方便用作标记事件已处理的设计)中移除这个元素,否则会被视为未被处理的事件,在下一次的select中会重新进入,那样就会造成重复的使用了
				}

			}
		}
		catch (IOException e)
		{
			// TODO hx940929 by 下午2:56:13
			logger.error("请描述异常信息", e, e.getClass());
		}
	}

	private void bufferAndChannel() throws Exception
	{

		FileChannel fileChannel = null;
		RandomAccessFile file = new RandomAccessFile("F:/Project_Management/Personal/开发路径-测试文件/nio_channel.txt", "rw");
		fileChannel = file.getChannel();
		CharBuffer charBuffer = null;
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
		byteBuffer.allocate(48);// 创建48个字节大小的byteBuffer ,其实现应该是使用了byte数组
		int byteread = fileChannel.read(byteBuffer);// 将通道中的数据读取到byteBuffer中

		while (byteread != -1)
		{
			logger.info("main()==>the numbers of byteread: " + byteread + ", position: [" + byteBuffer.position() + "]  ,  limit: [" + byteBuffer.limit() + "]");
			byteBuffer.flip();// 对buffer的模式切换,从写模式切换成读模式,把当前limit的值设置成当前的position的值,把当前position的值设置为0
			logger.info("main()==>the numbers of byteread: " + byteread + ", position: [" + byteBuffer.position() + "]  ,  limit: [" + byteBuffer.limit() + "]");
			while (byteBuffer.hasRemaining())// 意识就是在一个缓冲区中 当前位置和 缓冲的大小limit
											 // 中是否还有位置存放新数据
			{
				byte[] b1 = new byte[byteBuffer.limit()];
				byteBuffer.get(b1);
				logger.info("main()==>");
			}
			byteBuffer.clear();
			byteread = fileChannel.read(byteBuffer);
		}
		logger.info("main()==>main end");

	}

}
