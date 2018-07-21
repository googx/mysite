package com.thesunboy.test.nio.netty.s01;

import com.thesunboy.utils.mylog.client.MyLog;
import com.thesunboy.utils.mylog.core.MyLogger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NetUtil;

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
 * @CreateDate 2018-04-02 下午3:45
 * @Encoding UTF-8
 * @Version 1.0
 * @WebSite <a href="https://www.thesunboy.com">访问开发者个人主页</a>
 * @QQ 836845967
 */
public class DiscardServer implements  Runnable
{
MyLogger logger= MyLog.getLogger(DiscardServer.class, MyLogger.LogImplEnum.ConsoleLogImpl);

	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		logger.info("run()==>开始启动netty服务");
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();


		ServerBootstrap bootstrap2 = new ServerBootstrap();


		try {
			ChannelFactory<NioServerSocketChannel> factory=new ReflectiveChannelFactory(NioServerSocketChannel.class);

			ServerBootstrap bootstrap = new ServerBootstrap(); // (2)

			bootstrap.group(bossGroup, workerGroup)
					 //			 .channel(NioServerSocketChannel.class) // (3)
					 .channelFactory(factory)
					 .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
				 @Override
				 public void initChannel(SocketChannel ch) throws Exception {
					 ChannelPipeline pipeline = ch.pipeline();
					 pipeline.addLast("PrintReqHandler-->",new PrintReqHandler());
					 pipeline.addLast("timeServer", new TimeServerHandler());
					 pipeline.addLast("DiscardServerHandler-->",new DiscardServerHandler());

				 }
			 })
					 .option(ChannelOption.SO_BACKLOG, NetUtil.SOMAXCONN)          // (5)内核会根据somaxconn和backlog的较小值设置accept queue的大小，
					 //so,要调整这个tcp accept队列的大小,还需要同时调整内核的大小
					 .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			// 绑定端口，开始接收进来的连接

			ChannelFuture f = bootstrap.bind(port);
			logger.info("run()==>bind["+port+"] success");

			f.sync(); // (7)等待服务完成.
			logger.info("run()==>invoke method sync.1");


			// 等待服务器  socket 关闭 。
			// 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			f.channel().closeFuture().sync();
			logger.info("run()==>invoke method closeFuture().sync() .2");
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
			logger.info("run()==>close shutdownGracefully");
			
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new DiscardServer(port).run();
	}
}
