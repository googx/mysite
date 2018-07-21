package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.commons.utils.DateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author root
 * @CreateDate 2018-04-14 下午4:55
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TimeServerHandler extends PrintReqHandler
{
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception
	{
		super.channelActive(ctx);
		logger.info("channelActive()==>通道激活.");
		ByteBufAllocator alloc = ctx.alloc();
		ByteBuf buffer = alloc.buffer(8);

		long curtime =//
				(System.currentTimeMillis() / 1000L + 2208988800L);
		//				System.currentTimeMillis();
		logger.info("channelActive()==>" + DateUtils.getDateTimeSecondStr(curtime));
		//		logger.info("channelActive()==>"+ DateUtils.getDateTimeSecondStr(2208988800L));
		//
		//
		//		logger.info("channelActive()==>"+(1523516629525));

		TimeServerHandler.getfile(buffer);

		//		buffer.writeChar((int)'c');
		//		buffer.writeBoolean(true);
		// 在目前看到的语义上Promise就是一个Future. 用来返回异步任务执行结果用的.
		//ChannelPromise
		//ChannelPipeline
		ChannelFuture channelFuture = ctx.writeAndFlush(buffer);
		//		channelFuture.addListener(ChannelFutureListener.CLOSE);
		channelFuture.addListener(new ChannelFutureListener()
		{
			@Override
			public void operationComplete(ChannelFuture future) throws Exception
			{
				//请注意,close() 方法也可能不会立马关闭，他也会返回一个ChannelFuture。

				ctx.close();
				logger.info("channelActive>>operationComplete()==>关闭");
			}
		});

	}

	public static int getfile(ByteBuf buffer)
	{
		File file = new File("/media/hanxu/d_ext4/need.tar.gz");
		RandomAccessFile randomAccessFile = null;
		try
		{
			randomAccessFile = new RandomAccessFile(file, "rw");
			long fileLength = randomAccessFile.length();
			long spliltsize = fileLength / 1024;
			long yushu = fileLength % 1024;
			logger.info("getfile()==>" + spliltsize + "" + yushu);

			byte[] temp = new byte[1024];
			int s = 0, count = 0;
			while ((s = randomAccessFile.read(temp)) != -1)
			{
				buffer.writeBytes(temp, 0, s);
				count += s;
			}
			return count;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
