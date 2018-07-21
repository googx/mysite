package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.SystemPropertyUtil;

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
 * @author root
 * @CreateDate 2018-04-14 下午4:55
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class TimeClient
{
	static MyLogger logger= MyLog.getLogger(TimeClient.class, MyLogger.LogImplEnum.ConsoleLogImpl);

	public static void main(String[] args)
	{
//		TimeServerHandler.getfile();
		boolean windows = SystemPropertyUtil.get("os.name", "").toLowerCase(Locale.US).contains("win");
		logger.info("main()==>"+windows);

		//1.创建clientBootstrap
		Bootstrap bootstrap=new Bootstrap();
		SocketChannel socketChannel;
		ServerSocketChannel serverSocketChannel;
		EventLoopGroup boseloopGroup=new NioEventLoopGroup();//thread group
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.SO_KEEPALIVE,true	 );
		bootstrap.group(boseloopGroup	);
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception
			{
				logger.info("initChannel()==>init channel，"+ch.toString());

				ch.pipeline().addLast("TimeClientInboundHandler", new TimeClientInboundHandler());
			}
		});
		bootstrap.remoteAddress("localhost",8080 );
		ChannelFuture future = bootstrap.connect();

		try
		{
			future.sync();
//			synchronized (bootstrap){
//				logger.info("main()==>wait:"+Thread.currentThread().getId());
//				bootstrap.wait();
//				logger.info("main()==>wake up:"+Thread.currentThread().getId());
//			}
			Channel channel = future.channel();
			logger.info("main()==>channelClose："+channel.toString());
			ChannelFuture closeFuture = channel.closeFuture();
			closeFuture.sync();

//			future.get();

			boseloopGroup.shutdownGracefully();//关闭子线程，退出服务
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
