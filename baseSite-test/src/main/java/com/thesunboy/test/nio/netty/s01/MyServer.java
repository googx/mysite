package com.thesunboy.test.nio.netty.s01;/**
 * <b>功能说明:</b><p>
 * 一些说明写这里
 * </p></br> <b>设计思想、目的:</b><p>
 * 一些说明写这里
 * </p></br><b>设计缺陷: </b>
 * <p> 一些说明写这里
 * </p>
 *
 * @author hanxu
 * @CreateDate 2018-04-03 下午11:04
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * @author hanxu
 * @create 2018-04-03 下午11:04
 **/
public class MyServer
{

	public static void main(String[] args)
	{
		MyServer server=new MyServer();
		try
		{
			server.start();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	void start() throws InterruptedException
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workgroup = new NioEventLoopGroup();

		ChannelFactory<NioServerSocketChannel> channelFactory = new ReflectiveChannelFactory(NioServerSocketChannel.class);

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workgroup)//
				 .channelFactory(channelFactory)//
				 .childHandler(new ChannelInitializer<SocketChannel>()
				 {
					 @Override
					 protected void initChannel(SocketChannel ch) throws Exception
					 {
						 ChannelPipeline pipeline = ch.pipeline();
						 pipeline.addLast("MyDiscardHandler", new MyDiscardHandler());
					 }
				 })//
				 .option(ChannelOption.SO_BACKLOG, 128)
				 .childOption(ChannelOption.SO_KEEPALIVE, true);
				//初始化完成通道处理器对象的设置
				//初始化完成ServerBootstrap的设置
		ChannelFuture channelFuture = bootstrap.bind(8080);
		channelFuture.sync();//因 netty实现你是异步的,so这里等待服务完成
		//关闭服务
		channelFuture.channel().closeFuture().sync();
	}

}
